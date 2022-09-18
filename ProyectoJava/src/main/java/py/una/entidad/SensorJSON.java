package py.una.entidad;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class SensorJSON {
    public static void main(String[] args) throws Exception {
        SensorJSON representacion = new SensorJSON();

        System.out.println("Ejemplo de uso 1: pasar de objeto a string");
        Sensor p = new Sensor(
                1L, 1d, 1d, "01/01/2016", "12:00:00", 1d, "Asuncion");

        String r1 = SensorJSON.objetoString(p);
        System.out.println(r1);

        System.out.println("\n*************************************************************************");
        System.out.println("\nEjemplo de uso 2: pasar de string a objeto");
        String jsonString = "{\"id_estacion\":1,\"porcentaje_humedad\":0.5,\"velocidad_viento\":0.5,\"fecha\":\"01/01/2016\",\"hora\":\"12:00:00\",\"temperatura\":0.5,\"ciudad\":\"Asuncion\"}";

        Sensor r2 = SensorJSON.stringObjeto(jsonString);
        long idEstacion = r2.getIdEstacion();
        double porcentajeHumedad = r2.getPorcentajeHumedad();
        double velocidadViento = r2.getVelocidadViento();
        String fecha = r2.getFecha();
        String hora = r2.getHora();
        double temperatura = r2.getTemperatura();
        String ciudad = r2.getCiudad();

        System.out.println("idEstacion: " + idEstacion);
        System.out.println("porcentajeHumedad: " + porcentajeHumedad);
        System.out.println("velocidadViento: " + velocidadViento);
        System.out.println("fecha: " + fecha);
        System.out.println("hora: " + hora);
        System.out.println("temperatura: " + temperatura);
        System.out.println("ciudad: " + ciudad);

    }

    public static String objetoString(Sensor p) {

        JSONObject obj = new JSONObject();
        obj.put("id_estacion", p.getIdEstacion());
        obj.put("ciudad", p.getCiudad());
        obj.put("porcentaje_humedad", p.getPorcentajeHumedad());
        obj.put("temperatura", p.getTemperatura());
        obj.put("fecha", p.getFecha());
        obj.put("hora", p.getHora());
        obj.put("velocidad_viento", p.getVelocidadViento());

        return obj.toJSONString();
    }

    public static Sensor stringObjeto(String str) throws Exception {
        Sensor p = new Sensor();
        JSONParser parser = new JSONParser();

        Object obj = parser.parse(str.trim());
        JSONObject jsonObject = (JSONObject) obj;

        Long idEstacion = (Long) jsonObject.get("id_estacion");
        p.setIdEstacion(idEstacion);

        String ciudad = (String) jsonObject.get("ciudad");
        p.setCiudad(ciudad);

        Double porcentajeHumedad = (Double) jsonObject.get("porcentaje_humedad");
        p.setPorcentajeHumedad(porcentajeHumedad);

        Double temperatura = (Double) jsonObject.get("temperatura");
        p.setTemperatura(temperatura);

        String fecha = (String) jsonObject.get("fecha");
        p.setFecha(fecha);

        String hora = (String) jsonObject.get("hora");
        p.setHora(hora);

        Double velocidadViento = (Double) jsonObject.get("velocidad_viento");
        p.setVelocidadViento(velocidadViento);

        return p;

    }
}
