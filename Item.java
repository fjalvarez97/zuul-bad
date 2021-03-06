
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
    /**
     * Constructor for objects of class Item
     */
    public Item(String description, int weight, boolean ableToCatch)
    {
        this.description = description;
        this.weight = weight;
        this.ableToCatch = ableToCatch;
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
    
    /**
     * Devuelve la descripcion completa de un objeto
     */
    public String fullItemDescription()
    {
        return description + " that weights " + weight + "kg";
    }
    
    /**
     * Dice si el objeto se puede o no coger
     */
    public boolean ableToCatch()
    {
        return ableToCatch;
    }
}
