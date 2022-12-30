package observer;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

public class TestEfficency {
    public static final Logger logger = LoggerFactory.getLogger(Test.class);

    @Test
    public void testEfficency(){
        GroupAdmin admin = new GroupAdmin();
        ConcreteMember member1 = new ConcreteMember();
        ConcreteMember member2 = new ConcreteMember();

        //check memory size of each object 
        logger.info(()->"\nmember 1 : " + JvmUtilities.objectTotalSize(member1)
                    + "\nmember 2 : " + JvmUtilities.objectTotalSize(member2)
                    + "\nadmin    : " + JvmUtilities.objectTotalSize(admin) + "\n");

        //registe mem1 to admin and check again
        admin.register(member1); 
        logger.info(()->"\nafter adding mem1 : " + JvmUtilities.objectTotalSize(admin));
        
        //register mem2 to admin and check again
        admin.register(member2);  
        logger.info(()->"\nafter adding mem2 : " + JvmUtilities.objectTotalSize(admin));

        //show admin foorprint
        logger.info(()->"\n\nadmin footprint : " + JvmUtilities.objectFootprint(admin));

        //check if adding objects that are already observing the admin
        //will/ wont change the admins total objest size, bcs the same 
        //obj shouldnt be registered twice
        admin.register(member1);
        admin.register(member2);
        logger.info(()->JvmUtilities.objectTotalSize(admin));

        //check if adding objects that are already observing the admin
        //will/ wont change the admins total objest size, bcs the same 
        //obj shouldnt be registered twice [total = 680]
        admin.register(member1);
        admin.register(member2);
        logger.info(()->JvmUtilities.objectTotalSize(admin));

        //check if admin will go back to its original size
        //it took less space [512]
        admin.unregister(member1);
        admin.unregister(member2);
        logger.info(()->"admin aftrer unregistered : " + JvmUtilities.objectTotalSize(admin));
        
        //check if admins memory usage will stabilize [512]
        admin.register(member1);
        admin.register(member2);
        logger.info(()->"admin aftrer registered : " + JvmUtilities.objectTotalSize(admin));
        //check if admins memory usage will stabilize [312]
        admin.unregister(member1);
        admin.unregister(member2);
        logger.info(()->"admin aftrer unregistered : " + JvmUtilities.objectTotalSize(admin));
        //check if admins memory usage will stabilize [512]
        admin.register(member1);
        admin.register(member2);
        logger.info(()->"admin aftrer registered : " + JvmUtilities.objectTotalSize(admin));

        //check if append acts similarly on the admins and members memory
        logger.info(()->"\nadmin before append : " + JvmUtilities.objectTotalSize(admin) 
                    + "\nmember1 before append : " + JvmUtilities.objectTotalSize(member1) 
                    + "\nmember1 before append : " + JvmUtilities.objectTotalSize(member2));

        admin.append("hello world");

        logger.info(()->"\nadmin after append : " + JvmUtilities.objectTotalSize(admin) 
                    + "\nmember1 after append : " + JvmUtilities.objectTotalSize(member1) 
                    + "\nmember1 after append : " + JvmUtilities.objectTotalSize(member2));

        admin.undo();

        logger.info(()->"\nadmin after undo : " + JvmUtilities.objectTotalSize(admin) 
                    + "\nmember1 after undo : " + JvmUtilities.objectTotalSize(member1) 
                    + "\nmember1 after undo : " + JvmUtilities.objectTotalSize(member2));
        //memeber total memory went from 224 append-> 280 undo-> 224
    }
}
