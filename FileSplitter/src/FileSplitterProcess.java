import java.io.File; // Import the File class
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException; // Import the IOException class to handle errors
import java.util.Scanner;

public class FileSplitterProcess {

	File mFile;

	public boolean loadFile(String fileName) {
		try {
			mFile = new File(fileName);

			Scanner fileReader = new Scanner(mFile);
			while (fileReader.hasNextLine()) {
				break;
			}
			fileReader.close();
			return true;

		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
			e.printStackTrace();
			return false;
		}
	}

	public boolean processFiles(String sourceFile, int splitAt, boolean headerAtStart, boolean putHeaderinAllFiles) {
		int counter;
		int fileCounter;
		String firstLine = "";
		FileWriter fileWriter = null;
		try {
			Scanner fileReader = new Scanner(mFile);
			counter = 0;
			fileCounter = 1;
			while (fileReader.hasNextLine()) {
				String data = fileReader.nextLine();

				if (counter == 0) {
					if (headerAtStart == true) {
						firstLine = data.toString(); // get the first line to put it in next files
						data = fileReader.nextLine();
					}
				}

				// split the file
				if (counter % splitAt == 0) {
					if (fileWriter != null) {
						fileWriter.close();
						fileCounter++;
					}

					String fileExt = sourceFile.substring(sourceFile.indexOf('.'));
					String targetFileName = sourceFile.substring(0, sourceFile.indexOf('.')) + "" + fileCounter
							+ fileExt;

					fileWriter = new FileWriter(targetFileName);

					if (putHeaderinAllFiles == true) {
						fileWriter.write(firstLine);
						fileWriter.write(System.lineSeparator());
					}
				}

				fileWriter.write(data.toString());
				fileWriter.write(System.lineSeparator());
				counter++;
			}
			fileReader.close();
			if (fileWriter != null) {
				fileWriter.close();
			}

			return true;
		} catch (

		FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}
}
