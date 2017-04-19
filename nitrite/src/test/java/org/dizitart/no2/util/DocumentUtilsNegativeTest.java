package org.dizitart.no2.util;

import org.dizitart.no2.Document;
import org.dizitart.no2.exceptions.ValidationException;
import org.dizitart.no2.internals.JacksonMapper;
import org.dizitart.no2.internals.NitriteMapper;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.AbstractCollection;

import static org.dizitart.no2.util.DocumentUtils.emptyDocument;
import static org.dizitart.no2.util.DocumentUtils.getFieldValue;
import static org.junit.Assert.assertEquals;

/**
 * @author Anindya Chatterjee.
 */
public class DocumentUtilsNegativeTest {
    private Document doc;

    @Before
    public void setUp() throws IOException {
        NitriteMapper nitriteMapper = new JacksonMapper();
        doc = nitriteMapper.parse("{" +
                "  score: 1034," +
                "  location: {  " +
                "       state: 'NY', " +
                "       city: 'New York', " +
                "       address: {" +
                "            line1: '40', " +
                "            line2: 'ABC Street', " +
                "            house: ['1', '2', '3'] " +
                "       }" +
                "  }," +
                "  category: ['food', 'produce', 'grocery'], " +
                "  objArray: [{ value: 1}, {value: 2}]" +
                "}");
    }

    @Test(expected = ValidationException.class)
    public void testGetValueFailure() {
        assertEquals(getFieldValue(doc, "score.test"), 1034);
    }

    @Test(expected = ValidationException.class)
    public void testGetValueInvalidIndex() {
        assertEquals(getFieldValue(doc, "category.3"), "grocery");
    }

    @Test(expected = ValidationException.class)
    public void testGetValueObjectArray() {
        assertEquals(getFieldValue(doc, "objArray.0.value"), 1);
    }

    @Test(expected = ValidationException.class)
    public void testGetValueInvalidKey() {
        assertEquals(getFieldValue(doc, "."), 1);
    }

    @Test(expected = ValidationException.class)
    public void testEmptyDocumentForInterface() {
        emptyDocument(new JacksonMapper(), Comparable.class);
    }

    @Test(expected = ValidationException.class)
    public void testEmptyDocumentForPrimitive() {
        emptyDocument(new JacksonMapper(), int.class);
    }

    @Test(expected = ValidationException.class)
    public void testEmptyDocumentForArray() {
        emptyDocument(new JacksonMapper(), String[].class);
    }

    @Test(expected = ValidationException.class)
    public void testEmptyDocumentForAbstractClass() {
        emptyDocument(new JacksonMapper(), AbstractCollection.class);
    }
}
