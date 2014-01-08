import io.parse.QAParser;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;


import relatedness.AbstractRelatednessScorer;
import relatedness.CosineSimilarityRelatedness;
import relatedness.FMeasureRelatedness;
import relatedness.LeskSimilarity;
import relatedness.PrecisionRelatedness;
import relatedness.RecallRelatedness;
import data.QASet;

public class TrainerRunner {
	public static void main(String[] args) throws ParserConfigurationException,
			SAXException, IOException, InstantiationException,
			IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, ClassNotFoundException,
			IllegalAccessException {
		QAParser p = new QAParser();
		QASet train = p.parse(new File("data/train/beetle/"));
		Runner.train(null, train, new AbstractRelatednessScorer[] {

		new CosineSimilarityRelatedness(null, false),
				new FMeasureRelatedness("", false),
				new RecallRelatedness(null, false),
				new PrecisionRelatedness(null, false),
				new LeskSimilarity(null, false) }, "finalmodel.model");
	}
}
