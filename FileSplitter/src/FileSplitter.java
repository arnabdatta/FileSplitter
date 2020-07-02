
public class FileSplitter {

	public static void main(String args[]) {
		String fileName = args[0];
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
