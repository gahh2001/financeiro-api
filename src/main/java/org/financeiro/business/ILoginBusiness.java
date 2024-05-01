package org.financeiro.business;

import org.financeiro.dto.LoginDTO;
import org.financeiro.exceptions.LoginException;

public interface ILoginBusiness {

	LoginDTO validateLogin(String token) throws LoginException;
}
