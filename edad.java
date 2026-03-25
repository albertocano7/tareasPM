//Cano Mendoza Alberto
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

public class edad extends JFrame implements ActionListener {
    int ancho, alto;
    JTextField txtEdad;
    JButton btnAceptar;
    JPanel panelBotones;
    
    public edad(){
        ancho = 400;
        alto = 400;
        setTitle("Mi ventana");
        setSize(ancho,alto);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        txtEdad = new JTextField(5);
        btnAceptar = new JButton("Dame tu edad");
        panelBotones = new JPanel();
        
        btnAceptar.addActionListener(this);
        
        panelBotones.add(txtEdad);
        panelBotones.add(btnAceptar);
        
        add(panelBotones);
    }
    
    public static void main (String[]args){
        edad v = new edad();
        v.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int edad = Integer.parseInt(txtEdad.getText());
            
            if (edad >= 18) {
                JOptionPane.showMessageDialog(null, "Eres mayor de edad");
            } else {
                JOptionPane.showMessageDialog(null, "Eres menor de edad");
            }
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Por favor ingresa una edad válida");
        }
    }
}
