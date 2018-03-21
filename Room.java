import java.util.HashMap;
import java.util.ArrayList;
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */
public class Room 
{
    private String description;
    private HashMap<String, Room> mapaSalidas;
    private ArrayList<Item> items;
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        mapaSalidas = new HashMap<>();
        items = new ArrayList<>(); 
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor The room in the given direction.
     */
    public void setExit(String direction, Room neighbor)
    {
        mapaSalidas.put(direction, neighbor);
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Get the exit (if exist) of the parameter's direction.
     * @param direction The exit direction you want to know.
     * @return The room you want to exit to.
     */
    public Room getExit(String direction)
    {
        Room theRoom = mapaSalidas.get(direction);
        return theRoom;
    }

    /**
     * Return a description of the room's exits.
     * For example: "Exits: north east west"
     *
     * @ return A description of the available exits.
     */
    public String getExitString()
    {
        String salidas = "";
        if(mapaSalidas.get("north") != null) {
            salidas += "north ";
        }
        if(mapaSalidas.get("east") != null) {
            salidas += ("east ");
        }
        if(mapaSalidas.get("south") != null) {
            salidas += ("south ");
        }
        if(mapaSalidas.get("west") != null) {
            salidas += ("west ");
        }
        if(mapaSalidas.get("southEast") != null) {
            salidas += ("southEast ");
        }
        if(mapaSalidas.get("northWest") != null) {
            salidas += ("northWest ");
        }
        return salidas;
    }

    /**
     * Return a long description of this room, of the form:
     *     You are in the 'name of room'
     *     Exits: north west southwest
     * @return A description of the room, including exits.
     */
    public String getLongDescription()
    {
        String description = "You are " + getDescription() + "\nExits: " + getExitString() + "\n";
        if (items.size() > 0)
        {
            for (Item item : items) {
                description += "You find " + item.getDescription() + " that weigths " + item.getWeight() + "kg" + "\n";
            }
        }
        return description;
    }
    
    /**
     * Add an item to the room
     * @param descripcion items description
     * @param weight items weight
     */
    public void addItem(String description, int weight, boolean ableToCatch)
    {
        items.add(new Item(description, weight, ableToCatch));
    }
    
    /**
     * Adds an item to the room
     * @param item, the item you add
     */
    public void addItem(Item item)
    {
        items.add(item);
    }
    
    /**
     * Takes the item in the selected position
     * @param numItem the item's number position
     * @return The item taken by the character
     */
    public Item takeItem(int numItem)
    {
       return items.get(numItem);
    }
    
    /**
     * Removes an item from the rum
     * @param numItem the items number position
     */
    public void removeItem(Item item)
    {
        items.remove(item);
    }
}
