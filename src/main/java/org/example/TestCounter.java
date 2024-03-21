package org.example;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestCounter {
    @Test
    public void wordCounterTest() {
        String text = "Hello from Kazakhstan";
        int expRes = 3;
        assertEquals(expRes, Database.wordCountDb(text));
    }
}
