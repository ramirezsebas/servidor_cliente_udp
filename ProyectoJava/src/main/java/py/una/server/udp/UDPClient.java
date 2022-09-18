package py.una.server.udp;

import java.io.*;
import java.net.*;

import py.una.entidad.Sensor;
import py.una.entidad.SensorJSON;

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

            DatagramSocket clientSocket = new DatagramSocket();

            InetAddress IPAddress = InetAddress.getByName(direccionServidor);
            System.out.println("Intentando conectar a = " + IPAddress + ":" + puertoServidor + " via UDP...");

            byte[] sendData = new byte[1024];
            byte[] receiveData = new byte[1024];

            System.out.print("Ingrese el id del estacion (debe ser numérico): ");
            String strcedula = inFromUser.readLine();
            int idEstacion;
            ;
            try {
                idEstacion = Integer.parseInt(strcedula);
            } catch (Exception e1) {
                throw new Exception("El id del estacion debe ser numérico");
            }

            System.out.print("Ingrese la ciudad: ");
            String ciudad = inFromUser.readLine();

            System.out.print("Ingrese el porcentaje de humedad (debe ser numérico): ");
            String strporcentajeHumedad = inFromUser.readLine();
            double porcentajeHumedad;
            try {
                porcentajeHumedad = Double.parseDouble(strporcentajeHumedad);
            } catch (Exception e1) {
                throw new Exception("El porcentaje de humedad debe ser numérico");
            }

            System.out.print("Ingrese la temperatura (debe ser numérico): ");
            String strtemperatura = inFromUser.readLine();
            double temperatura;
            try {
                temperatura = Double.parseDouble(strtemperatura);
            } catch (Exception e1) {
                throw new Exception("La temperatura debe ser numérico");
            }

            System.out.print("Ingrese la velocidad del viento (debe ser numérico): ");
            String strvelocidadViento = inFromUser.readLine();
            double velocidadViento;
            try {
                velocidadViento = Double.parseDouble(strvelocidadViento);
            } catch (Exception e1) {
                throw new Exception("La velocidad del viento debe ser numérico");
            }

            System.out.print("Ingrese la fecha (dd/mm/aaaa): ");
            String fecha = inFromUser.readLine();

            System.out.print("Ingrese la hora (hh:mm:ss): ");
            String hora = inFromUser.readLine();

            Sensor sensor = new Sensor(idEstacion, porcentajeHumedad, velocidadViento, fecha, hora, temperatura,
                    ciudad);

            String json = SensorJSON.objetoString(sensor);

            sendData = json.getBytes();

            System.out.println("Enviar " + json + " al servidor. (" + sendData.length + " bytes)");
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, puertoServidor);

            clientSocket.send(sendPacket);

            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

            System.out.println("Esperamos si viene la respuesta.");

            // Vamos a hacer una llamada BLOQUEANTE entonces establecemos un timeout maximo
            // de espera
            clientSocket.setSoTimeout(10000);

            try {
                // ESPERAMOS LA RESPUESTA, BLOQUENTE
                clientSocket.receive(receivePacket);

                String respuesta = new String(receivePacket.getData());
                Sensor presp = SensorJSON.stringObjeto(respuesta.trim());

                InetAddress returnIPAddress = receivePacket.getAddress();
                int port = receivePacket.getPort();

                System.out.println("Respuesta desde =  " + returnIPAddress + ":" + port);

            } catch (SocketTimeoutException ste) {

                System.out.println("TimeOut: El paquete udp se asume perdido.");
            }
            clientSocket.close();
        } catch (UnknownHostException ex) {
            System.err.println(ex);
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
