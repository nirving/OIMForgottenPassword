package com.darkedges.oim.collection.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import local.darkedges.oim.schemas.Answer;

import com.darkedges.oim.collection.ICredentials;
import com.darkedges.oim.model.ICredential;
import com.darkedges.oim.model.ICredentialAnswer;
import com.darkedges.oim.model.ICredentialQuestion;
import com.darkedges.oim.model.impl.CredentialAnswer;
import com.darkedges.oim.model.impl.CredentialQuestion;

public class Credentials implements ICredentials {

	ArrayList<ICredential> credentials;
	ArrayList<ICredentialQuestion> questions;
	ArrayList<ICredentialAnswer> answers;

	public void addQuestion(ICredential credential) {
		if (this.credentials == null) {
			this.credentials = new ArrayList<ICredential>();
		}
		this.credentials.add(credential);
	}

	public Collection<ICredentialQuestion> getQuestions(String uid) {
		if (this.questions == null) {
			this.questions = new ArrayList<ICredentialQuestion>();
			for (Iterator<ICredential> iterator = credentials.iterator(); iterator
					.hasNext();) {
				ICredential type = iterator.next();
				questions.add(new CredentialQuestion(type.getID(), type
						.getQuestion()));
			}
		}
		return questions;
	}

	public Collection<ICredentialAnswer> getAnswers(String uid) {
		if (this.answers == null) {
			this.answers = new ArrayList<ICredentialAnswer>();
			for (Iterator<ICredential> iterator = credentials.iterator(); iterator
					.hasNext();) {
				ICredential type = iterator.next();
				answers.add(new CredentialAnswer(type.getID(), type.getAnswer()));
			}
		}
		return answers;
	}

	public boolean compareAnswer(Answer answer) {
		int id = answer.getId();
		ICredentialAnswer a = answers.get(id-1);
		return a.compareAnswer(answer.getAnswer());
	}

}
