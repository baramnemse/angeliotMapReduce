package net.angeliot.analysis.master;

import java.io.IOException;

import net.angeliot.analysis.file.FileSplitter;
import net.angeliot.analysis.worker.Worker;

public class Master {
	int nodeNumber=3;
	
	String applicationName;
	Object program;
	FileSplitter fileSplitter = new FileSplitter();

	public void setProgram(String applicationName,String rawDataPath, Object program){
		this.applicationName = applicationName;
		this.program = program;
		
		try {
			fileSplitter.setN(nodeNumber).post(rawDataPath);
			String splittedDataPath[] = fileSplitter.getSplittedFilePath();
			
			for(String dataPath:splittedDataPath){
				Worker worker = new Worker();
				worker.setDataPath(dataPath);
				worker.setProgram(program);
				worker.run();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
