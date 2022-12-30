-the code was built and compiled using Visual Studio Code with the java extentions
via the instructions from https://code.visualstudio.com/docs/java/java-tutorial
-the main function is located in Main.java, to use the code you can download the files
and edit the main function 

-to use the GroupAdmin with the ConcreteMember follow the next steps:
    -create the admin and memeber_1...member_k objects
    -call admin.register(member_1); ... admin.register(member_k);
    -now every change in admin using append delete etc, will also affect the members

-GroupAdmin:
    -Contains an data strucre of type array list of members that observe it
    -Contains a UndoableStringBuilder that works as instucted in the first assignment.
    -has the following built in functions :
        -bool contains(memeber)
        -void register(member)
        -void unregister(member)
        -void inster(offset, str)
        -void append(str)
        -void delter(start, end)
        -void undo()
    -explenations of each function can be found in the source code.


-ConcreteMember
    -Contains a shallow copy of the UndoableStringBuilder it observes
    -has the following built in functions :
        -void update(undoablestringbuilder)
        -void getValue()
    -explenations of each function can be found in the source code.

-UndoableStringBuilder works as mentioned in matala0
    -uses data stracture stack to store all previos versions
    of the string, after each modification