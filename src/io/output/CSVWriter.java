package io.output;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import relatedness.AbstractRelatednessScorer;
import relatedness.FeatureSet;

/**
 * 
 * @author Salim
 * 
 */
public class CSVWriter {

	public static void write(String[] columnNames, List<FeatureSet> setList,
			AbstractRelatednessScorer[] scorers, String output)
			throws IOException {
		File out = new File(output);
		BufferedWriter bw = new BufferedWriter(new FileWriter(out));
		bw.write(columnNames[0] + "");
		for (int i = 1; i < columnNames.length; i++) {
			bw.write(", " + columnNames[i]);
		}
		bw.write("\n");
		for (FeatureSet fs : setList) {
			bw.write(fs.toCSVLine() + "\n");
		}
		bw.flush();
		bw.close();
	}
}
