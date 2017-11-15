package exercise11_1;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloApp {
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("springconfig.xml");
		Greeting greetingService = context.getBean("greetingService", Greeting.class);
		greetingService.sayHello();
	}

}
