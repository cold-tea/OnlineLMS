package online.lms.validators;

import online.lms.models.User;

public class UserValidator {
	public static boolean validate(User user) {
		boolean status = true;
		//if (user.getUsername().trim().isEmpty()) status = false;
		//System.out.println(user.getUsername());
		if (!NullValidator.validate(user.getUsername())) status = false;
		if (!NullValidator.validate(user.getPassword())) status = false;
		else if (!NameValidator.validate(user.getFullName())) status = false;
		else if (!NullValidator.validate(user.getAddress())) status = false;
		System.out.println("Some Problem");
		return status;
	}
}
