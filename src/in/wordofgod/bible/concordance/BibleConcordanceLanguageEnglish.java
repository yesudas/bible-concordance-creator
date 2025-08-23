package in.wordofgod.bible.concordance;

import java.util.HashMap;
import java.util.Map;

public class BibleConcordanceLanguageEnglish implements BibleConcordanceLanguage {

	private final String STR_AUTHOR = "Yesudas Solomon";
	private final String STR_CREATOR = "Yesudas Solomon, www.WordOfGod.in";

	private final String SUB_TITLE_1 = "BBE Bible Concordance";
	private final String SUB_TITLE_1_FONT = "Cambria";
	private final int SUB_TITLE_1_FONT_SIZE = 36;

	private final String SUB_TITLE_2 = "| ------ Words |\n| ------- References |";
	private final String SUB_TITLE_2_FONT = "Calibri";
	private final int SUB_TITLE_2_FONT_SIZE = 28;

	private final String STR_INDEX_TITLE = "Index";
	private final String STR_INDEX_TITLE_FONT = "Calibri (Body)";
	private final int STR_INDEX_TITLE_FONT_SIZE = 35;

	private final String STR_INDEX_FONT = "Calibri (Body)";
	private final int STR_INDEX_FONT_SIZE = 41;

	private final String MORE_INFO = "Given free of cost based on Matthew 10:8 - \"Freely Received; Freely Give\". So, Share it freely!";
	private final String MORE_INFO_FONT = "Calibri (Body)";
	private final int MORE_INFO_FONT_SIZE = 19;

	private final String[] DOWNLOAD_INFO = { "www.WordOfGod.in", "www.Archive.org" };
	private final String DOWNLOAD_INFO_FONT = "Cambria";
	private final int DOWNLOAD_INFO_FONT_SIZE = 14;

	private final String SUB_TITLE_3 = "By:";
	private final String SUB_TITLE_3_FONT = "Cambria";
	private final int SUB_TITLE_3_FONT_SIZE = 16;

	private final String SUB_TITLE_4 = "Yesudas Solomon, www.WordOfGod.in";
	private final String SUB_TITLE_4_FONT = "Cambria";
	private final int SUB_TITLE_4_FONT_SIZE = 20;

	private final String STR_PDF_INDEX_ISSUE = "";
	private final String STR_PDF_INDEX_ISSUE_ENGLISH = "If you are using this PDF in mobile, Navigation by Index may not work with Google Drive's PDF viewer. Try opening in other readers like Adobe Reader, Evie, ReadEra, PDF Reader, PDF Viewer, etc\n\n\n";
	private final String STR_PDF_INDEX_ISSUE_FONT = "Calibri (Body)";
	private final int STR_PDF_INDEX_ISSUE_FONT_SIZE = 16;

	private final String CHAPTER_HEADING_FONT = "Calibri (Body)";
	private final int CHAPTER_HEADING_FONT_SIZE = 10;

	private final String DAY_TITLE_FONT = "Calibri (Body)";
	private final int DAY_TITLE_FONT_SIZE = 28;

	private final String VERSE_FONT = "Calibri (Body)";
	private final int VERSE_FONT_SIZE = 12;

	private int PARAGRAPH_SPACING_AFTER = 0;

	private Map<String, String[]> charMap = null;
	
	private static Map<String, Object> stringsMap = new HashMap<String, Object>();
	
	static {
		stringsMap.put(BibleConcordanceLanguage.BOOK_DETAILS_FONT, "Calibri (Body)");
		stringsMap.put(BibleConcordanceLanguage.BOOK_DETAILS_FONT_SIZE, 14);
		stringsMap.put(BibleConcordanceLanguage.STR_BOOK_NO, "WOG BOOKS 41");
		stringsMap.put(BibleConcordanceLanguage.STR_BOOK_EDITION, "First Edition 2022");
		stringsMap.put(BibleConcordanceLanguage.STR_AUTHOR_DETAILS, "Yesudas Solomon");
		stringsMap.put(BibleConcordanceLanguage.STR_COPYRIGHT_DETAILS, "This book is not copyright protected. You are free to download, print and make copies without any permission from us.");
		stringsMap.put(BibleConcordanceLanguage.STR_DOWNLOAD_DETAILS, "www.WordOfGod.in and www.Archive.org");
		stringsMap.put(BibleConcordanceLanguage.STR_CONTACT_US_DETAILS, "Email: wordofgod@wordofgod.in\nMobile/WhatsApp:  \n+91 90190 49070 or +91 7676 50 5599\n"
				+ "YouTube: Bible Minutes\nFacebook: Bible Minutes\n");
		stringsMap.put(BibleConcordanceLanguage.STR_THANKS_TO, "Thanks To");
		stringsMap.put(BibleConcordanceLanguage.STR_THANKS_FONT, "Calibri (Body)");
		stringsMap.put(BibleConcordanceLanguage.STR_THANKS_FONT_SIZE, 16);
		stringsMap.put(BibleConcordanceLanguage.STR_THANKS_TO_DETAILS, "Heartful Thanks to dear brother, my friend V. Madanraj (a) Joshua who encouraged me to publish the first Bible Concordance as PDF in Tamil language...");
		stringsMap.put(BibleConcordanceLanguage.STR_SUB_INDEX_FONT, "Calibri (Body)");
		stringsMap.put(BibleConcordanceLanguage.STR_SUB_INDEX_FONT_SIZE, 12);
		
	}

