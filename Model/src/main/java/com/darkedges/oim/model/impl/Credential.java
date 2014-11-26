package com.darkedges.oim.model.impl;

import java.io.StringWriter;

import com.darkedges.oim.model.ICredential;

public class Credential implements ICredential {

	public int ID;
	public String question;
	public String answer;

	public Credential(int ID, String question, String answer) {
		this.ID = ID;
		this.question = question;
		this.answer = answer;
	}

	public int getID() {
		return ID;
	}

	public String toString() {
		StringWriter s = new StringWriter();
		s.append("ID:       " + this.ID + "\n");
		s.append("question: " + this.question + "\n");
		s.append("answer:   " + this.answer + "\n");
		return s.toString();
	}

	public String getAnswer() {
		// TODO Auto-generated method stub
		return this.answer;
	}

	public String getQuestion() {
		// TODO Auto-generated method stub
		return this.question;
	}
}
