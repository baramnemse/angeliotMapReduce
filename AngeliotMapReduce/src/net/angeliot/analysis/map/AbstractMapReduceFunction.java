package net.angeliot.analysis.map;

import java.util.List;

public abstract class AbstractMapReduceFunction {
	public void read() {

	}

	public List<String[]> map(String key, String value) {
		return null;
	}
	
	public List<String[]> reduce(String key, String value) {
		return null;
	}
}
