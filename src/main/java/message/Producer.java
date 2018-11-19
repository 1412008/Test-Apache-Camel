package message;

import java.util.Date;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Producer {
	private static String url = "tcp://localhost:61616";

	private static String subject = "TESTQUEUE";

	public static void main(String[] args) throws JMSException {

		ActiveMQConnectionFactory jmsConnectionFactory = new ActiveMQConnectionFactory(url);

		jmsConnectionFactory.setUserName("admin");
		jmsConnectionFactory.setPassword("admin");

		Connection connection = jmsConnectionFactory.createConnection();
		connection.start();

		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		Destination destination = session.createQueue(subject);

		MessageProducer producer = session.createProducer(destination);

		Date d = new Date();
		String msg = "Hello World, " + d.toString();
		TextMessage message = session.createTextMessage(msg);
		producer.send(message);

		System.out.println("DONE");

		producer.close();
		System.exit(0);
	}
}
