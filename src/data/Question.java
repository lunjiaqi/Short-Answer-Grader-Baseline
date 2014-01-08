package data;

import java.util.ArrayList;
/**
 * 
 * Instance of a Question. 
 * 
 * @author Salim
 *
 */
public class Question extends DataUnit {
	private String qType;
	private String sType;
	private String module;
	private final ArrayList<StudentAnswer> studentAnswers;
	private final ArrayList<ReferenceAnswer> referenceAnswers;

	public Question(String id, String qType, String sTyppe, String module) {
		super(id);
		sType = sTyppe;
		this.qType = qType;
		this.module = module;
		studentAnswers = new ArrayList<>();
		referenceAnswers = new ArrayList<>();
	}

	public Question(String id, String qType, String sTyppe, String module,
			ArrayList<StudentAnswer> stdanswers,
			ArrayList<ReferenceAnswer> refanswers) {
		super(id);
		sType = sTyppe;
		this.qType = qType;
		this.module = module;
		this.studentAnswers = stdanswers;
		this.referenceAnswers = refanswers;
	}

	public String getqType() {
		return qType;
	}

	public void setqType(String qType) {
		this.qType = qType;
	}

	public String getsType() {
		return sType;
	}

	public void setsType(String sType) {
		this.sType = sType;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public ArrayList<StudentAnswer> getStudentAnswers() {
		return studentAnswers;
	}

	public ArrayList<ReferenceAnswer> getReferenceAnswers() {
		return referenceAnswers;
	}

}
