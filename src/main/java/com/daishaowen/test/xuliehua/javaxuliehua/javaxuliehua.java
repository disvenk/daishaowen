package com.daishaowen.test.xuliehua.javaxuliehua;

import java.io.*;

public class javaxuliehua {

    //序列化
    private static byte[] serailize(Teacher t) throws IOException {
        //序列化
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(t);
        byte[] bytes = out.toByteArray();
        return bytes;
    }

    //反序列化
    private static Teacher deserailize(byte[] bytes) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
        Teacher t = (Teacher) ois.readObject();;
        return t;
    }
}
