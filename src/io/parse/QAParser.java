package io.parse;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import data.DataType;
import data.QASet;
import data.Question;
import data.ReferenceAnswer;
import data.StudentAnswer;

/**
 * 
 * Set of questions and their answers.
 * 
 * @author Salim
 * 
 */
public class QAParser {

	public QASet parse(File in) throws ParserConfigurationException,
			SAXException, IOException {
		QASet result = new QASet(DataType.FIVE_WAY);
		if (!in.isDirectory())
			return parseFile(in);

		Collection<File> files = FileUtils.listFiles(in, new RegexFileFilter(
				".*\\.(xml)"), DirectoryFileFilter.DIRECTORY);

		for (File file : files) {
			QASet tmp = parseFile(file);
			result.getQuestions().addAll(tmp.getQuestions());
		}

		return result;

	}

	private QASet parseFile(File f) throws ParserConfigurationException,
			SAXException, IOException {
		QASet result = new QASet(DataType.FIVE_WAY);
		DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder();
		Document doc = dBuilder.parse(f);
		NodeList questions = doc.getElementsByTagName("question");
		for (int i = 0; i < questions.getLength(); i++) {
			Node qNode = questions.item(i);
			System.out.println();
			Question q = new Question(getAtt(qNode, "id"), getAtt(qNode,
					"qtype"), getAtt(qNode, "stype"), getAtt(qNode, "module"));
			NodeList childNodes = qNode.getChildNodes();
			for (int j = 0; j < childNodes.getLength(); j++) {
				Node child = childNodes.item(j);
				if (child.getNodeName().equals("questionText")) {
					q.setText(child.getTextContent());
				} else if (child.getNodeName().equals("referenceAnswers")) {
					NodeList answerNodes = child.getChildNodes();
					for (int k = 0; k < answerNodes.getLength(); k++) {
						if (answerNodes.item(k).getNodeName()
								.equals("referenceAnswer"))
							q.getReferenceAnswers().add(
									parseReferenceAnswer(answerNodes.item(k)));
					}

				} else if (child.getNodeName().equals("studentAnswers")) {
					NodeList answerNodes = child.getChildNodes();
					for (int k = 0; k < answerNodes.getLength(); k++) {
						if (answerNodes.item(k).getNodeName()
								.equals("studentAnswer"))
							q.getStudentAnswers().add(
									parseStudentAnswer(answerNodes.item(k)));
					}
				}
			}
			result.getQuestions().add(q);
		}
		return result;

	}

	public StudentAnswer parseStudentAnswer(Node node) {
		StudentAnswer answer = new StudentAnswer(getAtt(node, "id"), getAtt(
				node, "dialogue_id"), getAtt(node, "accuracy"), getAtt(node,
				"answerMatch"),
				Integer.parseInt(getAtt(node, "count") == null ? "0" : getAtt(
						node, "count")));
		answer.setText(node.getTextContent());
		return answer;
	}

	public ReferenceAnswer parseReferenceAnswer(Node node) {
		ReferenceAnswer answer = new ReferenceAnswer(getAtt(node, "id"),
				getAtt(node, "category"), getAtt(node, "fileID"));
		answer.setText(node.getTextContent());
		return answer;
	}

	public String getAtt(Node node, String name) {
		Node namedItem = node.getAttributes().getNamedItem(name);
		if (namedItem == null)
			return null;
		return namedItem.getNodeValue();
	}

}
