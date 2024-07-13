package user.domain.entity;

public class User {
    private int id_usuario;
    private String nombre_usuario;
    private String pass;
    private int id_rolUsuario;

    /*Constructors */
    public User() {
    }

    public User(int id_usuario, String nombre_usuario, String pass, int id_rolUsuario) {
        this.id_usuario = id_usuario;
        this.nombre_usuario = nombre_usuario;
        this.pass = pass;
        this.id_rolUsuario = id_rolUsuario;
    }

    /*getters and setters */
    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getId_rolUsuario() {
        return id_rolUsuario;
    }

    public void setId_rolUsuario(int id_rolUsuario) {
        this.id_rolUsuario = id_rolUsuario;
    }

}
