package com.deloitte.platform.api.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class JsonLongDeserializer extends JsonDeserializer {
    @Override
    public Long deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String str = node.asText();
        if(str!=null&&!"".equals(str)&&str.matches("^[-\\+]?[\\d]*$")){
            return Long.valueOf(str);
        }else {
            return null;
        }
    }
}
