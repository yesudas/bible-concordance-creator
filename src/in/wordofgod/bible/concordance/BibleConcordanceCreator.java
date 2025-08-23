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

		switch (outputFormat.toUpperCase()) {
		case Constants.WORD_DOCUMENT:
			WordDocument.createWordDocument();
			break;
		case Constants.WORD_DOCUMENT_BY_LETTERS:
			WordDocumentByLetters.createWordDocument();
			break;
		default:
			System.out.println("Given format is not supported, pls check the supported format below.");
			printHelpMessage();
			break;
		}
	}

	private static boolean validateInput(String[] args) throws URISyntaxException {
		if (args.length < 2) {
			System.out.println("Please give additional details in the expected format..");
			printHelpMessage();
			return false;
		} else {
			outputFormat = args[0];

			if (args[1] != null) {
				bibleSourcePath = args[1];
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
		System.out.println("\nSupported formats:\n\t1. WordDocument\n\t2. WordDocumentByLetters\n");
		System.out.println(
				"\nSyntax to run this program:\njava -jar bible-concordance-creator.jar [OUTPUT-FORMAT] [SOURCE-BIBLE-TEXT-FILE-PATH] [BIBLE-INFORMATION-FILE-PATH]");
		System.out.println(
				"\n[BIBLE-INFORMATION-FILE-PATH] is optional, if not given program will consider english book names");
		System.out.println(
				"\nExample 1: java -jar bible-concordance-creator.jar WordDocument C:/taOV.ont C:/taOV-information.ini");
	}

}
