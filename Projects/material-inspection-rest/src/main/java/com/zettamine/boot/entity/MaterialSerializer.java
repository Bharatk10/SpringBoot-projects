package com.zettamine.boot.entity;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.zettamine.boot.entity.Material;

import java.io.IOException;

public class MaterialSerializer extends StdSerializer<Material> {

   
	private static final long serialVersionUID = 1L;

	public MaterialSerializer() {
        this(null);
    }

    public MaterialSerializer(Class<Material> t) {
        super(t);
    }

    @Override
    public void serialize(Material material, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("materialId", material.getMaterialId());
        jsonGenerator.writeStringField("desc", material.getDesc());
        jsonGenerator.writeStringField("materialType", material.getMaterialType());
        jsonGenerator.writeObjectField("status", material.getStatus());

        // Serialize materialInspection only if it's not empty
        if (!material.getMatInsp().isEmpty()) {
            jsonGenerator.writeObjectField("matInsp", material.getMatInsp());
        }

        jsonGenerator.writeEndObject();
    }
}

