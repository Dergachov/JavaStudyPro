package io.serializable;

import java.io.*;

/**
 * Сериализация это процесс сохранения состояния объекта в последовательность байт;
 * Десериализация это процесс восстановления объекта, из этих байт.
 */
public class Main {
    public static void main(String[] args) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream("io\\src\\main\\java\\serializable\\temp.txt");
            oos = new ObjectOutputStream(fos);
            User user = new User("User", 23);
            oos.writeObject(user);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileInputStream fis = null;
        ObjectInputStream oin = null;
        try {
            fis = new FileInputStream("io\\src\\main\\java\\serializable\\temp.txt");
            oin = new ObjectInputStream(fis);

            User user = (User) oin.readObject();
            System.out.println(user.getName());
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    static class User implements Serializable{
        private String name;
        private int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }
}
