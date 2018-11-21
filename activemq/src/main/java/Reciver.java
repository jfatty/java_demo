/**
 * @author <a href="mailto:wanggy6@asiainfo.com">wanggy6</a>
 * @version 1.0
 */
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Reciver {

    static DateFormat df = new SimpleDateFormat("HH:mm:ss:SSS");

    static ConnectionFactory connectionFactory;
    static Connection connection = null;
    static Session session;
    static Destination destination;
    static MessageConsumer consumer;

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
            consumer = session.createConsumer(destination);
            while (true) {
                //监听和receive只能使用一个
                //consumer.setMessageListener(new AcListener());
                TextMessage message = (TextMessage) consumer.receive(100000);
                if (null != message) {
                    System.out.println(df.format(new Date())+"收到消息" + message.getText());
                    session.commit();
                } else {
                    break;
                }
            }
           // session.commit();
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

}