package in.wordofgod.bible.concordance;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.tmatesoft.sqljet.core.SqlJetException;
import org.tmatesoft.sqljet.core.SqlJetTransactionMode;
import org.tmatesoft.sqljet.core.table.SqlJetDb;

import in.wordofgod.bible.parser.vosgson.Verse;

public class Utils {

	static String getLanguageNameFromCode(String languageCode) {
		if (languageCode == null) {
			return "Unknown Language";
		}

		switch (languageCode.toLowerCase()) {
		case "ta":
			return "தமிழ்";
		case "en":
			return "English";
		case "kn":
			return "ಕನ್ನಡ";
		case "te":
			return "తెలుగు";
		case "hi":
			return "हिन्दी";
		case "ml":
			return "മലയാളം";
		case "he":
		case "iw":
			return "Hebrew";
		case "grc":
			return "Greek";
		case "el":
			return "Greek";
		case "ar":
			return "Arabic";
		case "la":
			return "Latin";
		case "awa":
			return "Awadhi";
		case "bn":
			return "Bengali";
		case "gu":
			return "Gujarati";
		case "mai":
			return "Maithili";
		case "mni":
			return "Manipuri";
		case "mr":
			return "Marathi";
		case "ms":
			return "Malay";
		case "ne":
			return "Nepali";
		case "original":
			return "Original";
		case "or":
			return "Odia";
		case "pa":
			return "Punjabi";
		case "sa":
			return "Sanskrit";
		case "si":
			return "Sinhala";
		case "ur":
			return "Urdu";
		default:
			// Log unknown language codes for user to be aware
			System.out.println("Warning: Unknown language code encountered: " + languageCode);
			return "Unknown Language";
		}
	}

	static VerseInfo getVerseInfo(VerseDetails verse, String LANGUAGE_NAME) {
		VerseInfo verseInfo = new VerseInfo();
		if ("en".equalsIgnoreCase(LANGUAGE_NAME)) {
			verseInfo.setVerse(StringUtils.capitalize(verse.getVerse()));
		} else {
			verseInfo.setVerse(verse.getVerse());
		}
		verseInfo.setReference(verse.getBook() + " " + verse.getChapter() + ":" + verse.getVerseNo());
		return verseInfo;
	}

	static String getOneLiner(String word, VerseDetails verse, String language) {
		String verseText = verse.getVerse();
		int beginningIndex = 0;
		int endingIndex = 0;
		if ("en".equalsIgnoreCase(language)) {
			verseText = verseText.toLowerCase();
			word = word.toLowerCase();
		}

		beginningIndex = verseText.indexOf(word);
		endingIndex = verseText.length();
		try {
			String temp = verseText.substring(beginningIndex, endingIndex);
			String[] ar = temp.split(" ");
			String reference = verse.getBook() + " " + verse.getChapter() + ":" + verse.getVerseNo();
			if (ar.length > 2) {
				return ar[0] + " " + ar[1] + " " + ar[2] + " - " + reference;
			} else if (ar.length > 1) {
				return ar[0] + " " + ar[1] + " - " + reference;
			} else {
				beginningIndex = 0;
				endingIndex = verseText.lastIndexOf(word);
				temp = verseText.substring(beginningIndex, endingIndex);
				ar = temp.split(" ");
				if (ar.length > 1) {
					return ar[ar.length - 2] + " " + ar[ar.length - 1] + " " + word + " - " + reference;
				} else {
					return ar[ar.length - 1] + " " + word + " - " + reference;
				}
			}
		} catch (java.lang.StringIndexOutOfBoundsException e) {
			System.out.println(word);
			System.out
					.println(verseText + " - " + verse.getBook() + " " + verse.getChapter() + ":" + verse.getVerseNo());
			e.printStackTrace();
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println(word);
			System.out.println(
					verse.getVerse() + " - " + verse.getBook() + " " + verse.getChapter() + ":" + verse.getVerseNo());
			e.printStackTrace();
		}
		System.out.println("ERROR for the word " + word);
		System.out.println(verseText + " - " + verse.getBook() + " " + verse.getChapter() + ":" + verse.getVerseNo());
		return "ERROR";
	}

