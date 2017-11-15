package cs544.exercise13_1;

import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class LogAdvice {

	// ANSWER A
	// @After("execution(public void cs544.exercise13_1.EmailSender.sendEmail(..)))
	// public void EmailLoggerAfter(JoinPoint joinpoint) {
	// Date date = new Date();
	// System.out.println(date + " method= " + joinpoint.getSignature().getName());
	// }

	// ANSWER B
	// @After("execution(public void cs544.exercise13_1.EmailSender.sendEmail(..))
	// && args(address, message)")
	// public void EmailLoggerAfter(JoinPoint joinpoint, String address, String
	// message) {
	// Date date = new Date();
	// System.out.println(date + " method= " + joinpoint.getSignature().getName() +
	// " address= " + address);
	// System.out.println("message = " + message);
	// }

	@After("execution(public void cs544.exercise13_1.EmailSender.sendEmail(..)) && args(address, message)")
	public void EmailLoggerAfter(JoinPoint joinpoint, String address, String message) {
		EmailSender emailSender = (EmailSender) joinpoint.getTarget();
		Date date = new Date();
		System.out.println(date + " method= " + joinpoint.getSignature().getName() + " address= " + address);
		System.out.println("message = " + message);
		System.out.println("outgoing mail server = " + emailSender.outgoingMailServer);
	}

	

}
