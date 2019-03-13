import java.io.EOFException;
import java.util.Random;

/**
 * @author: Redgate, edited by Siddharth Notani
 * The SimpleCharacter Reader class implements the ICharacterReader interface.
 * <p> This class is mainly used to get and set the string that the user wants the program to be run on.</p>
 */

public class SimpleCharacterReader implements ICharacterReader {
	private int m_Pos = 0;

	public static final char lf = '\n';

	private String m_Content = "It was the best of times, it was the worst of times," + 
	lf +
	"it was the age of wisdom, it was the age of foolishness," + 
	lf +
	"it was the epoch of belief, it was the epoch of incredulity," + 
	lf +
	"it was the season of Light, it was the season of Darkness," + 
	lf +
	"it was the spring of hope, it was the winter of despair," + 
	lf +
	"we had everything before us, we had nothing before us," + 
	lf +
	"we were all going direct to Heaven, we were all going direct" + 
	lf +
	"the other way--in short, the period was so far like the present" + 
	lf +
	"period, that some of its noisiest authorities insisted on its" + 
	lf +
	"being received, for good or for evil, in the superlative degree" + 
	lf +
	"of comparison only." + 
	lf +
	"There were a king with a large jaw and a queen with a plain face," + 
	lf +
	"on the throne of England; there were a king with a large jaw and" + 
	lf +
	"a queen with a fair face, on the throne of France.  In both" + 
	lf +
	"countries it was clearer than crystal to the lords of the State" + 
	lf +
	"preserves of loaves and fishes, that things in general were" + 
	lf +
	"settled for ever";

	Random m_Rnd = new Random();

	/**
	 * This method is used to loop through the string and get each individual character from it.
	 * @return character at which the string is pointing
	 * @throws EOFException if there are no more characters to read
	 */
	public char GetNextChar() throws EOFException {

		if (m_Pos >= m_Content.length()) {
			throw new EOFException();
		}

		return m_Content.charAt(m_Pos++);

	}

	/**
	 * This method does nothing but must be called after using the ICharacterReader interface
	 */
	public void Dispose() {
		// do nothing
	}

	/**
	 * Getter method for the string on which the program will run and will be analyzed.
	 * @return m_Content is the string which is passed to lower case and can be used by other classes
	 */
	public String getM_Content() {
		return m_Content.toLowerCase();
	}

	/**
	 * Setter method to test the program with different strings.
	 * @param newContent the new String which the user wants to run the program on.
	 */
	public void setM_Content(String newContent) {
		this.m_Content = newContent;
	}
}
