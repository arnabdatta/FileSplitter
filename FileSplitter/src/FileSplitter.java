
public class FileSplitter {

	public static void main(String args[]) {
		//Get the file name from command. error handling required
		String fileName = args[0];
		//Get the Split at number
		int splitAt = Integer.parseInt(args[1]);
		FileSplitterProcess fileSplitterProcess = new FileSplitterProcess();
		if (fileSplitterProcess.loadFile(fileName)) {
			boolean success = fileSplitterProcess.processFiles(fileName, splitAt, false, false);
			if (success == true) {
				System.out.println("Process Completed!");
			}
		}
	}
}
