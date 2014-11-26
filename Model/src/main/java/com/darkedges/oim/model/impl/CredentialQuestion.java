package com.darkedges.oim.model.impl;

import java.io.StringWriter;

import com.darkedges.oim.model.ICredentialQuestion;

public class CredentialQuestion extends Credential implements ICredentialQuestion{

	public CredentialQuestion(int id, String question) {
		super(id,question,"");
	}

	public String getQuestion() {
		return this.question;
	}
	
	public String toString() {
		StringWriter s = new StringWriter();
		s.append("ID:       " + this.ID + "\n");
		s.append("question: " + this.question + "\n");
		return s.toString();
	}
}
