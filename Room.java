import java.util.HashMap;
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
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     */
    public void setExits(Room north, Room east, Room south, Room west, Room southEast, Room northWest) 
    {
        if(north != null)
            mapaSalidas.put("north",north);
        if(east != null)
            mapaSalidas.put("east", east);
        if(south != null)
            mapaSalidas.put("south", south);
        if(west != null)
            mapaSalidas.put("west", west);
        if(southEast != null)
            mapaSalidas.put("southEast", southEast);
        if(northWest != null)
            mapaSalidas.put("northWest", northWest);
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
}
