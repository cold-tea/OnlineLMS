package online.lms.validators;

import java.util.regex.Pattern;

public class EmailValidator {
	public static boolean validate(String email) {
		boolean status = true;
		String email_pattern = 
			        "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		if (NullValidator.validate(email)) status = false;
		else if (!(Pattern.matches(email_pattern, email))) status = false;
		return status;
	}
}
