//Cano Mendoza Alberto

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class gato extends JFrame implements ActionListener{
    JButton botones [] = new JButton[9];
    boolean turnoX;
    Font fuente  = new Font("Arial", 1, 50);        
   
    public gato(){
        setSize(400,400);
        setTitle("Juego Gato");
        setDefaultCloseOperation(3);
        setLayout(new GridLayout(3, 3));
       
        for(int i=0; i<botones.length;i++){
            botones[i] = new JButton("");
            botones[i].setFont(fuente);
            botones[i].addActionListener(this);
            add(botones[i]);
        }
    }

    public static void main(String[] args) {
        gato gt = new gato();
        gt.setVisible(true);
    }
   
    public void actionPerformed(ActionEvent e){  
        String letra;
        if(turnoX){
             letra = "X";
             turnoX=false;
        }else{
            letra = "O";
            turnoX=true;
        }

        for(int i=0;i<botones.length;i++){
            if((e.getSource()==botones[i])){            
                botones[i].setText(letra);
                botones[i].setEnabled(false);
            }  
        }

        verificarGanador(); // 👈 Se llama después de cada jugada
    }

    // 🔹 Método para verificar ganador
    public void verificarGanador(){
        String[][] combinaciones = {
            {botones[0].getText(), botones[1].getText(), botones[2].getText()},
            {botones[3].getText(), botones[4].getText(), botones[5].getText()},
            {botones[6].getText(), botones[7].getText(), botones[8].getText()},
            {botones[0].getText(), botones[3].getText(), botones[6].getText()},
            {botones[1].getText(), botones[4].getText(), botones[7].getText()},
            {botones[2].getText(), botones[5].getText(), botones[8].getText()},
            {botones[0].getText(), botones[4].getText(), botones[8].getText()},
            {botones[2].getText(), botones[4].getText(), botones[6].getText()}
        };

        for(int i=0; i<combinaciones.length; i++){
            if(!combinaciones[i][0].equals("") &&
               combinaciones[i][0].equals(combinaciones[i][1]) &&
               combinaciones[i][1].equals(combinaciones[i][2])){
                
                JOptionPane.showMessageDialog(this, "Ganó: " + combinaciones[i][0]);
                desactivarBotones();
                return;
            }
        }
    }

    // 🔹 Desactiva todos los botones cuando hay ganador
    public void desactivarBotones(){
        for(int i=0; i<botones.length; i++){
            botones[i].setEnabled(false);
        }
    }
}