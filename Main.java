import java.io.EOFException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * @author: Siddharth Notani
 * The main class is used to split the words within the selected sentence.
 * The count and analysis of each word happens in this class.
 * The output of the word count is first sorted by the count in descending order and then alphabetically.
 */

public class Main {

    private String[] wordsArray;                // String array which will contain all the words in the sentence provided
    private String regex = "([.,!?:;\"]|\\)+";  // Regex string used to remove anything which is punctuation and not a word

    /**
     * This method gets each character and word from the sentence in SimpleCharacterReader class,
     * splits the words by spaces and stores them in an array of strings.
     * If the array string element contains anything which is not a letter, it is removed.
     * @param c used to get the methods in the defined interface
     * @param sentence the string that is going to be analyzed
     * @return the array of strings where each element contains each word (free of punctuation and symbols) in the sentence
     * @throws EOFException if there are no more characters to read
     */
    public String[] GetNextWord(ICharacterReader c, String sentence) throws EOFException {

        wordsArray = sentence.split("\\s+");

        for (int i = 0; i < sentence.length(); i++) {
            char next_char = c.GetNextChar();

            if (regex.contains(Character.toString(next_char))) {
                for (int j = 0; j < wordsArray.length; j++)
                    wordsArray[j] = wordsArray[j].replace(Character.toString(next_char), "");
            } else {
                c.Dispose();
            }
        }

        return wordsArray;
    }

    /**
     * Used to pattern match the words in the sentence which are stored in the array of strings.
     * Keep a count of all the words in a HashMap without any repeats.
     * Sort the HasMap firstly by the count in descending order and then alphabetically.
     * @param wordsArray array of string where each word in the sentence is split and stored
     * @param sentence string to be analyzed
     */
    public void WordCounter(String[] wordsArray, String sentence) {

        HashMap<String, Integer> hmap = new HashMap<>();

        for (int i = 0; i < wordsArray.length; i++) {
            Pattern pattern = Pattern.compile("\\b" + wordsArray[i] + "\\b");
            Matcher matcher = pattern.matcher(sentence);

            int count = 0;
            while (matcher.find()) {
                count++;
            }

            hmap.put(wordsArray[i], count);
        }

        SortByCountDescending(SortByAlphabet(hmap));
    }

    /**
     * Sorting method by the value (count) in descending order for the HashMap given.
     * This method compares uses Lists to sort them in descending order.
     * @param hmap HashMap which is going to be sorted by it's values
     */
    public void SortByCountDescending (TreeMap<String, Integer> hmap) {
        List<Map.Entry<String, Integer> > list = new LinkedList<Map.Entry<String, Integer> >(hmap.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        HashMap<String, Integer> finalhmap = new HashMap<String, Integer>();
        for (Map.Entry<String, Integer> entry : list) {
            finalhmap.put(entry.getKey(), entry.getValue());
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }

    }

    /**
     * Sorting method by keys (alphabetical order). Called after the inital HashMap being sorted by it's values (count)
     * It replaces HashMap (unsorted) with a TreeMap, which is sorted naturally by it's keys.
     * @param hmap HashMap that needs sorting on alphabetical order
     * @return sorted TreeMap which is sorted by it's keys on alphabetical order
     */
    public TreeMap<String, Integer> SortByAlphabet (HashMap<String, Integer> hmap) {
        TreeMap<String, Integer> sortedmap = new TreeMap<>();

        sortedmap.putAll(hmap);

        return sortedmap;
    }

    /**
     * Main method: Calls all the classes and methods and prints the output of the program
     * @param args terminal commands
     * @throws EOFException if there are no more characters to read
     */
    public static void main (String[] args) throws EOFException {
        Main m = new Main();
        SimpleCharacterReader s = new SimpleCharacterReader();
        Tests t = new Tests();

        // Test each unit test separately (one by one, comment others out)
        t.unitTest1();
        //t.unitTest2();
        //t.unitTest3();
        //t.unitTest4();
    }
}