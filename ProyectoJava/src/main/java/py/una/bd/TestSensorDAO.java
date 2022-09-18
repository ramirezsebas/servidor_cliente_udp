package py.una.bd;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import py.una.entidad.Sensor;

public class TestSensorDAO {
    public static void main(String args[]) throws SQLException {

        SensorDAO sdao = new SensorDAO();

        sdao.insertar(new Sensor(1, 1, 1, "2016-01-01", "00:00:00", 1, "Asuncion"));
        sdao.insertar(new Sensor(2, 2, 2, "2016-01-01", "00:00:00", 2, "Asuncion"));
        sdao.insertar(new Sensor(3, 3, 3, "2016-01-01", "00:00:00", 3, "Asuncion"));
        sdao.insertar(new Sensor(4, 4, 4, "2016-01-01", "00:00:00", 4, "Asuncion"));

        sdao.actualizar(new Sensor(2, 2, 2, "2016-01-01", "00:00:00", 2, "Asuncion"));

        sdao.borrar(4);
}
}
