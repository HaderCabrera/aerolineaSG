package detallevuelo.infraestructure.outRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.aeroline.DatabaseConfig;

import detallevuelo.domain.entity.DetalleVuelo;
import detallevuelo.domain.service.DetalleVueloService;
import empleado.domain.entity.Empleado;


public class DetalleVueloRepository implements DetalleVueloService {

    @Override
    public DetalleVuelo asignarTripulacionTrayecto(Empleado empleado) {
        

        return null;
            
    }

    @Override
    public DetalleVuelo consultarTracayecto(int id_trayecto) {
        DetalleVuelo detalleVuelo = null;
        String query = "CALL abstraerTrayecto_Escalas(?);";
        try (Connection conec = DatabaseConfig.getConnection();
            PreparedStatement stm = conec.prepareStatement(query)){
                
                stm.setInt(1, id_trayecto);

                try(ResultSet resultset = stm.executeQuery()){
                    if(resultset.next()) {
                        detalleVuelo = new DetalleVuelo();

                        detalleVuelo.setId_trayecto(resultset.getInt("ID_trayecto"));
                        detalleVuelo.setDesc_trayecto(resultset.getString("desc_trayecto"));
                        detalleVuelo.setOrigen_trayecto(resultset.getString("origen_trayecto"));
                        detalleVuelo.setDestino_tracyecto(resultset.getString("destino_trayecto"));
                        detalleVuelo.setDistancia(resultset.getString("distancia"));
                        detalleVuelo.setTimpoEstimado(resultset.getString("TiempoEstimado"));

                    }else return null;
                }

            }catch (SQLException e) { 
                e.printStackTrace();
                String mensaje = "Registro Fallido!"; 
                JOptionPane.showMessageDialog(null, mensaje, "Denied", JOptionPane.WARNING_MESSAGE);
            }
        
        return detalleVuelo;
    }

    @Override
    public boolean editarTrayecto(DetalleVuelo trayecto) {
        String query = "UPDATE trayecto " +
                    "SET origen_trayecto = ?, " +
                    "    destino_trayecto = ?, " +
                    "    desc_trayecto = ?, " +
                    "    distancia = ?, " +
                    "    TiempoEstimado = ? " +
                    "WHERE id_trayecto = ?;";
                try(Connection conec = DatabaseConfig.getConnection();
                    PreparedStatement stm = conec.prepareStatement(query)){
                        stm.setString(1, trayecto.getOrigen_trayecto());
                        stm.setString(2, trayecto.getDestino_tracyecto());
                        stm.setString(3, trayecto.getDesc_trayecto());
                        stm.setString(4, trayecto.getDistancia());
                        stm.setString(5, trayecto.getTimpoEstimado());
                        stm.setInt(6, trayecto.getId_trayecto());
                        stm.executeUpdate();


                    }catch(SQLException e){
                        e.printStackTrace();
                        return false;
                    }

        return true;
    }

    @Override
    public boolean eliminarTrayecto(int id_trayecto) {
        String query = "DELETE FROM trayecto WHERE id_trayecto = ?;";
        try(Connection conec = DatabaseConfig.getConnection();
            PreparedStatement stm = conec.prepareStatement(query)){
                stm.setInt(1, id_trayecto);
                stm.executeUpdate();
                return true;

            }catch(SQLException e){
                e.printStackTrace();
            }
        return true;
        
    }

    @Override
    public List<DetalleVuelo> listarTrayectos() {
        String sql = "SELECT id_trayecto, desc_trayecto FROM trayecto";
        List<DetalleVuelo> lstDescripcionTrayectos = new ArrayList<>();
        DetalleVuelo detalleVuelo = null;

        try (Connection connection = DatabaseConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    detalleVuelo = new DetalleVuelo();
                    detalleVuelo.setDesc_trayecto(resultSet.getString("desc_trayecto"));
                    detalleVuelo.setId_trayecto(Integer.parseInt(resultSet.getString("id_trayecto")));
                    lstDescripcionTrayectos.add(detalleVuelo);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lstDescripcionTrayectos;
    }

    @Override
    public DetalleVuelo obtenerTrayectoByDescripcion(String descripcion) {
        String sql = "SELECT id_trayecto, origen_trayecto, destino_trayecto, desc_trayecto, distancia, TiempoEstimado FROM trayecto WHERE desc_trayecto = ?";
        DetalleVuelo detalleVuelo = null;
        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, descripcion);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    detalleVuelo = new DetalleVuelo();
                    detalleVuelo.setId_trayecto(Integer.parseInt(resultSet.getString("id_trayecto")));
                    detalleVuelo.setOrigen_trayecto(resultSet.getString("origen_trayecto"));
                    detalleVuelo.setDestino_tracyecto(resultSet.getString("destino_trayecto"));
                    detalleVuelo.setDesc_trayecto(resultSet.getString("desc_trayecto"));
                    detalleVuelo.setDistancia(resultSet.getString("distancia"));
                    detalleVuelo.setTimpoEstimado(resultSet.getString("TiempoEstimado"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detalleVuelo;
    }
    

}
