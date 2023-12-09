/* ------------------------------ OnlineRec.java -------------------------------------
   Jamie Choi, CSS 143, Winter 2022, Educational Resources Database
   Created on:      Mar 4, 2022
   Last Modified:   Mar 10, 2022
   -----------------------------------------------------------------------------------
   Purpose: The project is created to handle Black Lives Matter resources database in
   the way importing database from text file, sorting database by multiple criteria
   on console, updating additional resources to the text file, and exporting them
   to another text file.

   Assumptions: the project does not use the built-in data structure classes in Java */
/**
 * OnlineRec
 * ---------
 * This class holds a record of some online resource like a website or an online article.
 * 
 * @author 
 */
public class OnlineRec extends Record {
    public String websiteURL;

    /**
     * Constructors for objects of class OnlineRec
     */
    public OnlineRec() {
        // initialise instance variables
        super("Online", "unNamed OnlineRec","blank description" );
        this.websiteURL = "https://www.youtube.com/watch?v=dQw4w9WgXcQ";
    }

    public OnlineRec(String name, String description, String websiteURL) {
        // initialise instance variables
        super("Online", name, description);
        this.websiteURL = websiteURL;
    }

    /**
     * compareToBy(Record, String)
     * -----------
     * Description: This method will compare this to other using the attribute specified by attributeToCompareBy.
     * PRE-CONDITION: a. a Record object and String (attribute) passed as a parameter
     *                b. a helper method for String comparison already built-in
     * POST-CONDITION: a. A negative number is returned if this < other
     *                    when comparing their respective attributeToCompareBy.
     *                 b. A 0 is returned if this = other with respect to attributeToCompareBy
     *                 c. positive number is returned if this > other with respect to their attributeToCompareBy
     * @return  int value based on the comparison result
     */
    public int compareToBy(Record other, String attributeToCompareBy){
        if (other == null || attributeToCompareBy == null) {
            return 0;
        }
        if(this.hasAttribute(attributeToCompareBy) && !other.hasAttribute(attributeToCompareBy)) return -1;
        if(!this.hasAttribute(attributeToCompareBy) && other.hasAttribute(attributeToCompareBy)) return 1;
        if (!this.hasAttribute(attributeToCompareBy) && !other.hasAttribute(attributeToCompareBy)) return 0;

        switch (attributeToCompareBy){
            case "recordtype": {
                return StringCompareTo(this.recordType, other.recordType);
            }
            case "identifier": {
                return StringCompareTo(this.identifier, other.identifier);
            }
            case "description": {
                return StringCompareTo(this.description, other.description);
            }

            case "websiteURL": {
                if (!(other instanceof OnlineRec)) return -1;
                OnlineRec otherOnline = (OnlineRec) other;
                return StringCompareTo(this.websiteURL, otherOnline.websiteURL);
            }
            default: {
                return -99999999;
            }
        }
    }

    /**
     * StringCompareTo(String, String)
     * Description: a helper method to compare this String attribute and other String attribute in compareToBy method
     * PRE-CONDITION: a. two String parameters(attribute) passed
     * POST-CONDITION:  a. if thisAttribute alphabetically comes after otherAttribute,
     *                     returns positive int
     *                  b. if thisAttribute alphabetically comes before other otherAttribute,
     *                     returns negative int
     *                  c. if thisAttribute is equal to other otherAttribute, returns 0
     * @param thisAttribute
     * @param otherAttribute
     * @return int value based on the comparison result
     */
    public int StringCompareTo(String thisAttribute, String otherAttribute) {
        return thisAttribute.compareTo(otherAttribute);
    }

    /**
     * getWebsiteURL
     * -----------
     * Description: Returns variable this website URL
     * PRE-CONDITION: None
     * POST-CONDITION: returns value of this website URL
     * @return this websiteURL
     */
    public String getWebsiteURL(){
        return this.websiteURL;
    }


    /**
     * toString
     * --------
     * This provides a nice looking String representation of this particular Record
     * PRE: none
     * POST: current object is unchanged. A String is returned.
     */
    public String toString(){
        return this.identifier + " (" + this.websiteURL + "): \n" + niceLookingDescription() + "\n";

    }
}
