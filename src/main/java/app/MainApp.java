package app;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

import route.SimpleRouteBuilder;

public class MainApp {

	private static String url = "tcp://localhost:61616";

	public static void main(String[] args) {
		SimpleRouteBuilder srb = new SimpleRouteBuilder();

		CamelContext cc = new DefaultCamelContext();
		ConnectionFactory cf = new ActiveMQConnectionFactory(url);
		cc.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(cf));

		try {
			cc.addRoutes(srb);
			cc.start();
			Thread.sleep(3 * 1000);
			cc.stop();
			System.out.println("Done!");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
