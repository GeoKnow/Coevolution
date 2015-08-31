package com.unister.semweb.geoknow.coevolution.rdf;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RdfUtilsTest {

    @Test
    public void testConvertIso8601IntoSimpleFormat() {
        assertEquals("2015-05-21T11:53:56+0000", RdfUtils.convertIso8601IntoSimpleFormat("2015-05-21T11:53:56+00:00"));
        assertEquals("2015-05-21T11:53:56+0000", RdfUtils.convertIso8601IntoSimpleFormat("2015-05-21T11:53:56Z"));
        assertEquals("2015-05-21T11:53:56+0330", RdfUtils.convertIso8601IntoSimpleFormat("2015-05-21T11:53:56+03:30"));
        assertEquals("2015-05-21T11:53:56-0330", RdfUtils.convertIso8601IntoSimpleFormat("2015-05-21T11:53:56-03:30"));
        assertEquals("2015-05-21T11:53:56", RdfUtils.convertIso8601IntoSimpleFormat("2015-05-21T11:53:56"));
    }

}