	static String normalizeVerse(Verse verse) {
		String tempVerse = verse.getUnParsedText();
		// (கவலை வேண்டாம்) ((மத்தேயு 6:25-34,19-21))
		while (tempVerse.matches(".*\\([^()]*\\).*")) {
			tempVerse = tempVerse.replaceAll("\\([^()]*\\)", "");
		}
		// 6,000 - ([0-9]+),([0-9]+)
		tempVerse = tempVerse.replaceAll("(?<=\\d),(?=\\d)", "");
		// அகன்றது.எனவே
		tempVerse = tempVerse.replace(".", " ");
		// அகமகிழ்ந்திருக்க,”சிம்சோனைக் - remove comma
		tempVerse = tempVerse.replace(",", " ");
		// ” - remove quotes
		tempVerse = tempVerse.replace("”", " ");
		// “ - remove quotes
		tempVerse = tempVerse.replace("“", " ");
		// : - remove colan
		tempVerse = tempVerse.replace(":", " ");
		// ; - remove semi colan
		tempVerse = tempVerse.replace(";", " ");
		// ? - remove question mark
		tempVerse = tempVerse.replace("?", " ");
		// – -> remove hiphen
		tempVerse = tempVerse.replace("–", " ");
		// - -> remove
		tempVerse = tempVerse.replace("-", " ");
		// ! -> remove
		tempVerse = tempVerse.replace("!", " ");
		// ’ -> remove
		tempVerse = tempVerse.replace("’", " ");
		// … -> remove
		tempVerse = tempVerse.replace("…", " ");
		// ‘ -> remove
		tempVerse = tempVerse.replace("‘", " ");
		// — -> remove
		tempVerse = tempVerse.replace("—", " ");
		// ῾ -> remove
		tempVerse = tempVerse.replace("῾", " ");
		// ` -> remove
		tempVerse = tempVerse.replace("`", " ");

		// Strip tags + decode entities, leave the Strong numbers as is
		// e.g., <WH1234>, <WG1234>
		tempVerse = stripTagsAndDecodeEntities(tempVerse);

		// Remove extra spaces
		tempVerse = tempVerse.replaceAll("\\s+", " ").trim();
		return tempVerse;
	}
	
	private static String stripTagsAndDecodeEntities(String text) {
		// First, temporarily replace WH and WG tags with placeholders to preserve them
		// These are standalone tags, not HTML-style with closing tags
		String tempText = text.replaceAll("<(WH\\d+)>", "PLACEHOLDER_WH_$1_PLACEHOLDER");
		tempText = tempText.replaceAll("<(WG\\d+)>", "PLACEHOLDER_WG_$1_PLACEHOLDER");
		
		// Use Jsoup to strip HTML tags and decode entities
		tempText = Jsoup.parse(tempText).text();
		
		// Restore the WH and WG tags
		tempText = tempText.replaceAll("PLACEHOLDER_WH_(WH\\d+)_PLACEHOLDER", "<$1>");
		tempText = tempText.replaceAll("PLACEHOLDER_WG_(WG\\d+)_PLACEHOLDER", "<$1>");
		
		return tempText;
	}

	static String normalizeWord(String word) {
		word = word.replaceAll("[\\\"\\(\\)\\.\\:\\,\\;\\}\\{\\?\\]\\[]", "");
		// word = word.replaceAll("14", "").replaceAll("6", "").replaceAll("20",
		// "").replaceAll("43","");
		return word;
	}

	static SqlJetDb openDB(File file, boolean write) throws SqlJetException {
		try (DataInputStream in = new DataInputStream(new FileInputStream(file))) {
			byte[] header = new byte[20];
			in.readFully(header);
			if (new String(header, 0, 16, StandardCharsets.ISO_8859_1).equals("SQLite format 3\0")
					&& (header[18] > 1 || header[19] > 1)) {
				System.err.println("WARNING: SQLite version of " + file.getName() + " is too new.");
				System.err.println(
						"To convert SQLite file to version 1, open it in a SQLite editor and run SQL 'PRAGMA journal_mode=DELETE;'.");
				System.err.println();
			}
		} catch (IOException ex) {
			// ex.printStackTrace();
			// Ignore this exception as the file will get created newly
		}
		return SqlJetDb.open(file, write);
	}

	static void configureDB(SqlJetDb db) throws SqlJetException {
		db.getOptions().setAutovacuum(true);
		db.beginTransaction(SqlJetTransactionMode.WRITE);
		db.getOptions().setUserVersion(0);
	}

	static String normaliseStrongNumber(String unParsedText) {
		if (unParsedText == null || unParsedText.isEmpty()) {
			return unParsedText;
		}
		
		String normalizedText = unParsedText;
		
		// Transform Hebrew Strong's references: <WH123> -> H123
		normalizedText = normalizedText.replaceAll("<WH(\\d+)>", "H$1");
		normalizedText = normalizedText.replaceAll("<wh(\\d+)>", "H$1");
		
		// Transform Greek Strong's references: <WG456> -> G456
		normalizedText = normalizedText.replaceAll("<WG(\\d+)>", "G$1");
		normalizedText = normalizedText.replaceAll("<wg(\\d+)>", "G$1");
		
		return normalizedText;
	}

}
