package net.angeliot.analysis.master;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.angeliot.analysis.file.FileSplitter;
import net.angeliot.analysis.map.AbstractMapReduceFunction;
import net.angeliot.analysis.worker.Worker;

public class Master {
	int nodeNumber = 3;

	String applicationName;
	Class program;
	FileSplitter fileSplitter = new FileSplitter();

	public void setProgram(String applicationName, String rawDataPath, Class class1) {
		this.applicationName = applicationName;
		this.program = class1;

		try {
			fileSplitter.setN(nodeNumber).post(rawDataPath);
			String splittedDataPath[] = fileSplitter.getSplittedFilePath();

			for (String dataPath : splittedDataPath) {
				Worker worker = new Worker(this);
				worker.setDataPath(dataPath);
				worker.setProgram((AbstractMapReduceFunction) class1.newInstance());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	Map reduceMap = new HashMap();

	synchronized public void putReduceMap(String key, String[] valueArray) {
		List valueList = (List) reduceMap.get(key);
		if (valueList == null) {
			valueList = new ArrayList();
			valueList.add(valueArray);
			reduceMap.put(key, valueList);
		} else {
			valueList.add(valueArray);
		}

	}
}
