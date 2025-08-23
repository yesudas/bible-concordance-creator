/**
 * 
 */
package in.wordofgod.bible.concordance;

/**
 * @author Yesudas.S
 *
 */
public class VerseDetails{

	private String book;
	private String chapter;
	private String verseNo;
	private String verse;

	public VerseDetails(String book, String chapter, String verseNo, String verse) {
		super();
		this.book = book;
		this.chapter = chapter;
		this.verseNo = verseNo;
		this.verse = verse;
	}

	public String getBook() {
		return book;
	}

	public void setBook(String book) {
		this.book = book;
	}

	public String getChapter() {
		return chapter;
	}

	public void setChapter(String chapter) {
		this.chapter = chapter;
	}

	public String getVerseNo() {
		return verseNo;
	}

	public void setVerseNo(String verseNo) {
		this.verseNo = verseNo;
	}

	public String getVerse() {
		return verse;
	}

	public void setVerse(String verse) {
		this.verse = verse;
	}

}
