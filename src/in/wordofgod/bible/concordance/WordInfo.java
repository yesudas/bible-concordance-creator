package in.wordofgod.bible.concordance;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//Section model
@JsonIgnoreProperties(ignoreUnknown = true)
public class WordInfo {

	private String word;
	private int versesCount;
	private String file;

	public WordInfo() {
	}

	public WordInfo(String word, int versesCount, String file) {
		super();
		this.word = word;
		this.versesCount = versesCount;
		this.file = file;
	}

	/**
	 * @return the word
	 */
	public String getWord() {
		return word;
	}

	/**
	 * @param word the word to set
	 */
	public void setWord(String word) {
		this.word = word;
	}

	/**
	 * @return the file
	 */
	public String getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(String file) {
		this.file = file;
	}

	/**
	 * @return the versesCount
	 */
	public int getVersesCount() {
		return versesCount;
	}

	/**
	 * @param versesCount the versesCount to set
	 */
	public void setVersesCount(int versesCount) {
		this.versesCount = versesCount;
	}
}