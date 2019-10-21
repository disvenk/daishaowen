package com.daishaowen.test.xuliehua.protobuf;

import com.daishaowen.test.xuliehua.javaxuliehua.Teacher;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.runtime.RuntimeSchema;

public class TecaherSerailizeUtils {
    private static RuntimeSchema<Teacher> schema = RuntimeSchema.createFrom(Teacher.class);

    public static byte[] serialize(Teacher t) {
        // Serializes the {@code message} into a byte array using the given schema
        return ProtostuffIOUtil.toByteArray(t, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
    }

    public static Teacher deserialize(byte[] array) {
        Teacher t = schema.newMessage();
        // Merges the {@code message} with the byte array using the given {@code schema}
        ProtostuffIOUtil.mergeFrom(array, t, schema);
        return t;
    }
}
