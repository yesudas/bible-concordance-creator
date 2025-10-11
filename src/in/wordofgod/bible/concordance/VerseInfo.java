package in.wordofgod.bible.concordance;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//Section model
@JsonIgnoreProperties(ignoreUnknown = true)
public class VerseInfo {
	
	private String verse;
	private String reference;

	public VerseInfo() {
	}

	public VerseInfo(String verse, String reference) {
		super();
		this.verse = verse;
		this.reference = reference;
	}

	/**
	 * @return the verse
	 */
	public String getVerse() {
		return verse;
	}

	/**
	 * @param verse the verse to set
	 */
	public void setVerse(String verse) {
		this.verse = verse;
	}

	/**
	 * @return the reference
	 */
	public String getReference() {
		return reference;
	}

	/**
	 * @param reference the reference to set
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}
}