package net.angeliot.analysis.worker;

import net.angeliot.analysis.map.AbstractMapReduceFunction;
import net.angeliot.analysis.master.Master;

public class Worker implements Runnable {
	public final int STATUS_READY = 0;
	public final int STATUS_WORKING = 1;
	int status = STATUS_READY;
	AbstractMapReduceFunction program;
	String dataPath;
	Master master;
	public Worker(Master master) {
		this.master = master;
	}

	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}

	public void setProgram(AbstractMapReduceFunction program) {
		this.program = program;
		program.setDataPath(dataPath);
		Thread thread = new Thread(this);
		thread.start();

	}

	public void run() {
		status = STATUS_WORKING;
		System.out.println("Map Processing is start:"+dataPath);
		program.mapProcess();
		program.shuffle(master);
		program.reduceProcess(master);
		status = STATUS_READY;
		System.out.println("Map Processing is complete:"+dataPath);
	}
}
