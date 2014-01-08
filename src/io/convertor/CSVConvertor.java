package io.convertor;

import io.parse.QAParser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import data.QASet;
import data.Question;
import data.ReferenceAnswer;
import data.StudentAnswer;

public class CSVConvertor {

	public CSVConvertor() {
	}

	public static void convert(File input, String out)
			throws ParserConfigurationException, SAXException, IOException {
		QAParser p = new QAParser();
		QASet data = p.parse(input);
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(out)));
		bw.write("qid, tpye, ref, answer, accuracy\n");
		for (Question q : data.getQuestions()) {
			for (StudentAnswer sa : q.getStudentAnswers()) {
				bw.write(q.getId() + ", question, " + q.getText() + ", "
						+ sa.getText() + ", " + sa.getAccuracy() + "\n");
				for (ReferenceAnswer ra : q.getReferenceAnswers()) {
					bw.write(q.getId() + ", refanswer" + ", " + ra.getText()
							+ ", " + sa.getText() + ", " + sa.getAccuracy()
							+ "\n");
				}

			}
		}
		bw.flush();
		bw.close();
	}

	public static void main(String[] args) throws ParserConfigurationException,
			SAXException, IOException {
		CSVConvertor.convert(new File("data/train"), "data/data.csv");
	}
}
