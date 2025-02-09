package org.financeiro.dto;

public class LoginDTO {

	private String credential;
	private String urlPicture;
	private String accessToken;

	public LoginDTO(){}

	public LoginDTO(String urlPicture, String token) {
		this.urlPicture = urlPicture;
		this.accessToken = token;
	}

	public String getCredential() {
		return credential;
	}
	public String getPicture() {
		return urlPicture;
	}
	public String getAccessToken() {
		return accessToken;
	}
}
