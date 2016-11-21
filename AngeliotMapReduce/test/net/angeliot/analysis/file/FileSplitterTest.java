package net.angeliot.analysis.file;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class FileSplitterTest {

	@Test
	public void testPost() {
		FileSplitter splitter = new FileSplitter();
		try {
			splitter.post("/Users/jini/Desktop/seoul9.csv");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
