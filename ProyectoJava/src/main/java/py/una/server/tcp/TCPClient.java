package py.una.server.tcp;


import java.io.*;
import java.net.*;

public class TCPClient {

    public static void main(String[] args) throws IOException {

        Socket unSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            unSocket = new Socket("localhost", 4444);
            // enviamos nosotros
            out = new PrintWriter(unSocket.getOutputStream(), true);

            //viene del servidor
            in = new BufferedReader(new InputStreamReader(unSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Host desconocido");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Error de I/O en la conexion al host");
            System.exit(1);
        }

        
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String fromServer;
        String fromUser;

        while ((fromServer = in.readLine()) != null) {
            System.out.println("Servidor: " + fromServer);
            if (fromServer.equals("Bye")) {
                break;
            }

            fromUser = stdIn.readLine();
            if (fromUser != null) {
                System.out.println("Cliente: " + fromUser);

                //escribimos al servidor
                out.println(fromUser);
            }
        }

        out.close();
        in.close();
        stdIn.close();
        unSocket.close();
    }
}
