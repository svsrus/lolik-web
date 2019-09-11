package ru.svs.lolik.web.kontroller.pogranichnik;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ru.svs.lolik.web.obiekt.PolzovatelOtvet;

/**
 * @author Sergei Shurpenkov
 * @version 1.0 - 25.03.2011
 */
@Service
public class OprosPogranichnik implements Validator {
	private static final String OTVETI = "otveti";
	private static final String OPROS_OTVET_OBAZATELEN = "opros.otvet.obazatelen";

	@Override
	public boolean supports(Class<?> clazz) {
		return PolzovatelOtvet.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		final PolzovatelOtvet polzovatelOtvet = (PolzovatelOtvet) target;
		
		//ValidationUtils.rejectIfEmpty(errors, OTVETI, OPROS_OTVET_OBAZATELEN);
		
		if (polzovatelOtvet.getOtveti() == null || polzovatelOtvet.getOtveti().size() != 1) {
			errors.rejectValue(OTVETI, OPROS_OTVET_OBAZATELEN);
		}
	}
}
