package edu.papolicy.controllers;

import edu.papolicy.daos.UserDAO;
import edu.papolicy.models.User;
import edu.papolicy.services.Account;

import java.nio.charset.Charset;
import java.sql.Timestamp;
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
		String[] values = Account.parseAuthHeader(authorization);

		// Query database and ensure the email exists.
		// On success, generate an access token with expiry date of 24 hours.
		try {
			User res = userDAO.find(values[0]);
			Calendar c = Calendar.getInstance();
			Date dt;
			Timestamp ts;

			c.setTime(new Date());
			c.add(Calendar.DATE, 1);
			dt = c.getTime();
			ts = new Timestamp(dt.getTime());

			String randString = RandomStringUtils.randomAlphanumeric(32);
			String token = randString + ":" + ts.getTime();

			// Update user and store in database.
			res.setAccessToken(token);
			userDAO.update(res);

			return new ResponseEntity<String>(token, HttpStatus.OK);
		} catch(Exception e){
			return new ResponseEntity<String>("Incorrect credentials provided", HttpStatus.UNAUTHORIZED);
		}
	}
}
