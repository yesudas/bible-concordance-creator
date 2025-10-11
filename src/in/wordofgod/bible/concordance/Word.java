package in.wordofgod.bible.concordance;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//Section model
@JsonIgnoreProperties(ignoreUnknown = true)
public class Word {

	private String word;
	private List<VerseInfo> verses = new ArrayList<>();

	public Word() {
	}

	public Word(String word) {
		super();
		this.word = word;
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
	 * @return the verses
	 */
	public List<VerseInfo> getVerses() {
		return verses;
	}

	/**
	 * @param verses the verses to set
	 */
	public void setVerses(List<VerseInfo> verses) {
		this.verses = verses;
	}

	public void addVerse(VerseInfo verse) {
		this.verses.add(verse);
	}
}