package ru.svs.lolik.web.servis.klass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import ru.svs.lolik.web.servis.EmailServis;

@Service
public class EmailServisKlass implements EmailServis {
	
	@Autowired
	private MailSender mailSender;
	
	/**
	 * Метод посылает письмо по указанному адресу.
	 * 
	 * @param ot - String.
	 * @param komu - String.
	 * @param tema - String.
	 * @param soobshenie - String.
	 */
	public void poslatPismo(String ot, String komu, String tema, String soobshenie) {
		final SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(ot);
		message.setTo(komu);
		message.setSubject(tema);
		message.setText(soobshenie);
		mailSender.send(message);
	}
}
