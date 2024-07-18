package tipoDocumento.domain.entity;

public class TipoDocumento {
    private Long id_tipo_documento;
    private String nombreDoc;
    
    public TipoDocumento() {
    }
    public TipoDocumento(Long id_tipo_documento, String nombreDoc) {
        this.id_tipo_documento = id_tipo_documento;
        this.nombreDoc = nombreDoc;
    }
    public Long getId_tipo_documento() {
        return id_tipo_documento;
    }
    public void setId_tipo_documento(Long id_tipo_documento) {
        this.id_tipo_documento = id_tipo_documento;
    }
    public String getNombreDoc() {
        return nombreDoc;
    }
    public void setNombreDoc(String nombreDoc) {
        this.nombreDoc = nombreDoc;
    }
    
}
