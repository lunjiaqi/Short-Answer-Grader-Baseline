package samples;

import preProcess.StopWordTool;

public class StopWordExample {
	
	public static void main(String[] args) {
		String test= "have";
		StopWordTool s = new StopWordTool();
		System.out.println(s.isStopWord(test));
	}

}
