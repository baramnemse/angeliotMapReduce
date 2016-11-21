package net.angeliot.analysis.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 파일을 분산 분석을 위해 N개로 분활해서 저장하는 클래스.
 * 
 * @author jini
 *
 */
public class FileSplitter {
	String splittedFilePath[];

	public String[] getSplittedFilePath() {
		return splittedFilePath;
	}

	/**
	 * 파일을 업로드한다.
	 * 
	 * @param filePath
	 *            파일패스.
	 * @throws IOException
	 */
	public FileSplitter post(String filePath) throws IOException {
		File file = new File(filePath);
		String fileName = file.getName();

		BufferedWriter fileWriter[] = new BufferedWriter[n];
		splittedFilePath = new String[n];

		for (int i = 0; i < fileWriter.length; i++) {
			splittedFilePath[i] = file.getParent() + "/" + fileName + i;
			fileWriter[i] = new BufferedWriter(new FileWriter(splittedFilePath[i]));
		}
		try {
			BufferedReader in = new BufferedReader(new FileReader(file));
			String s;

			int index = 0;
			while ((s = in.readLine()) != null) {
				// n 개에 나누어 저장한다.
				if (index / n > 1) {
					fileWriter[index % n].newLine();
				}
				fileWriter[index % n].write(s);
				index++;
			}
			in.close();
			for (int i = 0; i < fileWriter.length; i++) {
				fileWriter[i].flush();
				fileWriter[i].close();
			}
		} catch (IOException e) {
		}

		return this;
	}

	private int n = 3;

	/**
	 * 파일을 나눌 갯수를 설정한다.
	 * 
	 * @param n
	 *            나눌 갯수.
	 */
	public FileSplitter setN(int n) {
		return this;
	}
}