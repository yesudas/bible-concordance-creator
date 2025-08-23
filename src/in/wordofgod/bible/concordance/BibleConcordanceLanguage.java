package in.wordofgod.bible.concordance;

import java.util.Map;

public interface BibleConcordanceLanguage {
	
	public static String STR_AUTHOR = "Composed By: ";
	public static String STR_COPYRIGHT = "Copyright: Public Domain /ilavasam / Pothuthalam";
	public static String STR_DOWNLOAD ="Download:";
	public static String STR_CONTACT_US = "Contact Us:";
		
	public static String BOOK_DETAILS_FONT = "BOOK_DETAILS_FONT";
	public static String BOOK_DETAILS_FONT_SIZE = "BOOK_DETAILS_FONT_SIZE";
	public static String STR_BOOK_NO = "STR_BOOK_NO";
	public static String STR_BOOK_EDITION = "STR_BOOK_EDITION";
	public static String STR_COPYRIGHT_DETAILS = "STR_BOOK_COPYRIGHT";
	public static String STR_DOWNLOAD_DETAILS = "STR_DOWNLOAD";
	public static String STR_CONTACT_US_DETAILS = "STR_CONTACT_US_DETAILS";
	public static String STR_THANKS_TO = "STR_THANKS_TO";
	public static String STR_THANKS_TO_DETAILS = "STR_THANKS_TO_DETAILS";
	public static String STR_AUTHOR_DETAILS = "STR_AUTHOR_DETAILS";
	public static String STR_THANKS_FONT = "STR_THANKS_FONT";
	public static String STR_THANKS_FONT_SIZE = "STR_THANKS_FONT_SIZE";
	public static String STR_SUB_INDEX_FONT = "STR_SUB_INDEX_FONT";
	public static String STR_SUB_INDEX_FONT_SIZE = "STR_SUB_INDEX_FONT_SIZE";
	public static String STR_INDEX_BOOKMARK_PREFIX = "STR_INDEX_BOOKMARK_PREFIX";
	
	String getString(String key);
	
	int getInt(String key);
	
	String getSTR_AUTHOR();
	
	String getSTR_CREATOR();
	
	String getSTR_INDEX_TITLE();

	String getSTR_INDEX_TITLE_FONT();

	int getSTR_INDEX_TITLE_FONT_SIZE();

	String getSTR_INDEX_FONT();

	int getSTR_INDEX_FONT_SIZE();

	String getSUB_TITLE_1();

	String getSUB_TITLE_1_FONT();

	int getSUB_TITLE_1_FONT_SIZE();

	String getSUB_TITLE_2();

	String getSUB_TITLE_2_FONT();

	int getSUB_TITLE_2_FONT_SIZE();

	String getMORE_INFO();

	String getMORE_INFO_FONT();

	int getMORE_INFO_FONT_SIZE();

	String[] getDOWNLOAD_INFO();

	String getDOWNLOAD_INFO_FONT();

	int getDOWNLOAD_INFO_FONT_SIZE();

	String getSUB_TITLE_3();

	String getSUB_TITLE_3_FONT();

	int getSUB_TITLE_3_FONT_SIZE();

	String getSUB_TITLE_4();

	String getSUB_TITLE_4_FONT();

	int getSUB_TITLE_4_FONT_SIZE();

	String getSTR_PDF_INDEX_ISSUE();
	
	String getSTR_PDF_INDEX_ISSUE_ENGLISH();

	String getSTR_PDF_INDEX_ISSUE_FONT();

	int getSTR_PDF_INDEX_ISSUE_FONT_SIZE();

	String getCHAPTER_HEADING_FONT();

	int getCHAPTER_HEADING_FONT_SIZE();

	String getDAY_TITLE_FONT();

	int getDAY_TITLE_FONT_SIZE();

	String getVERSE_FONT();

	int getVERSE_FONT_SIZE();
	
	Map<String, String[]> getCharMap();
	
	int getPARAGRAPH_SPACING_AFTER();

	String getSTR_DAY_TEXT();
	
	String getSTR_INDEX_BOOKMARK_PREFIX();

}
