package in.wordofgod.bible.concordance;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.text.BreakIterator;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.wordofgod.bible.parser.Bible;
import in.wordofgod.bible.parser.TheWord;
import in.wordofgod.bible.parser.vosgson.Book;
import in.wordofgod.bible.parser.vosgson.Chapter;
import in.wordofgod.bible.parser.vosgson.Verse;

public class Json {

	private static BibleConcordanceLanguage language;

	public static final String EXTENSION = ".json";

	private static boolean WRITE_LOGS_TO_FILE;
	private static boolean CHANGE_BOOK_NAMES_TO_NON_ENGLISH;
	private static boolean USE_SHORT_NAMES;
	private static boolean INCLUDE_REFERENCES;
	private static Map<String, List<VerseDetails>> wordsMap;
	private static Map<String, TreeSet<String>> indexMap;

	private static String LANGUAGE_NAME;

	private static int totalReferencesCount;
	private static int uniqueBookMarkCounter;

	private static Bible bible;
	
	private static void init() {
		wordsMap = new TreeMap<String, List<VerseDetails>>();
		indexMap = new TreeMap<String, TreeSet<String>>();
		bible = null;
		totalReferencesCount = 0;
		uniqueBookMarkCounter = 1;
		LANGUAGE_NAME = "ta";
		language = null;
		WRITE_LOGS_TO_FILE = false;
		CHANGE_BOOK_NAMES_TO_NON_ENGLISH = true;
		USE_SHORT_NAMES = true;
		INCLUDE_REFERENCES = true;
	}

