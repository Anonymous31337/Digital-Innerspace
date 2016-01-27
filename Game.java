/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2008.03.30
 */
import java.util.ArrayList;
public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Player player;
    private ArrayList<Room> rooms;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
        player = new Player();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        rooms = new ArrayList<Room>();
        Room firstConnection, southbridgeS, southbridgeM, southbridgeN, pciBus, usbPorts,
        clockGeneration, sataPorts, powerManagement, bios, northbridge, graphicsCard, ram, cpu;

        // create the rooms
        firstConnection = new Room("First Connection", "on the way to the USB Ports.",true);
        southbridgeS = new Room("SouthbridgeS", "in the southern part of the Southbridge Chip",true);
        southbridgeM = new Room("SouthbridgeM", "in the middle of the Southbridge Chip",true);
        southbridgeN = new Room("SouthbridgeN", "in the northern part of the Southbridge Chip",true);
        pciBus = new Room("PCI Bus", "in the PCI Bus",true);
        usbPorts = new Room("USB Ports", "in the USB Ports",true);
        clockGeneration = new Room("Clock Generation", "in the Clock Generation",false);
        sataPorts = new Room("SATA Ports", "in the Serial ATA Ports",true);
        powerManagement = new Room("Power Management", "in the Power Management",true);
        bios = new Room("BIOS", "in the BIOS",false);
        northbridge = new Room("Northbridge", "in the Northbridge chip",true);
        graphicsCard = new Room("Graphics Card", "in the Graphics Card",false);
        ram = new Room("RAM", "in the RAM modules",false);
        cpu = new Room("CPU", "in the Central Processing Unit",false);

        // add rooms to an ArrayList
        rooms.add(firstConnection);rooms.add(southbridgeS);rooms.add(southbridgeM);rooms.add(southbridgeN);rooms.add(pciBus);
        rooms.add(usbPorts);rooms.add(usbPorts);rooms.add(clockGeneration);rooms.add(sataPorts);rooms.add(powerManagement);
        rooms.add(bios);rooms.add(northbridge);rooms.add(graphicsCard);rooms.add(ram);rooms.add(cpu);

        // create the items
        southbridgeM.addItem(new Item("Cooling-kit","This one will help you to cool down the CPU",cpu,16));
        pciBus.addItem(new Item("Overclock-config","This config will manage the clocking rates.",clockGeneration,4));
        usbPorts.addItem(new Item("RAM-connector","This connector will allow to get to your RAM.",northbridge,16));
        usbPorts.addItem(new Item("Fuel-pack","You can refuel your tank with this one.",null,16));
        clockGeneration.addItem(new Item("Repair-kit","This kit looks like it can repair the messed up BIOS.",bios,4));
        sataPorts.addItem(new Item("Bridge-connector","This connector allows you to get up to the Northbridge.",southbridgeN,32));
        bios.addItem(new Item("CPU-connector","This connector will get you to the Central Processing Unit.",northbridge,16));
        northbridge.addItem(new Item("RAM-manual","This little manual makes your RAM useful.",ram,8));
        graphicsCard.addItem(new Item("Graphics-speedup","Your graphics card will be able to go faster with that.",graphicsCard,8));
        ram.addItem(new Item("Power-switch","This switch will allow you to start the computer.",powerManagement,32));

        // initialise room exits
        firstConnection.setExit("east",usbPorts);

        southbridgeS.setExit("north",southbridgeM);
        southbridgeS.setExit("east",pciBus);
        southbridgeS.setExit("west",usbPorts);

        southbridgeM.setExit("north",southbridgeN);
        southbridgeM.setExit("east",clockGeneration);
        southbridgeM.setExit("south",southbridgeS);
        southbridgeM.setExit("west",sataPorts);

        //southbridgeN.setExit("north",northbridge);
        southbridgeN.setExit("east",powerManagement);
        southbridgeN.setExit("south",southbridgeM);
        southbridgeN.setExit("west",bios);

        pciBus.setExit("west",southbridgeS);
        usbPorts.setExit("east",southbridgeS);
        clockGeneration.setExit("west",southbridgeM);
        sataPorts.setExit("east",southbridgeM);
        powerManagement.setExit("west",southbridgeN);
        bios.setExit("east",southbridgeN);

        //northbridge.setExit("north",cpu);
        northbridge.setExit("east",graphicsCard);
        northbridge.setExit("south",southbridgeN);
        //northbridge.setExit("west",ram);

        graphicsCard.setExit("west",northbridge);
        ram.setExit("east",northbridge);

        cpu.setExit("south",northbridge);

        // start game in the first connection from the digitalizer
        currentRoom = firstConnection;  
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            String output = processCommand(command);
            finished = (null == output);
            if (!finished)
            { 
                System.out.print ('\f');
                delayedPrint(40,output);
            }
            if(player.isDead()==true){
                finished = true;
                System.out.println("You are out of fuel and suffer to death.");
            }
            if(player.hasWon()==true){
                finished = true;
            }
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    public void delayedPrint(int delay, String sentence) 
    {
        try {
            for (char character : sentence.toCharArray()) {
                System.out.print(character);  
                Thread.sleep(delay);  
            }
        } 
        catch (InterruptedException e) 
        {

        }
        System.out.println(); 
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.print ('\f');
        System.out.println();
        delayedPrint(40,"Welcome to Digital Innerspace!");
        delayedPrint(40,"You have been thrown into a digitalizer by your abominable brother.");
        delayedPrint(40,"You enter through the USB-port and you're about to reach the Southbridge.");
        delayedPrint(40,"Unfortunately, the computer has been shut down by him and you are stuck in your little rocket.");
        delayedPrint(40,"Remember that you lose fuel when moving, so keep a look on your fuel bar(moves).");
        delayedPrint(40,"Now you will have to escape by powering up your Computer, good luck!");
        delayedPrint(40,"Type 'help' if you need help.");
        delayedPrint(40,"You are " + currentRoom.getDescription());
        delayedPrint(40,currentRoom.getExitString());
        System.out.println();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    public String processCommand(Command command) 
    {
        boolean wantToQuit = false;
        if(command.isUnknown()) {
            return "I don't know what you mean...";
        }

        String commandWord = command.getCommandWord();
        String result = null;
        if (commandWord.equals("help"))
            result = printHelp();
        else if (commandWord.equals("go"))
            result = goRoom(command);
        else if (commandWord.equals("use"))
            result = use(command);
        else if (commandWord.equals("take"))
            result = take(command);
        else if (commandWord.equals("drop"))
            result = drop(command);
        else if (commandWord.equals("look")){
            result = "You are " + currentRoom.getDescription();
            result += "\n";
            result += currentRoom.getExitString();
            result += "\n";
            result += currentRoom.getItemsString();}
        else if(commandWord.equals("items")){
            result = player.getPlayerItems();
            result += "\n";
            result += "Current load: " + player.getWeight() + "/" + player.getMaxWeight() + " bits.";
        }
        else if (commandWord.equals("quit"))
            result = quit(command);

        return result;

    }

    // implementations of user commands:
    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private String printHelp() 
    {   String result = "";
        result += "You are lost. You are alone. You fly\n";
        result += "through your computer.\n";
        result += "\n";
        result += "Your command words are:\n";
        result += CommandWord.getValidCommandWords();
        return result;
    }

    /** 
     * Try to go to one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private String goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            return "Go where?";
        }

        String direction = command.getSecondWord();
        String result = "";
        // Try to leave current room.
        if(direction.equals("back")){
            currentRoom = player.getPastRoom();
            player.enterRoom(currentRoom);
            result += "You are " + currentRoom.getDescription()+"\n";
            result += currentRoom.getExitString();
            result += "\n";
            result += "Moves left: " + player.getMoves();
        }
        else{
            Room nextRoom = currentRoom.getExit(direction);
            if (nextRoom == null) {
                result += "There is no connection!";
            }
            else {
                currentRoom = nextRoom;
                player.enterRoom(currentRoom);
                result += "You are " + currentRoom.getDescription()+"\n";
                result += currentRoom.getExitString();
                result += "\n";
                result += "Moves left: " + player.getMoves();
            }
        }

        if(currentRoom.getDescription().equals("in the Power Management")){
            player.clearMoves();
            result += "\n" + "Your tank has been refueled.";
            result += "\n" + "Moves left: " + player.getMoves();
        }
        result += "\n";

        return result;
    }

    private String take(Command command){
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to take...
            return "Take what?";
        }
        String itemName = command.getSecondWord();
        String result = "";
        if(currentRoom.getItem(itemName)==null){
            result+= "Can't pick up " + itemName;
        }
        else if(player.addPlayerItem(currentRoom.getItem(itemName))==false){
            result+= "Can't pick up " + itemName + ". You can save only " + player.getMaxWeight() + " bits.";
            result+= "\n";
            result+= "Current load: " + player.getWeight() + " bits.";
        }
        else{
            player.addPlayerItem(currentRoom.removeItem(itemName));
            result+=itemName + " got picked up.";
        }
        return result;
    }

    private String drop(Command command){
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to take...
            return "Drop what?";
        }
        String itemName = command.getSecondWord();
        String result = "";
        if(itemName.equals("all")){
            player.dropAllItems();
            result+= "You dropped all your items here.";
        }
        else if(player.getPlayerItem(itemName)==null){
            result+= "Can't drop " + itemName;
        }
        else{
            player.dropPlayerItem(itemName);
            result+="You dropped " + itemName;
        }
        return result;
    }

    private String use(Command command){
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to use...
            return "Use what?";
        }
        String itemName = command.getSecondWord();
        String result = "";
        if(player.getPlayerItem(itemName)==null){
            result += "Can't use " + itemName;
        }
        else if(itemName.equals("Fuel-pack")){
            player.clearMoves();
            player.removePlayerItem(itemName);
            result = "Your tank got refueled.";
        }
        else if(player.getPlayerItem(itemName).getRoom()==currentRoom){
            if(itemName.equals("Cooling-kit")||itemName.equals("Overclock-config")||itemName.equals("Repair-kit")||itemName.equals("RAM-manual")||itemName.equals("Graphics-speedup")){
                currentRoom.setState(true);
                player.removePlayerItem(itemName);
                result = "The " + currentRoom.getName() + " got repaired.";
            }
            else if(itemName.equals("RAM-connector")){
                currentRoom.setExit("west",rooms.get(13)); // RAM
                player.removePlayerItem(itemName);
                result = "You can now get to the RAM modules.";
            }
            else if(itemName.equals("Bridge-connector")){
                currentRoom.setExit("north",rooms.get(11)); // Northbridge
                player.removePlayerItem(itemName);
                result = "You can now get to the Northbridge.";
            }
            else if(itemName.equals("CPU-connector")){
                currentRoom.setExit("north",rooms.get(14)); // CPU
                player.removePlayerItem(itemName);
                result = "You can now get to the Central Processing Unit.";
            }
            else if(itemName.equals("Power-switch")&&rooms.get(8).getState()==false){
                player.wins();
                return "You have finally escaped from the computer and now freedom is yours.\nGo and take revenge upon your brother!";
            }
            else{
                return "Can't use " + itemName + ".";
            }
            result += lastStep();
        }
        else{
            result = "You can't use " + itemName + " here.";
        }
        return result;
    }

    private String lastStep(){
        int counter = 0;
        for(Room room: rooms){
            if(room.getState()==false){
                counter++;
            }
        }
        String result = "\nThere are " + counter + " hardware components left to repair.";
        if(counter==0){
            rooms.get(8).setState(false); // Power Management
            result += "\n";
            result += "You now have to start the computer in the Power Management to escape.";
        }
        return result;
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return null, if this command quits the game, something else to output otherwise.
     */
    private String quit(Command command) 
    {
        if(command.hasSecondWord()) {
            return "Quit what?";
        }
        else {
            return null;  // signal that we want to quit
        }
    }

    public static void main(String[] args){
        Game game = new Game();
        game.play();
    }

}
