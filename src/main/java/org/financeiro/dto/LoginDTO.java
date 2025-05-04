package org.financeiro.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginDTO {

	private String credential;
	private String urlPicture;
	private String accessToken;

	public LoginDTO(String urlPicture, String token) {
		this.urlPicture = urlPicture;
		this.accessToken = token;
	}
}
