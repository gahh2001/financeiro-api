package org.financeiro.exceptions;

public class NonExistentAccount extends Exception{

	public NonExistentAccount(String googleId) {
		super("the account does not exist: " + googleId);
	}
}