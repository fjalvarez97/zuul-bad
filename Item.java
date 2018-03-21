
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
    private boolean ableToCatch;
    // If you can catch or not the item
    /**
     * Constructor for objects of class Item
     */
    public Item(String description, int weight, boolean catchAble)
    {
        this.description = description;
        this.weight = weight;
        ableToCatch = catchAble;
    }
    
    /**
     * Metodo getter del atributo description
     * Return items description
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * Metodo getter del atributo weight
     * Return items weight
     */
    public int getWeight()
    {
        return weight;
    }
    
    /**
     * Metodo getter del atributo ableToCatch
     * Return if the item is able or not to catch
     */
    public boolean getAbleToCatch()
    {
        return ableToCatch;
    }
}
