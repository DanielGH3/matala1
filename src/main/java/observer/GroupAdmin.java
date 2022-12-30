package observer;

import java.util.ArrayList;
import java.util.List;

class GroupAdmin implements Sender{
    List<Member> members;

    UndoableStringBuilder stringbuilder;

    /**
     * initialized the GroupAdmin class
     */
    public GroupAdmin(){
        members = new ArrayList<Member>();
        stringbuilder = new UndoableStringBuilder();
    }

    /**
     * returns true if the memeber is currently observing this obj instance
     * @param obj member that will be checked
     * @return true if memeber is observing, false otherwise
     */
    public boolean contains(Member obj){
        return members.contains(obj);
    }

    /**
     * adds a new memeber to the observers list
     * @param obj a memeber that will be added
     */
    @Override
    public void register(Member obj) {
        if(!members.contains(obj))
            members.add(obj);
    }

    /**
     * removes an existing memeber from the observers list
     * @param obj a memeber that will be removed
     */
    @Override
    public void unregister(Member obj) {
        if(members.contains(obj))
            members.remove(obj);
    }

    /**
     * Inserts the string into the current string builder character sequence.
     *
     * @param offset the index which the new substring will be appended to
     * @param obj the string that will be appended from the chosen index
     */
    @Override
    public void insert(int offset, String str) {
        stringbuilder.insert(offset, str);
        notifyMembers();
    }

    /**
     * Appends the specified string into the current string builder
     * @param str the string that we want to append to the original string
     */
    @Override
    public void append(String str) {
        stringbuilder.append(str);
        notifyMembers();
    }

    /**
     * Removes the characters in a substring in the current string builder. 
     * The substring begins at the specified start and extends to the character
     * at index end - 1 or to the end of the sequence if no such character exists.
     * If start is equal to end, no changes are made.
     *
     * @param start the first index of the substring that will be deleted
     * @param end the last index of the substring that will be deleted
     */
    @Override
    public void delete(int start, int end) {
        stringbuilder.delete(start, end);
        notifyMembers();
    }

    /**
     * undo - undoing the last modification
     */
    @Override
    public void undo() {
        stringbuilder.undo();
        notifyMembers();
    }

    /**
     * nofity all members of the last change
     */
    public void notifyMembers(){
        for (Member member : members) {
            member.update(stringbuilder);
        }
    }
}
