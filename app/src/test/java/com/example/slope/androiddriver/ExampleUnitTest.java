package com.example.slope.androiddriver;

import com.example.slope.androiddriver.utils.DES;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);

        String key = "ZHOU-PC9";//最长8位
        String text = "ExampleUnitTesExampleUnitTestExampleUnitTestExampleUnitTestExampleUnitTestExampleUnitTestt";


        try {
            String result1 = DES.encryptDES(text,key);
            String result2 = DES.decryptDES(result1, key);
            System.out.println("DES encode text is " + result1);
            System.out.print("DES encode text is " + result2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}