package com.deloitte.platform.api.utils;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class JsonLongSerialize extends JsonSerializer<Long> {
    @Override
    public void serialize(Long o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        serializerProvider.defaultSerializeValue(o==null?"":o.toString(),jsonGenerator);

    }
}
