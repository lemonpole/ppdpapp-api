package edu.papolicy.services;

import edu.papolicy.daos.UserDAO;
import edu.papolicy.models.User;

import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.util.Date;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		String[] values = token.split(":", 2);
		Timestamp ts = Timestamp.valueOf(values[1]);
		return Account.isAccessTokenExpired(new Date(ts.getTime()));
	}
	public static boolean isAccessTokenExpired(Date expiry){
		// is today's timestamp past the timestamp recorded?
		Date today = new Date();
		return today.after(expiry);
	}
}
