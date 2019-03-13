import java.io.EOFException;

/**
 * @author Siddharth Notani
 * This class contains unit tests for the program.
 */

public class Tests {

    public static final char lf = '\n';

    Main m = new Main();
    SimpleCharacterReader s = new SimpleCharacterReader();

    public void unitTest1() throws EOFException {
        m.WordCounter(m.GetNextWord(s, s.getM_Content()), s.getM_Content());
    }

    public void unitTest2() throws EOFException {
        String bio = "Hello, hello!. My name is Siddharth Notani, and I'm a Second Year Software Engineering (with Industrial Experience) student at Lancaster University?";
        s.setM_Content(bio);
        m.WordCounter(m.GetNextWord(s, s.getM_Content()), s.getM_Content());
    }

    public void unitTest3() throws EOFException {
        String text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " + lf
                + "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. " + lf
                + "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. " + lf
                + "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
        s.setM_Content(text);
        m.WordCounter(m.GetNextWord(s, s.getM_Content()), s.getM_Content());
    }

    public void unitTest4() throws EOFException {
        String repeated = "I enjoy team-work. Team-work, team-work and only team-work. [Team-work and Leadership] and and and and and " + lf
                + "a a a lot lot lot of of of innovation innovation innovation along along along with with with hard-work hard-work hard-work" + lf
                + "also also also obviously obviously obviously testing testing testing";
        s.setM_Content(repeated);
        m.WordCounter(m.GetNextWord(s, s.getM_Content()), s.getM_Content());
    }
}