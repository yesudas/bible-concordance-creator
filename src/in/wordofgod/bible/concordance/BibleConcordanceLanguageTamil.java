package in.wordofgod.bible.concordance;

import java.util.HashMap;
import java.util.Map;

public class BibleConcordanceLanguageTamil implements BibleConcordanceLanguage {

	private final String STR_AUTHOR = "Yesudas Solomon";
	private final String STR_CREATOR = "Yesudas Solomon, www.WordOfGod.in";

	private final String SUB_TITLE_1 = "ஒத்த வாக்கிய விளக்கவுரை";
	private final String SUB_TITLE_1_FONT = "Uni Ila.Sundaram-08";
	private final int SUB_TITLE_1_FONT_SIZE = 45;

	private final String SUB_TITLE_2 = "Tamil Bible Concordance [ O.V - Old Version ]";
	private final String SUB_TITLE_2_FONT = "Uni Ila.Sundaram-08";
	private final int SUB_TITLE_2_FONT_SIZE = 32;

	private final String STR_INDEX_TITLE = ". அட்டவணை .";
	private final String STR_INDEX_TITLE_FONT = "Uni Ila.Sundaram-02";
	private final int STR_INDEX_TITLE_FONT_SIZE = 18;

	private final String STR_INDEX_FONT = "Uni Ila.Sundaram-06";
	private final int STR_INDEX_FONT_SIZE = 24;

	private final String MORE_INFO = "மத்தேயு 10:8-ன் அடிப்படையில், இலவசமாக கொடுக்கப்படுகிறது - \"இலவசமாய்ப் பெற்றீர்கள், இலவசமாய்க் கொடுங்கள்\". ஆகையால், இலவசமாய் பகிருங்கள்.";
	private final String MORE_INFO_FONT = "Uni Ila.Sundaram-04";
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

	private final String STR_PDF_INDEX_ISSUE = "இந்த PDF-ஐ மொபைலில் பயன்படுத்தினால், அட்டவணையில் உள்ள் லிங்க்கள்(Index) கூகிள் ட்ரைவ்(Google Drive PDF Viewer) என்னும் ஆப்பில் (செயலியில்) வேலை செய்யாது, ReadEra என்னும் ஆப்பை (செயலி) பயன்படுத்துங்கள்.";
	private final String STR_PDF_INDEX_ISSUE_ENGLISH = "If you are using this PDF in mobile, Navigation by Index may not work with Google Drive's PDF viewer. I would recommend ReadEra App for better performance and navigation experience.";
	private final String STR_PDF_INDEX_ISSUE_FONT = "Uni Ila.Sundaram-04";
	private final int STR_PDF_INDEX_ISSUE_FONT_SIZE = 16;

	private final String CHAPTER_HEADING_FONT = "Uni Ila.Sundaram-02";
	private final int CHAPTER_HEADING_FONT_SIZE = 12;

	private final String DAY_TITLE_FONT = "Uni Ila.Sundaram-08";
	private final int DAY_TITLE_FONT_SIZE = 28;

	private final String VERSE_FONT = "Uni Ila.Sundaram-04";
	private final int VERSE_FONT_SIZE = 12;

	private final String STR_DAY_TEXT = "நாள்";

	private int PARAGRAPH_SPACING_AFTER = 0;

	private Map<String, String[]> charMap = null;
	
	private static Map<String, Object> stringsMap = new HashMap<String, Object>();
	
