package modeloavion.domain.entity;

public class ModeloAvion {
    private Long id_modelo;
    private String nombreModelo;
    private Long idFabricante;
    public ModeloAvion() {
    }
    public ModeloAvion(Long id_modelo, String nombreModelo, Long idFabricante) {
        this.id_modelo = id_modelo;
        this.nombreModelo = nombreModelo;
        this.idFabricante = idFabricante;
    }
    public Long getId_modelo() {
        return id_modelo;
    }
    public void setId_modelo(Long id_modelo) {
        this.id_modelo = id_modelo;
    }
    public String getNombreModelo() {
        return nombreModelo;
    }
    public void setNombreModelo(String nombreModelo) {
        this.nombreModelo = nombreModelo;
    }
    public Long getIdFabricante() {
        return idFabricante;
    }
    public void setIdFabricante(Long idFabricante) {
        this.idFabricante = idFabricante;
    }
    
}
