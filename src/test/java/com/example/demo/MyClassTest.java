package com.example.demo;

import org.junit.Test;

import static org.junit.Assert.*;

public class MyClassTest {

    public MyClassTest() {};

    @Test
    public void testMyTestMethod() {
        MyClass myClass = new MyClass();
        assertTrue(myClass.myTestMethod(100));
        assertFalse(myClass.myTestMethod(0));
    }

}
