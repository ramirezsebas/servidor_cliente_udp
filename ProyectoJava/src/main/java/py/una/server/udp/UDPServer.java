package py.una.server.udp;

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
            System.out.println("Alumno: " + "Matias Sebastian Ramirez Brizuela");
            System.out.println("Cedula: " + "4.449.096");
            System.out.println("Fecha Nacimiento: " + "20 de Diciembre de 1998");
            System.out.println();

            // 2) buffer de datos a enviar y recibir
            byte[] receiveData = new byte[1024];
            byte[] sendData = new byte[1024];

            // Create an array of Sensor
            Sensor[] sensors = new Sensor[10];

            sensors[0] = new Sensor(1L, 51d, 25d, "10/10/2022", "10:20:00", 50d, "Asuncion");
            sensors[1] = new Sensor(2L, 52d, 26d, "11/10/2022", "10:30:00", 51d, "San Lorenzo");
            sensors[2] = new Sensor(3L, 53d, 27d, "12/10/2022", "10:40:00", 52d, "Luque");
            sensors[3] = new Sensor(4L, 54d, 28d, "13/10/2022", "10:50:00", 53d, "Asuncion");
            sensors[4] = new Sensor(5L, 55d, 29d, "14/10/2022", "11:00:00", 54d, "Asuncion");
            sensors[5] = new Sensor(6L, 56d, 20d, "15/10/2022", "12:00:00", 55d, "Asuncion");
            sensors[6] = new Sensor(7L, 57d, 35d, "16/10/2022", "10:05:00", 56d, "San Lorenzo");
            sensors[7] = new Sensor(8L, 58d, 36d, "17/10/2022", "10:10:00", 57d, "Asuncion");
            sensors[8] = new Sensor(9L, 59d, 37d, "18/10/2022", "10:15:00", 58d, "Asuncion");
            sensors[9] = new Sensor(10L, 60d, 38d, "19/10/2022", "10:20:00", 59d, "Asuncion");

            // 3) Servidor siempre esperando
            while (true) {

                receiveData = new byte[1024];

                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                System.out.println("Esperando a algun cliente... ");
                System.out.println("________________________________________________");

                serverSocket.receive(receivePacket);

                System.out.println("Aceptamos un paquete");

                String idOperacion = new String(receivePacket.getData(), 0, receivePacket.getLength());

                System.out.println("El cliente nos envio: " + idOperacion);
                System.out.println("________________________________________________");

                if (idOperacion.equals("1")) {

                    System.out.println("Operacion: " + idOperacion + " - Retornar todos los sensores");
                    System.out.println("________________________________________________");

                    // 5) Enviamos la respuesta
                    InetAddress IPAddress = receivePacket.getAddress();
                    int port = receivePacket.getPort();

                    // Send list of sensors to client in JSON format
                    for (int i = 0; i < sensors.length; i++) {
                        Sensor sensor = sensors[i];
                        String json = SensorJSON.objetoString(sensor);
                        sendData = json.getBytes();
                        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                        serverSocket.send(sendPacket);

                        System.out.println("Enviando: " + json);

                        Thread.sleep(1000);
                    }

                    // Send end of list
                    sendData = "END".getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                    serverSocket.send(sendPacket);

                    System.out.println("Enviando: END");
                    System.out.println("________________________________________________");

                } else if (idOperacion.equals("2")) {
                    // retornar la temperatura de un sensor en particular dependiendo de la ciudad
                    System.out.println("Operacion: " + idOperacion
                            + " - Retornar la temperatura de un sensor en particular dependiendo de la ciudad");

                    // Recibimos la ciudad
                    receiveData = new byte[1024];
                    receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    serverSocket.receive(receivePacket);
                    String ciudad = new String(receivePacket.getData(), 0, receivePacket.getLength());
                    System.out.println("Ciudad: " + ciudad);
                    System.out.println("________________________________________________");

                    InetAddress IPAddress2 = receivePacket.getAddress();
                    int port2 = receivePacket.getPort();

                    // Buscamos el sensor con la ciudad recibida
                    Sensor sensor = null;
                    for (int i = 0; i < sensors.length; i++) {
                        if (sensors[i].getCiudad().equals(ciudad)) {
                            sensor = sensors[i];
                            break;
                        }
                    }

                    // Enviamos la temperatura del sensor
                    if (sensor != null) {
                        String json = SensorJSON.objetoString(sensor);
                        sendData = json.getBytes();
                        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress2, port2);
                        serverSocket.send(sendPacket);

                        System.out.println("Enviando: " + json);
                        System.out.println("________________________________________________");
                    } else {
                        sendData = "No se encontro el sensor".getBytes();
                        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress2, port2);
                        serverSocket.send(sendPacket);

                        System.out.println("Enviando: No se encontro el sensor");
                        System.out.println("________________________________________________");
                    }


                } else {
                    System.out.println("Operacion: " + idOperacion + " - No existe");

                }

            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }
}
