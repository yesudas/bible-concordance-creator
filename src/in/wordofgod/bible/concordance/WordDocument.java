package in.wordofgod.bible.concordance;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.math.BigInteger;
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
import org.apache.poi.ooxml.POIXMLProperties.CoreProperties;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFHyperlinkRun;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBody;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBookmark;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocument1;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHyperlink;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageSz;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STPageOrientation;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STShd;

import in.wordofgod.bible.parser.Bible;
import in.wordofgod.bible.parser.TheWord;
import in.wordofgod.bible.parser.vosgson.Book;
import in.wordofgod.bible.parser.vosgson.Chapter;
import in.wordofgod.bible.parser.vosgson.Verse;

public class WordDocument {

	private static BibleConcordanceLanguage language = null;

	public static final String EXTENSION = ".docx";

	private static boolean WRITE_LOGS_TO_FILE = false;
	private static boolean CHANGE_BOOK_NAMES_TO_NON_ENGLISH = true;
	private static boolean USE_SHORT_NAMES = true;
	private static boolean INCLUDE_REFERENCES = true;
	private static boolean LIMIT_OUTPUT = true;
	private static Map<String, List<VerseDetails>> wordsMap = new TreeMap<String, List<VerseDetails>>();
	private static Map<String, TreeSet<String>> indexMap = new TreeMap<String, TreeSet<String>>();

	private static String LANGUAGE_NAME = "ta";
	//private static final String OUTPUT_FOLDER = "D:\\GitLab\\WOG\\wog-json-utilities\\Output\\Bible-Concordance\\2022\\";
	
	private static int totalReferencesCount = 0;
	private static int uniqueBookMarkCounter = 1;

