
/**
 * Write a description of class Items here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.HashMap;
import java.util.Iterator;
public class Items
{
    private HashMap<String, Item> items = new HashMap<>();
    /**
     * Creates a new item list
     */
    public Items()
    {
    }

    public void put(String name, Item value){
        items.put(name,value);
    } 

    public Item remove(String name){
        return (Item) items.remove(name);
    }

    public Item get(String name){
        return (Item) items.get(name);
    }

    public String getItemDescriptions(){
        String returnedString = "Items: ";
        if(items.size()>0){
            for(Item item : items.values()){
                returnedString += "\n";
                returnedString += item.getName() + " - " + item.getDescription();
            }
        }
        else{
            returnedString += "none";
        }
        //returnedString.substring(0,(returnedString.length()-2));
        return returnedString;
    }
    
    public String getItemWeights(){
        String returnedString = "";
        if(items.size()>0){
            for(Item item : items.values()){
                returnedString += "\n";
                returnedString += item.getName() + " " + item.getWeight() + " bits";
            }
        }
        else{
            returnedString += "";
        }
        //returnedString.substring(0,(returnedString.length()-2));
        return returnedString;
    }
    
    public String getItemNames(){
        String returnedString = "";
        if(items.size()>0){
            for(Item item : items.values()){
                returnedString += item.getName() + " ";
            }
        }
        else{
            returnedString += "";
        }
        //returnedString.substring(0,(returnedString.length()-2));
        return returnedString;
    }
    
    public int calcWeight(){
        int weight = 0;
        for(Item item: items.values()){
            weight = weight + item.getWeight();
        }
        return weight;
    }

}
