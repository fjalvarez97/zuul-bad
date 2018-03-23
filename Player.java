import java.util.Vector;
import java.util.AbstractCollection;
import java.util.Stack;
import java.util.AbstractList;
/**
 * Write a description of class Player here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Player
{
    private Room currentRoom;
    private Stack<Room>lastRooms;
    /**
     * Constructor for objects of class Player
     */    
    public Player(Room defaultRoom)
    {
        lastRooms = new Stack<>();    
        currentRoom = defaultRoom;
        //Sala por defecto en la que empieza el jugador
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
}
