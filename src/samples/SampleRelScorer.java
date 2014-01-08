package samples;

import relatedness.AbstractRelatednessScorer;
import corpus.AbstractCorpus;
import data.DataUnit;
import data.QASet;

public class SampleRelScorer extends AbstractRelatednessScorer {

	public SampleRelScorer(String address, boolean forceTrain) {
		super(address, forceTrain);
	}

	@Override
	public void doTrain(AbstractCorpus[] corpora, QASet dataset) {
		// do nothing
	}

	@Override
	public double score(DataUnit[] references, DataUnit answer) {
		return 1;
	}

	@Override
	public String getName() {
		return "Sample Relatedness Scorer";
	}

	@Override
	protected void doLoad(String address) {

	}

	@Override
	protected void doSave(String address) {

	}

}
