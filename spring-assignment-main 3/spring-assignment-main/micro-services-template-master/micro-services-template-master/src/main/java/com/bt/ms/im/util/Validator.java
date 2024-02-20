package com.bt.ms.im.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.bt.ms.im.exception.StandardError;
import com.bt.ms.im.exception.handler.standardexception.BadRequestException;

@Component
public class Validator {

	private static final String TACKINGREGEX = "^[a-zA-Z0-9-]{1,50}$";

	public void setvalidator1(String trackingid) {

//	  check if nul or nit 
//		write same as otp validation

		if (trackingid == null) {
			StandardError errormsg = StandardError.ERR400_25;
//			log.error(errormsg.getMessage());
			throw new BadRequestException(errormsg);
		} else {
			String regex = TACKINGREGEX;
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(trackingid);
			boolean trackingid1 = matcher.matches();
			if (!trackingid1) {
				StandardError errormsg = StandardError.ERR400_26;
//				log.error(errormsg.getMessage());
				throw new BadRequestException(errormsg);

			}
		}
	}

	public void setvalidator2(String str) {

	}

}
