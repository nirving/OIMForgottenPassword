package com.darkedges.ws.credentials.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import local.darkedges.oim.schemas.Answer;
import local.darkedges.oim.schemas.AnswersRequest;
import local.darkedges.oim.schemas.AnswersResponse;
import oracle.iam.platform.OIMClient;
import oracle.iam.selfservice.exception.AuthSelfServiceException;
import oracle.iam.selfservice.exception.NumberOfChallengesMismatchException;
import oracle.iam.selfservice.exception.PasswordIncorrectException;
import oracle.iam.selfservice.exception.PasswordMismatchException;
import oracle.iam.selfservice.exception.PasswordPolicyException;
import oracle.iam.selfservice.exception.PasswordResetAttemptsExceededException;
import oracle.iam.selfservice.exception.QuestionsNotDefinedException;
import oracle.iam.selfservice.exception.UserAccountDisabledException;
import oracle.iam.selfservice.exception.UserAccountInvalidException;
import oracle.iam.selfservice.exception.UserAlreadyLoggedInException;
import oracle.iam.selfservice.uself.uselfmgmt.api.UnauthenticatedSelfService;

import com.darkedges.oim.client.OIMClientImpl;
import com.darkedges.oim.model.ICredentialQuestion;
import com.darkedges.oim.model.impl.CredentialQuestion;
import com.darkedges.ws.credentials.service.CredentialsService;

@Service("oimService")
public class OIMCredentialsServiceImpl implements CredentialsService {
	private OIMClient client;

	public OIMCredentialsServiceImpl() {
		OIMClientImpl clientImpl = new OIMClientImpl();
		clientImpl.setOIMInitialContextFactory("weblogic.jndi.WLInitialContextFactory");
		clientImpl.setOIMURL("t3://localhost:14000");
		client = clientImpl.getInstance();
	}

	public Collection<ICredentialQuestion> getQuestions(String uid) {
		ArrayList<ICredentialQuestion> questions = new ArrayList<ICredentialQuestion>();
		UnauthenticatedSelfService service = client
				.getService(UnauthenticatedSelfService.class);
		try {
			String[] c = service.getChallengeQuestions(uid);
			for (int i = 0; i < c.length; i++) {
				questions.add(new CredentialQuestion(i+1,c[i]));
			}
		} catch (UserAccountDisabledException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UserAccountInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AuthSelfServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return questions;
	}

	public AnswersResponse validate(AnswersRequest requestObject) {
		HashMap<String,Object> map = new HashMap<String,Object>();
		AnswersResponse answer = new AnswersResponse();
		UnauthenticatedSelfService service = client.getService(UnauthenticatedSelfService.class);
		List<Answer> answers = requestObject.getAnswers().getAnswerList();

		for (Iterator<Answer> iterator = answers.iterator(); iterator.hasNext();) {
			Answer answerRequestType = iterator.next();
			map.put(answerRequestType.getQuestion(),(Object) answerRequestType.getAnswer());
			System.out.println(answerRequestType.getQuestion()+":"+answerRequestType.getAnswer());
		}
		boolean a = true;


		try {
			a = service.resetPassword(requestObject.getUid(), map, requestObject.getPassword().getString().toCharArray());
		} catch (Exception e) {
			a = false;
		}
		answer.setAnswersResponse(a);
		return answer;
	}

}
