/**
 * 
 */
package in.wordofgod.bible.concordance;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

/**
 * 
 */
public class BibleConcordanceCreator {

	public static String bibleSourcePath;
	public static String[] bibleSourcePaths;
	public static String bibleInformationPath;
	public static String outputFormat;
	public static String outputPath;

	/**
	 * @param args
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws URISyntaxException
	 */
	public static void main(String[] args) throws ParserConfigurationException, TransformerException,
			FileNotFoundException, IOException, URISyntaxException {

		if (!validateInput(args)) {
			return;
		}
		if (bibleSourcePath != null) {
			process();
		} else {
			if (bibleSourcePaths != null) {
				for (String bible : bibleSourcePaths) {
					System.out.println("Processing the bible: " + bible);
					bibleSourcePath = bible;
					if (bibleSourcePath.endsWith(".ont")) {
						bibleInformationPath = bibleSourcePath.replace(".ont", "-information.ini");
					} else {
						bibleInformationPath = bibleSourcePath.replace(".nt", "-information.ini");
					}
					process();
				}
			}
		}
	}

	public static void process() throws ParserConfigurationException, TransformerException, FileNotFoundException,
			IOException, URISyntaxException {
		if (bibleInformationPath == null) {
			if (bibleSourcePath.endsWith(".ont")) {
				bibleInformationPath = bibleSourcePath.replace(".ont", "-information.ini");
			} else {
				bibleInformationPath = bibleSourcePath.replace(".nt", "-information.ini");
			}
		}
		switch (outputFormat.toUpperCase()) {
		case Constants.WORD_DOCUMENT:
			WordDocument.createWordDocument();
			break;
		case Constants.WORD_DOCUMENT_BY_LETTERS:
			WordDocumentByLetters.createWordDocument();
			break;
		case Constants.JSON:
			Json.create();
			break;
		case Constants.SQLITE:
			SQLite.create();
			break;
		default:
			System.out.println("Given format is not supported, pls check the supported format below.");
			printHelpMessage();
			break;
		}
	}

	private static boolean validateInput(String[] args) throws URISyntaxException {
		if (args.length < 1) {
			System.out.println("Please give additional details in the expected format..");
			printHelpMessage();
			return false;
		} else {
			outputFormat = args[0];

			if (args[1] != null) {
				if (args[1].contains(",")) {
					bibleSourcePaths = args[1].split(",");
				} else {
					bibleSourcePath = args[1];
				}
			}

			if (args.length > 2 && args[2] != null) {
				bibleInformationPath = args[2];
			}

			// Get path of the running JAR
			File jarFile = new File(
					BibleConcordanceCreator.class.getProtectionDomain().getCodeSource().getLocation().toURI());

			// Get parent directory of the JAR
			outputPath = jarFile.getParentFile().getAbsolutePath() + "/Output/";

			/*
			 * outputPath = bibleSourcePath != null && bibleSourcePath.lastIndexOf(".") > 0
			 * ? bibleSourcePath.substring(0, bibleSourcePath.lastIndexOf(".")) :
			 * bibleSourcePath;
			 */
		}
		return true;
	}

	public static void printHelpMessage() {
		System.out.println("\nHelp on Usage of this program:");
		System.out.println("\nSupported formats:\n\t1. WordDocument\n\t2. WordDocumentByLetters\n\t3. Json\n");
		System.out.println(
				"\nSyntax to run this program:\njava -jar bible-concordance-creator.jar [OUTPUT-FORMAT] [SOURCE-BIBLE-TEXT-FILE-PATH] [BIBLE-INFORMATION-FILE-PATH]");
		System.out.println(
				"\n[BIBLE-INFORMATION-FILE-PATH] is optional, if not given program will consider english book names");
		System.out.println(
				"\nExample: java -jar bible-concordance-creator.jar WordDocument \"C:/taOV.ont\" \"C:/taOV-information.ini\"");
		System.out.println(
				"\nIf you want to create concordance for multiple bible versions together, then use the below format");
		System.out.println(
				"\n\tjava -jar bible-concordance-creator.jar [OUTPUT-FORMAT] [SOURCE-BIBLE-TEXT-FILE-PATH-1],[SOURCE-BIBLE-TEXT-FILE-PATH-2],[SOURCE-BIBLE-TEXT-FILE-PATH-1]");
		System.out.println(
				"\n\tExample: java -jar bible-concordance-creator.jar Json \"C:/bible1.ont,C:/bible2.ont,C:/bible3.ont\"");
	}

}
