package samples;

import org.tartarus.snowball.SnowballStemmer;
import org.tartarus.snowball.ext.englishStemmer;


public class WordStemmerExample {
	public static void main(String[] args) {
		SnowballStemmer stemmer = new englishStemmer();
		stemmer.setCurrent("writing");
		stemmer.stem();
		System.out.println(stemmer.getCurrent());
	}
}
