package org.enoy.klc.control.external;

import java.io.IOException;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExternalClient implements Runnable {

    private static final String MSG_REGEX_STRING = "^(\\w+);(\\w+);([^;\\n\\r]+)?;(\\d);([^\\n\\r]+)$";
    private static final Pattern MSG_REGEX = Pattern.compile(MSG_REGEX_STRING);

    private Socket socket;

    ExternalClient(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try {
            try (Scanner scanner = new Scanner(socket.getInputStream())) {

                while (!Thread.interrupted()) {

                    String line = scanner.nextLine();
                    handleMessage(line);

                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (NoSuchElementException e) {
                // Connection Lost
                System.out.println("Connection closed: " + socket.getInetAddress());
            }
        } finally {
            disconnect();
        }

    }

    private void handleMessage(String line) {

        line = line.trim();
        Matcher matcher = MSG_REGEX.matcher(line);
        if (matcher.find()) {
            String scope = matcher.group(1);
            String identifier = matcher.group(2);
            String parameter = matcher.group(3);
            String dataType = matcher.group(4);
            String data = matcher.group(5);

            ExternalValueContainer.getInstance().put(scope, identifier, parameter, dataType, data);
        }

    }

    private void disconnect() {
        if (socket != null && !socket.isClosed())
            try {
                socket.close();
                socket = null;
            } catch (IOException e) {
                e.printStackTrace();
            }

    }

    Thread start(ThreadGroup threadGroup) {
        Thread clientThread = new Thread(threadGroup, this, "Client Thread");
        clientThread.setDaemon(true);
        clientThread.start();

        return clientThread;
    }

}
