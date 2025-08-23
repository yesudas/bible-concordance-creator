package in.wordofgod.bible.concordance;

import java.util.Map;

public class BibleConcordanceLanguageTelugu implements BibleConcordanceLanguage {

	private final String STR_AUTHOR = "Yesudas Solomon";
	private final String STR_CREATOR = "Yesudas Solomon, www.WordOfGod.in";

	private final String STR_INDEX_TITLE = "సూచిక";
	private final String STR_INDEX_TITLE_FONT = "Gautami";//"Pothana2000";
	private final int STR_INDEX_TITLE_FONT_SIZE = 20;

	private final String STR_INDEX_FONT = "Gautami";//"Pothana2000";
	private final int STR_INDEX_FONT_SIZE = 12;

	private final String SUB_TITLE_1 = "ఒక  సంవత్సరం  బైబిల్ [ Telugu O.V ]";
	private final String SUB_TITLE_1_FONT = "Pothana2000";
	private final int SUB_TITLE_1_FONT_SIZE = 72;

	private final String SUB_TITLE_2 = "| పూర్తిగా  ఉచితం |\n| పుస్తకాల  కాలక్రమానుసారం |\n| PDFలో  365  రోజుల  పఠన  ప్రణాళిక |";
	private final String SUB_TITLE_2_FONT = "Gautami";//"Vemana2000";
	private final int SUB_TITLE_2_FONT_SIZE = 20;

	private final String MORE_INFO = "Made available to public based on Matthew 10:8 - \"Freely You have Received; Freely Give\". So feel free to distribute freely.";
	private final String MORE_INFO_FONT = "Cambria";
	private final int MORE_INFO_FONT_SIZE = 16;

	private final String[] DOWNLOAD_INFO = { "www.WordOfGod.in", "www.Archive.org" };
	private final String DOWNLOAD_INFO_FONT = "Cambria";
	private final int DOWNLOAD_INFO_FONT_SIZE = 14;

	private final String SUB_TITLE_3 = "By:";
	private final String SUB_TITLE_3_FONT = "Cambria";
	private final int SUB_TITLE_3_FONT_SIZE = 16;

	private final String SUB_TITLE_4 = "Yesudas Solomon, www.WordOfGod.in";
	private final String SUB_TITLE_4_FONT = "Cambria";
	private final int SUB_TITLE_4_FONT_SIZE = 16;

	private final String STR_PDF_INDEX_ISSUE = "మీరు మొబైల్‌లో ఈ PDFని ఉపయోగిస్తుంటే, నావిగేషన్ బై ఇండెక్స్ Google డిస్క్ యొక్క PDF వ్యూయర్‌తో పని చేయకపోవచ్చు. Adobe Reader, Evie, ReadEra, PDF Reader, PDF Viewer మొదలైన ఇతర రీడర్‌లలో తెరవడానికి ప్రయత్నించండి";
	private final String STR_PDF_INDEX_ISSUE_ENGLISH = "If you are using this PDF in mobile, Navigation by Index may not work with Google Drive's PDF viewer. Try opening in other readers like Adobe Reader, Evie, ReadEra, PDF Reader, PDF Viewer, etc";
	private final String STR_PDF_INDEX_ISSUE_FONT = "Gautami";//"Pothana2000";
	private final int STR_PDF_INDEX_ISSUE_FONT_SIZE = 18;

	private final String CHAPTER_HEADING_FONT = "Gautami";//"Pothana2000";
	private final int CHAPTER_HEADING_FONT_SIZE = 18;

	private final String DAY_TITLE_FONT = "Gautami";//"Pothana2000";
	private final int DAY_TITLE_FONT_SIZE = 26;

	private final String VERSE_FONT = "Gautami";//"Pothana2000";
	private final int VERSE_FONT_SIZE = 12;
	
	private String STR_DAY_TEXT = "రోజు";

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
	public String getSTR_DAY_TEXT() {
		return this.STR_DAY_TEXT;
	}

	@Override
	public String getString(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getInt(String key) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Map<String, String[]> getCharMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPARAGRAPH_SPACING_AFTER() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getSTR_INDEX_BOOKMARK_PREFIX() {
		// TODO Auto-generated method stub
		return null;
	}

}