	public static void create() throws FileNotFoundException, IOException {
		StopClock clock = new StopClock();
		
		init();
		
		initSystemOutSettings();

		createLanguageInstance();

		File file = new File(BibleConcordanceCreator.bibleSourcePath);

		System.out.println("TheWord Bible loading started...");

		try {
			bible = TheWord.getBible(file.getAbsolutePath(), BibleConcordanceCreator.bibleInformationPath);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		if (bible != null) {
			System.out.println("TheWord Bible loaded successfully...");
		}

		LANGUAGE_NAME = bible.getLanguageCode();

		System.out.println("Started Creating Words Map");
		for (Book book : bible.getBooks()) {
			for (Chapter chapter : book.getChapters()) {
				for (Verse verse : chapter.getVerses()) {
					verse.setText(Utils.normalizeVerse(verse));
					String[] words = verse.getText().split("[\\s']");
					for (String word : words) {
						word = Utils.normalizeWord(word);
						if (!word.trim().equals("")) {
							addToWordsMap(book, chapter, verse, word);
						}
					}
				}
			}
		}
		System.out.println("Completed Creating Words Map");

		String outputFolder = BibleConcordanceCreator.outputPath + "\\Json\\" + LANGUAGE_NAME + "\\" + bible.getAbbr()
				+ "\\";

		Concordance concordance = new Concordance();
		concordance.setTitle(language.getSUB_TITLE_1());
		concordance.setAuthor(language.getSUB_TITLE_4());
		concordance.setBibleInfo(bible);
		concordance.getBibleInfo().setTotalUniqueWords(wordsMap.size());

		System.out.println("Concordance Creation started");
		for (Map.Entry<String, TreeSet<String>> entry : indexMap.entrySet()) {
			System.out.println("Processing the Letter :: " + entry.getKey());
			String indexLetter = entry.getKey();
			Letter letterFile = new Letter(indexLetter);

			try {

				// Create Index
				concordance.addLetterInfo(createIndex(indexLetter));

				TreeSet<String> wordList = entry.getValue();

				for (String word : wordList) {
					letterFile.addWord(
							new WordInfo(word, wordsMap.get(word).size(), indexLetter + "-" + word + EXTENSION));
					Word wordFile = new Word(word);
					for (VerseDetails verse : wordsMap.get(word)) {
						totalReferencesCount++;
						wordFile.addVerse(Utils.getVerseInfo(verse, LANGUAGE_NAME));
					}
					// Write dictionary object into L-lord.json file
					ObjectMapper mapper = new ObjectMapper();
					String outputPath = outputFolder + "words\\" + indexLetter + "\\";
					(new File(outputPath)).mkdirs();
					File outputFile = new File(outputPath + indexLetter + "-" + word + EXTENSION);
					mapper.writer().writeValue(outputFile, wordFile);
					System.out.println("JSON created successfully: " + indexLetter + "-" + word + " :: "
							+ outputFile.getAbsolutePath());
				}

				// Write dictionary object into L.json file
				ObjectMapper mapper = new ObjectMapper();
				String outputPath = outputFolder + "letters\\";
				(new File(outputPath)).mkdirs();
				File outputFile = new File(outputPath + indexLetter + EXTENSION);
				mapper.writer().writeValue(outputFile, letterFile);
				System.out.println(
						"JSON created successfully for: " + indexLetter + " :: " + outputFile.getAbsolutePath());

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("FileNotFoundException occurs.." + e.getMessage());
			}
		}

		concordance.getBibleInfo().setTotalReferences(totalReferencesCount);
		// Write dictionary object into L.json file
		ObjectMapper mapper = new ObjectMapper();
		File outputFile = new File(outputFolder + "Concordance" + EXTENSION);
		mapper.writer().writeValue(outputFile, concordance);
		System.out.println("JSON created successfully for Concordance.json : " + outputFile.getAbsolutePath());

		outputStatistics();

		wordsMap.clear();
		indexMap.clear();
		System.out.println("Total Time taken :: " + clock.elapsedTime() + " seconds");
	}

	private static void createLanguageInstance() {
		switch (LANGUAGE_NAME) {
		case "ta":
			language = new BibleConcordanceLanguageTamil();
			break;
		case "en":
			language = new BibleConcordanceLanguageEnglish();
			break;
		case "ml":
			language = new BibleConcordanceLanguageMalayalam();
			break;
		case "kn":
			language = new BibleConcordanceLanguageKannada();
			break;
		case "hi":
			language = new BibleConcordanceLanguageHindi();
			break;
		case "te":
			language = new BibleConcordanceLanguageTelugu();
			break;
		}
	}

	private static LetterInfo createIndex(String indexLetter) throws Exception {
		System.out.println("Index Creation Started...");

		LetterInfo letterInfo = new LetterInfo();
		letterInfo.setLetter(indexLetter);
		letterInfo.setFile(indexLetter + EXTENSION);

		int totalCountAtLetterLevel = 0;
		for (String word : indexMap.get(indexLetter)) {
			totalCountAtLetterLevel = totalCountAtLetterLevel + wordsMap.get(word).size();
		}
		letterInfo.setWordsCount(totalCountAtLetterLevel);

		System.out.println("Index Creation Completed...");
		return letterInfo;
	}

	private static void initSystemOutSettings() throws FileNotFoundException {
		if (WRITE_LOGS_TO_FILE) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH mm ss");
			String timeStamp = dateFormat.format(new Date());
			timeStamp = timeStamp.replaceAll(" ", "-");
			File outputFolder = new File(BibleConcordanceCreator.outputPath);
			outputFolder.mkdirs();
			File outputFile = new File(BibleConcordanceCreator.outputPath + "/output-restults " + timeStamp + ".txt");
			System.out.println("Output-Results are stored at :: " + outputFile.getAbsolutePath());
			System.setOut(new PrintStream(outputFile));
		}
	}

	static void characterExamples() {
		// BreakIterator arCharIterator = BreakIterator.getCharacterInstance(new
		// Locale("ta", "IN"));
		// String house = "தாகம்";
		BreakIterator arCharIterator = BreakIterator.getCharacterInstance(new Locale("ar", "SA"));
		// Arabic word for "house"
		String house = "\u0628" + "\u064e" + "\u064a" + "\u0652" + "\u067a" + "\u064f";
		arCharIterator.setText(house);
		int boundary = arCharIterator.first();
		while (boundary != BreakIterator.DONE) {
			System.out.println(boundary);
			boundary = arCharIterator.next();
		}
	}

