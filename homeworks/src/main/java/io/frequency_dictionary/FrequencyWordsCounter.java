package io.frequency_dictionary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.TreeMap;

/**
 * Created by Serezha on 14.08.17.
 * Frequency Dictionary v.0.1
 * without regular expression
 */
public class FrequencyWordsCounter {
    private String pathToFile;
    private TreeMap<String, Integer> words;
    private TreeMap<String, Integer> sortedWords;

    public FrequencyWordsCounter(String pathToFile) {
        this.pathToFile = pathToFile;
        this.words = new TreeMap<>();
        readAndSplit();
    }

    private void readAndSplit() {
        File file = new File(pathToFile);
        try (FileReader fileReader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            //All files .srt have two lines in the begin (number and timing line)
            //Ok, lets start from line 3
            bufferedReader.readLine();
            bufferedReader.readLine();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                //words to lower case
                line = line.toLowerCase();
                // break from number and time line
                if (line.length() == 0) {
                    bufferedReader.readLine();
                    bufferedReader.readLine();
                }
                //Split words
                String[] splitWords = line.split(" ");
                //Clear words
                splitWords = clearSplitWords(splitWords);
                //add words to TreeMap
                addWords(splitWords);
            }
            //here sorted words by ComparatorByValue();
            sortWords();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addWords(String[] splitWords) {
        for (int index = 0; index < splitWords.length; index++) {
            if (!splitWords[index].isEmpty()) {
                if (words.containsKey(splitWords[index])) {
                    words.put(splitWords[index], words.get(splitWords[index]) + 1);
                } else {
                    words.put(splitWords[index], 1);
                }
            }
        }
    }

    private String[] clearSplitWords(String[] words) {
        for (int index = 0; index < words.length; index++) {
            //in begin word
            if (words[index].startsWith("<i>")) {
                words[index] = words[index].substring(3, words[index].length());
                clearSplitWords(words);
            }
            if (words[index].startsWith("- ")) {
                words[index] = words[index].substring(2, words[index].length());
                clearSplitWords(words);
            }
            if (words[index].startsWith("-")) {
                words[index] = words[index].substring(1, words[index].length());
                clearSplitWords(words);
            }
            if (words[index].startsWith("\"") || words[index].startsWith("\'")) {
                words[index] = words[index].substring(1, words[index].length());
                clearSplitWords(words);
            }
            if (words[index].startsWith("(") || words[index].startsWith(".")) {
                words[index] = words[index].substring(1, words[index].length());
                clearSplitWords(words);
            }
            //in the end word
            if (words[index].endsWith("\"") || words[index].endsWith("\'")) {
                words[index] = words[index].substring(0, words[index].length() - 1);
                clearSplitWords(words);
            }
            if (words[index].endsWith(",") || words[index].endsWith(".")) {
                words[index] = words[index].substring(0, words[index].length() - 1);
                clearSplitWords(words);
            }
            if (words[index].endsWith(":") || words[index].endsWith(";")) {
                words[index] = words[index].substring(0, words[index].length() - 1);
                clearSplitWords(words);
            }
            if (words[index].endsWith("!") || words[index].endsWith("?")) {
                words[index] = words[index].substring(0, words[index].length() - 1);
                clearSplitWords(words);
            }
            if (words[index].endsWith(".") || words[index].endsWith(")")) {
                words[index] = words[index].substring(0, words[index].length() - 1);
                clearSplitWords(words);
            }
            if (words[index].endsWith("</i>")) {
                words[index] = words[index].substring(0, words[index].length() - 4);
                clearSplitWords(words);
            }

        }
        return words;
    }

    private void sortWords() {
        sortedWords = new TreeMap<>(new ComparatorByValue(words));
        sortedWords.putAll(words);
    }

    public TreeMap<String, Integer> getTreeMapResult() {
        return sortedWords;
    }

    public int numberUniqueWords() {
        return sortedWords.size();
    }

    @Override
    public String toString() {
        return "FrequencyWordsCounter{" +
                "UniqueWords=" + sortedWords +
                '}';
    }

    private class ComparatorByValue implements Comparator<String> {
        private TreeMap<String, Integer> unsortedMap;

        private ComparatorByValue(TreeMap<String, Integer> unsortedMap) {
            this.unsortedMap = unsortedMap;
        }

        @Override
        public int compare(String o1, String o2) {
            int value1 = this.unsortedMap.get(o1);
            int value2 = this.unsortedMap.get(o2);
            if (value1 < value2) {
                return 1;
            } else {
                return -1;
            }
        }
    }
}
