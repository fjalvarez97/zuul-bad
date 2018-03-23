import java.util.Vector;
import java.util.AbstractCollection;
import java.util.Stack;
import java.util.AbstractList;
import java.util.ArrayList;
/**
 * Write a description of class Player here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Player
{
    private Room currentRoom;
    private Stack<Room> lastRooms;
    private ArrayList<Item> inventory;
    //Items carried by the player
    private int actualWeight;
    /**
     * Constructor for objects of class Player
     */    
    public Player(Room defaultRoom)
    {
        lastRooms = new Stack<>();    
        currentRoom = defaultRoom;
        //Sala por defecto en la que empieza el jugador
        inventory = new ArrayList<>();
        actualWeight = 0;
    }

    /**
     * Getter campo currentRoom
     * @return current room
     */
    public Room getCurrentRoom()
    {
        return currentRoom;
    }

    /**
     * Getter campo lastRooms
     * @return all the rooms you have been in
     */
    public Stack<Room> getLastRooms()
    {
        return lastRooms;
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    public void goRoom(Command command) 
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
            look();
        }
    }

    /**
     * Return to the last Room you have been in
     */
    public void back()
    {
        if (!lastRooms.empty()) {
            currentRoom = lastRooms.peek();
            lastRooms.pop();
            look();
        }
        else {
            System.out.println("You cant go back");
        }
    }   

    /**
     * Prints the long description of the currentroom
     */
    public void look()
    {
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Prints a message that you have eaten
     */
    public void eat()
    {
        System.out.println("You have eaten now and you are not hungry any more");
    }

    /**
     * Takes an item that name's match with the parameter's name 
     */
    public void takeItem(Command command)
    {
        if (command.hasSecondWord()) {
            Item itemToTake = currentRoom.takeRoomsItem(command.getSecondWord());
            if (itemToTake != null) {
                if (itemToTake.ableToCatch()) {
                    inventory.add(itemToTake);
                    currentRoom.removeItem(itemToTake);
                    actualWeight += itemToTake.getWeight();
                    System.out.println("You take " + itemToTake.fullItemDescription());
                }
                else {
                    System.out.println("You can't take that item!");
                }
            }
            else {
                System.out.println("You can't find that item here!");
            }
        }
        else {
            System.out.println("Take what?");
        }
    }

    /**
     * Drops an item that name's match with the parameter's name 
     */
    public void dropItem(Command command)
    {
        if (command.hasSecondWord()) {
            Item itemToDrop = null;
            int i = 0;
            boolean buscando = true;
            for(Item actualItem : inventory)
            {
                String[] parts = (actualItem.getDescription().split(" ")); 
                String itemName = parts[0];
                if (itemName.equals(command.getSecondWord())){
                    itemToDrop = actualItem;
                }
            }
            if (itemToDrop != null) {
                if (itemToDrop.ableToCatch()) {
                    inventory.remove(itemToDrop);
                    currentRoom.addItem(itemToDrop);
                    actualWeight -= itemToDrop.getWeight();
                    System.out.println("You take " + itemToDrop.fullItemDescription());
                }
                else {
                    System.out.println("You can't take that item!");
                }
            }
            else {
                System.out.println("You can't find that item here!");
            }
        }
        else {
            System.out.println("Take what?");
        }
    }

    /**
     * Informs about the items in the inventory
     */
    public void itemsCarriedInfo()
    {
        if (inventory.size()==0){
            System.out.println("You aren't carrying anything");
        }
        else {
            for (Item actualItem : inventory) {
                System.out.println("You have " + actualItem.getDescription() + " that weights " + actualItem.getWeight() + "kg");
            }
        }
        System.out.println("Your actual weight is " + actualWeight + "kg");
    }
}
