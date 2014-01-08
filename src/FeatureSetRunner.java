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

public class FeatureSetRunner {
	public static void main(String[] args) throws ParserConfigurationException,
			SAXException, IOException, InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		QAParser p = new QAParser();
		QASet test = p.parse(new File("data/train/beetle"));
		Runner.run(null, test, test, new AbstractRelatednessScorer[] {
				new CosineSimilarityRelatedness(null, false),
				new FMeasureRelatedness("", false),
				new RecallRelatedness(null, false),
				new PrecisionRelatedness(null, false),
				new LeskSimilarity(null, false) }, "feature-set.csv");

	}
}
