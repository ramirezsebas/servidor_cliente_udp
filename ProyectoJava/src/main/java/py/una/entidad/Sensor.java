package py.una.entidad;

public class Sensor {

    private int idEstacion;
    private double porcentajeHumedad;
    private double velocidadViento;
    private String fecha;
    private String hora;
    private double temperatura;
    private String ciudad;

    public Sensor() {
    }

    public Sensor(int idEstacion, double porcentajeHumedad, double velocidadViento, String fecha, String hora,
            double temperatura, String ciudad) {
        this.idEstacion = idEstacion;
        this.porcentajeHumedad = porcentajeHumedad;
        this.velocidadViento = velocidadViento;
        this.fecha = fecha;
        this.hora = hora;
        this.temperatura = temperatura;
        this.ciudad = ciudad;

    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public double getPorcentajeHumedad() {
        return porcentajeHumedad;
    }

    public void setPorcentajeHumedad(double porcentajeHumedad) {
        this.porcentajeHumedad = porcentajeHumedad;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public double getVelocidadViento() {
        return velocidadViento;
    }

    public void setVelocidadViento(double velocidadViento) {
        this.velocidadViento = velocidadViento;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getIdEstacion() {
        return idEstacion;
    }

    public void setIdEstacion(int idEstacion) {
        this.idEstacion = idEstacion;
    }

}