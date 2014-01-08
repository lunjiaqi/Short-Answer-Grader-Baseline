package corpus;

public abstract class AbstractCorpus {

	public abstract String getText();

	public abstract String getFileAddress();

	public abstract String[] getTextsBySubject(String subject);

}
