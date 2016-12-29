package com.example.jacosta.myapplication;

import com.example.jacosta.myapplication.model.SubwayStations;
import com.example.jacosta.myapplication.network.InterwebzLoader;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void subwayAPI_notNull() throws Exception {
        assertNotNull(InterwebzLoader.getSubwayAPI());
    }

}