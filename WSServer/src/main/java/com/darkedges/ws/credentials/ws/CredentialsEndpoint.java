package com.darkedges.ws.credentials.ws;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.annotation.Resource;
import javax.xml.parsers.ParserConfigurationException;

import local.darkedges.oim.schemas.AnswersRequest;
import local.darkedges.oim.schemas.AnswersResponse;
import local.darkedges.oim.schemas.Question;
import local.darkedges.oim.schemas.Questions;
import local.darkedges.oim.schemas.QuestionsRequest;
import local.darkedges.oim.schemas.QuestionsResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.darkedges.oim.model.ICredentialQuestion;
import com.darkedges.ws.credentials.service.CredentialsService;

@Endpoint
public class CredentialsEndpoint {
	@Resource(name="oimService")
	private CredentialsService credentialsService;
	public static final String NAMESPACE_URI = "http://darkedges.local/oim/schemas";

	public static final String QUESTIONS_REQUEST_LOCAL_NAME = "QuestionsRequest";
	public static final String ANSWERS_REQUEST_LOCAL_NAME = "AnswersRequest";

	public CredentialsEndpoint() {
	}

	@PayloadRoot(localPart = ANSWERS_REQUEST_LOCAL_NAME, namespace = NAMESPACE_URI)
	@ResponsePayload
	public AnswersResponse handleAnswerRequest(
			@RequestPayload AnswersRequest requestObject)
			throws ParserConfigurationException {
		AnswersResponse answer = credentialsService.validate(requestObject);
		return answer;

	}

	@PayloadRoot(localPart = QUESTIONS_REQUEST_LOCAL_NAME, namespace = NAMESPACE_URI)
	@ResponsePayload
	public QuestionsResponse handleQuestionsRequest(
			@RequestPayload QuestionsRequest requestObject)
			throws ParserConfigurationException {
		Collection<ICredentialQuestion> questions = credentialsService
				.getQuestions(requestObject.getQuestionsRequest());
		ArrayList<Question> list = new ArrayList<Question>();
		for (Iterator<ICredentialQuestion> iterator = questions.iterator(); iterator
				.hasNext();) {
			ICredentialQuestion iCredentialQuestion = iterator.next();
			Question questionResponseType = new Question();
			questionResponseType.setId(new BigInteger(""
					+ iCredentialQuestion.getID()));
			questionResponseType.setQuestion(iCredentialQuestion.getQuestion());
			list.add(questionResponseType);
		}
		Questions questionsResponseType = new Questions();
		questionsResponseType.setQuestionList(list);
		QuestionsResponse response = new QuestionsResponse();
		response.setQuestions(questionsResponseType);
		return response;
	}

}
