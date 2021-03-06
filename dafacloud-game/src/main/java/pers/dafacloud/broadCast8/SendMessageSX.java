package pers.dafacloud.broadCast8;

import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.net.URI;
import java.util.UUID;

public class SendMessageSX {
    static WebSocketContainer container = ContainerProvider.getWebSocketContainer();

    public static void process() {
         //WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        try {
            //System.out.println("ws://dafacloud-test.com/v1/broadCast/security?type=8&uuid=" + UUID.randomUUID() + "_" + System.currentTimeMillis());
            Session session = container.connectToServer(new ResponceMessage(), URI.create("ws://dafacloud-master.com/v1/broadCast/security?type=8&uuid=" + UUID.randomUUID() + "_" + System.currentTimeMillis()));
            //Session session = container.connectToServer(new ResponceMessage(), URI.create("ws://dafacloud-master.com/v1/broadCast/security?type=8&uuid=ca2ce00a-520e-43f0-bb39-975be33144c6_1590667441358"));
            //ws://dafacloud-test.com/v1/broadCast/security?type=8&uuid=022f4390-e4ce-4541-a719-4b5644f40781_1590553903184
            //ws://dafacloud-test.com/v1/broadCast/security?type=8&uuid=6de1e24e-fa38-49ba-9909-fbaf020a6e32_1590554140920
            session.setMaxIdleTimeout(5000);
            session.setMaxTextMessageBufferSize(128);//设置缓冲文本大小
            //session.setMaxBinaryMessageBufferSize(204800);
            session.setMaxBinaryMessageBufferSize(128);
            //System.out.println(ClassLayout.parseInstance(session).toPrintable());
            for (; ; ) {
                if (session.isOpen()) {
                    session.getBasicRemote().sendText("{\"code\":9}");//发送消息
                    Thread.sleep(3000);
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

