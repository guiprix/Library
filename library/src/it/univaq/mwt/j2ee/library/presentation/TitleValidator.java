package it.univaq.mwt.j2ee.library.presentation;

import it.univaq.mwt.j2ee.library.business.model.Title;
import it.univaq.mwt.j2ee.library.common.spring.validation.ValidationUtility;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class TitleValidator implements Validator {

	@Override
	public boolean supports(Class<?> klass) {
		return Title.class.isAssignableFrom(klass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Title title = (Title) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "errors.required");
		
		ValidationUtility.rejectIfMaxLength(errors, "name", "errors.maxlength", title.getName(), 255);
		ValidationUtility.rejectIfMaxLength(errors, "author", "errors.maxlength", title.getAuthor(), 255);
		ValidationUtility.rejectIfMaxLength(errors, "description", "errors.maxlength", title.getDescription(), 255);
		ValidationUtility.rejectIfMaxLength(errors, "isbn", "errors.maxlength", title.getIsbn(), 20);
		ValidationUtility.rejectIfMaxLength(errors, "editor", "errors.maxlength", title.getEditor(), 100);
	}

}