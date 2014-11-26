package com.darkedges.oim.model.impl;

import java.io.StringWriter;

import com.darkedges.oim.model.ICredentialAnswer;

public class CredentialAnswer extends Credential implements ICredentialAnswer {

	public CredentialAnswer(int id, String answer) {
		super(id, "", answer);
	}

	public String getAnswer() {
		return this.answer;
	}

	public boolean compareAnswer(String currentAnswer) {
		return this.answer.equals(currentAnswer);
	}

	public String toString() {
		StringWriter s = new StringWriter();
		s.append("ID:     " + this.ID + "\n");
		s.append("answer: " + this.answer + "\n");
		return s.toString();
	}
}
