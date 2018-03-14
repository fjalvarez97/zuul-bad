
/**
 * Class Item 
 * This class represents an item that appears in a room.
 *
 * @Fran Alvarez (your name)
 * @1.0 (a version number or a date)
 */
public class Item
{
    private String description;
    // item's description
    private int weight;
    // item's weight (kg)
    /**
     * Constructor for objects of class Item
     */
    public Item(String description, int weight)
    {
        this.description = description;
        this.weight = weight;
    }
    
    /**
     * Metodo getter del atributo description
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * Metodo getter del atributo weight
     */
    public int getWeight()
    {
        return weight;
    }
}
