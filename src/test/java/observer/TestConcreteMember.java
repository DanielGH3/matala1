package observer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TestConcreteMember {
    
    @Test
    public void testUpdate(){
        ConcreteMember member = new ConcreteMember();   
        UndoableStringBuilder builder = new UndoableStringBuilder();

        builder.append("hello");
        member.update(builder);
        assertEquals("hello", member.getValue());

        builder.delete(0,2);
        member.update(builder);
        assertEquals("llo", member.getValue());

        builder.undo();
        member.update(builder);
        assertEquals("hello", member.getValue());

        builder.insert(3, " [hello] ");
        member.update(builder);
        assertEquals("hel [hello] lo", member.getValue());
    }

    @Test
    public void testGetValue(){
        ConcreteMember member = new ConcreteMember();   
        UndoableStringBuilder builder = new UndoableStringBuilder();

        builder.append("hello");
        member.update(builder);
        assertEquals("hello", member.getValue());

        builder.append("world");
        member.update(builder);
        assertEquals("helloworld", member.getValue());

        builder.insert(5," ");
        member.update(builder);
        assertEquals("hello world", member.getValue());

        builder.append(" :)");
        member.update(builder);
        assertEquals("hello world :)", member.getValue());
    }
}
