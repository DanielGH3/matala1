package observer;

public class ConcreteMember implements Member {

    UndoableStringBuilder stringbuilder;

    /**
     * initilize ConcreteMember
     */
    public ConcreteMember(){
        stringbuilder = new UndoableStringBuilder();
    }

    /**
    * update the member after each changes in each Sender 
    * it observes
    * @param usb the observed data 
    */
    @Override
    public void update(UndoableStringBuilder usb) {
        if(stringbuilder != usb){
            stringbuilder = usb;
        }
    }    

    /**
     * @return the current value of the string builder
     */
    public String getValue(){
        return stringbuilder.toString();
    }
}
