package ex5._57;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class DummyMailSender implements MailSender {

	@Override
	public void send(SimpleMailMessage mailMessage) throws MailException {
		// TODO Auto-generated method stub
		System.out.println("메일 전송 dummy send");
	}

	@Override
	public void send(SimpleMailMessage... mailMessage) throws MailException {
		// TODO Auto-generated method stub
		
	}

}
