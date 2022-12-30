package observer;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TestGroupAdmin {
    @Test
    public void testAppend(){
        GroupAdmin admin = new GroupAdmin();
        ConcreteMember member1 = new ConcreteMember();
        ConcreteMember member2 = new ConcreteMember();
      
        admin.register(member1);
        admin.register(member2);

        admin.append("hello");
        assertEquals("hello", member1.getValue());

        admin.append(" world!");
        assertEquals("hello world!", member1.getValue());

        admin.append(" :)");
        assertEquals("hello world! :)", member1.getValue());
        assertEquals("hello world! :)", member2.getValue()); 
    }

    @Test
    public void testContains(){
        GroupAdmin admin = new GroupAdmin();
        ConcreteMember member1 = new ConcreteMember();
        ConcreteMember member2 = new ConcreteMember();
        ConcreteMember member3 = new ConcreteMember();

        admin.register(member1);
        assertEquals(true, admin.contains(member1));
        assertEquals(false, admin.contains(member2));
        assertEquals(false, admin.contains(member3));

        admin.register(member3);
        assertEquals(true, admin.contains(member1));
        assertEquals(false, admin.contains(member2));
        assertEquals(true, admin.contains(member3));

        admin.register(member2);
        assertEquals(true, admin.contains(member1));
        assertEquals(true, admin.contains(member2));
        assertEquals(true, admin.contains(member3));

        admin.unregister(member1);
        admin.unregister(member2);
        admin.unregister(member3);
        assertEquals(false, admin.contains(member1));
        assertEquals(false, admin.contains(member2));
        assertEquals(false, admin.contains(member3));

        admin.register(member1);
        admin.register(member2);
        admin.register(member3);
        assertEquals(true, admin.contains(member1));
        assertEquals(true, admin.contains(member2));
        assertEquals(true, admin.contains(member3));
    }
    
    @Test
    public void testRegister(){
        GroupAdmin admin = new GroupAdmin();
        ConcreteMember member1 = new ConcreteMember();
        ConcreteMember member2 = new ConcreteMember();

        admin.register(member1);
        assertEquals(true, admin.contains(member1));
        assertEquals(false, admin.contains(member2));

        admin.register(member2);
        assertEquals(true, admin.contains(member1));
        assertEquals(true, admin.contains(member2));
    }

    @Test
    public void testUnregister(){
        GroupAdmin admin = new GroupAdmin();
        ConcreteMember member1 = new ConcreteMember();
        ConcreteMember member2 = new ConcreteMember();
        ConcreteMember member3 = new ConcreteMember();

        admin.register(member1);
        admin.register(member2);
        admin.register(member3);
        assertEquals(true, admin.contains(member1));
        assertEquals(true, admin.contains(member2));
        assertEquals(true, admin.contains(member2));

        admin.unregister(member2);
        admin.unregister(member3);
        assertEquals(true, admin.contains(member1));
        assertEquals(false, admin.contains(member2));
        assertEquals(false, admin.contains(member2));

        admin.unregister(member1);
        assertEquals(false, admin.contains(member1));
        assertEquals(false, admin.contains(member2));
        assertEquals(false, admin.contains(member2));
    }

    @Test
    public void testInsert(){
        GroupAdmin admin = new GroupAdmin();
        ConcreteMember member1 = new ConcreteMember();
        ConcreteMember member2 = new ConcreteMember();
      
        admin.register(member1);
        admin.register(member2);

        admin.insert(0,"world!");
        assertEquals("world!", member1.getValue());

        admin.insert(0, "hello");
        assertEquals("helloworld!", member1.getValue());

        admin.insert(5, " ");
        assertEquals("hello world!", member1.getValue());

        admin.insert(12, " :)");
        assertEquals("hello world! :)", member1.getValue());
        assertEquals("hello world! :)", member2.getValue()); 
    }

    @Test
    public void testDelete(){
        GroupAdmin admin = new GroupAdmin();
        ConcreteMember member1 = new ConcreteMember();
        ConcreteMember member2 = new ConcreteMember();

        admin.register(member1);
        admin.register(member2);

        admin.append("hello world! :)");
        assertEquals("hello world! :)", member1.getValue());

        admin.delete(6, 12);
        assertEquals("hello  :)", member1.getValue());

        admin.delete(5, 7);
        assertEquals("hello:)", member1.getValue());
        assertEquals("hello:)", member2.getValue());

        admin.delete(0, 5);
        assertEquals(":)", member1.getValue());

        admin.delete(0, 2);
        assertEquals("", member1.getValue());
        assertEquals("", member2.getValue());
    }

    @Test
    public void testUndo(){
        GroupAdmin admin = new GroupAdmin();
        ConcreteMember member1 = new ConcreteMember();
        ConcreteMember member2 = new ConcreteMember();

        admin.register(member1);
        admin.register(member2);

        //the same as delete but the test is 
        //in revese
        admin.append("hello world! :)");  //"hello world! :)"
        admin.delete(6, 12);       //"hello  :)"
        admin.delete(5, 7);        //"hello:)"
        admin.delete(0, 5);        //":)"
        admin.delete(0, 2);        //""

        assertEquals("", member1.getValue());
        assertEquals("", member2.getValue());
        admin.undo();

        assertEquals(":)", member1.getValue());
        admin.undo();

        assertEquals("hello:)", member1.getValue());
        admin.undo();

        assertEquals("hello  :)", member1.getValue());
        assertEquals("hello  :)", member2.getValue());
        admin.undo();

        assertEquals("hello world! :)", member1.getValue());
        assertEquals("hello world! :)", member2.getValue());
    }

    @Test
    public void testNotifyMembers(){
        GroupAdmin admin = new GroupAdmin();
        ConcreteMember member1 = new ConcreteMember();
        ConcreteMember member2 = new ConcreteMember();

        admin.register(member1);
        admin.register(member2);

        admin.append("hello");
        assertEquals("hello", member1.getValue());
        assertEquals("hello", member2.getValue());

        admin.insert(5," world! :)");
        assertEquals("hello world! :)", member1.getValue());
        assertEquals("hello world! :)", member2.getValue());

        admin.delete(5, 12);
        assertEquals("hello :)", member1.getValue());
        assertEquals("hello :)", member2.getValue());

        admin.undo();
        assertEquals("hello world! :)", member1.getValue());
        assertEquals("hello world! :)", member2.getValue());
    }
}
