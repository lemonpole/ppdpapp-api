package edu.papolicy.services;

import edu.papolicy.daos.UserDAO;
import edu.papolicy.models.User;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.security.sasl.AuthenticationException;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Account {
	@Autowired private UserDAO userDAO;

	public User parseAccessToken(String token) throws Exception {
		return new User();
	}
	public String[] parseAuthHeader(String header){
		String base64Credentials = header.substring("Basic".length()).trim();
		String credentials = new String(Base64.decodeBase64(base64Credentials), Charset.forName("UTF-8"));
		return credentials.split(":",2);
	}
	public boolean isAccessTokenExpired(String token){
		// simply extracting the timestamp from the token string and passing it as a date to the overloaded method.
		try {
			String[] values = token.split(":", 2);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date dt = df.parse(values[1]);
			Date today = new Date();
			//return today.after(dt);
			return false;
		} catch(Exception e){
			return false; // change back to true after done debugging...
		}
	}

	public User doAuthentication(String token) throws AuthenticationException{
		if(this.isAccessTokenExpired(token)) throw new AuthenticationException();
		return userDAO.findByToken(token);
	}
}