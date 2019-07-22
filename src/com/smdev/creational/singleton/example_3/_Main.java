package com.smdev.creational.singleton.example_3;

import java.io.*;

public class _Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Enum Singleton:");
        serializeAndDeserialize(SerializedEnumSingleton.INSTANCE);

        System.out.println();

        System.out.println("Classic Singleton:");
        serializeAndDeserialize(SerializedClassicSingleton.getInstance());
    }

    private static void serializeAndDeserialize(Serializable serializableOne) throws Exception {
        // serialize it
        ObjectOutput out = new ObjectOutputStream(new FileOutputStream(
                "filename.ser"));
        out.writeObject(serializableOne);
        out.close();

        //deserialize from file to object
        ObjectInput in = new ObjectInputStream(new FileInputStream(
                "filename.ser"));
        Serializable serializableOneTwo = (Serializable) in.readObject();
        in.close();

        System.out.println("singleton hashCode=" + serializableOne.hashCode());
        System.out.println("deserialized singleton hashCode=" + serializableOneTwo.hashCode());
    }
}
