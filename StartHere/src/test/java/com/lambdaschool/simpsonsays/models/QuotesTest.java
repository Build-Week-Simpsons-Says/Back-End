package com.lambdaschool.simpsonsays.models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class QuotesTest {

    Quotes testQuote1;

    @Before
    public void setUp() throws Exception {
        User testUser1 = new User();
        testQuote1 = new Quotes("Eat my shorts", testUser1);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getQuotesid() {
        assertNotNull(testQuote1.getQuotesid());
    }

    @Test
    public void setQuotesid() {
        testQuote1.setQuotesid(8);
        assertEquals(8, testQuote1.getQuotesid());
    }

    @Test
    public void getQuote() {
        assertEquals("Eat my shorts", testQuote1.getQuote());
    }

    @Test
    public void setQuote() {
        testQuote1.setQuote("Boy, everyone is stupid except me.");
        assertEquals("Boy, everyone is stupid except me.", testQuote1.getQuote());
    }
}