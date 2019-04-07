package subsetsum;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;

/**
 * This class reads an input groceries file. The format of the file is item_name, item_price
 * @author anuva
 *
 */
public class GroceriesFileReader {

	/**
	 * Read a comma separated (CSV) file from given file path
	 * @param filePath String representing relative file path of file to be read
	 * @return ArrayList of doubles representing item prices in input file
	 */
	public ArrayList<Double> readFile(String filePath) {
		ArrayList<Double> prices = new ArrayList<Double>();
		BufferedReader br = null;
		try {
			// wraps a buffered reader around file for buffering characters for more efficient reading
			br = new BufferedReader(new FileReader(new File(filePath)));
			for (int k = 0; br.ready(); k++) {
				String line = br.readLine();
				if(line == null) {
					throw new Exception("Please check input file contents");
				} else {
					String[] tokens = line.split(",");
					if(tokens.length == 0)
						throw new Exception("Please check input file contents");
					prices.add(Double.parseDouble(tokens[1]));
				}
			}
		} catch (NumberFormatException e) {
			printError(e,"\nOops. NumberFormatException! Please check file contents and try again. Exiting program.");
		} catch (FileNotFoundException e) {
			printError(e,"\nProblem opening the file! Please check file path is correct and that file exists at that location. Exiting program.");
		} catch (IOException ioe) {
			printError(ioe, "\nIOException! Exiting program.");
		} catch (Exception e) {
			printError(e, "Exception in parsing input file. Exiting program.");
		} finally {
			close(br);
		}
		return prices;
	}

	/**
	 * Close buffered reader
	 * @param br BufferedReader
	 */
	private void close(BufferedReader br) {
		try {
			br.close();
		} catch (IOException e) {
			printError(e, "\nIO Exception! Exiting program");
		}
	}

	/**
	 * Print stack trace and exit
	 * @param e Exception 
	 * @param msg String representing error message
	 */
	private void printError(Exception e, String msg) {
		System.out.println(msg);
		e.printStackTrace();			
		System.exit(1);
	}
}
