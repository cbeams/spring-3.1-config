import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HelloWorld {

	public static void main(String... args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(MyConfig.class);

		GreetingService greetingService = ctx.getBean(GreetingService.class);

		System.out.println(greetingService.getGreeting());
	}

}
