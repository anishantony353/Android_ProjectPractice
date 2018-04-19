package com.example.anish.practice.testClasses;

import com.example.anish.practice.testing.TestUtilities;
import com.example.anish.practice.testing.classes.ClassToTest;
import com.example.anish.practice.testing.interfaces.ServiceInterface;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Anish on 29-03-2018.
 */

public class EmptyTest_Test {


    @Test
    public void testAnnonymousInnerClassUsingInterface(){

        ClassToTest classToTest = new ClassToTest();

        ServiceInterface serviceInterface = classToTest.getServiceInterface();

        Assert.assertEquals("dummy",serviceInterface.getAbstractStrFromService());

    }

    @Test
    public void testAnnonymousInnerClassUsingNormalClass(){

        ClassToTest classToTest = new ClassToTest();

        TestUtilities testUtilities = classToTest.getTestUtilitiesClass();

        //Assert.assertEquals("TestUtilitiesStr",testUtilities.getString());


        //Mockito.verify(testUtilities,Mockito.atMost(3)).getString();

    }

}
