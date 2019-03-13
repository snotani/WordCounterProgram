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
 * The main class
 */

public class Main {

    private String[] wordsArray;
    private String regex = "([.,!?:;\"]|\\)+";


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

    public void WordCounter(String[] wordsArray, String sentence) {

        HashMap<String, Integer> hmap = new HashMap<>();

        for (int i = 0; i < wordsArray.length; i++) {
            Pattern pattern = Pattern.compile(wordsArray[i]);
            Matcher matcher = pattern.matcher(sentence);

            int count = 0;
            while (matcher.find()) {
                count++;
            }

            hmap.put(wordsArray[i], count);
        }

        SortByCountDescending(SortByAlphabet(hmap));
    }

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

    public TreeMap<String, Integer> SortByAlphabet (HashMap<String, Integer> hmap) {
        TreeMap<String, Integer> sortedmap = new TreeMap<>();

        sortedmap.putAll(hmap);

        return sortedmap;
    }


    public static void main (String[] args) throws EOFException {
        Main m = new Main();
        SimpleCharacterReader s = new SimpleCharacterReader();

        m.WordCounter(m.GetNextWord(s, s.getM_Content()), s.getM_Content());
    }
}