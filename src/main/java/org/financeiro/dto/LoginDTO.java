package org.financeiro.dto;

public class LoginDTO {

	private String credential;

	public LoginDTO(){}

	public LoginDTO( String credential ) {
		this.credential = credential;
	}

	public String getCredential() {
		return credential;
	}

	public void setCredential( String credential ) {
		this.credential = credential;
	}
}
