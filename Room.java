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
    private Room northExit;
    private Room southExit;
    private Room eastExit;
    private Room westExit;
    private Room southEastExit;
    private Room northWestExit;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     */
    public void setExits(Room north, Room east, Room south, Room west, Room southeast, Room northwest) 
    {
        if(north != null)
            northExit = north;
        if(east != null)
            eastExit = east;
        if(south != null)
            southExit = south;
        if(west != null)
            westExit = west;
        if(southeast != null)
            southEastExit = southeast;
        if(northwest != null)
            northWestExit = northwest;
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
        Room theRoom = null;
        if(direction.equals("north")) {
            theRoom = northExit;
        }
        if(direction.equals("east")) {
            theRoom = eastExit;
        }
        if(direction.equals("south")) {
            theRoom = southExit;
        }
        if(direction.equals("west")) {
            theRoom = westExit;
        }
        if(direction.equals("southeast")) {
            theRoom = southEastExit;
        }
        if(direction.equals("northwest")) {
            theRoom = northWestExit;
        }
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
        if(northExit != null) {
            salidas += "north ";
        }
        if(eastExit != null) {
            salidas += ("east ");
        }
        if(southExit != null) {
            salidas += ("south ");
        }
        if(westExit != null) {
            salidas += ("west ");
        }
        if(southEastExit != null) {
            salidas += ("southeast ");
        }
         if(northWestExit != null) {
            salidas += ("northwest ");
        }
        return salidas;
    }
}
