package com.example.anish.practice.testClasses;

import com.example.anish.practice.testing.classes.ClassToTest;

import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertArrayEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Anish on 29-03-2018.
 */

public class ClassToTest_Test {

    ClassToTest classToTest;

    @Test
    public void ClassToTest_method_getIntArray(){

        classToTest = new ClassToTest();

        assertArrayEquals(new int[]{1,2,3,4},classToTest.getIntArray()); //assertArrayEquals => for comparing arrays


    }

    @Test(timeout = 2000)//milliseconds
    public void checkTimoutTest() throws InterruptedException {

        Thread.sleep(1000);  // PASS
        //Thread.sleep(3000); // FAIL

    }

    @Test(expected = NullPointerException.class)
    public void checkExpectedTest(){

        Object obj = null;            // PASS
        //Object obj = new Object(); // FAIL

        obj.toString();

    }

   /*
    @Test
    public void ClassToTest_getStrs() throws Exception{

        //ServiceImpStub serviceImpStub = new ServiceImpStub(); //Stub Class without Mocking

        ServiceInterface serviceImpStub = mock(ServiceInterface.class);// Stub Class with Mocking
        List<String> strsStub = Arrays.asList("first","second");

        when(serviceImpStub.getStrsFromService()).thenReturn(strsStub);//stub Methods


        ClassToTest classToTest;
        classToTest = new ClassToTest(serviceImpStub);
        List<String> strs = classToTest.getStrsFromClass();

        assertEquals("Number of items does not match",2,strs.size());

    }

    */

    @Test
    public void mockListInterface() throws InterruptedException {


        List listStub = mock(List.class);
        when(listStub.contains(anyObject())).thenReturn(true);//anyObject(),anyInt() etc => Argument Macher

        boolean b = listStub.contains(new Object());
        assertTrue("List can contain any Object",b);
    }


}
