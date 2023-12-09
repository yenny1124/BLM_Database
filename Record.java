/* -------------------------------- Record.java --------------------------------------
   Jamie Choi, CSS 143, Winter 2022, Educational Resources Database
   Created on:      Mar 4, 2022
   Last Modified:   Mar 10, 2022
   -----------------------------------------------------------------------------------
   Purpose: The project is created to handle Black Lives Matter resources database in
   the way importing database from text file, sorting database by multiple criteria
   on console, updating additional resources to the text file, and exporting them
   to another text file.

   Assumptions: the project does not use the built-in data structure classes in Java */

import java.util.StringTokenizer;
import java.lang.reflect.*;
/**
 * Record  
 * ------
 * This is the abstract class that is the general template for all
 * the different kinds of BLM resources ("records") we might build.
 * This will guarantee that regardless of whatever other differences
 * the different kinds of Records might have from each other,
 * they will all at least have a recordType (for example, "Online", "Book", "Movie"),
 * an identifier (like the name of the book, movie, or online organization or article),
 * and a description.
 * Because the Record class implements the CompareToByInterface,
 * it will also force any children classes to have the CompareToBy method
 * which will allow them to be sorted by different criteria.
 * 
 * @author Karen
 */
public abstract class Record implements CompareToByInterface {

    protected String recordType; // for exqmple: 'Online', 'Book', 'Movie'
    protected String identifier; // name of the online source or the title of the movie or book
    protected String description;

    public Record(String type, String identifier, String description){
        this.recordType = type;
        this.identifier = identifier;
        this.description = description;
    }

    /**
     * niceLookingDescription
     * ----------------------
     * Since the descriptions for a record may sometimes be long,
     * they will sometimes go out of the screen or wrap in ways that won't look good.
     * This method solves that problem by returning a string representation of the description
     * that separates it onto different lines.
     * PRE: none
     * POST: class instance is unchanged. This returns a properly formatted String representation of the description.`
     */
    public String niceLookingDescription(){
        int maxLineLength = 80;
        String indent = "     ";
        String d = indent;
        int numberOfCharsSoFar = 0;
        StringTokenizer st = new StringTokenizer(this.description);
        int currentLineLength = 0;
        while (st.hasMoreTokens()){
            String next = st.nextToken();
            // if adding the next word (plus one space) would be more than maxLineLength, then add a carriage return.
            if ((currentLineLength + next.length() + 1) > maxLineLength) {
                d = d + "\n" + indent;
                currentLineLength = 0;
            }
            d = d + " " + next;
            currentLineLength += next.length() + 1;
        }
        return d;
    }


    /**
     * hasAttribute
     * ------------
     * This method can be used to determine if a Record has a particular attribute
     * (by attribute, I basically mean instance variable).
     * For example, OnlineRec has a 'websiteURL' attribute,
     * while Book has an 'author' attribute.
     * This method is useful because, the Record class implements the CompareToByInterface,
     * and the CompareToBy method might need to check if a Record has a particular attribute
     * before it attempts to compare a Record using that attribute.
     * NOTE: the attribute parameter here is NOT case sensitive.
     *       Thus, if the attribute parameter is "auTHOR", but the class has an attribute called "author"
     *       or "Author", this method will return true.
     * PRE: none
     * POST: The object is unchanged. A boolean is returned.
     */
    public boolean hasAttribute(String attribute){
        if (    attribute.equalsIgnoreCase("recordType") ||
                attribute.equalsIgnoreCase("identifier") ||
                attribute.equalsIgnoreCase("description")) return true; // we KNOW that all Records have these.

        // get the fields ("attributes" or instance variables) of this class:
        // (This will produce a list of items that look like "public java.lang.string book.author"
        Field[] fields = this.getClass().getFields();

        // iterate through the fields and check if each one contains inside of it our attribute string parameter
        for (int i = 0; i < fields.length; i++){
            //System.out.println(this.getClass().toString() + ": [" + fields[i].toString().toLowerCase() + "], attrbute: " + attribute);
            if (fields[i].toString().toLowerCase().contains(attribute.toLowerCase()) ) {
                // FOR TESTING: System.out.println("YES! " + fields[i].toString() + "contains " +attribute+ "!");
                return true;
            }
        }

        // FOR TESTING: System.out.println("No, " +this.getClass() + " does not have " + attribute);
        return false;

    }

    /* -------- */
    /* GETTERS: */
    /* -------- */

    /**
     * getRecordType
     * -------------
     * This returns the recordType of the current Record
     * PRE: none
     * POST: The object is unchanged. A String is returned
     *
     */
    public String getRecordType(){
        return this.recordType;
    }


    /**
     * getIdentifier
     * -------------
     * This returns the identifier of the current Record
     * PRE: none
     * POST: The object is unchanged. A String is returned
     *
     */
    public String getIdentifier(){
        return this.identifier;
    }


    /**
     * getDescription
     * --------------
     * This returns the dsecription of the current Record
     * PRE: none
     * POST: The object is unchanged. A String is returned
     *
     */
    public String getDescription(){
        return this.description;
    }
}
