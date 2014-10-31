package edu.papolicy.controllers;

import edu.papolicy.daos.UserDAO;
import edu.papolicy.models.User;

import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.Date;

import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {
	@Autowired
	private UserDAO userDAO;

	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity postAccount(@RequestHeader(value = "Authorization") String authorization){
		// Take Base64 encoded string from authorization header and extract username and password.
		String base64Credentials = authorization.substring("Basic".length()).trim();
		String credentials = new String(Base64.decodeBase64(base64Credentials), Charset.forName("UTF-8"));
		String[] values = credentials.split(":",2);

		// Query database and ensure the email exists.
		// On success, generate an access token with expiry date of 24 hours.
		try {
			long timestamp;
			User res = userDAO.find(values[0]);
			Calendar c = Calendar.getInstance();

			c.setTime(new Date());
			c.add(Calendar.DATE, 1);
			timestamp = c.getTimeInMillis();

			String randString = RandomStringUtils.randomAlphanumeric(32);
			String token = randString + ":" + timestamp;

			// Update user and store in database.
			res.setAccessToken(token);
			userDAO.update(res);

			return new ResponseEntity<String>(token, HttpStatus.OK);
		} catch(Exception e){
			return new ResponseEntity<String>("Incorrect credentials provided", HttpStatus.UNAUTHORIZED);
		}
	}
}
