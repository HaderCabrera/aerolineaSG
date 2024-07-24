package vuelo.infraestructure.outRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.aeroline.DatabaseConfig;

import vuelo.domain.entity.Vuelo;
import vuelo.domain.service.VueloService;

public class VueloRepository implements VueloService{

    @Override
    public List<Long> getIdAvionByIdTrayecto(Long id_trayecto) {
        String sql = "CALL obtenerIdAvionByIdTrayecto(?);";
        List<Long> lstIdsAvion = new ArrayList<>();

        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id_trayecto);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    lstIdsAvion.add(resultSet.getLong("id_avion"));
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al generar consulta.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return lstIdsAvion;
    }

    @Override
    public List<Vuelo> obtenerIdVuelos() {
        List<Vuelo> ListaVuelos_To_Tripulates = new ArrayList<>();
        String query = "SELECT id_vuelo, numero_vuelo, aeropuerto_destino, aeropuerto_origen, hora_llegada , hora_salida FROM vuelo;";
        
        try(Connection conect = DatabaseConfig.getConnection();
            PreparedStatement stm = conect.prepareStatement(query);
                ResultSet rs = stm.executeQuery()){ 

                    while (rs.next()) {
                        Vuelo vuelo = new Vuelo();
                        vuelo.setId_vuelo(rs.getLong("id_vuelo"));
                        vuelo.setNumero_vuelo(rs.getString("numero_vuelo"));
                        vuelo.setAeropuerto_destino(rs.getLong("aeropuerto_destino"));
                        vuelo.setAeropuerto_origen(rs.getLong("aeropuerto_origen"));
                        vuelo.setHora_llegada(rs.getString("hora_llegada"));
                        vuelo.setHora_salida(rs.getString("hora_salida"));
                        ListaVuelos_To_Tripulates.add(vuelo);
                    }
                    if (ListaVuelos_To_Tripulates.isEmpty()) {
                        System.out.println("No se encontraron vuelos.");
                    } else {
                        System.out.println("Se encontraron " + ListaVuelos_To_Tripulates.size() + " vuelos.");
                    }
                
            }catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al generar consulta.", "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        return ListaVuelos_To_Tripulates;
    }

}