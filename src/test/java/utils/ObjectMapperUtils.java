package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperUtils {
    // We create this method for Deserialization we will convert the response
    // into JsonPlaceHolder using this reuseble method.This method will return any type of data
    public static <T> T convertJsonToJava(String json,Class<T> tClass){// This is Generic Method
        // That means Generic will return any data type that mean we can use any data type
        // This method will accept Json as String and converted into any data type
        try {
            return new ObjectMapper().readValue(json,tClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
