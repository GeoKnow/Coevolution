package com.unister.semweb.geoknow.coevolution.rdf;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.lang.StringUtils;

public class RdfUtils {

    public static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd'T'HH:mm:ssZ";
    
    /**
     * Formatter with already applied pattern and UTC time zone to correctly format and parse the
     * {@link #getCreatedDate() createdDate} of a changeset.
     */
    public static final SimpleDateFormat CREATED_DATE_FORMAT = new UtcSimpleDateFormat(DATE_FORMAT_PATTERN);

    /**
     * Extension of the {@link SimpleDateFormat} with UTC set as time zone.
     */
    static class UtcSimpleDateFormat extends SimpleDateFormat {

        private static final long serialVersionUID = 4061200409639155907L;

        /**
         * Creates a new instance and sets the time zone to UTC.
         * 
         * @see SimpleDateFormat#SimpleDateFormat(String)
         */
        public UtcSimpleDateFormat(String pattern) {
            super(pattern);
            super.setTimeZone(TimeZone.getTimeZone("UTC"));
        }
    }

    /**
     * converts a ISO8601 date (xsd:dateTime) into simple date format (sort of supporting the X in the format for Java
     * 6)
     * 
     * @return string matching the {@link #CREATED_DATE_FORMAT}
     */
    public static final String convertIso8601IntoSimpleFormat(String iso8601date) {
        if (iso8601date.matches(".*[+-]\\d\\d:\\d\\d$")) {
            String endDigits = StringUtils.substring(iso8601date, -2);
            iso8601date = StringUtils.substringBeforeLast(iso8601date, ":").concat(endDigits);
        }
        return iso8601date.replaceAll("Z$", "+0000");
    }

    /**
     * converts a simple date into a ISO8601 date (xsd:dateTime) (sort of supporting the X in the format for Java
     * 6)
     * 
     * @return string matching the ISO8601. X (e.g. +00:00) will be converted to +0000, not Z.
     */
    public static final String convertSimpleFormatIntoIso8601(String simpleDate) {
        if (simpleDate.matches(".*[+-]\\d\\d\\d\\d$")) {
            String endDigits = StringUtils.substring(simpleDate, -2);
            simpleDate = StringUtils.removeEnd(simpleDate, endDigits).concat(":").concat(endDigits);
        }
        return simpleDate;
    }

    /**
     * @return a Date from ISO8601 date string
     * @throws ParseException
     */
    public static final Date parseIso8601Date(String iso8601date) throws ParseException {
        return RdfUtils.CREATED_DATE_FORMAT.parse(
                convertIso8601IntoSimpleFormat(iso8601date));
    }

    /**
     * @return a Date from ISO8601 date string
     * @throws ParseException
     */
    public static final String formatIso8601Date(Date date) {
        return convertSimpleFormatIntoIso8601(RdfUtils.CREATED_DATE_FORMAT.format(date));
    }
}
