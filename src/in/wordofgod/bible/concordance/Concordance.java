package in.wordofgod.bible.concordance;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import in.wordofgod.bible.parser.Bible;

@JsonIgnoreProperties(ignoreUnknown = true)
class Concordance {

	private String title;
	private String author;
	private BibleInfo bibleInfo;

	private List<LetterInfo> letters = new ArrayList<>();

	// Constructors
	public Concordance() {
	}

	public Concordance(String title, String author) {
		this.title = title;
		this.author = author;
	}

	public void addLetterInfo(LetterInfo letterInfo) {
		this.letters.add(letterInfo);
	}

	// Getters and Setters
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the bibleInfo
	 */
	public BibleInfo getBibleInfo() {
		return bibleInfo;
	}

	/**
	 * @param bibleInfo the bibleInfo to set
	 */
	public void setBibleInfo(BibleInfo bibleInfo) {
		this.bibleInfo = bibleInfo;
	}

	/**
	 * @return the letters
	 */
	public List<LetterInfo> getLetters() {
		return letters;
	}

	/**
	 * @param letters the letters to set
	 */
	public void setLetters(List<LetterInfo> letters) {
		this.letters = letters;
	}

	public void setBibleInfo(Bible bible) {
		bibleInfo = new BibleInfo();
		bibleInfo.setAbbr(bible.getAbbr());
		bibleInfo.setCommonName(bible.getCommonName());
		bibleInfo.setCopyRight(bible.getCopyRight());
		bibleInfo.setAdditionalInformation(bible.getAdditionalInformation());
		bibleInfo.setHasNT(bible.isHasNT());
		bibleInfo.setHasOT(bible.isHasOT());
		bibleInfo.setLanguageCode(bible.getLanguageCode());
		bibleInfo.setLongEnglishName(bible.getLongEnglishName());
		bibleInfo.setLongName(bible.getLongName());
		bibleInfo.setPublishedBy(bible.getPublishedBy());
		bibleInfo.setPublishedYear(bible.getPublishedYear());
		bibleInfo.setShortName(bible.getShortName());
		bibleInfo.setTotalBooks(bible.getBooks().size());
		bibleInfo.setTotalChapters(bible.getTotalChapters());
		bibleInfo.setTotalUniqueWords(bible.getTotalUniqueWords());
		bibleInfo.setTotalVerses(bible.getTotalVerses());
		bibleInfo.setTotalWords(bible.getTotalWords());
		bibleInfo.setTranslatedBy(bible.getTranslatedBy());
	}
}