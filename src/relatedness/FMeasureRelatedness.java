package relatedness;

import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

import corpus.AbstractCorpus;
import data.DataUnit;
import data.QASet;
import edu.stanford.nlp.ling.CoreAnnotations.LemmaAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

/**
 * 
 * @author Jaromir
 * 
 */
public class FMeasureRelatedness extends AbstractRelatednessScorer {

	StanfordCoreNLP pipeline;

	public FMeasureRelatedness(String address, boolean forceTrain) {
		super(address, forceTrain);
		Properties props = new Properties();
		props.put("annotators", "tokenize, ssplit, pos, lemma");
		pipeline = new StanfordCoreNLP(props);
	}

	@Override
	protected void doTrain(AbstractCorpus[] corpora, QASet dataset) {
		// do nothing
	}

	public double score(DataUnit[] references, DataUnit answer) {
		double max = -99999;
		Set<String> refSet = new TreeSet<>();
		Set<String> ansSet = new TreeSet<>();
		for (DataUnit ref : references) {
			String preparedRef = prepareText(ref.getText());
			String[] refList = preparedRef.split(" ");
			for (String item : refList) {
				refSet.add(item);
			}
		}
		String preparedAns = prepareText(answer.getText());
		String[] ansList = preparedAns.split(" ");
		for (String item : ansList) {
			ansSet.add(item);
		}
		double value = 0.0;
		double value2 = 0.0;
		for (String item : ansList) {
			if (refSet.contains(item)) {
				value++;
			}
		}
		for (String item : refSet) {
			if (ansSet.contains(item)) {
				value2++;
			}
		}

		double precision = value / new Double(ansList.length);
		double recall = value2 / ansSet.size();

		if (precision == 0.0 || recall == 0.0) {
			return 0.0;
		}

		if (((2.0 * precision * recall) / (precision + recall)) > max) {
			max = (2.0 * precision * recall) / (precision + recall);
		}
		return max;
	}

	private String prepareText(String words) {
		String text = words.trim().replaceAll("[^\\x00-\\x7F]", "")
				.replaceAll("[^\\w\\.\\s\\-]", "").replaceAll("[0-9]", "")
				.replaceAll("\\.", "").replaceAll("\\s+", " ");
		Annotation document = new Annotation(text);
		pipeline.annotate(document);
		List<CoreMap> sentences = document.get(SentencesAnnotation.class);
		StringBuilder builder = new StringBuilder();
		for (CoreMap sentence : sentences) {
			for (CoreLabel token : sentence.get(TokensAnnotation.class)) {
				String word = token.get(LemmaAnnotation.class);
				builder.append(word.toLowerCase());
				builder.append(" ");
			}
		}
		return builder.toString().trim();
	}

	@Override
	public String getName() {
		return "F-Measure";
	}

	@Override
	protected void doLoad(String address) {
	}

	@Override
	protected void doSave(String address) {
		// do nothing
	}

}
