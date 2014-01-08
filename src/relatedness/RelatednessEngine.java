package relatedness;

import java.util.ArrayList;
import java.util.List;

import corpus.AbstractCorpus;
import data.DataUnit;
import data.QASet;
import data.Question;
import data.StudentAnswer;
import edu.stanford.nlp.util.Pair;

/**
 * 
 * @author Salim
 * 
 */
public class RelatednessEngine {
	AbstractRelatednessScorer[] scorers;

	public RelatednessEngine(AbstractRelatednessScorer[] extractors) {
		this.scorers = extractors;
	}

	public void train(AbstractCorpus[] corpora, QASet train) {
		for (AbstractRelatednessScorer sc : scorers) {
			sc.train(corpora, train);
		}
	}

	public void load() {
		for (AbstractRelatednessScorer sc : scorers) {
			sc.load();
		}
	}

	public void save() {
		for (AbstractRelatednessScorer sc : scorers) {
			sc.save();
		}
	}

	public Pair<String[], List<FeatureSet>> score(QASet test) {
		List<FeatureSet> result = new ArrayList<>();
		String[] attNames = new String[scorers.length * 2 + 3];
		attNames[0] = "QID";
		attNames[1] = "AID";
		attNames[attNames.length-1] = "Label";
		int index = 0;
		for (AbstractRelatednessScorer fe : scorers) {
			attNames[(index++) + 2] = fe.getName() + "-onRefAnswers";
			attNames[(index++) + 2] = fe.getName() + "-onQuestion";
		}
		int count = 0;
		for (Question q : test.getQuestions()) {
			for (StudentAnswer a : q.getStudentAnswers()) {
				double[] tmp = new double[scorers.length * 2];
				index = 0;
				for (AbstractRelatednessScorer fe : scorers) {
					double s1 = fe.score(
							q.getReferenceAnswers().toArray(new DataUnit[] {}),
							a);
					double s2 = fe.score(new DataUnit[] { q }, a);
					tmp[index++] = s1;
					tmp[index++] = s2;
				}
				result.add(new FeatureSet(q, a, tmp));
			}
			count++;
			System.out.println(((float) count) / test.getQuestions().size()
					* 100);

		}
		return new Pair<String[], List<FeatureSet>>(attNames, result);
	}
}
