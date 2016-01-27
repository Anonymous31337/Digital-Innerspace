import java.util.ArrayList;
public class Player
{
    private String name;
    private Room currentRoom;
    private Items items = new Items();
    public static final int maxWeight = 64;
    private int moves = 0;
    public static final int maxMoves = 10;
    private ArrayList<Room> pastRooms;
    private boolean finished = false;

    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        pastRooms = new ArrayList<Room>();
    }

    /**
     * Enter the given room.
     */
    public void enterRoom(Room room) {
        moves++;
        currentRoom = room;
        if(pastRooms.size()!=0){
            if(currentRoom!=pastRooms.get(pastRooms.size()-1)){
                pastRooms.add(currentRoom);
            }
        }
        else{
            pastRooms.add(currentRoom);
        }
    }

    /**
     * Gets the room in which the player is currently located.
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * Gets the last Room entered.
     */
    public Room getPastRoom() {
        pastRooms.remove(pastRooms.size()-1);
        if(pastRooms.size()!=0){
            Room pastRoom = pastRooms.get(pastRooms.size()-1);
            return pastRoom;
        }
        return currentRoom;
    }

    public boolean addPlayerItem(Item item){
        if((item.getWeight() + items.calcWeight()) <= maxWeight){
            items.put(item.getName(),item);
            return true;
        }
        else{
            return false;
        }
    }
    
    public void removePlayerItem(String name){
        items.remove(name);
    }
    
    public Item getPlayerItem(String name){
        return items.get(name);
    }
    
    public void dropPlayerItem(String name){
        currentRoom.addItem(items.remove(name));
    }
    
    public void dropAllItems(){
        String[] allItems = items.getItemNames().split(" ");
        for(String item: allItems){
            dropPlayerItem(item);
        }
    }
    
    /**
     * Returns a string describing the items that the player carries.
     */
    public String getPlayerItems() {
        return "You are carrying: " + items.getItemWeights();
    }
    
    public int getWeight(){
        return items.calcWeight();
    }
    
    public int getMaxWeight(){
        return maxWeight;
    }
    
    public int getMoves(){
        return maxMoves - moves;
    }
    
    public void clearMoves(){
        moves = 0;
    }

    /**
     * Checks if the player is dead. 
     * The player dies when he has exceeded some number of moves.
     */
    public boolean isDead() {
        return moves > maxMoves;
    }
    
    public void wins(){
        finished = true;
    }
    
    public boolean hasWon() {
        return finished;
    }
    
}