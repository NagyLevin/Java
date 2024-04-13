package hu.ppke.itk.java.serialization;

import java.io.*;
import java.util.Date;

public class WriteSerialized {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        FileOutputStream f = new FileOutputStream("tmp2");
//        ObjectOutput s = new ObjectOutputStream(f);
//        MyClass m = new MyClass();
//        m.s = "Hello world!";
//        m.i = 42;
//        s.writeObject(m);
//        s.flush();

        FileInputStream in = new FileInputStream("tmp2");
        ObjectInputStream s2 = new ObjectInputStream(in);
        MyClass m2 = (MyClass) s2.readObject();
        System.out.println(m2);
    }

    public static class MyClass implements Serializable {
        private static final long serialVersionUID = 1L;
        String s;
        Integer i;

        @Override
        public String toString() {
            return "MyClass{" +
                    "s='" + s + '\'' +
                    ", i=" + i +
                    '}';
        }
    }
}
