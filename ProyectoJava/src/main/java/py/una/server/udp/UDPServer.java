package py.una.server.udp;

import java.io.*;
import java.net.*;

import py.una.bd.SensorDAO;
import py.una.entidad.Sensor;
import py.una.entidad.SensorJSON;

public class UDPServer {

    public static void main(String[] a) {

        // Variables
        int puertoServidor = 9876;
        SensorDAO Sensor = new SensorDAO();

        try {
            // 1) Creamos el socket Servidor de Datagramas (UDP)
            DatagramSocket serverSocket = new DatagramSocket(puertoServidor);
            System.out.println("Servidor Sistemas Distribuidos - UDP ");

            // 2) buffer de datos a enviar y recibir
            byte[] receiveData = new byte[1024];
            byte[] sendData = new byte[1024];

            // 3) Servidor siempre esperando
            while (true) {

                receiveData = new byte[1024];

                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                System.out.println("Esperando a algun cliente... ");

                // 4) Receive LLAMADA BLOQUEANTE
                serverSocket.receive(receivePacket);

                System.out.println("________________________________________________");
                System.out.println("Aceptamos un paquete");

                // Datos recibidos e Identificamos quien nos envio
                String datoRecibido = new String(receivePacket.getData());
                datoRecibido = datoRecibido.trim();
                System.out.println("DatoRecibido: " + datoRecibido);
                Sensor p = SensorJSON.stringObjeto(datoRecibido);

                InetAddress IPAddress = receivePacket.getAddress();

                int port = receivePacket.getPort();

                System.out.println("De : " + IPAddress + ":" + port);
                System.out.println("________________________________________________");

                System.out.println("Sensor Recibida : " + p.getIdEstacion() + ", " + p.getCiudad() + " "
                        + p.getPorcentajeHumedad() + " " + p.getTemperatura() + " " + p.getFecha() + " " + p.getHora());

                try {
                    Sensor.insertar(p);
                    System.out.println("Sensor insertada exitosamente en la Base de datos");
                } catch (Exception e) {
                    System.out.println("Sensor NO insertada en la Base de datos, razón: " + e.getLocalizedMessage());
                }

                // Enviamos la respuesta inmediatamente a ese mismo cliente
                // Es no bloqueante
                sendData = SensorJSON.objetoString(p).getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);

                serverSocket.send(sendPacket);

            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }
}