	public static void createWordDocument() throws FileNotFoundException, IOException {
		StopClock clock = new StopClock();
		initSystemOutSettings();

		createLanguageInstance();
		
		File file = new File(BibleConcordanceCreator.bibleSourcePath);

		System.out.println("TheWord Bible loading started...");
		Bible bible;
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
					String[] words = verse.getText().split("[\\s']");
					for (String word : words) {
						//word = word.replaceAll("[\\\"\\(\\)\\-\\.\\:\\,\\;]", "");
						word = word.replaceAll("[\\\"\\(\\)\\.\\:\\,\\;\\}\\{\\?\\]\\[]", "");
						word = word.replaceAll("“","").replaceAll("14","").replaceAll("6","").replaceAll("20","");
						if (!word.trim().equals("")) {
							addToWordsMap(book, chapter, verse, word);
						}
					}
				}
			}
		}
		//System.out.println("Total No of Words: " + wordsMap.size());
		System.out.println("Completed Creating Words Map");

		String wordDocFolder = BibleConcordanceCreator.outputPath + "\\" + LANGUAGE_NAME + "\\";
		File wordDocFolderFile = new File(wordDocFolder);
		wordDocFolderFile.mkdirs();

		String wordDocFinalFileName = wordDocFolder + bible.getAbbr() + EXTENSION;
		File outputFile = new File(wordDocFinalFileName);

		XWPFDocument document = new XWPFDocument();

		doPageSettings(document);

		try {

			createMetaData(document);
			createTitlePage(document);
			createTitlePage(document);
			createBookDetailsPage(document);
			createThanksPage(document);
			createPDFIssuePage(document);

			XWPFParagraph paragraph = null;
			XWPFRun run = null;

			// Create Index
			createIndex(document);
			paragraph = document.createParagraph();
			run = paragraph.createRun();
			run.addBreak(BreakType.PAGE);
			System.out.println("Index Pages Creation completed");

			System.out.println("Concordance Creation started");
			for (Map.Entry<String, TreeSet<String>> entry : indexMap.entrySet()) {
				System.out.println("Processing the Letter :: " + entry.getKey());
				String indexLetter = entry.getKey();

				// if(!"ஆ".equals(indexLetter)) {
				// continue;
				// }

				TreeSet<String> wordList = entry.getValue();

				paragraph = document.createParagraph();
				paragraph.setAlignment(ParagraphAlignment.CENTER);
				paragraph.setStyle("Heading1");

				run = paragraph.createRun();
				run.setFontFamily(language.getDAY_TITLE_FONT());
				run.setFontSize(language.getDAY_TITLE_FONT_SIZE());
				paragraph.setSpacingBefore(18 * 20);
				run.setText(indexLetter);
				CTShd cTShd = run.getCTR().addNewRPr().addNewShd();
				cTShd.setVal(STShd.CLEAR);
				cTShd.setFill("ABABAB");
/*
				CTBookmark bookmark = paragraph.getCTP().addNewBookmarkStart();
				bookmark.setName(indexLetter);
				bookmark.setId(BigInteger.valueOf(uniqueBookMarkCounter));
				paragraph.getCTP().addNewBookmarkEnd().setId(BigInteger.valueOf(uniqueBookMarkCounter));
				uniqueBookMarkCounter++;
				run.setText(" ");
*/
				int counter = 1;
				for (String word : wordList) {
					if(LIMIT_OUTPUT) {
						if (counter > 1000) {
							break;
						}
					 	counter++;
					}

					paragraph = document.createParagraph();
					paragraph.setAlignment(ParagraphAlignment.BOTH);
					paragraph.setSpacingAfter(language.getPARAGRAPH_SPACING_AFTER());
					run = paragraph.createRun();
					run.setFontFamily(language.getVERSE_FONT());
					run.setFontSize(language.getCHAPTER_HEADING_FONT_SIZE());
					run.setBold(true);
					run.setText(word + " (" + wordsMap.get(word).size() + "): ");

					CTBookmark bookmark = paragraph.getCTP().addNewBookmarkStart();
					bookmark.setName(word);
					bookmark.setId(BigInteger.valueOf(uniqueBookMarkCounter));
					paragraph.getCTP().addNewBookmarkEnd().setId(BigInteger.valueOf(uniqueBookMarkCounter));
					uniqueBookMarkCounter++;
					run.setText(" ");
					
					for (VerseDetails verse : wordsMap.get(word)) {
						totalReferencesCount++;
						paragraph = document.createParagraph();
						paragraph.setSpacingAfter(language.getPARAGRAPH_SPACING_AFTER());
						run = paragraph.createRun();
						run.setFontFamily(language.getVERSE_FONT());
						run.setFontSize(10);// language.getVERSE_FONT_SIZE());
						run.setBold(false);
						if("en".equalsIgnoreCase(LANGUAGE_NAME)) {
							run.setText(StringUtils.capitalize(getOneLiner(word, verse)));
						}else {
							run.setText(getOneLiner(word, verse));
						}
					}
				}
			}
			System.out.println("Pages Creation completed");
			
			// Write the Document in file system

			FileOutputStream out = new FileOutputStream(outputFile);
			document.write(out);
			out.close();
			System.out.println("Concordance Created Successfully :: " + wordDocFinalFileName);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("FileNotFoundException occurs.." + e.getMessage());
		}

		outputStatistics();
		
		System.out.println("Total Time taken :: " + clock.elapsedTime() + " seconds");
	}

	private static void createThanksPage(XWPFDocument document) {
		XWPFParagraph paragraph = null;
		XWPFRun run = null;

		paragraph = document.createParagraph();
		paragraph.setAlignment(ParagraphAlignment.CENTER);
		run = paragraph.createRun();
		run.addBreak();run.addBreak();run.addBreak();run.addBreak();run.addBreak();
		run.setFontFamily(language.getString(BibleConcordanceLanguage.STR_THANKS_FONT));
		run.setFontSize(language.getInt(BibleConcordanceLanguage.STR_THANKS_FONT_SIZE));
		run.setBold(true);
		run.setText(language.getString(BibleConcordanceLanguage.STR_THANKS_TO));
		run.addBreak();
		
		run = paragraph.createRun();
		run.setFontFamily(language.getString(BibleConcordanceLanguage.STR_THANKS_FONT));
		run.setFontSize(language.getInt(BibleConcordanceLanguage.STR_THANKS_FONT_SIZE));
		run.setBold(false);
		run.setText(language.getString(BibleConcordanceLanguage.STR_THANKS_TO_DETAILS));

		run.addBreak(BreakType.PAGE);
	}

	private static void createBookDetailsPage(XWPFDocument document) {
		XWPFParagraph paragraph = null;
		XWPFRun run = null;

		paragraph = document.createParagraph();
		paragraph.setAlignment(ParagraphAlignment.LEFT);
		run = paragraph.createRun();
		run.setFontFamily(language.getString(BibleConcordanceLanguage.BOOK_DETAILS_FONT));
		run.setFontSize(language.getInt(BibleConcordanceLanguage.BOOK_DETAILS_FONT_SIZE));
		run.setText(language.getString(BibleConcordanceLanguage.STR_BOOK_NO));
		run.addBreak();

		run = paragraph.createRun();
		run.setFontFamily(language.getString(BibleConcordanceLanguage.BOOK_DETAILS_FONT));
		run.setFontSize(language.getInt(BibleConcordanceLanguage.BOOK_DETAILS_FONT_SIZE));
		run.setText(language.getString(BibleConcordanceLanguage.STR_BOOK_EDITION));
		run.addBreak();
		run.addBreak();

		run = paragraph.createRun();
		run.setFontFamily(language.getString(BibleConcordanceLanguage.BOOK_DETAILS_FONT));
		run.setFontSize(language.getInt(BibleConcordanceLanguage.BOOK_DETAILS_FONT_SIZE));
		run.setBold(true);
		run.setText(BibleConcordanceLanguage.STR_AUTHOR);
		run.setBold(false);
		run.setText(language.getString(BibleConcordanceLanguage.STR_AUTHOR_DETAILS));
		run.addBreak();
		run.addBreak();

		run = paragraph.createRun();
		run.setFontFamily(language.getString(BibleConcordanceLanguage.BOOK_DETAILS_FONT));
		run.setFontSize(language.getInt(BibleConcordanceLanguage.BOOK_DETAILS_FONT_SIZE));
		run.setBold(true);
		run.setText(BibleConcordanceLanguage.STR_COPYRIGHT);
		run.setBold(false);
		run.addCarriageReturn();
		run.setText(language.getString(BibleConcordanceLanguage.STR_COPYRIGHT_DETAILS));
		run.addBreak();
		run.addBreak();

		run = paragraph.createRun();
		run.setFontFamily(language.getString(BibleConcordanceLanguage.BOOK_DETAILS_FONT));
		run.setFontSize(language.getInt(BibleConcordanceLanguage.BOOK_DETAILS_FONT_SIZE));
		run.setBold(true);
		run.setText(BibleConcordanceLanguage.STR_DOWNLOAD);
		run.setBold(false);
		run.addCarriageReturn();
		run.setText(language.getString(BibleConcordanceLanguage.STR_DOWNLOAD_DETAILS));
		run.addBreak();
		run.addBreak();

		run = paragraph.createRun();
		run.setFontFamily(language.getString(BibleConcordanceLanguage.BOOK_DETAILS_FONT));
		run.setFontSize(language.getInt(BibleConcordanceLanguage.BOOK_DETAILS_FONT_SIZE));
		run.setBold(true);
		run.setText(BibleConcordanceLanguage.STR_CONTACT_US);
		run.setBold(false);
		run.addCarriageReturn();
		run.setText(language.getString(BibleConcordanceLanguage.STR_CONTACT_US_DETAILS));
		run.addBreak();
		run.addBreak();

		run.addBreak(BreakType.PAGE);
	}

	private static String getOneLiner(String word, VerseDetails verse) {
		int beginningIndex = 0;
		int endingIndex = 0;
		String verseText = verse.getVerse();
		if("en".equalsIgnoreCase(LANGUAGE_NAME)) {
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
			System.out.println(
					verseText + " - " + verse.getBook() + " " + verse.getChapter() + ":" + verse.getVerseNo());
			e.printStackTrace();
		}
		System.out.println("ERROR for the word " + word);
		System.out.println(
				verseText + " - " + verse.getBook() + " " + verse.getChapter() + ":" + verse.getVerseNo());
		return "ERROR";
	}

	private static void doPageSettings(XWPFDocument document) {
		CTDocument1 doc = document.getDocument();
		CTBody body = doc.getBody();

		if (!body.isSetSectPr()) {
			body.addNewSectPr();
		}

		CTSectPr section = body.getSectPr();

		if (!section.isSetPgSz()) {
			section.addNewPgSz();
		}
		CTPageSz pageSize = section.getPgSz();
		pageSize.setOrient(STPageOrientation.PORTRAIT);
		pageSize.setW(BigInteger.valueOf(595 * 20));
		pageSize.setH(BigInteger.valueOf(842 * 20));
		System.out.println("Page Setting completed");
	}

	private static void createMetaData(XWPFDocument document) {
		CoreProperties props = document.getProperties().getCoreProperties();
		// props.setCreated("2019-08-14T21:00:00z");
		props.setLastModifiedByUser(language.getSTR_AUTHOR());
		props.setCreator(language.getSTR_CREATOR());
		// props.setLastPrinted("2019-08-14T21:00:00z");
		// props.setModified("2019-08-14T21:00:00z");
		try {
			document.getProperties().commit();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Meta Data Creation completed");
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

	private static void createTitlePage(XWPFDocument document) {
		XWPFParagraph paragraph = null;
		XWPFRun run = null;

		paragraph = document.createParagraph();
		paragraph.setAlignment(ParagraphAlignment.CENTER);
		run = paragraph.createRun();
		run.setFontFamily(language.getSUB_TITLE_1_FONT());
		run.setFontSize(language.getSUB_TITLE_1_FONT_SIZE());
		run.setText(language.getSUB_TITLE_1());
		run.addBreak();
		run.addBreak();

		if (language.getSUB_TITLE_2().contains("\n")) {
			String[] titles = language.getSUB_TITLE_2().split("\n");
			for (String title : titles) {
				paragraph = document.createParagraph();
				paragraph.setAlignment(ParagraphAlignment.CENTER);
				run = paragraph.createRun();
				run.setFontFamily(language.getSUB_TITLE_2_FONT());
				run.setFontSize(language.getSUB_TITLE_2_FONT_SIZE());
				run.setText(title);
			}
		}

		paragraph = document.createParagraph();
		paragraph.setAlignment(ParagraphAlignment.CENTER);
		run = paragraph.createRun();
		run.setFontFamily(language.getSUB_TITLE_3_FONT());
		run.setFontSize(language.getSUB_TITLE_3_FONT_SIZE());
		run.addBreak();
		run.addBreak();
		run.addBreak();
		run.addBreak();
		run.addBreak();
		run.addBreak();
		run.addBreak();
		run.setText(language.getSUB_TITLE_3());
		paragraph = document.createParagraph();
		paragraph.setAlignment(ParagraphAlignment.CENTER);
		run = paragraph.createRun();
		run.setFontFamily(language.getSUB_TITLE_4_FONT());
		run.setFontSize(language.getSUB_TITLE_4_FONT_SIZE());
		run.setText(language.getSUB_TITLE_4());

		run.addBreak();
		run.addBreak();
		paragraph = document.createParagraph();
		paragraph.setAlignment(ParagraphAlignment.CENTER);
		run = paragraph.createRun();
		run.setFontFamily(language.getMORE_INFO_FONT());
		run.setFontSize(language.getMORE_INFO_FONT_SIZE());
		run.setText(language.getMORE_INFO());

		run.addBreak(BreakType.PAGE);
		System.out.println("Title Page Creation completed");
	}

	private static void createPDFIssuePage(XWPFDocument document) {
		XWPFParagraph paragraph = null;
		XWPFRun run = null;
		paragraph = document.createParagraph();
		paragraph.setAlignment(ParagraphAlignment.CENTER);
		run = paragraph.createRun();
		run.setFontFamily(language.getSTR_PDF_INDEX_ISSUE_FONT());
		run.setFontSize(language.getSTR_PDF_INDEX_ISSUE_FONT_SIZE());
		run.setText(language.getSTR_PDF_INDEX_ISSUE_ENGLISH());
		run.addBreak();
		run.addBreak();
		paragraph = document.createParagraph();
		paragraph.setAlignment(ParagraphAlignment.CENTER);
		run = paragraph.createRun();
		run.setFontFamily(language.getSTR_PDF_INDEX_ISSUE_FONT());
		run.setFontSize(language.getSTR_PDF_INDEX_ISSUE_FONT_SIZE());
		run.setText(language.getSTR_PDF_INDEX_ISSUE());

		run.addBreak(BreakType.PAGE);
	}

	private static void createIndex(XWPFDocument document) throws Exception {

		if (indexMap.isEmpty()) {
			return;
		}

		System.out.println("Index Creation Started...");
		XWPFParagraph paragraph;
		XWPFRun run = null;

		paragraph = document.createParagraph();
		paragraph.setAlignment(ParagraphAlignment.CENTER);
		paragraph.setStyle("Heading1");

		//Index Page Heading
		run = paragraph.createRun();
		run.setFontFamily(language.getSTR_INDEX_TITLE_FONT());
		run.setFontSize(language.getSTR_INDEX_TITLE_FONT_SIZE());
		run.setText(language.getSTR_INDEX_TITLE());
		CTShd cTShd = run.getCTR().addNewRPr().addNewShd();
		cTShd.setVal(STShd.CLEAR);
		cTShd.setFill("ABABAB");

		CTBookmark bookmark = paragraph.getCTP().addNewBookmarkStart();
		bookmark.setName(language.getSTR_INDEX_TITLE());
		bookmark.setId(BigInteger.valueOf(uniqueBookMarkCounter));
		paragraph.getCTP().addNewBookmarkEnd().setId(BigInteger.valueOf(uniqueBookMarkCounter));
		uniqueBookMarkCounter++;
		
		paragraph = document.createParagraph();
		paragraph.setSpacingAfter(language.getPARAGRAPH_SPACING_AFTER());
		for (Map.Entry<String, TreeSet<String>> entry : indexMap.entrySet()) {
			String indexLetter = entry.getKey();
			createAnchorLink(paragraph, indexLetter + " (" + entry.getValue().size() +")", language.getString(BibleConcordanceLanguage.STR_INDEX_BOOKMARK_PREFIX) + indexLetter, false);
		}
		
		for (Map.Entry<String, TreeSet<String>> entry : indexMap.entrySet()) {
			String indexLetter = entry.getKey();
			
			paragraph = document.createParagraph();
			paragraph.setAlignment(ParagraphAlignment.CENTER);
			run = paragraph.createRun();
			//run.addBreak(BreakType.PAGE);
			
			run.setFontFamily(language.getSTR_INDEX_FONT());
			run.setFontSize(language.getSTR_INDEX_FONT_SIZE());
			run.setText(indexLetter);
			cTShd = run.getCTR().addNewRPr().addNewShd();
			cTShd.setVal(STShd.CLEAR);
			cTShd.setFill("ABABAB");

			bookmark = paragraph.getCTP().addNewBookmarkStart();
			bookmark.setName(language.getString(BibleConcordanceLanguage.STR_INDEX_BOOKMARK_PREFIX) + indexLetter);
			bookmark.setId(BigInteger.valueOf(uniqueBookMarkCounter));
			paragraph.getCTP().addNewBookmarkEnd().setId(BigInteger.valueOf(uniqueBookMarkCounter));
			uniqueBookMarkCounter++;
			
			paragraph = document.createParagraph();
			paragraph.setSpacingAfter(language.getPARAGRAPH_SPACING_AFTER());
			int counter = 1;
			for(String word: entry.getValue()) {
				if(LIMIT_OUTPUT) {
					if (counter > 10) {
						break;
					}
				 	counter++;
				}
				createAnchorLink(paragraph, word + " (" + wordsMap.get(word).size() + ")", word, true);
			}
			
		}

		System.out.println("Index Creation Completed...");

	}/*
		 * 
		 * private static void createSubIndex(XWPFDocument document) throws Exception {
		 * 
		 * if (indexMap.isEmpty()) { return; }
		 * 
		 * System.out.println("Sub Index Creation Started..."); XWPFParagraph paragraph;
		 * XWPFRun run = null;
		 * 
		 * paragraph = document.createParagraph();
		 * paragraph.setAlignment(ParagraphAlignment.CENTER);
		 * paragraph.setStyle("Heading1");
		 * 
		 * //Sub Index Page Heading run = paragraph.createRun();
		 * run.setFontFamily(language.getSTR_INDEX_TITLE_FONT());
		 * run.setFontSize(language.getSTR_INDEX_TITLE_FONT_SIZE());
		 * run.setText(language.getSTR_INDEX_TITLE()); CTShd cTShd =
		 * run.getCTR().addNewRPr().addNewShd(); cTShd.setVal(STShd.CLEAR);
		 * cTShd.setFill("ABABAB");
		 * 
		 * CTBookmark bookmark = paragraph.getCTP().addNewBookmarkStart();
		 * bookmark.setName(language.getSTR_INDEX_TITLE());
		 * bookmark.setId(BigInteger.valueOf(uniqueBookMarkCounter));
		 * paragraph.getCTP().addNewBookmarkEnd().setId(BigInteger.valueOf(
		 * uniqueBookMarkCounter)); uniqueBookMarkCounter++;
		 * 
		 * paragraph = document.createParagraph();
		 * paragraph.setSpacingAfter(language.getPARAGRAPH_SPACING_AFTER()); for
		 * (Map.Entry<String, TreeSet<String>> entry : indexMap.entrySet()) { String
		 * indexLetter = entry.getKey(); createAnchorLink(paragraph, indexLetter,
		 * indexLetter, false); }
		 * 
		 * for (Map.Entry<String, TreeSet<String>> entry : indexMap.entrySet()) { String
		 * indexLetter = entry.getKey();
		 * 
		 * paragraph = document.createParagraph();
		 * paragraph.setAlignment(ParagraphAlignment.CENTER); run =
		 * paragraph.createRun(); run.addBreak(BreakType.PAGE);
		 * 
		 * run.setFontFamily(language.getSTR_INDEX_FONT());
		 * run.setFontSize(language.getSTR_INDEX_FONT_SIZE()); run.setText(indexLetter);
		 * cTShd = run.getCTR().addNewRPr().addNewShd(); cTShd.setVal(STShd.CLEAR);
		 * cTShd.setFill("ABABAB");
		 * 
		 * bookmark = paragraph.getCTP().addNewBookmarkStart();
		 * bookmark.setName(indexLetter);
		 * bookmark.setId(BigInteger.valueOf(uniqueBookMarkCounter));
		 * paragraph.getCTP().addNewBookmarkEnd().setId(BigInteger.valueOf(
		 * uniqueBookMarkCounter)); uniqueBookMarkCounter++;
		 * 
		 * paragraph = document.createParagraph();
		 * paragraph.setSpacingAfter(language.getPARAGRAPH_SPACING_AFTER()); int counter
		 * = 1; for(String word: entry.getValue()) { if(LIMIT_OUTPUT) { if (counter >
		 * 10) { break; } counter++; } createAnchorLink(paragraph, word, word, true); }
		 * 
		 * }
		 * 
		 * System.out.println("Sub Index Creation Completed...");
		 * 
		 * }
		 */

	private static void createAnchorLink(XWPFParagraph paragraph, String linkText, String bookMarkName, boolean subIndex) {
		CTHyperlink cthyperLink = paragraph.getCTP().addNewHyperlink();
		cthyperLink.setAnchor(bookMarkName);
		cthyperLink.addNewR();
		XWPFHyperlinkRun hyperlinkrun = new XWPFHyperlinkRun(cthyperLink, cthyperLink.getRArray(0), paragraph);
		if(subIndex) {
			hyperlinkrun.setFontFamily(language.getString(BibleConcordanceLanguage.STR_SUB_INDEX_FONT));
			hyperlinkrun.setFontSize(language.getInt(BibleConcordanceLanguage.STR_SUB_INDEX_FONT_SIZE));
		}else {
			hyperlinkrun.setFontFamily(language.getSTR_INDEX_FONT());
			hyperlinkrun.setFontSize(language.getSTR_INDEX_FONT_SIZE());
		}		
		hyperlinkrun.setText(linkText);// .replace("1-1","1"));
		hyperlinkrun.setColor("0000FF");
		hyperlinkrun.setUnderline(UnderlinePatterns.SINGLE);
		XWPFRun run = paragraph.createRun();
		run.setFontFamily(language.getSTR_INDEX_FONT());
		run.setFontSize(language.getSTR_INDEX_FONT_SIZE());
		//run.setText("   ");
		run.addCarriageReturn();
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
		if("en".equalsIgnoreCase(LANGUAGE_NAME)) {
			if(word.length()==1) {
				return;
			}
			word = StringUtils.capitalize(word);
		}
		List<VerseDetails> list = wordsMap.get(word);
		boolean alreadyExists = false;
		String bookName = null;
		if(USE_SHORT_NAMES) {
			bookName = book.getShortName();
		}else if (CHANGE_BOOK_NAMES_TO_NON_ENGLISH) {
			bookName = book.getLongName();//getBookName(book.getBook(), getNonEnglishBookNames());
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
			if (wordsList == null) {
				wordsList = new TreeSet<String>();
				indexMap.put(actualLetter, wordsList);
			}
			wordsList.add(word);
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