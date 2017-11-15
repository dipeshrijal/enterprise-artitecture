package cs544.exercise13_2.bank.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.util.StopWatch;

import cs544.exercise13_2.bank.domain.Account;

@Aspect
public class LoggerAdvice {

	@After("execution(public * cs544.exercise13_2.bank.dao.*.*(..)) && args(account)")
	public void logger(JoinPoint joinPoint, Account account) {

		String toPrint = null;

		String method = joinPoint.getSignature().getName();

		if (method == "saveAccount")
			toPrint = "saving";

		else if (method == "updateAccount")
			toPrint = "update";

		else
			toPrint = "loading";

		System.out.println(joinPoint.getTarget().getClass().getSimpleName() + ": " + toPrint
				+ " account with accountnr =" + account.getAccountnumber());
	}

	@After("execution(public * cs544.exercise13_2.bank.service.*.*(..)) && args(accountNumber, amount)")
	public void jmsLogger(JoinPoint joinPoint, long accountNumber, double amount) {
		if (amount > 10000) {
			System.out.println("JMS Message Aop: Deposit of $ " + amount + " to account with accountNumber= " + accountNumber);
		}
	}

	@Around("execution(public * cs544.exercise13_2.bank.service.*.*(..))")
	public Object invoke(ProceedingJoinPoint call) throws Throwable {
		StopWatch sw = new StopWatch();
		sw.start(call.getSignature().getName());
		Object retVal = call.proceed();
		sw.stop();

		long totaltime = sw.getLastTaskTimeMillis();

		System.out.println("Time to execute save = " + totaltime + " ms");

		return retVal;
	}

}
