package herokuapp_smoketest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
        // This class will run the classes below in this order
        C01_CreateBooking.class,
        C02_UpdateBooking.class,
        C03_ReadBooking.class


        })

public class Runner {

}
