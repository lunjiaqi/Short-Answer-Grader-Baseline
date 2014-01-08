package data;

/**
 * Contains information for Reference Answers
 * 
 * @author Salim
 * 
 */
public class ReferenceAnswer extends Answer {
	private String category;
	private String fileID;

	public ReferenceAnswer(String id,String category, String fileID) {
		super(id);
		this.category = category;
		this.fileID = fileID;

	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getFileID() {
		return fileID;
	}

	public void setFileID(String fileID) {
		this.fileID = fileID;
	}
}
