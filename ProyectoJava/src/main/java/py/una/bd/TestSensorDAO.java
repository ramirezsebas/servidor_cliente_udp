package py.una.bd;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import py.una.entidad.Sensor;

public class TestSensorDAO {
    public static void main(String args[]) throws SQLException {

        SensorDAO sdao = new SensorDAO();

        sdao.insertar(new Sensor(1L, 1.0, 1.0, "2016-01-01", "00:00:00", 1.0, "Asuncion"));
        sdao.insertar(new Sensor(2L, 2.0, 2.0, "2016-01-01", "00:00:00", 2.0, "Asuncion"));
        sdao.insertar(new Sensor(3L, 3.0, 3.0, "2016-01-01", "00:00:00", 3.0, "Asuncion"));
        sdao.insertar(new Sensor(4L, 4.0, 4.0, "2016-01-01", "00:00:00", 4.0, "Asuncion"));

        sdao.actualizar(new Sensor(2L, 2.0, 2.0, "2016-01-01", "00:00:00", 2.0, "Asuncion"));

        sdao.borrar(4);
    }
}
