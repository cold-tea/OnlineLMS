package online.lms.validators;

public class NullValidator {
	public static boolean validate(String text) {
		if (text.trim().isEmpty()) {
			return false;
		}
		return true;
	}
	
	public static boolean validate(Integer number) {
		if (number.toString().isEmpty()) {
			return false;
		} 
		return true;
	}
	
	public static boolean validate(Double number) {
		if (number.toString().isEmpty()) {
			return false;
		} 
		return true;
	}
}
