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
 * @author  Michael Kolling and David J. Barnes
 * @version 2008.03.30
 */
import java.util.HashMap;

public class Room 
{
    private String name;
    private String description;
    private boolean state;
    private HashMap<String, Room> exits;
    private Items items;
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String name, String description, boolean state) 
    {
        this.name = name;
        this.description = description;
        this.state = state;
        exits = new HashMap<String, Room>();
        items = new Items();
    }

    /**
     * Define the exit of a room.
     * @param direction The direction of the exit
     * @param room The room where it leads to.
     */
    public void setExit(String direction, Room room) 
    {
        exits.put(direction, room);
    }
    
    /**
     * Returns the exit at the given direction
     */
    public Room getExit(String direction){
        return (Room)exits.get(direction);
    }
    
    /**
     * Puts an item into the room
     */
    public void addItem(Item item){
        items.put(item.getName(), item);
    } 
    
    /**
     * Removes an item in the room and returns it
     */
    public Item removeItem(String name){
        return (Item) items.remove(name);
    }
    
    /**
     * Returns the item
     */
    public Item getItem(String name){
        return (Item) items.get(name);
    }
    
    /**
     * Returns the Exits in a String
     */
    public String getExitString(){
        String exitsOutput = "Connections: ";
        for(String direction : exits.keySet()){
            exitsOutput+=direction+" ";
        }
        return exitsOutput;
    }

    /**
     * Returns the Items in a String
     */
    public String getItemsString(){
        return items.getItemDescriptions();
    }
    
    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }
    
    public String getName(){
        return name;
    }
    
    public boolean getState(){
        return state;
    }
    
    public void setState(boolean state){
        this.state = state;
    }
    /**
     * @return All exits of the room.
     */
    
}
