package com.epam.ta.AssertionExtension;

import org.testng.Assert;

public class Verification {

    private StringBuffer errorBuffer;

    public void verifyTrue(boolean actual) {
        errorBuffer = new StringBuffer();
        try {
            Assert.assertTrue(actual);
        } catch (AssertionError e) {
            errorBuffer.append(e.getMessage());
        }
        if (errorBuffer.length() > 0) {
            throw new AssertionError(errorBuffer.toString());
        }
    }

    public void verifyEquals(String expected, String actual) {
        errorBuffer = new StringBuffer();
        try {
            Assert.assertEquals(expected, actual);
        } catch (AssertionError e) {
            errorBuffer.append(e.getMessage());
        }
        if (errorBuffer.length() > 0) {
            throw new AssertionError(errorBuffer.toString());
        }
    }


}
