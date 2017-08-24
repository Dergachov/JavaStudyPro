package io.frequency_dictionary;

public class FWordsDemo {
    public static void main(String[] args) {
        String path = "/home/serezha/IdeaProjects/JavaStudyPro/homeworks/src/main/java/io/frequency_dictionary/LOR1.srt";
        FWords fWords = new FWords(path);
        System.out.println("Have a unique words: " + fWords.numberUniqueWords());
        System.out.println("Print words:\n" + fWords.toString());
    }
}
