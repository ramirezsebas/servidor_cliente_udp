package py.una.server.tcp;


import java.net.*;
import java.io.*;

public class TCPServer2 {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(4444);
        } catch (IOException e) {
            System.err.println("No se puede abrir el puerto: 4444.");
            System.exit(1);
        }
        System.out.println("Puerto abierto: 4444.");
        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Fallo el accept().");
            System.exit(1);
        }

        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
                                                new InputStreamReader(
                                                clientSocket.getInputStream())
                                               );

        out.println("Bienvenido!");
        String inputLine, outputLine;
        while (true) {
            inputLine = in.readLine();
            System.out.println("Mensaje recibido: " + inputLine);
            if (inputLine.equals("Bye")) {
                out.println(inputLine);
                break;
            }
            outputLine = "Eco desdce el server: " + inputLine;
            out.println(outputLine);
        }
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}
