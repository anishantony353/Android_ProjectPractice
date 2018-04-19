package com.example.anish.practice.testSuites;

import com.example.anish.practice.testClasses.ClassToTest_Test;
import com.example.anish.practice.testClasses.EmptyTest_Test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Anish on 29-03-2018.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ClassToTest_Test.class, EmptyTest_Test.class})
public class Suite_NonParametisedTest {
}
