package to.be.excluded;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MyExcludedClassTest {

    public MyExcludedClassTest() {};

    @Test
    public void testMyTestMethod() {
        MyExcludedClass myClass = new MyExcludedClass();
        assertTrue(myClass.myTestMethod(100));
        assertFalse(myClass.myTestMethod(0));
    }

}
