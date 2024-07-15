package vuelo.infraestructure.inController;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import vuelo.application.VueloUseCase;

public class Vuelocontroller {
    private VueloUseCase vueloUseCase;

    public Vuelocontroller(VueloUseCase vueloUseCase) {
        this.vueloUseCase = vueloUseCase;
    }

    public void start() {
        boolean bandera0 = true;
        while (bandera0) {
            // Mostrar el menú principal
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.add(new JButton("Button 1"));
            panel.add(new JButton("Button 2"));
            panel.add(new JButton("Button 3"));
            JFrame frame = new JFrame("Ejemplo de BoxLayout");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(panel);
            frame.pack(); // Ajustar el tamaño del marco para que se adapte al contenido
            frame.setVisible(true);

        }

    }

}
