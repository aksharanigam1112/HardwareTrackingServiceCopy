package controller;

import controller.AsynListener;
import javax.jms.JMSException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Scheduling
{
	public static void main(String arg[]) throws InterruptedException, JMSException
	{
		ConfigurableApplicationContext context = SpringApplication.run(Scheduling.class);
		AsynListener async = context.getBean(AsynListener.class);
		async.runAsync();
	}
}