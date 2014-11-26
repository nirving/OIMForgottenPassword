package com.darkedges.ws.credentials.service;

import java.util.Collection;

import local.darkedges.oim.schemas.AnswersRequest;
import local.darkedges.oim.schemas.AnswersResponse;

import com.darkedges.oim.model.ICredentialQuestion;

public interface CredentialsService {
	Collection<ICredentialQuestion> getQuestions(String uid) ;

	AnswersResponse validate(AnswersRequest requestObject);
}
