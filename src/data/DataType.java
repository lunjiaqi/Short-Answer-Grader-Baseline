package data;

import java.util.ArrayList;
/**
 * Different data types. 5 Way, 3Way or 2Way.
 * @author Salim
 *
 */
public enum DataType {

	FIVE_WAY(new String[] { "correct", "partially_correct_incomplete",
			"contradictory", "irrelevant", "non_domain" }), THREE_WAY(
			new String[] { "correct", "contradictory", "incorrect" }), TWO_WAY(
			new String[] { "correct", "incorrect" });
	public String[] values;

	DataType(String[] vals) {
		this.values = vals;
	}

	public ArrayList<String> toArrayList() {
		ArrayList<String> list = new ArrayList<>(values.length);
		for (String s : values) {
			list.add(s);
		}
		return list;
	}
}
