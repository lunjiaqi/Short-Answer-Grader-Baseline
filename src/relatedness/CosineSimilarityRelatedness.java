package relatedness;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import preProcess.StopWordTool;

import corpus.AbstractCorpus;
import data.DataUnit;
import data.QASet;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.CoreAnnotations.LemmaAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

public class CosineSimilarityRelatedness extends AbstractRelatednessScorer {
	StanfordCoreNLP pipeline;
	public CosineSimilarityRelatedness(String address, boolean forceTrain) {
		super(address, forceTrain);
		Properties props = new Properties();
		props.put("annotators", "tokenize, ssplit, pos, lemma");
		pipeline = new StanfordCoreNLP(props);
	}

	@Override
	protected void doTrain(AbstractCorpus[] corpora, QASet dataset) {
	}

	@Override
	public double score(DataUnit[] references, DataUnit answer) {
		double sim = 0.0;
		double max = -99999999;
		String ansLem = lemmatization(answer);
		List<String> ansTokens = Arrays.asList(ansLem.toLowerCase().split(" "));
		HashSet<String> allTokens = new HashSet<String>(ansTokens);
		HashMap<String, Integer> fv1 = new HashMap<String, Integer>(); 
		HashMap<String, Integer> fv2 = new HashMap<String, Integer>();
		for (DataUnit ref : references) {
			String refLem = lemmatization(ref);
			List<String> refTokens = Arrays.asList(refLem.toLowerCase().split(" "));
			allTokens.addAll(refTokens);
			fv1 = featureVector(allTokens, ansTokens);
			fv2 = featureVector(allTokens, refTokens);
			sim = calculateCosineSimilarity(fv1, fv2);
			max = Math.max(max,sim);
		}
		return max;
	}
	
	private HashMap<String, Integer> featureVector(
			HashSet<String> allTokens, List<String> tokens) {
		HashMap<String, Integer> fv = new HashMap<String, Integer>();
		for (String s: allTokens){
			if (tokens.contains(s))
				fv.put(s, frequencyCount(tokens,s));
			else
				fv.put(s, 0);
		}

		return fv;
	}
	
	private int frequencyCount(List<String> array, String s) {
		int count = 0;
		for (String s1 : array)
			if (s1.equalsIgnoreCase(s))
				count ++;
		return count;
	}
	private double calculateCosineSimilarity(HashMap<String, Integer> fv1, HashMap<String, Integer> fv2) {
        Double similarity = 0.0;
        Double sum = 0.0;        // the numerator of the cosine similarity
        Double fnorm = 0.0;        // the first part of the denominator of the cosine similarity
        Double snorm = 0.0;        // the second part of the denominator of the cosine similarity
        Set<String> fkeys = fv1.keySet();
        Iterator<String> fit = fkeys.iterator();
        while (fit.hasNext()) {
                String featurename = fit.next();
                boolean containKey = fv2.containsKey(featurename);
                if (containKey) {
                        sum = sum + fv1.get(featurename) * fv2.get(featurename);
                }
        }
        fnorm = calculateNorm(fv1);
        snorm = calculateNorm(fv2);
        similarity = sum / (fnorm * snorm);
        return similarity;
	}

/**
 * calculate the norm of one feature vector
 * 
 */
	private double calculateNorm(HashMap<String, Integer> fv1) {
        Double norm = 0.0;
        Set<String> keys = fv1.keySet();
        Iterator<String> it = keys.iterator();
        while (it.hasNext()) {
                String featurename = it.next();
                norm = norm + Math.pow(fv1.get(featurename), 2);
        }
        return Math.sqrt(norm);
	}

	private String lemmatization(DataUnit answer){
		
		Annotation ansLemma = new Annotation(answer.getText());
		pipeline.annotate(ansLemma);
		List<CoreMap> ansSentences = ansLemma.get(SentencesAnnotation.class);
		String ans = "";
		for (CoreMap sentence : ansSentences) {
			ans = "";
			for (CoreLabel token : sentence.get(TokensAnnotation.class)) {
				String lem = token.get(LemmaAnnotation.class);
				StopWordTool s = new StopWordTool();
				if (!s.isStopWord(lem)){
					ans = ans + lem + " ";
				}
			}
			ans = ans.trim();	
		}
		return ans;
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Cosine Similarity";
	}

	@Override
	protected void doLoad(String address) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void doSave(String address) {
		// TODO Auto-generated method stub

	}
}
