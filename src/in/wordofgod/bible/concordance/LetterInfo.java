package in.wordofgod.bible.concordance;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//Model class for each word entry
@JsonIgnoreProperties(ignoreUnknown = true)
class LetterInfo {

	private String letter;
	private int wordsCount;
	private String file;

	// Constructors
	public LetterInfo() {
	}

	public LetterInfo(String letter, int wordsCount, String file) {
		super();
		this.letter = letter;
		this.wordsCount = wordsCount;
		this.file = file;
	}

	/**
	 * @return the letter
	 */
	public String getLetter() {
		return letter;
	}

	/**
	 * @param letter the letter to set
	 */
	public void setLetter(String letter) {
		this.letter = letter;
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
	 * @return the wordsCount
	 */
	public int getWordsCount() {
		return wordsCount;
	}

	/**
	 * @param wordsCount the wordsCount to set
	 */
	public void setWordsCount(int wordsCount) {
		this.wordsCount = wordsCount;
	}
}