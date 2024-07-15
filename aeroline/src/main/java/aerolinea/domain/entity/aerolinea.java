package aerolinea.domain.entity;

public class aerolinea {
        private int id_aerolinea;
        private String nombre;
        

        public aerolinea() {
        }


        public aerolinea(int id_aerolinea, String nombre) {
            this.id_aerolinea = id_aerolinea;
            this.nombre = nombre;
        }


        public int getId_aerolinea() {
            return id_aerolinea;
        }


        public void setId_aerolinea(int id_aerolinea) {
            this.id_aerolinea = id_aerolinea;
        }


        public String getNombre() {
            return nombre;
        }


        public void setNombre(String nombre) {
            this.nombre = nombre;
        }
        
}
