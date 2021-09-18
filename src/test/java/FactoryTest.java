

import org.testng.annotations.Factory;

//using @Factory
public class FactoryTest {
    @Factory
    public Object[] factoryMethod() {

        return new Object [] {
                //Each parameter should receive 3 different values
                new ReadExcel(0),
                new ReadExcel(1),
                new ReadExcel(2)
        } ;
    }
}

