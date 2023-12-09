/* ------------------------------ RecordList.java ------------------------------------
   Jamie Choi, CSS 143, Winter 2022, Educational Resources Database
   Created on:      Mar 4, 2022
   Last Modified:   Mar 10, 2022
   -----------------------------------------------------------------------------------
   Purpose: The project is created to handle Black Lives Matter resources database in
   the way importing database from text file, sorting database by multiple criteria
   on console, updating additional resources to the text file, and exporting them
   to another text file.

   Assumptions: the project does not use the built-in data structure classes in Java */


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * RecordList
 * ----------
 * RecordList is essentially an ArrayList of Records with a few sorting methods and display methods added.
 * 
 *
 * @author 
 */
public class RecordList extends ArrayList<Record> {
    /**
     * Constructor for objects of class RecordList
     */
    public RecordList() {
        super();
    }


    /**
     * sortBy
     * ------
     * This sorts the Records by featureToSortBy.
     * This uses the Insertion Sort algorithm, since that one is stable
     * PRE: featureToSortBy describes one of the instance variables of at least one of the Records in the database,
     *      such that it is taken into account by that Record's compareToBy method.
     *      (If not, correct sorting is not guaranteed, but the program should not crash.)
     * POST: the records in the database are sorted in ascending order by the sorting criteria specified.
     */
    public void sortBy(String featureToSortBy){
        if(featureToSortBy.equals("recordType")) {
            for (int j = 1; j < this.size(); j++) {
                Record current = (Record) this.get(j);
                int i = j - 1;
                Record other = (Record) this.get(i);
                int compare = other.recordType.compareToIgnoreCase(current.recordType);
                while (i >-1 && compare > 0) {
                    this.swap(i + 1, i);
                    i--;
                }
                this.overwrite(i + 1, current);
            }
            printOutList();
        }else if(featureToSortBy.equals("identifier")){
            for (int j = 1; j < this.size(); j++) {
                Record current = (Record) this.get(j);
                int i = j - 1;
                Record other = (Record) this.get(i);
                int compare = other.identifier.compareToIgnoreCase(current.identifier);
                while (i >-1 && compare > 0) {
                    this.swap(i + 1, i);
                    i--;
                }
                this.overwrite(i + 1, current);
            }
            printOutList();

        }else if(featureToSortBy.equals("description")){
            for (int j = 1; j < this.size(); j++) {
                Record current = (Record) this.get(j);
                int i = j - 1;
                Record other = (Record) this.get(i);
                int compare = other.description.compareToIgnoreCase(current.description);
                while (i >-1 && compare > 0) {
                    this.swap(i + 1, i);
                    i--;
                }
                this.overwrite(i + 1, current);
            }
            printOutList();

        }else if(featureToSortBy.equals("author")){
            for (int j = 1; j < this.size(); j++) {
                Record current = (Record) this.get(j);
                int i = j - 1;
                Record other = (Record) this.get(i);
                if(current instanceof Book && other instanceof Book){
                    int compare = (other.compareToBy(current, "author"));
                    while (i >-1 && compare > 0) {
                        this.swap(i + 1, i);
                        i--;
                    }
                    this.overwrite(i + 1, current);
                }
            }
            printOutList();

        }else if(featureToSortBy.equals("director")){
            for (int j = 1; j < this.size(); j++) {
                Record current = (Record) this.get(j);
                int i = j - 1;
                Record other = (Record) this.get(i);
                if(current instanceof Movie && other instanceof Movie){
                    int compare = (other.compareToBy(current,"director"));
                    while (i >-1 && compare > 0) {
                        this.swap(i + 1, i);
                        i--;
                    }
                    this.overwrite(i + 1, current);
                }
            }
            printOutList();

        }else if(featureToSortBy.equals("websiteURL")){
            for (int j = 1; j < this.size(); j++) {
                Record current = (Record) this.get(j);
                int i = j - 1;
                Record other = (Record) this.get(i);
                if(current instanceof OnlineRec && other instanceof OnlineRec){
                    int compare = (other.compareToBy(current, "websiteURL"));
                    while (i >-1 && compare > 0) {
                        this.swap(i + 1, i);
                        i--;
                    }
                    this.overwrite(i + 1, current);
                }
            }
            printOutList();

        }else if(featureToSortBy.equals("year")){
            for (int j = 1; j < this.size(); j++) {
                Record current = (Record) this.get(j);
                int i = j - 1;
                Record other = (Record) this.get(i);
                if((current instanceof Book||current instanceof Movie)
                        && (other instanceof Book||other instanceof Movie)){
                    int compare = other.compareToBy(current, "year");
                    while (i >-1 && compare > 0) {
                        this.swap(i + 1, i);
                        i--;
                    }
                    this.overwrite(i + 1, current);
                }
            }
            printOutList();
        }
    }

