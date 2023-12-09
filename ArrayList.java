/* ------------------------------ ArrayList.java -------------------------------------
   Jamie Choi, CSS 143, Winter 2022, Educational Resources Database
   Created on:      Mar 4, 2022
   Last Modified:   Mar 10, 2022
   -----------------------------------------------------------------------------------
   Purpose: The project is created to handle Black Lives Matter resources database in
   the way importing database from text file, sorting database by multiple criteria
   on console, updating additional resources to the text file, and exporting them
   to another text file.

   Assumptions: the project does not use the built-in data structure classes in Java */
public class ArrayList <E>{
    private Object[] dataArray = new Object[1];

    /**
     * insert(E, int)
     * -----------
     * Description: Inserts a new Object at an user specified index
     * PRE-CONDITION: an Object and int index passed
     * POST-CONDITION: adds an object at the specified index, shifting other elements over as needed
     */
    public void insert(E obj, int index) {
        if(index>this.size()||index<0){
            System.out.println("Index "+index +" is out of bounds for the ArrayList of " +
                    "size " +this.size()); // out of bounds message
        }
        int counter = 0; // int to count the number of iteration
        try {
            // creates a temporary array with 1 increased length
            Object[] tempArray = new Object[this.dataArray.length+1];
            if(index==0){ // index = 0;
                tempArray[0] = obj;
                for(int i=0; i<this.size(); i++) { // shifts other elements over to the right
                    tempArray[i+1] = this.dataArray[i];
                }
                this.dataArray = tempArray;
            }else{ // index != 0;
                for(int i=0; i<index; i++) {
                    tempArray[i] = this.dataArray[i];
                }
                tempArray[index] = obj;
                for(int j=index; j<this.size(); j++) {
                    tempArray[j+1] = this.dataArray[j];
                }
                this.dataArray = tempArray;
            }
        } catch (ArrayIndexOutOfBoundsException e) { // to handle out of bounds exception in an array
            increaseListSize(); // extends the size of dataArray
            if(++counter == 2){
                System.out.println("Error occurs!");
                System.exit(0);
            }
        }
    }

    /**
     * remove(int)
     * -----------
     * Description: Removes and returns the object at the user specified index.
     * PRE-CONDITION: an int index passed
     * POST-CONDITION: deletes and returns the removed element at the specified index.
     * @return Object removed
     */
    public E remove(int index){
        if(this.size()==0){
            System.out.println("The List is empty");
            return null;
        }

        if(index>this.size()||index<0){
            System.out.println("Index "+index +" is out of bounds for the ArrayList of " +
                    "size " +this.size()); // out of bounds message
            return null;
        }

        E removed = (E) this.dataArray[index];
        int counter = 0; // int to count the number of iteration
        try {
            // creates a temporary array with 1 reduced length
            Object[] tempArray = new Object[this.dataArray.length-1];
            for(int i=0, j=0; i < this.dataArray.length; i++){
                if (i != index) {
                    tempArray[j++] = this.dataArray[i];
                }
                // otherwise, j does not increment (when i is index)
            }
            this.dataArray = tempArray;
        }
        catch(ArrayIndexOutOfBoundsException e) { // to handle out of bounds exception in an array
            increaseListSize(); // extends the size of dataArray
            if(++counter == 2){
                System.out.println("Error occurs!");
                System.exit(0);
            }
        }
        return removed; // returns the removed element
    }

    /**
     * size()
     * -----------
     * Description: Checks existing elements and counts the array length(size)
     * PRE-CONDITION: None
     * POST-CONDITION: returns the length of the array
     * @return int countSize
     */
    public int size(){
        int countSize = 0;
        for(Object e : this.dataArray) {
            if(e==null) {
                continue; // if null exists, skip counting
            }else {
                countSize++; // if non-null element, continue counting
            }
        }
        return countSize; // returns the array length
    }

    /**
     * toString()
     * -----------
     * Description: Returns the elements of the array as a string representation
     * PRE-CONDITION: None
     * POST-CONDITION: changes array into String representation and returns
     * @return String str
     */
    public String toString(){
        String str = "{";
        for(int i=0;i<this.size();i++){
            if(i<this.size()-1) {
                str += this.dataArray[i] + ", ";
            }else {
                str += this.dataArray[i]; // last element in array isn't followed by a comma
            }
        }
        str+= "}";
        return str; // returns the array as a string representation
    }

