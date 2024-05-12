package org.financeiro.dto;

public class LoginDTO {

	private String credential;
	private String urlPicture;

	public LoginDTO(){}

	public LoginDTO( String credential, String urlPicture ) {
		this.credential = credential;
		this.urlPicture = urlPicture;
	}

	public String getCredential() {
		return credential;
	}

	public void setCredential( String credential ) {
		this.credential = credential;
	}

	public String getPicture() {
		return urlPicture;
	}

	public void setPicture( String urlPicture ) {
		this.urlPicture = urlPicture;
	}
}