    /**
     * printOutList()
     * ----------------------------
     * Description: a helper method to print out a nice representation of all the records in the RecordList
     * PRE: none
     * POST: a. It displays them in their current order.
     *       b. Each record is on its own line.
     */
    public void printOutList() {
        for(int i=0; i<this.size(); i++){
            Record curRecord = (Record) this.get(i);
            if (curRecord instanceof Movie && curRecord.getRecordType().equals("Movie")){
                Movie movie = (Movie)curRecord;
                System.out.println("\nMovie\n"+"Name/Title: "+movie.getIdentifier() + "\n" +
                        movie.getDescription() + "\n" +
                        movie.getDirector() + "\n" +
                        movie.getYear());
            }else if(curRecord instanceof Book && curRecord.getRecordType().equals("Book")){
                Book book = (Book)curRecord;
                System.out.println("\nBook\n"+"Name/Title: "+book.getIdentifier() + "\n" +
                        book.getDescription() + "\n" +
                        book.getAuthor() + "\n" +
                        book.getYear());
            }else if(curRecord instanceof OnlineRec && curRecord.getRecordType().equals("Online")){
                OnlineRec online = (OnlineRec)curRecord;
                System.out.println("\nOnline\n"+"Name/Title: "+online.getIdentifier() + "\n" +
                        online.getDescription() + "\n" +
                        online.getWebsiteURL());
            }
        }
        System.out.println();
    }

     /*
      * // NOTE FROM KAREN:
      *
      * I got this Insertion Sort code from
      * https://stackabuse.com/insertion-sort-in-java/
      * (I'll use it as a template for making my own insertSort -- sortBy -- that works with my own ArrayList/RecordList)
      *
      * public static void insertionSort(int array[]) {
      *     for (int j = 1; j < array.length; j++) {
      *         int current = array[j];
      *         int i = j-1;
      *         while ((i > -1) && (array[i] > current)) {
      *             array[i+1] = array[i];
      *             i--;
      *         }
      *         array[i+1] = current;
      *     }
      * }
      */


    /**
     * swap
     * ----
     * If you're gonna sort an ArrayList,
     * you might need a way to swap the elements of our ArrayList by index.
     * PRE: indices refer to elements in the arrayList.
     * POST: elements at indices a and b will have swapped places (the memory locations)
     */
    private void swap(int a, int b){
        this.set(a,this.get(b));
    }

    /**
     * overwrite
     * ---------
     * This method overwrites the elements at index i with Record r,
     * so long as there is an element at index i.
     * PRE: there's an element at i
     * POST: overwrites element at i with r.
     */
    private void overwrite(int i,Record r){
        this.set(i,r);
    }

    /**
     * displayAllRecords
     * -----------------
     * This prints to the screen a nice representation of all the records in their current order
     * Each record is on its own line.
     * PRE: none
     * POST: none.
     */
    public void displayAllRecords() {
        for(int i=0; i<this.size(); i++){
            System.out.println(this.get(i));
        }
    }

    /**
     * displayRecordsOfRecordType()
     * ----------------------------
     * This prints to the screen a nice representation of all the records in the RecordList
     * whose recordType matches the parameter recType.  It displays them in their current order.
     * Each record is on its own line.
     * PRE: r must be a recordType of at least some records, or else it will display no records.
     * POST: none
     */
    public void displayRecordsOfRecordType(String recType){
        for(int i=0; i<this.size(); i++){
            Record curRecord = (Record) this.get(i);
            if (curRecord instanceof Movie && recType.equals("Movie")){
                Movie movie = (Movie)curRecord;
                System.out.println("\n"+movie.getIdentifier() + "\n" +
                        movie.getDescription() + "\n" +
                        movie.getDirector() + "\n" +
                        movie.getYear());
            }else if(curRecord instanceof Book && recType.equals("Book")){
                Book book = (Book)curRecord;
                System.out.println("\n"+book.getIdentifier() + "\n" +
                        book.getDescription() + "\n" +
                        book.getAuthor() + "\n" +
                        book.getYear());
            }else if(curRecord instanceof OnlineRec && recType.equals("Online")){
                OnlineRec online = (OnlineRec)curRecord;
                System.out.println("\n"+online.getIdentifier() + "\n" +
                        online.getDescription() + "\n" +
                        online.getWebsiteURL());
            }
        }

    }

    /**
     * append(E)
     * -----------
     * Description: Adds an object to the end of the RecordList
     * PRE-CONDITION: a Record parameter passed
     * POST-CONDITION: inserts Record object to the end of the List (last index)
     * @param newData
     */
    @Override
    public void append(Record newData) {
        super.append(newData);
    }
}
