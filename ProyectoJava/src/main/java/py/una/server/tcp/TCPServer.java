package py.una.server.tcp;

import java.util.concurrent.TimeUnit;
import java.net.*;
import java.io.*;

public class TCPServer {

    public static void main(String[] args) throws Exception {

        int puertoServidor = 4444;
        int tiempo_procesamiento_miliseg = 2000;
		
		try{
			tiempo_procesamiento_miliseg = Integer.parseInt(args[0]);
		}catch(Exception e1){
			System.out.println("Se omite el argumento, tiempo de procesamiento " + tiempo_procesamiento_miliseg  + ". Ref: " + e1);
		}
		
		
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(puertoServidor);
        } catch (IOException e) {
            System.err.println("No se puede abrir el puerto: " +puertoServidor+ ".");
            System.exit(1);
        }
        System.out.println("Puerto abierto: "+puertoServidor+".");
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
                clientSocket.getInputStream()));

        out.println("Bienvenido!");
        String inputLine, outputLine;

        inputLine = in.readLine();
        System.out.println("Mensaje recibido: " + inputLine);
        outputLine = "Respuesta igual al recibido: " + inputLine;

		TimeUnit.MILLISECONDS.sleep(tiempo_procesamiento_miliseg);
		
        out.println(outputLine);

        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}
