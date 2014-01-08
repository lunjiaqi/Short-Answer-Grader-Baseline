package data;

/**
 * This class contains information specifically for students' answers
 * 
 * @author Salim
 * 
 */
public class StudentAnswer extends Answer {
	private String dialogueId;
	private String accuracy;
	private String answerMatch;
	private int count;

	public StudentAnswer(String id, String dialogueId, String accuracy,
			String answerMatch, int count) {
		super(id);
		this.count = count;
		this.answerMatch = answerMatch;
		this.accuracy = accuracy;
		this.dialogueId = dialogueId;
	}

	public String getDialogueId() {
		return dialogueId;
	}

	public void setDialogueId(String dialogueId) {
		this.dialogueId = dialogueId;
	}

	public String getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(String accuracy) {
		this.accuracy = accuracy;
	}

	public String getAnswerMatch() {
		return answerMatch;
	}

	public void setAnswerMatch(String answerMatch) {
		this.answerMatch = answerMatch;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
