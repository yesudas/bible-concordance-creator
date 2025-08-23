package in.wordofgod.bible.concordance;

import java.util.Map;

public class BibleConcordanceLanguageMalayalam implements BibleConcordanceLanguage {

	private final String STR_AUTHOR = "Yesudas Solomon";
	private final String STR_CREATOR = "Yesudas Solomon, www.WordOfGod.in";

	private final String STR_INDEX_TITLE = "സൂചിക";
	private final String STR_INDEX_TITLE_FONT = "Cambria";
	private final int STR_INDEX_TITLE_FONT_SIZE = 20;

	private final String STR_INDEX_FONT = "Cambria";
	private final int STR_INDEX_FONT_SIZE = 12;

	private final String SUB_TITLE_1 = "ഒരു വർഷത്തെ ബൈബിൾ";
	private final String SUB_TITLE_1_FONT = "Cambria";
	private final int SUB_TITLE_1_FONT_SIZE = 40;

	private final String SUB_TITLE_2 = "പൂർണ്ണമായും സൗജന്യം\nPDF-ൽ 365 ദിവസത്തെ വായനാ പദ്ധതി\nപുസ്തകങ്ങളുടെ കാലക്രമ ക്രമത്തിൽ";
	private final String SUB_TITLE_2_FONT = "Cambria";
	private final int SUB_TITLE_2_FONT_SIZE = 16;

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

	private final String STR_PDF_INDEX_ISSUE = "നിങ്ങൾ മൊബൈലിൽ ഈ PDF ഉപയോഗിക്കുകയാണെങ്കിൽ, സൂചിക പ്രകാരം നാവിഗേഷൻ Google ഡ്രൈവ് PDF വ്യൂവറിൽ പ്രവർത്തിച്ചേക്കില്ല. Adobe Reader, Evie, ReadEra, PDF Reader, PDF Viewer തുടങ്ങിയ മറ്റ് വായനക്കാരിൽ തുറക്കാൻ ശ്രമിക്കുക";
	private final String STR_PDF_INDEX_ISSUE_ENGLISH = "If you are using this PDF in mobile, Navigation by Index may not work with Google Drive's PDF viewer. Try opening in other readers like Adobe Reader, Evie, ReadEra, PDF Reader, PDF Viewer, etc\n\n\n";
	private final String STR_PDF_INDEX_ISSUE_FONT = "Cambria";
	private final int STR_PDF_INDEX_ISSUE_FONT_SIZE = 18;

	private final String CHAPTER_HEADING_FONT = "Cambria";
	private final int CHAPTER_HEADING_FONT_SIZE = 16;

	private final String DAY_TITLE_FONT = "Cambria";
	private final int DAY_TITLE_FONT_SIZE = 26;

	private final String VERSE_FONT = "Cambria";
	private final int VERSE_FONT_SIZE = 12;
	
	private String STR_DAY_TEXT = "ദിവസം";

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