    /**
     * isEmpty()
     * -----------
     * Description: returns true/false depending on the array is empty or not
     * PRE-CONDITION: None
     * POST-CONDITION: checks if there are null elements and returns true/false
     * @return boolean true/false
     */
    public boolean isEmpty() {
        boolean checkEmpty = true;
        for (Object e : this.dataArray) {
            if(e!=null){
                checkEmpty = false;
                break;
            }else{
                checkEmpty = true; // if there are null elements, the array is empty
                break;
            }
        }
        return checkEmpty; // returns the boolean result
    }

    /**
     * indexOf(E)
     * -----------
     * Description: Returns the Object's index in array
     * PRE-CONDITION: an Object parameter passed
     * POST-CONDITION: a. checks if the object exists in array by retrieval
     *                 b. if it exists, returns the index the object located at
     *                 c. if it doesn't exist, returns -1
     * @return int index
     */
    public int indexOf(E obj){
        int index = -1;
        for(int i=0;i<this.size();i++){
            if(this.dataArray[i]!=obj) { // return -1 if obj isn't found
                continue;
            }else{ index = i;}
        }
        return index;
    }

    /**
     * equals(ArrayList)
     * -----------
     * Description: Checks the equivalence between ArrayList "this" and "other"
     * PRE-CONDITION: an ArrayList parameter passed
     * POST-CONDITION: a. compares lengths of the two ArrayList
     *                 b. compares the elements of the two ArrayList
     *                 c. returns true/false depending on the equivalence
     * @return boolean true/false
     */
    public boolean equals(ArrayList other){
        if(other==null){ return false; }
        if(this.getClass()!= other.getClass()){ return false; }

        if(this.dataArray.length != other.dataArray.length){
            return false;
        }else{ // the array length are the same
            for(int i=0; i <other.dataArray.length; i++){
                if(!this.dataArray[i].equals(other.dataArray[i])){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * get(int)
     * -----------
     * Description: Returns the object at the user specified index
     * PRE-CONDITION: an int index parameter passed
     * POST-CONDITION: a. checks if the index is out of bounds
     *                 b. if it is in range of bounds, returns the object at the index
     *                 c. if it is out of bounds, returns error message as IndexOutOfBoundsException handling
     * @return Object at the index
     */
    public E get(int index) throws IndexOutOfBoundsException{
        if(index>this.size()||index<0){ // index is out of bounds
            throw new IndexOutOfBoundsException("The index "+index+" is out of bounds");
        }else{
            return (E) this.dataArray[index]; // returns the Object at the index
        }
    }

    /**
     * set(int, E)
     * -----------
     * Description: a method to replace an element at specified index
     * PRE-CONDITION: an int and object parameters
     * POST-CONDITION: replaces elements at user specified index
     * @param index
     * @param obj
     */
    public void set(int index, E obj){
        this.dataArray[index] = obj;
    }

    /**
     * append(E)
     * -----------
     * Description: Adds an object to the end of the array
     * PRE-CONDITION: an Object parameter passed
     * POST-CONDITION: inserts Object to the end of the array (last index)
     */
    public void append(E obj) throws ArrayIndexOutOfBoundsException{
        int counter = 0; // int to count the number of iteration
        try{
            this.dataArray[this.size()] = obj; // adds to the end of the array
        }
        catch (ArrayIndexOutOfBoundsException e) { // out of bounds handling
            increaseListSize(); // extends length of the array
            if(++counter==2){
                System.out.println("Error occurs!");
                System.exit(0);
            }else{
                this.append(obj); // recursive call if error doesn't exist
            }
        }
    }

    /**
     * increaseListSize()
     * -----------
     * Description: Extends array twice the length
     * PRE-CONDITION: None
     * POST-CONDITION: a. copies the original array to a temp array double sized
     *                 b. copies the temp array to the original array again
     */
    public void increaseListSize() throws IndexOutOfBoundsException{
        Object[] tempArray = new Object[this.dataArray.length*2];
        for(int i=0; i<this.dataArray.length ;i++) {
            tempArray[i] = this.dataArray[i];
        }
        this.dataArray = tempArray;
    }
}
