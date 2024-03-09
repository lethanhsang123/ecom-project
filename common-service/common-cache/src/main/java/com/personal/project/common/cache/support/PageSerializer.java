package com.personal.project.common.cache.support;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.data.domain.PageImpl;

import java.io.IOException;

public class PageSerializer extends StdSerializer<PageImpl> {


    public PageSerializer() {
        super(PageImpl.class);
    }

    @Override
    public void serialize(PageImpl page, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("number", page.getNumber());
        jsonGenerator.writeNumberField("numberOfElements", page.getNumberOfElements());
        jsonGenerator.writeNumberField("totalElements", page.getTotalElements());
        jsonGenerator.writeNumberField("totalPages", page.getTotalPages());
        jsonGenerator.writeNumberField("size", page.getSize());
        jsonGenerator.writeFieldName("content");
        serializerProvider.defaultSerializeValue(page.getContent(), jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
