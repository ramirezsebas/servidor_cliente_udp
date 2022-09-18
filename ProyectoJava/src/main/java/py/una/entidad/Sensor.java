package py.una.entidad;

public class Sensor {

    private long idEstacion;
    private Double porcentajeHumedad;
    private Double velocidadViento;
    private String fecha;
    private String hora;
    private Double temperatura;
    private String ciudad;

    public Sensor() {
    }

    

    public Sensor(long idEstacion, Double porcentajeHumedad, Double velocidadViento, String fecha, String hora,
            Double temperatura, String ciudad) {
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

    public Double getPorcentajeHumedad() {
        return porcentajeHumedad;
    }

    public void setPorcentajeHumedad(Double porcentajeHumedad) {
        this.porcentajeHumedad = porcentajeHumedad;
    }

    public Double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Double temperatura) {
        this.temperatura = temperatura;
    }

    public Double getVelocidadViento() {
        return velocidadViento;
    }

    public void setVelocidadViento(Double velocidadViento) {
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

    public long getIdEstacion() {
        return idEstacion;
    }

    public void setIdEstacion(long idEstacion) {
        this.idEstacion = idEstacion;
    }

    @Override
    public String toString() {
        return "Sensor [idEstacion=" + idEstacion + ", porcentajeHumedad=" + porcentajeHumedad + ", velocidadViento="
                + velocidadViento + ", fecha=" + fecha + ", hora=" + hora + ", temperatura=" + temperatura + ", ciudad="
                + ciudad + "]";
    }

}