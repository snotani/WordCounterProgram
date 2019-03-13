import java.io.EOFException;

/**
 * @author: Redgate
 * <p> The ICharacterReader Interface defines to methods:
 * GetNextChar() which gets the next character of the selected string and
 * Dispose() which does nothing </p>
 */

public interface ICharacterReader {
	char GetNextChar() throws EOFException;
	void Dispose();
}
