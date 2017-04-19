package org.dizitart.no2.internals;

import org.dizitart.no2.Document;
import org.dizitart.no2.exceptions.ObjectMappingException;

/**
 * Represents an object mapper for nitrite database. It
 * converts an object into a Nitrite {@link Document}.
 *
 * @author Anindya Chatterjee.
 * @since 1.0
 */
public interface NitriteMapper {
    /**
     * Converts and `object` to a {@link Document}.
     *
     * @param <T>    the type parameter
     * @param object the object
     * @return the document
     * @throws ObjectMappingException if circular reference found.
     */
    <T> Document asDocument(T object);

    /**
     * Tries to convert a `document` to an object of type `T`.
     *
     * @param <T>      the type parameter
     * @param document the document
     * @param type     the type of the object
     * @return the object
     * @throws IllegalArgumentException if conversion fails due to incompatible type.
     * @throws ObjectMappingException   if no public parameter-less constructor found.
     */
    <T> T asObject(Document document, Class<T> type);

    /**
     * Determines if an object would be stored as a value type.
     *
     * @param object the object to check
     * @return `true` of `object` would be stored as a value type; `false` otherwise.
     */
    boolean isValueType(Object object);

    /**
     * Tries to convert an `object` to a value type, which will be stored in
     * the document.
     * 
     * [icon="{@docRoot}/note.png"]
     * NOTE: As an example, a {@link java.util.Date} object is stored
     * as a {@link java.lang.Long} value in the document. This operation
     * will return the long value of a {@link java.util.Date} object.
     *
     * @param object the object to convert
     * @return the object as a value type.
     */
    Object asValue(Object object);

    /**
     * Parses a json string into a nitrite {@link Document}.
     *
     * @param json the json string to parse
     * @return the document
     */
    Document parse(String json);

    /**
     * Serializes an object to a json string
     *
     * @param object the object
     * @return the json string
     */
    String toJson(Object object);
}
