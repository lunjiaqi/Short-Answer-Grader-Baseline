package relatedness;

import corpus.AbstractCorpus;
import data.DataUnit;
import data.QASet;

/**
 * A simple abstract classs that everyone implement their methods within this
 * interface
 * 
 * @author Salim
 * 
 */
public abstract class AbstractRelatednessScorer {
	protected String address;
	boolean trained = false;
	boolean forceTrain;

	public AbstractRelatednessScorer(String address, boolean forceTrain) {
		this.address = address;
		this.forceTrain = forceTrain;
	}

	public void train(AbstractCorpus[] corpora, QASet dataset) {
		if (address == null || address.equals("") || forceTrain) {
			doTrain(corpora, dataset);
			trained = true;
		}
	}

	protected abstract void doTrain(AbstractCorpus[] corpora, QASet dataset);

	public abstract double score(DataUnit[] references, DataUnit answer);

	public String getName() {
		return getClass().getSimpleName();
	}

	public void load() {

		if (address == null || address.equals(""))
			return;
		doLoad(address);

	}

	protected abstract void doLoad(String address);

	public void save() {
		if (address == null || address.equals(""))
			return;
		if (trained)
			doSave(address);
	}

	protected abstract void doSave(String address);
}
