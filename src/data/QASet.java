package data;

import java.util.ArrayList;


/**
 * 
 * Set of questions.
 * 
 * @author Salim
 *
 */
public class QASet {
	private final DataType type;

	private ArrayList<Question> questions;

	public QASet(DataType type) {
		this.type = type;
		this.questions = new ArrayList<>();
	}

	public QASet(ArrayList<Question> questions, DataType type) {
		this.questions = questions;
		this.type = type;
	}

	public ArrayList<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}
	
	public QASet convertData(DataType type) {
		if (type == this.getType())
			return this;
		if (type == DataType.FIVE_WAY && this.getType() != DataType.FIVE_WAY)
			throw new Error("Conversion from " + this.getType()
					+ " to FIVE_WAY is not possible");

		if (type == DataType.THREE_WAY && this.getType() == DataType.TWO_WAY)
			throw new Error(
					"Conversion from TWO_WAY to THREE_WAY is not possible");
		ArrayList<Question> tmpquestions = new ArrayList<>();
		for (Question q : questions) {
			@SuppressWarnings("unchecked")
			ArrayList<ReferenceAnswer> tmpra = (ArrayList<ReferenceAnswer>) q
					.getReferenceAnswers().clone();

			ArrayList<StudentAnswer> tmpsa = new ArrayList<>();
			for (StudentAnswer a : q.getStudentAnswers()) {
				StudentAnswer sa = new StudentAnswer(a.getId(),
						a.getDialogueId(), convertAccuracy(a.getAccuracy(),
								type), a.getAnswerMatch(), a.getCount());
				sa.setText(a.getText());
				tmpsa.add(sa);
			}

			Question tmpq = new Question(q.getId(), q.getqType(), q.getsType(),
					q.getModule(), tmpsa, tmpra);

			tmpquestions.add(tmpq);
		}
		return new QASet(tmpquestions, type);
	}

	private String convertAccuracy(String acc, DataType dst) {
		if (dst == DataType.THREE_WAY) {
			if (acc.equals("correct"))
				return acc;
			if (acc.equals("contradictory"))
				return acc;
			return "incorrect";
		}
		if (dst == DataType.TWO_WAY) {
			if (acc.equals("correct"))
				return acc;
			return "incorrect";
		}
		return "";

	}

	public DataType getType() {
		return type;
	}
}
