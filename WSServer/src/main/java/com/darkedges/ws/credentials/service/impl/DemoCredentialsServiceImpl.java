package com.darkedges.ws.credentials.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import local.darkedges.oim.schemas.Answer;
import local.darkedges.oim.schemas.AnswersRequest;
import local.darkedges.oim.schemas.AnswersResponse;

import org.springframework.stereotype.Service;

import com.darkedges.oim.collection.impl.Credentials;
import com.darkedges.oim.model.ICredentialQuestion;
import com.darkedges.oim.model.impl.Credential;
import com.darkedges.ws.credentials.service.CredentialsService;
@Service("demoService")
public class DemoCredentialsServiceImpl implements CredentialsService {
	public HashMap<String, Credentials> users = new HashMap<String, Credentials>();
	public DemoCredentialsServiceImpl() {
		Credentials creds = new Credentials();
		creds.addQuestion(new Credential(1,
				"What is your mother's maiden name?", "a"));
		creds.addQuestion(new Credential(2, "What is the name of your pet?",
				"a"));
		creds.addQuestion(new Credential(3, "What is your favorite color?", "a"));
		users.put("nirving", creds);
	}

	public Collection<ICredentialQuestion> getQuestions(String uid) {
		Credentials creds = users.get(uid);
		return creds.getQuestions(uid);
	}

	public AnswersResponse validate(AnswersRequest requestObject) {
		boolean allCorrect = true;
		AnswersResponse answer = new AnswersResponse();
		List<Answer> answers = requestObject.getAnswers().getAnswerList();
		Credentials creds = users.get(requestObject.getUid());
		creds.getAnswers("");
		allCorrect = requestObject.getPassword().getString().equals(requestObject.getPassword().getConfirm());
		for (Iterator<Answer> iterator = answers.iterator(); iterator.hasNext();) {
			Answer answerRequestType = iterator.next();
			allCorrect = allCorrect && creds.compareAnswer(answerRequestType);
		}
		if (allCorrect) {
			answer.setAnswersResponse(true);
		} else {
			answer.setAnswersResponse(false);
		}
		return answer;
	}

}
