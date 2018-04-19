package com.example.anish.practice.testClasses;

import com.example.anish.practice.testing.classes.ClassToTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
@RunWith(Parameterized.class)
public class ParameterisedUnit_Test {
    static String TAG = "ParameterisedUnit_Test";

    ClassToTest classToTest;

    private int input,expected;

    public ParameterisedUnit_Test(int input, int expected){
        this.input = input;
        this.expected = expected;

    }

    @Parameterized.Parameters
    public static Integer[][] getParametersForTesting(){



        Integer[][] params = {
                {0,1},
                {1,2},
                {2,3},
                {3,4}

        };
        return params;


        //return Arrays.asList(params);
    }



    @Test //all the test under @RunWith(Parametrised.class) runs number of times corresponding to number of parameters
    public void ClassToTest_method_getIncrementedValue(){

        classToTest = new ClassToTest();

        assertEquals(expected,classToTest.getIncrementedValue(input));

    }







}