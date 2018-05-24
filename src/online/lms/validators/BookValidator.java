package online.lms.validators;

import online.lms.models.Book;

public class BookValidator {
	public static boolean validate(Book book) {
		boolean status = true;
		if (!NullValidator.validate(book.getId())) status = false;
		if (!NameValidator.validate(book.getName())) status = false;
		if (!NameValidator.validate(book.getAuthor())) status = false;
		if (!NameValidator.validate(book.getPublisher())) status = false;
		if (!NameValidator.validate(book.getEdition())) status = false;
		if (!NullValidator.validate(book.getPrice())) status = false;
		return status;
	}
}
