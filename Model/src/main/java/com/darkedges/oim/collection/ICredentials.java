package com.darkedges.oim.collection;

import java.util.Collection;

import local.darkedges.oim.schemas.Answer;

import com.darkedges.oim.model.ICredentialQuestion;

public interface ICredentials {
	Collection<ICredentialQuestion> getQuestions(String uid);
	boolean compareAnswer(Answer answer);
}
