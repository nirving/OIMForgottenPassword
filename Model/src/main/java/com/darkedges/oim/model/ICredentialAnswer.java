package com.darkedges.oim.model;

public interface ICredentialAnswer {

	public String getAnswer();
	public boolean compareAnswer(String currentAnswer);
	public String getQuestion();
	public String toString();
	
}
