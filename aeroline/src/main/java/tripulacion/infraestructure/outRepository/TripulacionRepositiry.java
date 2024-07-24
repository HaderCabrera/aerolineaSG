package tripulacion.infraestructure.outRepository;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.aeroline.DatabaseConfig;

import empleado.domain.entity.Empleado;
import tripulacion.domain.entity.Tripulacion;
import tripulacion.domain.service.TripulacionService;

public class TripulacionRepositiry implements  TripulacionService{

    @Override
    public Tripulacion asignarEmpleadoToTripulacion(Tripulacion tripulacion) {
        String query = "INSERT INTO tripulacion (id_vuelo, id_empleado) VALUES (?, ?)";

        try(Connection conec = DatabaseConfig.getConnection();
            PreparedStatement stm = conec.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)){

                stm.setInt(1, tripulacion.getId_vuelo());
                stm.setString(2, tripulacion.getId_empelado());

                int rowsAffected = stm.executeUpdate();

                if (rowsAffected > 0) {
                    try (ResultSet generatedKeys = stm.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            int id_tripulacion = generatedKeys.getInt(1);
                            tripulacion.setId_tripulacion(id_tripulacion); // Asignar la clave generada al objeto tripulacion
                            return tripulacion;
                        }
                    }
                }
                
            }catch(SQLException e){
                e.printStackTrace();

                String mensaje = "Registro Fallido!"; 
                JOptionPane.showMessageDialog(null, mensaje, "Denied", JOptionPane.WARNING_MESSAGE);
                return null;
            }
            return null;
        }

    @Override
    public List<Empleado> obtenerTripulacionPorVuelo(String Codec_vuelo) {
       
         List<Empleado> lstTripulantes = new ArrayList<>();
        
        String query = "CALL obtenerEmpleadosPor_Codec_vuelo(?);";
        try(Connection conec = DatabaseConfig.getConnection();
            PreparedStatement stm = conec.prepareStatement(query)){

                stm.setString(1, Codec_vuelo);
                try(ResultSet  rs = stm.executeQuery()){
                    while (rs.next()){
                        Empleado TripulantesAsignados = new Empleado();
                        TripulantesAsignados.setId_empleado(rs.getString ("CODEC_T"));
                        TripulantesAsignados.setNombre1(rs.getString("Tripulante"));
                        TripulantesAsignados.setId_empleado(rs.getString("Estado_Empleado"));
                        lstTripulantes.add(TripulantesAsignados);
                    }   
                }
            }catch(SQLException e){
                e.printStackTrace();

                String mensaje = "Registro Fallido!"; 
                JOptionPane.showMessageDialog(null, mensaje, "Denied", JOptionPane.WARNING_MESSAGE);
                return null;
            }
            
        return lstTripulantes;
    }

    @Override
    public List<Empleado> ObtenerTripulantesDisponibles(String dispo) {
        String query = "CALL Listar_Empleados_Activos(?);";
        List<Empleado> lstDispo = new ArrayList<>();
        Empleado estado =null;

        try(Connection conec = DatabaseConfig.getConnection();
            PreparedStatement stm = conec.prepareStatement(query)){
                
                stm.setString(1, dispo );
                try(ResultSet rs = stm.executeQuery()){
                    while (rs.next()){
                        estado = new Empleado();

                        estado.setId_empleado(rs.getString("CODEC_T"));
                        estado.setNombre1(rs.getString("Tripulante"));
                        estado.setId_estadoEmpleado(rs.getString("Estado_Empleado"));;
                        lstDispo.add(estado);
                    }
                }
            } catch(SQLException e){
                e.printStackTrace();

                String mensaje = "Registro Fallido!"; 
                JOptionPane.showMessageDialog(null, mensaje, "Denied", JOptionPane.WARNING_MESSAGE);
                return null;
            }
        return lstDispo;
    }

    
    }   

   


