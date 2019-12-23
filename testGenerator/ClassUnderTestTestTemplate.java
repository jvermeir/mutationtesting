package x;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClassUnderTestTestTemplate {

    @Test
    public void testMyTestMethod() {
        x.ClassUnderTestTemplate classUnderTestTemplate = new x.ClassUnderTestTemplate();
        assertEquals(4, classUnderTestTemplate.someMethod(4, 2));
        assertEquals(5, classUnderTestTemplate.someMethod(3, 5));
    }

}
