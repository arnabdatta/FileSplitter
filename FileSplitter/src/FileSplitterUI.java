import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class FileSplitterUI implements ActionListener {

	JFrame frame;
	JPanel panel;
	JPanel panel1;
	JLabel label;
	JButton btnBrowseSrc;
	JButton btnBrowseTrgt;
	JButton btnStart;
	JLabel lblTargetDir;
	JLabel lblStatus;
	JLabel lblSplitAt;
	JTextField txtSplitAt;
	JLabel lblHeaderAtStart;
	JCheckBox chkHeaderAtStart;
	JLabel lblHeaderInEachFile;
	JCheckBox chkAddHeaderInEachFile;

	String srcFilePath;
	String trgtDirPath;

	public FileSplitterUI() {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		frame = new JFrame();

		panel = new JPanel();

		panel1 = new JPanel();

		label = new JLabel("No File selected");
		label.setPreferredSize(new Dimension(300, 20));
		lblTargetDir = new JLabel("No directory selected");

		// button to open open dialog
		btnBrowseSrc = new JButton("Select Source file");
		btnBrowseSrc.addActionListener(this);

		btnBrowseTrgt = new JButton("Select Target Directory");
		btnBrowseTrgt.addActionListener(this);

		lblSplitAt = new JLabel("Split at");
		txtSplitAt = new JTextField();

		lblHeaderAtStart = new JLabel("File has header at start");
		chkHeaderAtStart = new JCheckBox();

		lblHeaderInEachFile = new JLabel("Add header to each file");
		chkAddHeaderInEachFile = new JCheckBox();

		btnStart = new JButton("Split");
		btnStart.addActionListener(this);

		lblStatus = new JLabel("");

		panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 0, 30));
		GridLayout gl = new GridLayout(5, 2);
		gl.setVgap(10);
		gl.setHgap(10);

		panel.setLayout(gl);
		panel.add(btnBrowseSrc);
		panel.add(label);
		panel.add(btnBrowseTrgt);
		panel.add(lblTargetDir);
		panel.add(lblSplitAt);
		panel.add(txtSplitAt);
		panel.add(lblHeaderAtStart);
		panel.add(chkHeaderAtStart);
		panel.add(lblHeaderInEachFile);
		panel.add(chkAddHeaderInEachFile);

		panel1.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel1.add(btnStart);
		panel1.add(lblStatus);

		frame.setResizable(false);
		frame.add(panel, BorderLayout.NORTH);
		frame.add(panel1, BorderLayout.PAGE_END);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("File Splitter");

		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String args[]) {
		new FileSplitterUI();
	}

	@Override
	public void actionPerformed(ActionEvent a) {
		// source file
		if (a.getSource() == btnBrowseSrc) {
			JFileChooser jf = new JFileChooser();
			int returnVal = jf.showOpenDialog(panel);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = jf.getSelectedFile();
				// This is where a real application would open the file.
				srcFilePath = file.getAbsolutePath();
				label.setText(srcFilePath);
			}
		}

		// target dir
		if (a.getSource() == btnBrowseTrgt) {
			JFileChooser jft = new JFileChooser();
			jft.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int returnVal = jft.showSaveDialog(panel);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File dir = jft.getSelectedFile();
				// This is where a real application would open the file.
				trgtDirPath = dir.getAbsolutePath();
				lblTargetDir.setText(trgtDirPath);
			}
		}

		// start split
		if (a.getSource() == btnStart) {
			FileSplitterProcess fileSplitterProcess = new FileSplitterProcess();
			if (fileSplitterProcess.loadFile(srcFilePath)) {
				boolean success = fileSplitterProcess.processFiles(srcFilePath, Integer.parseInt(txtSplitAt.getText()),
						chkAddHeaderInEachFile.isSelected(), chkHeaderAtStart.isSelected());
				if (success == true) {
					lblStatus.setText("Process Completed!");
				} else {
					lblStatus.setText("Process failed!");
				}
			}
		}
	}
}
