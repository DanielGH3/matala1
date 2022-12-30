package observer;

import java.util.EmptyStackException;
import java.util.Stack;

public class UndoableStringBuilder  {
       private Stack<String> t;
       private StringBuilder s;
       
    public UndoableStringBuilder() {
        this.s=new StringBuilder();
        t=new Stack<>();
        t.push(s.toString());
    }

    /**
     * Appends the specified string to this character sequence
     * @param str the string that we want to append to the original string
     * @return the modified string
     */
    public UndoableStringBuilder append(String str)
    {

        this.s.append(str);
        t.push(s.toString());
        return this;
    }

    /**
     * Removes the characters in a substring of this sequence. The substring begins
     * at the specified start and extends to the character at index
     * end - 1 or to the end of the sequence if no such character exists.
     * If start is equal to end, no changes are made.
     *
     * @param start the first index of the substring that will be deleted
     * @param end the last index of the substring that will be deleted
     * @return the modified string
     */
    public UndoableStringBuilder delete(int start, int end){
        try{
            this.s.delete(start, end);
            t.push(s.toString());
        }
        catch(StringIndexOutOfBoundsException e){

            System.out.println(e.getMessage());
            e.printStackTrace();

        }

        return this;

    }


    /**
     * Inserts the string into this character sequence.
     *
     * @param offset the index which the new substring will be appended to
     * @param str the string that will be appended from the chosen index
     * @return the modified string
     */
    public UndoableStringBuilder insert(int offset, String str)
    {

        try{
            this.s.insert(offset, str);
            t.push(s.toString());
        }
        catch(StringIndexOutOfBoundsException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return this;


    }

    /**
     * Replaces the characters in a substring of this sequence with characters in
     * the specified String. The substring begins at the specified start and
     * extends to the character at index end - 1 or to the end of the sequence if
     * no such character exists. First the characters in the substring are removed
     * and then the specified String is inserted at start. (This sequence will be
     * lengthened to accommodate the specified String if necessary).
     *
     * @param start the first index of the substring which will be replaced
     * @param end the last index of the substring which will be replaced
     * @param str the string that will replace the chosen substring
     * @return the modified string
     */
    public UndoableStringBuilder replace(int start,int end, String str){



        try{
            this.s.replace(start, end, str);
            t.push(s.toString());
        }
        catch(StringIndexOutOfBoundsException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return this;
    }


    /**
     * Causes this character sequence to be replaced by the reverse of the
     * sequence.
     *
     * @return a reversed version of the original string
     */
    public UndoableStringBuilder reverse()
    {
        try{
            this.s.reverse();
            t.push(s.toString());
        }
        catch(StringIndexOutOfBoundsException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return this;
    }





    public String toString ()
    {
        return s.toString();
    }

    /**
     *
     * undo - undoing the last modification
     *
     */
    public void undo()
    {
        try {
            t.pop();
            this.s.replace(0,s.length(),t.peek());
        }
        catch (EmptyStackException e){
            return;

        }



    }





}
