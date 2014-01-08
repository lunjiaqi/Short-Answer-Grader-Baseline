package data;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import edu.stanford.nlp.util.Pair;
import relatedness.FeatureSet;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

/**
 * This class converts SAG feature set to Weka's.
 * 
 * @author Salim
 * 
 */
public class FeatureSetToInstancesConvertor {

	public static final Instances convert(Pair<String[], List<FeatureSet>> in,
			DataType type) throws InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		ArrayList<Attribute> atts = new ArrayList<>(in.first.length);
		atts.add(Attribute.class.getConstructor(
				new Class[] { String.class, List.class }).newInstance(
				in.first[0], null));
		atts.add(Attribute.class.getConstructor(
				new Class[] { String.class, List.class }).newInstance(
				in.first[1], null));
		for (int i = 2; i < in.first.length - 1; i++) {
			atts.add(new Attribute(in.first[i]));
		}
		atts.add(Attribute.class.getConstructor(
				new Class[] { String.class, List.class }).newInstance(
				in.first[in.first.length - 1], type.toArrayList()));
		Instances result = new Instances("set" + (int) (Math.random() * 10000),
				atts, in.second.size());
		for (FeatureSet fs : in.second) {
			Instance i = new DenseInstance(in.first.length);
			i.setDataset(result);
			i.setValue(0, fs.getQuestion().getId());
			i.setValue(1, fs.getAnswer().getId());
			i.setValue(in.first.length - 1, fs.getAnswer().getAccuracy());
			for (int j = 0; j < fs.getFeatures().length; j += 2) {
				i.setValue(j + 2, fs.getFeatures()[j]);
				i.setValue(j + 3, fs.getFeatures()[j + 1]);

			}
			result.add(i);
		}
		return result;

	}
	// public static void main(String[] args) {
	// ByteArrayOutputStream bos = new ByteArrayOutputStream();
	// for (int i = 0; i < args.length; i++) {
	// bos.write(""+i);
	//
	// }
	// }

}
