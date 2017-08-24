package org.enoy.klc.control.external;

import org.enoy.klc.control.utils.Sleep;

import java.io.IOException;
import java.net.*;

public class ExternalServer implements Runnable {

    public static final int EXTERNAL_SERVER_PORT = 42359;
    private static Thread currentServer;
    private static ExternalServer currentInstance;

    private ServerSocket serverSocket;
    private ThreadGroup clientsThreadGroup;

    private ExternalServer() {
        clientsThreadGroup = new ThreadGroup("External Server Client Threads");
    }

    @Override
    public void run() {

        try {
            runServer();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            stopServer();
        }

    }

    private void runServer() throws IOException {
        // Only localhost!
        SocketAddress socketAddress = new InetSocketAddress(InetAddress.getByName( null ), EXTERNAL_SERVER_PORT);
        serverSocket = new ServerSocket();
        serverSocket.bind(socketAddress);

        while(!Thread.interrupted()) {

            Socket socket = serverSocket.accept();

            ExternalClient client = new ExternalClient(socket);
            client.start(clientsThreadGroup);

            Sleep.sleep(20);

        }

    }

    private void stopServer() {
        currentServer = null;
        currentInstance = null;
        clientsThreadGroup.interrupt();
    }

    public int getActiveClientsCount() {
        return clientsThreadGroup.activeCount();
    }

    public static void startExternalServer() {

        if(currentServer == null) {
            ExternalServer externalServer = new ExternalServer();
            startExternalServer(externalServer);
        } else {
            throw new RuntimeException("External Server already Running");
        }

    }

    public static ExternalServer getCurrentInstance() {
        return currentInstance;
    }

    private static void startExternalServer(ExternalServer externalServer) {
        currentServer = new Thread(externalServer);
        currentServer.setDaemon(true);
        currentServer.start();
        currentInstance = externalServer;
    }

}
