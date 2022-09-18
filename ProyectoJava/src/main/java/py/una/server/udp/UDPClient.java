package py.una.server.udp;

import java.io.*;
import java.net.*;

class UDPClient {

    public static void main(String a[]) throws Exception {

        // Datos necesario
        String direccionServidor = "127.0.0.1";

        if (a.length > 0) {
            direccionServidor = a[0];
        }

        int puertoServidor = 9876;

        try {

            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            InetAddress IPAddress = InetAddress.getByName(direccionServidor);
            DatagramSocket clientSocket = new DatagramSocket();

            System.out.println("UDP Cliente");
            System.out.println("Alumno: " + "Matias Sebastian Ramirez Brizuela");
            System.out.println("Cedula: " + "4.449.096");
            System.out.println("Fecha Nacimiento: " + "20 de Diciembre de 1998");
            System.out.println();
            System.out.println("Intentando conectar a = " + IPAddress + ":" + puertoServidor + " via UDP...");

            System.out.println("_____________________________________________________________________");

            // Preguntarle al usuario que operacion desea realizar
            System.out.println("Seleccione la operacion que desea realizar: ");
            System.out.println("1 - Retornar todos los sensores");
            System.out.println("2 - Retornar la temperatura de un sensor en particular dependiendo de la ciudad");
            System.out.println("Cualquier otro numero - Cerrar el cliente");

            String operacion = inFromUser.readLine();


           

            byte[] sendData = new byte[1024];
            byte[] receiveData = new byte[1024];

            sendData = operacion.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, puertoServidor);

            System.out.println("Enviando paquete...");
            clientSocket.send(sendPacket);

            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

            

            if (operacion.equals("1")) {
                // retornar todos los sensores
                System.out.println("Operacion: " + operacion + " - Retornar todos los sensores");
                System.out.println("________________________________________________");

                // keep receiving packets until we receive the "END" packet
                while (true) {
                    clientSocket.receive(receivePacket);
                    String json = new String(receivePacket.getData(), 0, receivePacket.getLength());
                    if (json.equals("END")) {
                        break;
                    }
                    System.out.println("Recibiendo: " + json);
                }

            } else if (operacion.equals("2")) {
                // retornar la temperatura de un sensor en particular dependiendo de la ciudad
                System.out.println("Operacion: " + operacion
                        + " - Retornar la temperatura de un sensor en particular dependiendo de la ciudad");

                System.out.println("________________________________________________");
                // ASk for ciudad
                System.out.println("Ingrese la ciudad: ");
                String ciudad = inFromUser.readLine();

                sendData = ciudad.getBytes();

                sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, puertoServidor);

                System.out.println("Enviando paquete...");

                clientSocket.send(sendPacket);

                receivePacket = new DatagramPacket(receiveData, receiveData.length);

                System.out.println("Esperando por paquete...");

                clientSocket.receive(receivePacket);

                //Recibimos el sensor 
                String json = new String(receivePacket.getData(), 0, receivePacket.getLength());

                System.out.println("Recibiendo: " + json);

            } else {
                System.out.println("Operacion: " + operacion + " - No existe");

            }

            // clientSocket.close();
        } catch (UnknownHostException ex) {
            System.err.println(ex);
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
