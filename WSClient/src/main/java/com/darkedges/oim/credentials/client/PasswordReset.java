package com.darkedges.oim.credentials.client;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import local.darkedges.oim.schemas.Answer;
import local.darkedges.oim.schemas.Answers;
import local.darkedges.oim.schemas.AnswersRequest;
import local.darkedges.oim.schemas.AnswersResponse;
import local.darkedges.oim.schemas.Password;
import local.darkedges.oim.schemas.Question;
import local.darkedges.oim.schemas.QuestionsRequest;
import local.darkedges.oim.schemas.QuestionsResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class PasswordReset extends WebServiceGatewaySupport {

	public static void main(String[] args) throws IOException {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"classpath:applicationContext.xml");
		PasswordReset passwordResetClient = (PasswordReset) applicationContext
				.getBean("passwordResetClient");
		passwordResetClient.reset();
	}

	private void reset() throws IOException {
		QuestionsRequest qRequest = new QuestionsRequest();
		qRequest.setQuestionsRequest("nirving");
		QuestionsResponse qResponse = (QuestionsResponse) getWebServiceTemplate()
				.marshalSendAndReceive(qRequest);
		System.out.println("Have received the following Questions: ");
		for (Iterator<Question> iterator = qResponse.getQuestions()
				.getQuestionList().iterator(); iterator.hasNext();) {
			Question type = iterator.next();
			System.out.println("\t" + type.getQuestion());
		}
		System.out.println("Responding with: \n\ta\n\ta\n\ta\n");

		AnswersRequest aRequest = new AnswersRequest();
		aRequest.setUid("nirving");
		Answers answers = new Answers();
		List<Answer> list = new ArrayList<Answer>();

		int i=1;
		for (Iterator<Question> iterator = qResponse.getQuestions()
				.getQuestionList().iterator(); iterator.hasNext();) {
			Question type = iterator.next();
			Answer answer = new Answer();
			answer.setId(i++);
			answer.setQuestion(type.getQuestion());
			answer.setAnswer("a");
			list.add(answer);
		}
		
		answers.setAnswerList(list);
		aRequest.setAnswers(answers);
		Password password = new Password();
		password.setString("pass");
		password.setConfirm("pass");
		aRequest.setPassword(password);
		AnswersResponse aResponse = (AnswersResponse) getWebServiceTemplate()
				.marshalSendAndReceive(aRequest);
		System.out.println("Can Change Password: ");
		System.out.println("\t" + aResponse.isAnswersResponse());
	}

}
