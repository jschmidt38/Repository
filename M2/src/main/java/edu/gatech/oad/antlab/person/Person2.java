package edu.gatech.oad.antlab.person;

import java.util.Random;

/**
 *  A simple class for person 2
 *  returns their name and a
 *  modified string 
 *
 * @author John
 * @version 1.2
 */
public class Person2 {
    /** Holds the persons real name */
    private String name;
         /**
     * The constructor, takes in the persons
     * name
     * @param pname the person's real name
     */
     public Person2(String pname) {
       name = pname;
     }
/**
     * This method should take the string
     * input and return its characters in
     * random order.
     * given "gtg123b" it should return
     * something like "g3tb1g2".
     *
     * @param input the string to be modified
     * @return the modified string
     */
    private String calc(String input) {
            Random random = new Random();
    // Convert your string into a simple char array:
            char arr[] = input.toCharArray(); 
            for(int i=0;i<arr.length-1;i++)
                {
                    int j = random.nextInt(arr.length-1);
                    char temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                   }       
    return new String(arr);
}
/**
     * Return a string rep of this object
     * that varies with an input string
     *
     * @param input the varying string
     * @return the string representing the 
     *         object
     */
    public String toString(String input) {
      return name + calc(input);
    }
}

