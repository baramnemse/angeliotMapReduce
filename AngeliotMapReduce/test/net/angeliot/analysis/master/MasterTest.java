package net.angeliot.analysis.master;

import org.junit.Test;

import net.angeliot.analysis.map.AbstractMapReduceFunction;

public class MasterTest {

	@Test
	public void testSetProgram() {
		Master master = new Master();
		master.setProgram("WordCount", "/Users/jini/Desktop/seoul9.csv", WordCount.class);
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

class WordCount extends AbstractMapReduceFunction {
	public String[] map(String key, String valueArray[]) {
		String result[] = new String[2];
		result[0] = key + valueArray[0] + valueArray[1];
		result[1] = "1";
		// System.out.println(result[0] + " " + result[1]);
		return result;
	}

	public String[] reduce(String key, String valueArray1[], String valueArray2[]) {
		String result[] = new String[2];
		result[0] = key;
		result[1] = ""+(Long.parseLong(valueArray1[0])+Long.parseLong(valueArray2[0]));
		// System.out.println(result[0] + " " + result[1]);
		return result;
	}
}