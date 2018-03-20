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
    private Room lastRoom;
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
        lastRoom = null;
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
        recepcion.addItem("a sack of coins", 2);
        salaMaquinas = new Room("in the machine room");
        oficinas = new Room("in the offices"); 
        oficinas.addItem("a lot of pappers", 5);
        oficinas.addItem("some boxes", 10);
        oficinas.addItem("three cards", 1);
        almacen = new Room("in the warehouse");
        cajaFuerte = new Room("in the bank safety deposit box room!");
        cajaFuerte.addItem("a gold chest", 1);
        cajaFuerte.addItem("some gold coins", 2);

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
            lastRoom = currentRoom;
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
        if (lastRoom != null) {
            currentRoom = lastRoom;
            lastRoom = null;
             printLocationInfo();
        }
        else {
            System.out.println("You cant go back");
        }
    }
}
