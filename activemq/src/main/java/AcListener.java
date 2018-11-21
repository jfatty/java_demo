/**
 * @author <a href="mailto:wanggy6@asiainfo.com">wanggy6</a>
 * @version 1.0
 */
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class AcListener implements MessageListener{
    static DateFormat dfm = new SimpleDateFormat("HH:mm:ss:SSS");

    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                TextMessage msg = (TextMessage)message;
                System.out.println(dfm.format(new Date())+"收到消息" + msg.getText());
            }
            if (message instanceof MapMessage){
                MapMessage map = (MapMessage)message;
                String stock = map.getString("stock");
                double price = map.getDouble("price");
                double offer = map.getDouble("offer");
                boolean up = map.getBoolean("up");
                DecimalFormat df = new DecimalFormat( "#,###,###,##0.00" );
                System.out.println(dfm.format(new Date())+"收到消息"+stock + "\t" + df.format(price) + "\t" + df.format(offer) + "\t" + (up?"up":"down"));
            }
        } catch (Exception ee) { }
    }


}