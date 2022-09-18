package py.una.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import py.una.entidad.Sensor;

public class SensorDAO {

    /**
     * 
     * @param condiciones
     * @return
     */
    public List<Sensor> seleccionar() {

        String query = "SELECT * FROM sensor";

        List<Sensor> lista = new ArrayList<Sensor>();

        Connection conn = null;
        try {
            conn = Bd.connect();
            ResultSet rs = conn.createStatement().executeQuery(query);

            while (rs.next()) {
                Sensor p = new Sensor();
                p.setCiudad(rs.getString("ciudad"));
                p.setFecha(rs.getString("fecha"));
                p.setHora(rs.getString("hora"));
                p.setIdEstacion(rs.getInt("idEstacion"));
                p.setPorcentajeHumedad(rs.getDouble("porcentajeHumedad"));
                p.setTemperatura(rs.getDouble("temperatura"));
                p.setVelocidadViento(rs.getDouble("velocidadViento"));

                lista.add(p);
            }

        } catch (SQLException ex) {
            System.out.println("Error en la seleccion: " + ex.getMessage());
        } finally {
            try {
                conn.close();
            } catch (Exception ef) {
                System.out.println("No se pudo cerrar la conexion a BD: " + ef.getMessage());
            }
        }
        return lista;

    }

    public String seleccionarTemperaturaPorCiudad(String ciudad) {
        String query = "SELECT temperatura FROM sensor WHERE ciudad = ?";

        Connection conn = null;
        try {
            conn = Bd.connect();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, ciudad);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                return rs.getString("temperatura");
            }

        } catch (SQLException ex) {
            System.out.println("Error en la seleccion: " + ex.getMessage());
        } finally {
            try {
                conn.close();
            } catch (Exception ef) {
                System.out.println("No se pudo cerrar la conexion a BD: " + ef.getMessage());
            }
        }
        return null;
    }

    public List<Sensor> seleccionarPorIdEstacion(int idEstacion) {
        String SQL = "SELECT * FROM sensor WHERE idEstacion = ?";

        List<Sensor> lista = new ArrayList<Sensor>();

        Connection conn = null;
        try {
            conn = Bd.connect();
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, idEstacion);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Sensor p = new Sensor();
                p.setCiudad(rs.getString("ciudad"));
                p.setFecha(rs.getString("fecha"));
                p.setHora(rs.getString("hora"));
                p.setIdEstacion(rs.getInt("idEstacion"));
                p.setPorcentajeHumedad(rs.getDouble("porcentajeHumedad"));
                p.setTemperatura(rs.getDouble("temperatura"));
                p.setVelocidadViento(rs.getDouble("velocidadViento"));

                lista.add(p);
            }

        } catch (SQLException ex) {
            System.out.println("Error en la seleccion: " + ex.getMessage());
        } finally {
            try {
                conn.close();
            } catch (Exception ef) {
                System.out.println("No se pudo cerrar la conexion a BD: " + ef.getMessage());
            }
        }
        return lista;

    }

    public long insertar(Sensor p) throws SQLException {

        String SQL = "INSERT INTO sensor (id_estacion, porcentaje_humedad, velocidad_viento, fecha, hora, temperatura, ciudad) VALUES (?, ?, ?, ?, ?, ?, ?)";

        long id = 0;
        Connection conn = null;

        try {
            conn = Bd.connect();
            PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            pstmt.setLong(1, p.getIdEstacion());
            pstmt.setDouble(2, p.getPorcentajeHumedad());
            pstmt.setDouble(3, p.getVelocidadViento());
            pstmt.setString(4, p.getFecha());
            pstmt.setString(5, p.getHora());
            pstmt.setDouble(6, p.getTemperatura());
            pstmt.setString(7, p.getCiudad());

            int affectedRows = pstmt.executeUpdate();
            // check the affected rows
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getLong(1);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error en la insercion: " + ex.getMessage());
        } finally {
            try {
                conn.close();
            } catch (Exception ef) {
                System.out.println("No se pudo cerrar la conexion a BD: " + ef.getMessage());
            }
        }

        return id;

    }

    public long actualizar(Sensor p) throws SQLException {

        String SQL = "UPDATE sensor SET idEstacion = ?, porcentajeHumedad = ?, velocidadViento = ?, fecha = ?, hora = ?, temperatura = ?, ciudad = ? WHERE idEstacion = ?";

        long id = 0;
        Connection conn = null;

        try {
            conn = Bd.connect();
            PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            pstmt.setLong(1, p.getIdEstacion());
            pstmt.setDouble(2, p.getPorcentajeHumedad());
            pstmt.setDouble(3, p.getVelocidadViento());
            pstmt.setString(4, p.getFecha());
            pstmt.setString(5, p.getHora());
            pstmt.setDouble(6, p.getTemperatura());
            pstmt.setString(7, p.getCiudad());
            pstmt.setLong(8, p.getIdEstacion());

            int affectedRows = pstmt.executeUpdate();
            // check the affected rows
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getLong(1);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error en la actualizacion: " + ex.getMessage());
        } finally {
            try {
                conn.close();
            } catch (Exception ef) {
                System.out.println("No se pudo cerrar la conexion a BD: " + ef.getMessage());
            }
        }
        return id;
    }

    public long borrar(int idEstacion) throws SQLException {

        String SQL = "DELETE FROM sensor WHERE idEstacion = ?";

        long id = 0;
        Connection conn = null;

        try {
            conn = Bd.connect();
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, idEstacion);

            int affectedRows = pstmt.executeUpdate();
            // check the affected rows
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getLong(1);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error en la eliminación: " + ex.getMessage());
        } finally {
            try {
                conn.close();
            } catch (Exception ef) {
                System.out.println("No se pudo cerrar la conexion a BD: " + ef.getMessage());
            }
        }
        return id;
    }
}
