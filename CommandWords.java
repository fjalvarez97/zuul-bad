import java.util.*;
/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class CommandWords
{
    // a constant array that holds all valid command words
    HashMap<String,CommandWord> validCommands;

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        validCommands = new HashMap<>();
        validCommands.put("go", CommandWord.GO);
        validCommands.put("quit", CommandWord.QUIT);
        validCommands.put("help", CommandWord.HELP);
        validCommands.put("look", CommandWord.LOOK);
        validCommands.put("eat", CommandWord.EAT);
        validCommands.put("back", CommandWord.BACK);
        validCommands.put("take", CommandWord.TAKE);
        validCommands.put("drop", CommandWord.DROP);
        validCommands.put("items", CommandWord.ITEMS);
        validCommands.put("open", CommandWord.OPEN);
    }

    /**
     * Return the CommandWord associated with a word.
     * @param commandWord The word to look up (as a string).
     * @return The CommandWord corresponding to the String commandWord, or UNKNOWN
     *         if it is not a valid command word.
     */
    public CommandWord getCommandWord(String commandWord)
    {
        CommandWord command = CommandWord.UNKNOWN;
        switch(commandWord.toLowerCase()) {
            case "help":
            command = CommandWord.HELP;
            break;
            case "go":
            command = CommandWord.GO;
            break;
            case "quit":
            command = CommandWord.QUIT;
            break;
            case "look":
            command = CommandWord.LOOK;
            break;
            case "eat":
            command = CommandWord.EAT;
            break;
            case "back":
            command = CommandWord.BACK;
            break;
            case "take":
            command = CommandWord.TAKE;
            break;
            case "items":
            command = CommandWord.ITEMS;
            break;
            case "drop":
            command = CommandWord.DROP;
            break;
            case "open":
            command = CommandWord.OPEN;
            break;
        }
        return command;
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        return validCommands.containsKey(aString);
    }

    public String getCommandList()
    {
        String infoCmd = "";
        for (String cmd : validCommands.keySet()){
            infoCmd+= cmd + " ";
        }
        return infoCmd;
    }
}