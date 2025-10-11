package in.wordofgod.bible.concordance;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//Model class for each word entry
@JsonIgnoreProperties(ignoreUnknown = true)
class Letter {

	private String letter;
	private List<WordInfo> words = new ArrayList<>();

	// Constructors
	public Letter() {
	}

	public Letter(String letter) {
		super();
		this.letter = letter;
	}

	public Letter(String letter, List<WordInfo> words) {
		super();
		this.letter = letter;
		this.words = words;
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
	 * @return the words
	 */
	public List<WordInfo> getWords() {
		return words;
	}

	/**
	 * @param words the words to set
	 */
	public void setWords(List<WordInfo> words) {
		this.words = words;
	}

	public void addWord(WordInfo word) {
		this.words.add(word);
	}
}