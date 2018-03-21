import java.util.Vector;
import java.util.AbstractCollection;
import java.util.Stack;
import java.util.AbstractList;
import java.util.ArrayList;
/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Stack<Room>lastRooms;
    private ArrayList<Item> characterItems;
    private static final int maxWeight = 10;
    private int actualWeight;
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
        lastRooms = new Stack<>();
        characterItems = new ArrayList<>();
        actualWeight = 0;
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room entrada, recepcion, salaMaquinas, oficinas, almacen, cajaFuerte;

        // create the rooms
        entrada = new Room("inside the main entrance of the bank");
        recepcion = new Room("in the reception"); 
        recepcion.addItem("a sack of coins", 2, true);
        salaMaquinas = new Room("in the machine room");
        oficinas = new Room("in the offices"); 
        oficinas.addItem("a lot of pappers", 5, false);
        oficinas.addItem("some boxes", 10, true);
        oficinas.addItem("three cards", 1, true);
        almacen = new Room("in the warehouse");
        cajaFuerte = new Room("in the bank safety deposit box room!");
        cajaFuerte.addItem("a gold chest", 9, false);
        cajaFuerte.addItem("a security guard", 100, true);

        // initialise room exits
        entrada.setExit("north", recepcion);
        entrada.setExit("northWest", salaMaquinas);

        recepcion.setExit("north", almacen);
        recepcion.setExit("east", oficinas);
        recepcion.setExit("south", entrada);
        recepcion.setExit("west", salaMaquinas);

        salaMaquinas.setExit("east", recepcion);

        oficinas.setExit("north", cajaFuerte);
        oficinas.setExit("west", recepcion);
        oficinas.setExit("northWest", almacen);

        almacen.setExit("east", cajaFuerte);
        almacen.setExit("south", recepcion);
        almacen.setExit("southEast", oficinas);

        cajaFuerte.setExit("south", oficinas);
        cajaFuerte.setExit("west", almacen);

        currentRoom = entrada;  //Empieza el juego en la entrada del banco.
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */ 
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        printLocationInfo();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("look")) {
            look();
        }
        else if (commandWord.equals("eat")) {
            eat();
        }
        else if (commandWord.equals("back")) {
            back();
        }
        else if (commandWord.equals("take")) {
            int itemNum = Integer.parseInt(command.getSecondWord());
            takeItem(itemNum);
        }
        else if (commandWord.equals("drop")){
            int itemNum = Integer.parseInt(command.getSecondWord());
            removeCharactersItem(itemNum);
        }
        else if (commandWord.equals("items")){
            itemsCarriedInfo();
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println(parser.showCommands());
    }

    /**
     * Prints the long description of the currentroom
     */
    private void look()
    {
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Prints a message that you have eaten
     */
    private void eat()
    {
        System.out.println("You have eaten now and you are not hungry any more");
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            lastRooms.push(currentRoom);
            currentRoom = nextRoom;
            printLocationInfo();
        }
    }

    /**
     * Print the info of the actual location (Current room and possibles room's exits)
     */
    private void printLocationInfo()
    {
        System.out.println(currentRoom.getLongDescription());
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

    /**
     * Return to the last Room you have been in
     */
    private void back()
    {
        if (!lastRooms.empty()) {
            currentRoom = lastRooms.peek();
            lastRooms.pop();
            printLocationInfo();
        }
        else {
            System.out.println("You cant go back");
        }
    }

    /**
     * Takes an item in the current room
     * @param item - the item you take
     */
    private void takeItem(int numItem)
    {
        if (currentRoom.getItems().size()>0) {
        if (actualWeight < maxWeight) {
            Item itemToTake = currentRoom.takeItem(numItem);
            if (itemToTake.getWeight()+ actualWeight > maxWeight) {
                System.out.println("That's too heavy for you, drop something!");
            }
            else if (itemToTake.getAbleToCatch()) {
                characterItems.add(itemToTake);
                actualWeight += itemToTake.getWeight();
                currentRoom.removeItem(itemToTake);                
                System.out.println("You catch " + itemToTake.getDescription());
            }
            else {
                System.out.println("You cant catch this item");
            }
        }
        else {
            System.out.println("You cant carry anything else");
        }
        }
        else {
            System.out.println("This room is empty");
        }
    }

    /** 
     * Shows the info of the items taked by a character
     */
    private void itemsCarriedInfo() 
    {
        System.out.println("Your actual weight is " + actualWeight);
        System.out.println("The max weight you can carry is " + maxWeight);
        if (characterItems.size()==0){
            System.out.println("You aren't carrying anything");
        }
        else {
            for (Item actualItem : characterItems) {
                System.out.println("You have " + actualItem.getDescription() + " that weights " + actualItem.getWeight() + "kg");
            }
        }
    }
    
    /** 
     * Removes an item
     */
    private void removeCharactersItem(int numItem) 
    {
        Item itemToRemove = characterItems.get(numItem);
        currentRoom.addItem(itemToRemove);
        characterItems.remove(numItem);
        actualWeight -= itemToRemove.getWeight();
        System.out.println("You have droped " + itemToRemove.getDescription());
    }
}
