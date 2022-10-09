package reveiced;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
 
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
 
public class MessageReceiver {
 
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

    private static String subject = "JCG_QUEUE";
 
    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection myConnection = connectionFactory.createConnection();
        myConnection.start();
 
        Session session = myConnection.createSession(false,
                Session.AUTO_ACKNOWLEDGE);
 
        Destination myDestination = session.createQueue(subject);
 
        MessageConsumer sendToConsumer = session.createConsumer(myDestination);
 
        Message myMessage = sendToConsumer.receive();
 
        if (myMessage instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) myMessage;
            System.out.println("Received message '" + textMessage.getText() + "'");
        }
        myConnection.close();
    }
}