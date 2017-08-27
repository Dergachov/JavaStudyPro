package io.frequency_dictionary;

public class FrequencyWordsCounterDemo {
    public static void main(String[] args) {
        String path = "/home/serezha/IdeaProjects/JavaStudyPro/homeworks/src/main/java/io/frequency_dictionary/LOR1.srt";
        FrequencyWordsCounter frequencyWordsCounter = new FrequencyWordsCounter(path);
        System.out.println("Have a unique words: " + frequencyWordsCounter.numberUniqueWords());
        System.out.println("Print words:\n" + frequencyWordsCounter.toString());
    }
}
