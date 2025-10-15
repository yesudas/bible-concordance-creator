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
		String tempVerse = verse.getText();
		// (கவலை வேண்டாம்) ((மத்தேயு 6:25-34,19-21))
		while (tempVerse.matches(".*\\([^()]*\\).*")) {
			tempVerse = tempVerse.replaceAll("\\([^()]*\\)", "");
		}
		// 6,000 - ([0-9]+),([0-9]+)
		tempVerse = tempVerse.replaceAll("(?<=\\d),(?=\\d)", "");		
		// அகன்றது.எனவே
		tempVerse = tempVerse.replace(".", " ");
		//அகமகிழ்ந்திருக்க,”சிம்சோனைக் - remove comma
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

		// Strip tags + decode entities
		tempVerse = Jsoup.parse(tempVerse).text();

		// Remove extra spaces
		tempVerse = tempVerse.replace("   ", " ");
		tempVerse = tempVerse.replace("  ", " ");
		return tempVerse;
	}

	static String normalizeWord(String word) {
		word = word.replaceAll("[\\\"\\(\\)\\.\\:\\,\\;\\}\\{\\?\\]\\[]", "");
		word = word.replaceAll("“", "").replaceAll("14", "").replaceAll("6", "").replaceAll("20", "").replaceAll("43",
				"");
		word = word.replace("”", "");
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

}