	@Override
	public String getSTR_INDEX_TITLE() {
		return this.STR_INDEX_TITLE;
	}

	@Override
	public String getSTR_INDEX_TITLE_FONT() {
		return this.STR_INDEX_TITLE_FONT;
	}

	@Override
	public int getSTR_INDEX_TITLE_FONT_SIZE() {
		return this.STR_INDEX_TITLE_FONT_SIZE;
	}

	@Override
	public String getSTR_INDEX_FONT() {
		return this.STR_INDEX_FONT;
	}

	@Override
	public int getSTR_INDEX_FONT_SIZE() {
		return this.STR_INDEX_FONT_SIZE;
	}

	@Override
	public String getSUB_TITLE_1() {
		return this.SUB_TITLE_1;
	}

	@Override
	public String getSUB_TITLE_1_FONT() {
		return this.SUB_TITLE_1_FONT;
	}

	@Override
	public int getSUB_TITLE_1_FONT_SIZE() {
		return this.SUB_TITLE_1_FONT_SIZE;
	}

	@Override
	public String getSUB_TITLE_2() {
		return this.SUB_TITLE_2;
	}

	@Override
	public String getSUB_TITLE_2_FONT() {
		return this.SUB_TITLE_2_FONT;
	}

	@Override
	public int getSUB_TITLE_2_FONT_SIZE() {
		return this.SUB_TITLE_2_FONT_SIZE;
	}

	@Override
	public String getMORE_INFO() {
		return this.MORE_INFO;
	}

	@Override
	public String getMORE_INFO_FONT() {
		return this.MORE_INFO_FONT;
	}

	@Override
	public int getMORE_INFO_FONT_SIZE() {
		return this.MORE_INFO_FONT_SIZE;
	}

	@Override
	public String[] getDOWNLOAD_INFO() {
		return this.DOWNLOAD_INFO;
	}

	@Override
	public String getDOWNLOAD_INFO_FONT() {
		return this.DOWNLOAD_INFO_FONT;
	}

	@Override
	public int getDOWNLOAD_INFO_FONT_SIZE() {
		return this.DOWNLOAD_INFO_FONT_SIZE;
	}

	@Override
	public String getSUB_TITLE_3() {
		return this.SUB_TITLE_3;
	}

	@Override
	public String getSUB_TITLE_3_FONT() {
		return this.SUB_TITLE_3_FONT;
	}

	@Override
	public int getSUB_TITLE_3_FONT_SIZE() {
		return this.SUB_TITLE_3_FONT_SIZE;
	}

	@Override
	public String getSUB_TITLE_4() {
		return this.SUB_TITLE_4;
	}

	@Override
	public String getSUB_TITLE_4_FONT() {
		return this.SUB_TITLE_4_FONT;
	}

	@Override
	public int getSUB_TITLE_4_FONT_SIZE() {
		return this.SUB_TITLE_4_FONT_SIZE;
	}

	@Override
	public String getSTR_PDF_INDEX_ISSUE() {
		return this.STR_PDF_INDEX_ISSUE;
	}

	@Override
	public String getSTR_PDF_INDEX_ISSUE_ENGLISH() {
		return this.STR_PDF_INDEX_ISSUE_ENGLISH;
	}

	@Override
	public String getSTR_PDF_INDEX_ISSUE_FONT() {
		return this.STR_PDF_INDEX_ISSUE_FONT;
	}

	@Override
	public int getSTR_PDF_INDEX_ISSUE_FONT_SIZE() {
		return this.STR_PDF_INDEX_ISSUE_FONT_SIZE;
	}

	@Override
	public String getCHAPTER_HEADING_FONT() {
		return this.CHAPTER_HEADING_FONT;
	}

	@Override
	public int getCHAPTER_HEADING_FONT_SIZE() {
		return this.CHAPTER_HEADING_FONT_SIZE;
	}

	@Override
	public String getDAY_TITLE_FONT() {
		return this.DAY_TITLE_FONT;
	}

	@Override
	public int getDAY_TITLE_FONT_SIZE() {
		return this.DAY_TITLE_FONT_SIZE;
	}

	@Override
	public String getVERSE_FONT() {
		return this.VERSE_FONT;
	}

	@Override
	public int getVERSE_FONT_SIZE() {
		return this.VERSE_FONT_SIZE;
	}

	@Override
	public String getSTR_AUTHOR() {
		return this.STR_AUTHOR;
	}

	@Override
	public String getSTR_CREATOR() {
		return this.STR_CREATOR;
	}

	@Override
	public Map<String, String[]> getCharMap() {
		if (this.charMap == null) {
			// https://ilearntamil.com/tamil-alphabets-chart/
			this.charMap = new HashMap<String, String[]>();
		}
		return this.charMap;
	}

	@Override
	public int getPARAGRAPH_SPACING_AFTER() {
		return this.PARAGRAPH_SPACING_AFTER;
	}

	@Override
	public String getString(String key) {
		return (String)stringsMap.get(key);
	}

	@Override
	public int getInt(String key) {
		return (Integer) stringsMap.get(key);
	}

	@Override
	public String getSTR_DAY_TEXT() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSTR_INDEX_BOOKMARK_PREFIX() {
		// TODO Auto-generated method stub
		return null;
	}

}