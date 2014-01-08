import io.parse.QAParser;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import data.QASet;
import relatedness.AbstractRelatednessScorer;
import relatedness.FMeasureRelatedness;
import relatedness.LeskSimilarity;
import relatedness.PrecisionRelatedness;
import relatedness.RecallRelatedness;
import weka.classifiers.trees.RandomForest;

public class CrossValidationRunner {

	public static void main(String[] args) throws InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException,
			SecurityException, ParserConfigurationException, SAXException, IOException {
		QAParser p = new QAParser();
		QASet train = p.parse(new File("data/train/beetle/"));
		Runner.run(null, train, new AbstractRelatednessScorer[] {
				new FMeasureRelatedness("", false),
				new RecallRelatedness(null, false),
				new PrecisionRelatedness(null, false),
				new LeskSimilarity(null, false) }, new RandomForest());
	}

}
