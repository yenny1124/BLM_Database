/* --------------------------------- Movie.java --------------------------------------
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
 * Movie
 * ----
 * This class holds a record of a movie.
 * 
 * @author 
 */
public class Movie extends Record {
    //public String name;
    //public String description;
    public String director;
    public int year;

    /**
     * Constructors for objects of class Movie
     */
    public Movie() {
        // initialise instance variables
        super("Movie", "unNamed Movie","blank description" );
        this.director = "UnNamed Director";
        this.year = 2021;
    }

    public Movie(String name, String description, String director, int year) {
        // initialise instance variables
        super("Movie", name, description );
        this.director = director;
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

            case "director": {
                if (!(other instanceof Movie)) return -1;
                Movie otherMovie = (Movie) other;
                return StringCompareTo(this.director, otherMovie.director);
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
     * getDirector
     * -----------
     * Description: Returns variable this director
     * PRE-CONDITION: None
     * POST-CONDITION: returns value of this director
     * @return this director
     */
    public String getDirector() {
        return this.director;
    }

    /**
     * getYear
     * -----------
     * Description: Returns variable this year
     * PRE-CONDITION: None
     * POST-CONDITION: returns value of this year
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
        return this.identifier + " (" + this.year + "), directed by " + this.director + ": \n" + niceLookingDescription()+ "\n";
    }
}
