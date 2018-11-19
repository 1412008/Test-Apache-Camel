package message;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Consumer {

	private static String url = "tcp://localhost:61616";

	private static String subject = "myjms";

	public static void main(String[] args) throws JMSException, InterruptedException {

		ActiveMQConnectionFactory jmsConnectionFactory = new ActiveMQConnectionFactory(url);
		jmsConnectionFactory.setUserName("admin");
		jmsConnectionFactory.setPassword("admin");

		ConnectionFactory connectionFactory = jmsConnectionFactory;
		Connection connection = connectionFactory.createConnection();
		connection.start();

		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		Destination destination = session.createQueue(subject);

		MessageConsumer consumer = session.createConsumer(destination);

		while (true) {
			Message message = consumer.receive();

			if (message instanceof TextMessage) {
				TextMessage textMessage = (TextMessage) message;
				System.out.println("Received message '" + textMessage.getText() + "'");
			}
			//Thread.sleep(500);
		}

		// connection.close();
	}
}
