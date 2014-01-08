package data;


/**
 * Interface for Question and Answer
 * 
 * @author Salim
 * 
 */
public abstract class DataUnit {
	private String id;
	private String text;
	public DataUnit(String id){
		this.id=id;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
//	public String[] getTokens() {
//		return getText().split(" |\\.|,|;|!|\\?|'|\"|\\(|\\)|\\[|\\]|\\{|\\}|@|#|$|&|\\*|\\^");
//	}

	// public String[] getPreProcessedTokens() {
	//
	// String[] tokens = getTokens();
	// String[] result = new String[tokens.length];
	//
	// SnowballStemmer stemmer = new englishStemmer();
	// for (int i = 0; i < tokens.length; i++) {
	// stemmer.setCurrent(tokens[i]);
	// stemmer.stem();
	// result[i] =stemmer.getCurrent();
	// }
	// return result;
	// }
//	public static void main(String[] args) {
//		SnowballStemmer stemmer = new englishStemmer();
//		stemmer.setCurrent("doing");
//		stemmer.stem();
//		System.out.println(stemmer.getCurrent());
//	}
}
