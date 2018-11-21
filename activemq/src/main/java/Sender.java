/**
 * @author <a href="mailto:wanggy6@asiainfo.com">wanggy6</a>
 * @version 1.0
 */
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
public class Sender {
    private static final int SEND_NUMBER = 5;

    static DateFormat df = new SimpleDateFormat("HH:mm:ss:SSS");

    static ConnectionFactory connectionFactory;
    static Connection connection = null;
    static Session session;
    static Destination destination;
    static MessageProducer producer;

    public static void main(String[] args) {
        connectionFactory = new ActiveMQConnectionFactory(
                ActiveMQConnection.DEFAULT_USER,
                ActiveMQConnection.DEFAULT_PASSWORD,
                "tcp://192.168.150.128:61616");
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("queue");
            producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            sendMessage(session, producer);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != connection)
                    connection.close();
            } catch (Throwable ignore) {
            }
        }
    }

    public static void sendMessage(Session session, MessageProducer producer) throws Exception {
        for (int i = 1; i <= SEND_NUMBER; i++) {
            TextMessage message = session.createTextMessage(" 发送的消息" + i);
            System.out.println(df.format(new Date())+"发送消息：" + "ActiveMq 发送的消息" + i);
            producer.send(message);
        }
    }
}
