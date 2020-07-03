
public class FileSplitter {

	public static void main(String args[]) {
		// Get the file name from command. error handling required
		String fileName = args[0];
		// Get the Split at number
		int splitAt = Integer.parseInt(args[1]);

		boolean headerAtStart = false;
		try {
			headerAtStart = Boolean.parseBoolean(args[2]);
		} catch (ArrayIndexOutOfBoundsException e) {
			headerAtStart = false;
		}

		boolean putHeaderinAllFiles = false;
		try {
			putHeaderinAllFiles = Boolean.parseBoolean(args[3]);
		} catch (ArrayIndexOutOfBoundsException e) {
			putHeaderinAllFiles = false;
		}
		// process file and create new files
		FileSplitterProcess fileSplitterProcess = new FileSplitterProcess();
		if (fileSplitterProcess.loadFile(fileName)) {
			boolean success = fileSplitterProcess.processFiles(fileName, splitAt, headerAtStart, putHeaderinAllFiles);
			if (success == true) {
				System.out.println("Process Completed!");
			}
		}
	}
}