	static {
		stringsMap.put(BibleConcordanceLanguage.BOOK_DETAILS_FONT, "Calibri (Body)");
		stringsMap.put(BibleConcordanceLanguage.BOOK_DETAILS_FONT_SIZE, 14);
		stringsMap.put(BibleConcordanceLanguage.STR_BOOK_NO, "WOG BOOKS 190");
		stringsMap.put(BibleConcordanceLanguage.STR_BOOK_EDITION, "First Edition 2025");
		stringsMap.put(BibleConcordanceLanguage.STR_AUTHOR_DETAILS, "Yesudas Solomon");
		stringsMap.put(BibleConcordanceLanguage.STR_COPYRIGHT_DETAILS, "This book is not copyright protected. You are free to download, print and make copies without any permission from us.");
		stringsMap.put(BibleConcordanceLanguage.STR_DOWNLOAD_DETAILS, "www.WordOfGod.in and www.Archive.org");
		stringsMap.put(BibleConcordanceLanguage.STR_CONTACT_US_DETAILS, "Email: wordofgod@wordofgod.in\nMobile/WhatsApp:  \n+91 95970 37781 or +91 7676 50 5599\n"
				+ "YouTube: Bible Minutes\nFacebook: Bible Minutes\n");
		stringsMap.put(BibleConcordanceLanguage.STR_THANKS_TO, "நன்றிகள் பல");
		stringsMap.put(BibleConcordanceLanguage.STR_THANKS_FONT, "Uni Ila.Sundaram-06");
		stringsMap.put(BibleConcordanceLanguage.STR_THANKS_FONT_SIZE, 16);
		stringsMap.put(BibleConcordanceLanguage.STR_THANKS_TO_DETAILS, "இந்த புத்தகத்தை வெளியிடும்படி என்னை உற்சாகப்படுத்திய, மேம்படுத்த ஆலோசனைகளை வழங்கிய அன்பு நண்பர், சகோதரர் வ.மதன்ராஜ் என்கிற ஜோசுவாவுக்கு நன்றிகள் பல..");
		stringsMap.put(BibleConcordanceLanguage.STR_SUB_INDEX_FONT, "Uni Ila.Sundaram-04");
		stringsMap.put(BibleConcordanceLanguage.STR_SUB_INDEX_FONT_SIZE, 12);
		stringsMap.put(BibleConcordanceLanguage.STR_INDEX_BOOKMARK_PREFIX, "எழுத்து-");
		
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
	public String getSTR_DAY_TEXT() {
		return this.STR_DAY_TEXT;
	}

	@Override
	public Map<String, String[]> getCharMap() {
		if (this.charMap == null) {
			// https://ilearntamil.com/tamil-alphabets-chart/
			this.charMap = new HashMap<String, String[]>();
			charMap.put("க்ஷ", new String[] { "க்ஷௌ", "க்ஷோ", "க்ஷொ", "க்ஷை", "க்ஷே", "க்ஷெ", "க்ஷூ", "க்ஷு", "க்ஷீ",
					"க்ஷி", "க்ஷா", "க்ஷ்", "க்ஷ" });
			charMap.put("க",
					new String[] { "கௌ", "கோ", "கொ", "கை", "கே", "கெ", "கூ", "கு", "கீ", "கி", "கா", "க்", "க" });
			charMap.put("ங",
					new String[] { "ஙௌ", "ஙோ", "ஙொ", "ஙை", "ஙே", "ஙெ", "ஙூ", "ஙு", "ஙீ", "ஙி", "ஙா", "ங்", "ங" });
			charMap.put("ச",
					new String[] { "சௌ", "சோ", "சொ", "சை", "சே", "செ", "சூ", "சு", "சீ", "சி", "சா", "ச்", "ச" });
			charMap.put("ஞ",
					new String[] { "ஞௌ", "ஞோ", "ஞொ", "ஞை", "ஞே", "ஞெ", "ஞூ", "ஞு", "ஞீ", "ஞி", "ஞா", "ஞ்", "ஞ" });
			charMap.put("ட",
					new String[] { "டௌ", "டோ", "டொ", "டை", "டே", "டெ", "டூ", "டு", "டீ", "டி", "டா", "ட்", "ட" });
			charMap.put("ண",
					new String[] { "ணௌ", "ணோ", "ணொ", "ணை", "ணே", "ணெ", "ணூ", "ணு", "ணீ", "ணி", "ணா", "ண்", "ண" });
			charMap.put("த",
					new String[] { "தௌ", "தோ", "தொ", "தை", "தே", "தெ", "தூ", "து", "தீ", "தி", "தா", "த்", "த" });
			charMap.put("ந",
					new String[] { "நௌ", "நோ", "நொ", "நை", "நே", "நெ", "நூ", "நு", "நீ", "நி", "நா", "ந்", "ந" });
			charMap.put("ப",
					new String[] { "பௌ", "போ", "பொ", "பை", "பே", "பெ", "பூ", "பு", "பீ", "பி", "பா", "ப்", "ப" });
			charMap.put("ம",
					new String[] { "மௌ", "மோ", "மொ", "மை", "மே", "மெ", "மூ", "மு", "மீ", "மி", "மா", "ம்", "ம" });
			charMap.put("ய",
					new String[] { "யௌ", "யோ", "யொ", "யை", "யே", "யெ", "யூ", "யு", "யீ", "யி", "யா", "ய்", "ய" });
			charMap.put("ர",
					new String[] { "ரௌ", "ரோ", "ரொ", "ரை", "ரே", "ரெ", "ரூ", "ரு", "ரீ", "ரி", "ரா", "ர்", "ர" });
			charMap.put("ல",
					new String[] { "லௌ", "லோ", "லொ", "லை", "லே", "லெ", "லூ", "லு", "லீ", "லி", "லா", "ல்", "ல" });
			charMap.put("வ",
					new String[] { "வௌ", "வோ", "வொ", "வை", "வே", "வெ", "வூ", "வு", "வீ", "வி", "வா", "வ்", "வ" });
			charMap.put("ழ",
					new String[] { "ழௌ", "ழோ", "ழொ", "ழை", "ழே", "ழெ", "ழூ", "ழு", "ழீ", "ழி", "ழா", "ழ்", "ழ" });
			charMap.put("ள",
					new String[] { "ளௌ", "ளோ", "ளொ", "ளை", "ளே", "ளெ", "ளூ", "ளு", "ளீ", "ளி", "ளா", "ள்", "ள" });
			charMap.put("ற",
					new String[] { "றௌ", "றோ", "றொ", "றை", "றே", "றெ", "றூ", "று", "றீ", "றி", "றா", "ற்", "ற" });
			charMap.put("ன",
					new String[] { "னௌ", "னோ", "னொ", "னை", "னே", "னெ", "னூ", "னு", "னீ", "னி", "னா", "ன்", "ன" });
			charMap.put("ஜ",
					new String[] { "ஜௌ", "ஜோ", "ஜொ", "ஜை", "ஜே", "ஜெ", "ஜூ", "ஜு", "ஜீ", "ஜி", "ஜா", "ஜ்", "ஜ" });
			charMap.put("ஷ",
					new String[] { "ஷௌ", "ஷோ", "ஷொ", "ஷை", "ஷே", "ஷெ", "ஷூ", "ஷு", "ஷீ", "ஷி", "ஷா", "ஷ்", "ஷ" });
			charMap.put("ஸ",
					new String[] { "ஸௌ", "ஸோ", "ஸொ", "ஸை", "ஸே", "ஸெ", "ஸூ", "ஸு", "ஸீ", "ஸி", "ஸா", "ஸ்", "ஸ" });
			charMap.put("ஹ",
					new String[] { "ஹௌ", "ஹோ", "ஹொ", "ஹை", "ஹே", "ஹெ", "ஹூ", "ஹு", "ஹீ", "ஹி", "ஹா", "ஹ்", "ஹ" });
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
	public String getSTR_INDEX_BOOKMARK_PREFIX() {
		// TODO Auto-generated method stub
		return null;
	}

}