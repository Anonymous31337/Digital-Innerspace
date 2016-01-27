
/**
 * Write a description of class Item here.
 * 
 * @author Georg Donner, Kevin Wrede
 * @version 12.01.2016
 */
public class Item
{
    private String name;
    private String description;
    private Room room;
    private int weight;

    /**
     * Constructor for objects of class Item
     */
    public Item(String name, String description, Room room, int weight)
    {
        this.name = name;
        this.description = description;
        this.room = room;
        this.weight = weight;
    }

    /**
     * @return Item name
     */
    public String getName(){
        return name;
    }
    
    /**
     * @return Item description
     */
    public String getDescription(){
        return description;
    }
    
    /**
     * @return Item weight
     */
    public int getWeight(){
        return weight;
    }
    
    public Room getRoom(){
        return room;
    }
}
