package io.data_stream;

import java.io.*;

public class Test {
    public static void main(String[] args) {
        Person tom = new Person("Tom", 35, 1.75, true);

        try (DataOutputStream dos = new DataOutputStream(
                new FileOutputStream("src\\main\\java\\io\\data_stream\\file.txt"))
        ) {
            dos.writeUTF(tom.name);
            dos.writeInt(tom.age);
            dos.writeDouble(tom.height);
            dos.writeBoolean(tom.married);
            System.out.println("Запись в файл произведена");
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        }

        try (DataInputStream dos = new DataInputStream(
                new FileInputStream("src\\main\\java\\io\\data_stream\\file.txt"))
        ) {
            String name = dos.readUTF();
            int age = dos.readInt();
            double height = dos.readDouble();
            boolean married = dos.readBoolean();
            System.out.printf("Человека зовут: %s , его возраст: %d , его рост: %f метров, женат/замужем: %b",
                    name, age, height, married);
        } catch(IOException ex) {

            System.out.println(ex.getMessage());
        }
    }
    static class Person {
        public String name;
        public int age;
        public double height;
        public boolean married;

        public Person(String n, int a, double h, boolean m) {
            this.name=n;
            this.height=h;
            this.age=a;
            this.married=m;
        }
    }
}


