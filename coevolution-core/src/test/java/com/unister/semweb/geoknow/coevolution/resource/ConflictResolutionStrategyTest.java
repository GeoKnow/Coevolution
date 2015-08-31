package com.unister.semweb.geoknow.coevolution.resource;

import static org.junit.Assert.*;

import org.junit.Test;

public class ConflictResolutionStrategyTest {

    @Test
    public void test() {
        assertEquals(ConflictResolutionStrategy.IgnoreEntireChange, ConflictResolutionStrategy.valueOf("IgnoreEntireChange"));
    }

}
