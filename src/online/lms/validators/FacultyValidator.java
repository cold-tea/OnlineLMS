package online.lms.validators;

import online.lms.models.Faculty;

public class FacultyValidator {
	public static boolean validate(Faculty faculty) {
		boolean status = true;
		if (!NullValidator.validate(faculty.getId())) return false;
		if (!NameValidator.validate(faculty.getName())) return false;
		if (!NullValidator.validate(faculty.getDescription())) return false;
		return status;
	}
}
