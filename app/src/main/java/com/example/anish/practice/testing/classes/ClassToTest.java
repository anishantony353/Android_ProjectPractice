package com.example.anish.practice.testing.classes;

import com.example.anish.practice.testing.TestUtilities;
import com.example.anish.practice.testing.interfaces.ServiceInterface;

/**
 * Created by Anish on 27-03-2018.
 */

public class ClassToTest {
    //ServiceInterface serviceInterface;

//    public ClassToTest(ServiceInterface serviceInterface){
//        this.serviceInterface = serviceInterface;
//    }

//    public List<String> getStrsFromClass(){
//
//        return serviceInterface.getStrsFromService();
//    }

    public int getIncrementedValue(int i){

        return i+1;

    }

    public int[] getIntArray(){

        return new int[]{1,2,3,4};

    }

    public ServiceInterface getServiceInterface(){


        return new ServiceInterface() {// Anonymous Inner Class(Interface)
            @Override
            public String getAbstractStrFromService() {
                return str;
            }
        };
    }

    public TestUtilities getTestUtilitiesClass(){

        return new TestUtilities(){// Anonymous Inner class(Normal Class)

            public String getString(){
                return "hgsdf";
            }

        };
    }



}
