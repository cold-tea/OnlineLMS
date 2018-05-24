package online.lms.validators;

import java.util.regex.Pattern;

public class NameValidator {
	
	//("^[a-zA-Z\\s]*$")
	public static boolean validate(String name) {
		boolean status = true;
		if (!NullValidator.validate(name)) status = false;
		else if (!(Pattern.matches("^[\\p{L} .'-]+$", name))) status = false;
		return status;
	}
}