	private static void addToWordsMap(Book book, Chapter chapter, Verse verse, String word) {
		if ("ta".equalsIgnoreCase(LANGUAGE_NAME) && word.equals("43")) {
			return;
		}
		if ("en".equalsIgnoreCase(LANGUAGE_NAME)) {
			if (word.length() == 1) {
				return;
			}
			word = StringUtils.capitalize(word);
		}
		List<VerseDetails> list = wordsMap.get(word);
		boolean alreadyExists = false;
		String bookName = null;
		if (USE_SHORT_NAMES) {
			bookName = book.getShortName();
		} else if (CHANGE_BOOK_NAMES_TO_NON_ENGLISH) {
			bookName = book.getLongName();// getBookName(book.getBook(), getNonEnglishBookNames());
		} else {
			bookName = book.getShortName();
		}
		if (list != null) {
			for (VerseDetails vd : list) {
				if (vd.getBook().equalsIgnoreCase(bookName) && vd.getChapter().equals(chapter.getChapter())
						&& vd.getVerseNo().equals(verse.getNumber())) {
					alreadyExists = true;
					break;
				}
			}
			if (!alreadyExists) {
				list.add(new VerseDetails(bookName, chapter.getChapter(), verse.getNumber(), verse.getText()));
			}
		} else {
			list = new ArrayList<VerseDetails>();
			list.add(new VerseDetails(bookName, chapter.getChapter(), verse.getNumber(), verse.getText()));
			wordsMap.put(word, list);
		}
		if (!alreadyExists) {
			String actualLetter = null;
			String firstLetter = "" + word.charAt(0);
			String[] letterSet = language.getCharMap().get(firstLetter);
			if (letterSet != null) {
				for (String letter : letterSet) {
					if (word.startsWith(letter)) {
						actualLetter = letter;
						break;
					}
				}
			}
			if (actualLetter == null) {
				actualLetter = firstLetter;
			}
			TreeSet<String> wordsList = indexMap.get(actualLetter);
			if (!actualLetter.equalsIgnoreCase("்")) {
				if (wordsList == null) {

					wordsList = new TreeSet<String>();
					indexMap.put(actualLetter, wordsList);
				}
				wordsList.add(word);
			}
		}
	}

	private static String getCSV(List<VerseDetails> verses) {
		if (INCLUDE_REFERENCES) {
			StringBuilder sb = new StringBuilder();
			int counter = 1;
			for (VerseDetails vd : verses) {
				if (counter > 20) {
					break;
				}
				sb.append(vd.getBook());
				sb.append(" ");
				sb.append(vd.getChapter());
				sb.append(":");
				sb.append(vd.getVerseNo());
				sb.append(", ");
				counter++;
			}
			return sb.toString();
		} else {
			return "";
		}
	}

	private static void outputStatistics() throws IOException {
		StopClock clock = new StopClock();
		System.out.println("Started Words Statistics");
		System.out.println("Total No of Hyperlinks: " + uniqueBookMarkCounter);
		if (!wordsMap.isEmpty()) {
			System.out.println("Total Unique Words: " + wordsMap.size());
			System.out.println("Total References: " + totalReferencesCount);
			for (Map.Entry<String, List<VerseDetails>> entry : wordsMap.entrySet()) {
				// if(entry.getKey().length()<3){
				if (true) {
					String references = getCSV(entry.getValue());
					String word = entry.getKey();
					String outStr = null;
					outStr = word + "\t" + entry.getValue().size() + references;
					// System.out.println(outStr);
				}
			}
		}
		long temp = clock.elapsedTime();
		System.out.printf("Elapsed time for Words Statistics :: " + temp + " seconds\n");
	}

}