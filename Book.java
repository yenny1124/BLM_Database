/* --------------------------------- Book.java ---------------------------------------
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
 * Book
 * ----
 * This class holds a record of a book.
 * 
 * @author 
 */
public class Book extends Record {
    //public String name;
    //public String description;
    public String author;
    public int year;

    /**
     * Constructors for objects of class Book
     */
    public Book() {
        // initialise instance variables
        super("Book", "unNamed Book","blank description" );
        this.author = "UnNamed author";
        this.year = 2021;
    }

    public Book(String title, String description, String author, int year) {
        // initialise instance variables
        super("Book", title, description );
        this.author = author;
        if (year < 1000 || year > 2021) year = 2021;
        else this.year = year;
    }

    /**
     * compareToBy(Record, String)
     * -----------
     * Description: This method will compare this to other using the attribute specified by attributeToCompareBy.
     * PRE-CONDITION: a. a Record object and String (attribute) passed as a parameter
     *                b. two helper methods for String comparison and int comparison already built-in
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

            case "author": {
                if (!(other instanceof Book)) return -1;
                Book otherBook = (Book) other;
                return StringCompareTo(this.author, otherBook.author);
            }

            case "year": {
                if (other instanceof Book) {
                    Book otherBook = (Book) other;
                    return IntCompareTo(this.year, otherBook.year);
                }
                if (other instanceof Movie) {
                    Movie otherMovie = (Movie) other;
                    return IntCompareTo(this.year, otherMovie.year);
                }
            }
            default: {
                return -99999999;
            }
        }
    }

    /**
     * IntCompareTo(int, int)
     * -----------
     * Description: a helper method to compare this year and other year in compareToBy method
     * PRE-CONDITION: a. two int parameters(year) passed
     * POST-CONDITION:  a. if thisInt is greater than otherInt, returns 1
     *                  b. if thisInt is less than otherInt, returns
     *                  c. if thisInt equals to otherInt, returns 0
     * @param thisInt
     * @param otherInt
     * @return int value based on the comparison result
     */
    public int IntCompareTo(int thisInt, int otherInt) {
        if(thisInt>otherInt){
            return 1;
        }else if (thisInt<otherInt){
            return -1;
        }else{
            return 0;
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
     * getAuthor
     * -----------
     * Description: Returns variable this author
     * PRE-CONDITION: None
     * POST-CONDITION: returns value of this author
     * @return this author
     */
    public String getAuthor(){
        return this.author;
    }

    /**
     * getYear
     * -----------
     * Description: Returns variable this year
     * PRE-CONDITION: None
     * POST-CONDITION: returns value of this tear
     * @return this year
     */
    public int getYear(){
        return this.year;
    }


     /**
     * toString
     * --------
     * This provides a nice looking String representation of this particular Record
     * PRE: none
     * POST: current object is unchanged. A String is returned.
     */
    public String toString(){
        return this.identifier + " (" + this.year + "), written by " + this.author + ": \n" + niceLookingDescription()+ "\n";

    }
}
