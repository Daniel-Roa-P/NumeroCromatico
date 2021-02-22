
package numerocromatico;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class NumeroCromatico extends JFrame implements ActionListener {

    public JLabel label1 = new JLabel("Grafo:");
    public JLabel label2 = new JLabel("Matriz de adyacencia:");
    
    JScrollPane scrollPane = new JScrollPane();
    JScrollPane scrollPane1 = new JScrollPane();
    
    JScrollPane scrollPane2 = new JScrollPane();
    JScrollPane scrollPane3 = new JScrollPane();
    
    public JButton botonIngresar = new JButton("Calcular numero cromatico");
    public JButton botonReiniciar = new JButton("Limpiar Grafo");
    
    ArrayList<JLabel> numeros = new ArrayList<JLabel>(); 
    JTextField[][] tabla;
    
    int cantidadNodos = 0;
    
    public static void main(String[] args) {

        NumeroCromatico nc = new NumeroCromatico(); 
        nc.setBounds(0, 0, 820, 520);
        nc.setTitle("Numero Cromatico");
        nc.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        nc.setVisible(true);
        
    }

    NumeroCromatico(){
        
        Container c = getContentPane();
        c.setLayout(null);
        this.getContentPane().setBackground(Color.LIGHT_GRAY);
        
        c.add(scrollPane1);
        c.add(scrollPane3);
        
        c.add(botonIngresar);
        c.add(botonReiniciar);
        
        c.add(label1);
        c.add(label2);
        
        botonIngresar.addActionListener(this);
        botonIngresar.setBackground(Color.CYAN);
        botonIngresar.setBounds(50, 400, 320, 40);
        botonReiniciar.addActionListener(this);
        botonReiniciar.setBackground(Color.YELLOW);
        botonReiniciar.setBounds(420, 400, 320, 40);
        
        label1.setBounds(50, 30, 200, 20);
        label2.setBounds(420, 30, 500, 20);
      
//        scrollPane.setBounds(400, 40, 2500, 2500);
//        scrollPane.setPreferredSize(new Dimension(2500, 2500));  
//        scrollPane.setBackground(Color.WHITE);
        
        scrollPane1.setBounds(50, 50, 320, 320);
        scrollPane1.setPreferredSize(new Dimension(450, 450)); 
        scrollPane1.setBackground(Color.WHITE);
        
        scrollPane2.setBounds(50, 50, 320, 320);
        scrollPane2.setPreferredSize(new Dimension(320, 320));  
        scrollPane2.setBackground(Color.WHITE);
        
        scrollPane3.setBounds(420, 50, 320, 320);
        scrollPane3.setPreferredSize(new Dimension(320, 320)); 
        scrollPane3.setBackground(Color.WHITE);        
        
        scrollPane1.addMouseListener(new MouseAdapter() {
            
            @Override 
            public void mousePressed(MouseEvent e) {
                
                if(cantidadNodos<9){
                
                    scrollPane1.removeAll();

                    cantidadNodos++;

                    JLabel tempLabel = new JLabel(Integer.toString(cantidadNodos));
         
                    tempLabel.setBounds(e.getX(), e.getY(), 10, 15);
                    tempLabel.setForeground(Color.BLACK);
                    tempLabel.setOpaque(true);
                    tempLabel.setBackground(Color.lightGray);
                    
                    numeros.add(tempLabel);

                    for(int i = 0; i < numeros.size(); i++){

                        scrollPane1.add(numeros.get((numeros.size() - i) - 1));

                    }

                    scrollPane1.repaint();

                    dibujarTabla();

                }
                
            }
            
        });
        
    }
    
    public void dibujarTabla(){
        
        scrollPane2.removeAll();
        
        tabla = new JTextField[cantidadNodos][cantidadNodos];
        
        for(int i = 0; i < cantidadNodos; i++){
            
            for(int j = 0; j < cantidadNodos; j++){
                
                if(i<j){
                
                    tabla[i][j] = new JTextField("X");
                    tabla[i][j].setEditable(false);
                    
                } else if(j == i){
                    
                    tabla[i][j] = new JTextField("0");
                    tabla[i][j].setEditable(false);
                    
                } else {
                    
                    tabla[i][j] = new JTextField("0");
                    
                } 
                
                tabla[i][j].setBounds(30 + (i*30), 25 + (j*30), 20, 20);
                scrollPane2.add(tabla[i][j]);
                
            }
            
        }
        
        for(int k = 0; k < cantidadNodos; k++){
                    
            JLabel tempLabel = new JLabel(Integer.toString(k+1));
            tempLabel.setBounds( 5, 25 + (k*30), 50, 20 );
            
            JLabel tempLabel2 = new JLabel(Integer.toString(k+1));
            tempLabel2.setBounds( 35 + (k*30), 5, 50, 10 );
            
            scrollPane2.add(tempLabel);
            scrollPane2.add(tempLabel2);
        }
        
        scrollPane2.repaint();
        scrollPane3.setViewportView(scrollPane2);
        
    }
    
    public void dibujarGrafo(){
        
        JLabel padre, hijo;
        String cadena = ""; 
        
        for(int i = 0 ; i < numeros.size(); i++){
            
            padre = numeros.get(i);
            
            for(int j = 0; j< numeros.size(); j++){
            
                if(tabla[j][i].getText().equals("1")){
                
                    hijo = numeros.get(j);
                    
                    int coorY = 0;
                    int coorX = 0;
                    
                    if(padre.getY() >= hijo.getY() && padre.getX() <= hijo.getX()){

                        cadena = cadena + "ArribaAdelante.png";
                        coorY = padre.getY() - Math.abs(padre.getY() - hijo.getY());
                        coorX = padre.getX();
                        
                    } else if ((padre.getY() <= hijo.getY() && padre.getX() <= hijo.getX())){

                        cadena = cadena + "AbajoAdelante.png";
                        coorY = padre.getY();
                        coorX = padre.getX();
                        
                    } else if ((padre.getY() <= hijo.getY() && padre.getX() >= hijo.getX())){

                        cadena = cadena + "ArribaAtras.png";
                        coorY = hijo.getY() - Math.abs(padre.getY() - hijo.getY());
                        coorX = hijo.getX();
                        
                    } else if ((padre.getY() >= hijo.getY() && padre.getX() >= hijo.getX())){

                        cadena = cadena + "AbajoAtras.png";
                        coorY = hijo.getY();
                        coorX = hijo.getX();
                            
                    }
                    
                    JLabel img = new JLabel();

                    ImageIcon imgIcon = new ImageIcon(getClass().getResource(cadena));

                    Image imgEscalada = imgIcon.getImage().getScaledInstance(Math.abs(padre.getX() - hijo.getX()), Math.abs(padre.getY() - hijo.getY()), Image.SCALE_SMOOTH);
                    Icon iconoEscalado = new ImageIcon(imgEscalada);

                    img.setIcon(iconoEscalado);
                    img.setBounds(coorX, coorY, Math.abs(padre.getX() - hijo.getX()), Math.abs(padre.getY() - hijo.getY()));
                    
                    cadena = "";
                    
                    scrollPane1.add(img);
                    
                }

            }
            
        }
        
        scrollPane1.repaint();
                
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    
        if(e.getSource() == botonIngresar){
            
            dibujarGrafo();
            
        }
        
    }
    
}
