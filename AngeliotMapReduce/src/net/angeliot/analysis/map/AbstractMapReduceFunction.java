package net.angeliot.analysis.map;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import net.angeliot.analysis.master.Master;

public abstract class AbstractMapReduceFunction {
	String filePath;

	public void setDataPath(String filePath) {
		this.filePath = filePath;
	}

	List<String[]> mapResult = new ArrayList<String[]>();

	public List<String[]> mapProcess() {
		File file = new File(filePath);

		try {
			BufferedReader in = new BufferedReader(new FileReader(file));
			String s;

			while ((s = in.readLine()) != null) {
				String stringArray[] = s.split(",");
				String valueArray[] = new String[stringArray.length - 1];
				System.arraycopy(stringArray, 1, valueArray, 0, stringArray.length - 1);
				mapResult.add(map(stringArray[0], valueArray));
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	abstract public String[] map(String key, String valueArray[]);

	public void reduceProcess(Master master) {
		for (String stringArray[] : mapResult) {
			if (stringArray != null) {
				String valueArray[] = new String[stringArray.length - 1];
				System.arraycopy(stringArray, 1, valueArray, 0, stringArray.length - 1);
				master.putReduceMap(stringArray[0], valueArray);
			}
		}
	}

	abstract public String[] reduce(String key, String valueArray1[], String valueArray2[]);
}

class ReduceComparator implements Comparator {

	@Override
	public int compare(Object o1, Object o2) {
		String item1[] = (String[]) o1;
		String item2[] = (String[]) o2;
		if (item1 == null) {
			return 1;
		}
		if (item2 == null) {
			return -1;
		}
		return item1[0].compareTo(item2[0]);
	}

}
