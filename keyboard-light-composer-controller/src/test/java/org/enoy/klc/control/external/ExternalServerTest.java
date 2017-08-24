package org.enoy.klc.control.external;

import org.enoy.klc.control.external.ExternalValue.ExternalValueDataType;
import org.enoy.klc.control.utils.Sleep;
import org.junit.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ExternalServerTest {

    @Test
    public void startExternalServer() throws Exception {

        ExternalServer.startExternalServer();

        Socket socket = getSocket();
        Socket socket2 = getSocket();

        sendMsg(socket, "health", "Player1", ExternalValueDataType.FLOAT, 100f);
        sendMsg(socket, "inWater", "Player1", ExternalValueDataType.BOOLEAN, true);

        sendMsg(socket2, "money", "Player2", ExternalValueDataType.LONG, 50);
        sendMsg(socket2, "inWater", "Player2", ExternalValueDataType.BOOLEAN, false);

        socket.close();
        socket2.close();

        Sleep.sleep(2000);
    }

    private void sendMsg(Socket socket, String identifier, String parameter, ExternalValueDataType dataType, Object value) throws IOException {
        sendMsg(socket, "application;" + identifier + ";" + parameter + ";" + dataType.getDataTypeId() + ";" + value);
    }

    private void sendMsg(Socket socket, String msg) throws IOException {
        PrintWriter pw = new PrintWriter(socket.getOutputStream());
        pw.println(msg);
        pw.flush();
    }

    private Socket getSocket() throws IOException {
        return new Socket("localhost", ExternalServer.EXTERNAL_SERVER_PORT);
    }

}