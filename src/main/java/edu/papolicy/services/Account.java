package edu.papolicy.services;

import edu.papolicy.daos.UserDAO;
import edu.papolicy.daos.UserDAOImpl;
import edu.papolicy.models.User;

import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.sasl.AuthenticationException;

@Service
public class Account {
	@Autowired
	private static UserDAO userDAO;

	public static User parseAccessToken(String token) throws Exception {
		return new User();
	}
	public static String[] parseAuthHeader(String header){
		String base64Credentials = header.substring("Basic".length()).trim();
		String credentials = new String(Base64.decodeBase64(base64Credentials), Charset.forName("UTF-8"));
		return credentials.split(":",2);
	}
	public static boolean isAccessTokenExpired(String token){
		// simply extracting the timestamp from the token string and passing it as a date to the overloaded method.
		try {
			String[] values = token.split(":", 2);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date dt = df.parse(values[1]);

			return Account.isAccessTokenExpired(dt);
		} catch(Exception e){
			return true;
		}
	}
	public static boolean isAccessTokenExpired(Date expiry){
		// is today's timestamp past the timestamp recorded?
		//Date today = new Date();
		//return today.after(expiry);
		return false;
	}

	public static User doAuthentication(String token) throws AuthenticationException{
		//check if token is expired, if not then throw exception
		if (isAccessTokenExpired(token)) {
			throw new AuthenticationException();
		}
		//gets user
		User user = userDAO.findByToken(token);
		//return user
		return user;
	}
}