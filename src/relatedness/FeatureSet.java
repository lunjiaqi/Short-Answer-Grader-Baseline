package relatedness;

import data.Question;
import data.StudentAnswer;

public class FeatureSet {
	private Question question;
	private StudentAnswer answer;
	private double[] features;

	public FeatureSet(Question q, StudentAnswer a, double[] features) {
		question = q;
		answer = a;
		this.features = features;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public StudentAnswer getAnswer() {
		return answer;
	}

	public void setAnswer(StudentAnswer answer) {
		this.answer = answer;
	}

	public double[] getFeatures() {
		return features;
	}

	public void setFeatures(double[] features) {
		this.features = features;
	}

	public String toCSVLine() {
		String tmp = "" + question.getId() + ", " + answer.getId() + "";
		for (double d : features) {
			tmp += ", " + d;
		}

		return tmp + ", " + answer.getAccuracy();
	}
}
