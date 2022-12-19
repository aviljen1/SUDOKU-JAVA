
package sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class SUDOKU extends JFrame implements ActionListener{
    private JMenuBar menubar;
    private JMenu menu1;
    private JMenuItem menuitem1,menuitem2,menuitem3,menuitem4;
    private static JFrame f=new JFrame("Sudoku");
    private static JPanel p=new JPanel();
    private static JTextField[][]M=new JTextField[9][9];
    private static int  nivelDificultad;
    TABLEROS  T=new TABLEROS();
    
    SUDOKU(){
        menuBar();
        panel();
    }
  
    public void colorea(JTextField M[][]){
        int x,y;
        for(x=0;x<9;x++){
            for(y=0;y<9;y++){
                M[x][y].setFont(new java.awt.Font("Tahoma", 0, 19));
                switch(x){
                    case 0:case 1: case 2: case 6:case 7:case 8:{
                        switch(y){
                            case 0:case 1:case 2: case 6:case 7:case 8:
                                M[x][y].setBackground(new java.awt.Color(102, 204, 255));       
                                break;
                        }
                    break;
                    }
                    case 3:case 4:case 5:{
                        switch(y){
                            case 3:case 4:case 5:
                                M[x][y].setBackground(new java.awt.Color(102, 204, 255));
                                break;
                        }
                    break;
                        
                    }
                        
                }
                
            }
        }
        
    }
    
    public void bloquear(JTextField K[][]){
        int i,j;
        for(i=0;i<9;i++){
            for(j=0;j<9;j++){
                if(K[i][j].getText().equals("0")){
                    
                    K[i][j].setEditable(true);
                }else{
                    K[i][j].setEditable(false);
                }
            }
        }   
    } 
     
    public void panel(){
        f.setSize(600,600);
        p.setVisible(false);
        p.setLayout(new GridLayout(9,9));
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                M[i][j]=new JTextField();
                M[i][j].setForeground(Color.black);
                M[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                eventosDelTeclado(M,i,j);
                p.add(M[i][j]);
               
            }
        }
       
        f.setContentPane(p);
        p.setVisible(false);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//ESTO HACE QUE LA EJECUCION TERMINE AL CIERRAR LA VENTANA
        f.setResizable(false);//para que no se pueda agrandar
    }
     
    public void menuBar(){
        setLayout(null);
	menubar=new JMenuBar();
	setJMenuBar(menubar);
	//pestañas desplegables:
	menu1=new JMenu("Opciones");
	menuitem1=new JMenuItem("EASY");
	//evento:
	menuitem1.addActionListener(this);
	menu1.add(menuitem1);
	menuitem2=new JMenuItem("MEDIO");
	//evento:
	menuitem2.addActionListener(this);
	menu1.add(menuitem2);
	menuitem3=new JMenuItem("DIFICIL");
	//evento:
	menuitem3.addActionListener(this);
	menu1.add(menuitem3);
        menuitem4=new JMenuItem("SOLUCIONES");
	//evento:
	menuitem4.addActionListener(this);
	menu1.add(menuitem4);
        
        menubar.add(menu1);
        
       f.setJMenuBar(menubar);    
     }
    
    @Override
    public void actionPerformed(ActionEvent e){
	//Container fondo=this.getContentPane();
	if(e.getSource()==menuitem1){
                p.setVisible(true);
		T.iniciarValoresEASY(M); 
                bloquear(M);
                colorea(M);
                nivelDificultad=1;   
	}      
	if(e.getSource()==menuitem2){
                p.setVisible(true);
                T.iniciarValoresMEDIO(M);
                bloquear(M);
                colorea(M);
                nivelDificultad=2;   
	}
	if(e.getSource()==menuitem3){
		p.setVisible(true);
                T.iniciarValoresDIFICIL(M);
                bloquear(M);
                colorea(M);
                nivelDificultad=3;   
                
	}
        if(e.getSource()==menuitem4){
            try {
                T.escribirArchivo();
            } catch (IOException ex) {
                
            }
            T.abrirarchivo("C:\\Users\\Jen\\Desktop\\SOLUCIONES.txt");      
	}
    }
    
    public boolean existe_fila(JTextField   K[][],int fila, int col) {
        int numero=Integer.parseInt(K[fila][col].getText());
        boolean resultado = false;
        for (int c = 0; c < K.length; c++) {
            if (numero == 0) {
            }else{
                if(c!=col){//esto es para que no se compare consigo misma
                    try{     
                        if (Integer.parseInt(K[fila][c].getText()) == numero) {
                            resultado = true;
                            break;
                        }
                    }catch(NumberFormatException ex){}
                }      
            }
        }
        
        return resultado;
    }
    
    public boolean existe_columna(JTextField   K[][],int fila, int col) {
        int numero=Integer.parseInt(K[fila][col].getText());
        boolean resultado = false;
        
        for (int f = 0; f <K.length; f++) {
            if (numero==0){
            }else{
                if(f!=fila){
                    try{
                        if (Integer.parseInt(K[f][col].getText()) == numero){
                            resultado = true;
                            break;
                            }
                    }catch(NumberFormatException ex){}
                }
            }
        }
        return resultado;
    }
    
    public boolean existe_caja(JTextField   K[][],int fila, int columna) {
        int valor=Integer.parseInt(K[fila][columna].getText());
        //VARIABLES.
        int minimo_fila;
        int maximo_fila;
        int minimo_columna;
        int maximo_columna;
        boolean resultado = false;

        //DETERMINAMOS LAS FILAS DE LA CAJA.
        if (fila >= 0 && fila < 3) {
            minimo_fila = 0;
            maximo_fila = 2;
        } else if (fila >= 3 && fila < 6) {
            minimo_fila = 3;
            maximo_fila = 5;
        } else {
            minimo_fila = 6;
            maximo_fila = 8;
        }

        //DETERMINAMOS LAS COLUMNAS DE LA CAJA.
        if (columna >= 0 && columna < 3) {
            minimo_columna = 0;
            maximo_columna = 2;
        } else if (columna >= 3 && columna < 6) {
            minimo_columna = 3;
            maximo_columna = 5;
        } else {
            minimo_columna = 6;
            maximo_columna = 8;
        }

        //RECORREMOS EL RANGO DE LA CAJA, Y BUSCAMOS EL VALOR.
        for (int f = minimo_fila; f <= maximo_fila; f++) {
            for (int c = minimo_columna; c <= maximo_columna; c++) {
                if (valor == 0) {
                }else {
                    if(f!=fila || c!=columna){
                        try{
                            if(Integer.parseInt(K[f][c].getText()) == valor) {
                                resultado = true;
                                break;
                            }
                        }catch(NumberFormatException ex){}
                    }
                }
            }
        }
        return resultado;
    }
    
    public  boolean verif(JTextField L[][] ){
	for(int i=0;i<9;i++){
		for(int j=0;j<9;j++){
                    try{
                        if(nivelDificultad==1){
                            if(T.SOLEASY[i][j]!=(Integer.parseInt(L[i][j].getText()))){
                                return false;
                            }
                        }  
                        if(nivelDificultad==2){
                            if(T.SOLMEDIO[i][j]!=(Integer.parseInt(L[i][j].getText()))){
                                return false;
                            }
                        }
                        if(nivelDificultad==3){
                            if(T.SOLDIFICIL[i][j]!=(Integer.parseInt(L[i][j].getText()))){
                                return false;
                            }
                        }  
                    }catch(NumberFormatException ex){}
		}
                
	}
	return true;
    }

    public void eventosDelTeclado(JTextField K[][],int f,int c){
	KeyListener eventoTeclado=new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyPressed(KeyEvent e) {
               // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyReleased(KeyEvent e) { 
                int v=0;int v1=0;
                JTextField tf=(JTextField) e.getSource();
                tf.setFont(new Font("Arial Black",Font.BOLD,28));
                tf.setForeground(Color.black);
                String text=tf.getText();

                if((!text.equals("1"))&&(!text.equals("2"))&&(!text.equals("3"))&&(!text.equals("4"))&&(!text.equals("5"))&&(!text.equals("6"))&&(!text.equals("7"))&&(!text.equals("8"))&&(!text.equals("9")))
                    tf.setText("");
                
                for(int i=0;i<M.length;i++) 
                    for(int j=0;j<M[i].length;j++){
                        if(M[i][j].getText().equalsIgnoreCase("")){
                            tf.setText("0");
                        }
                        if(existe_fila(M,i,j)){
                           tf.setForeground(Color.red);
                        } 
                         if(existe_columna(M,i,j)){
                             tf.setForeground(Color.red);
                         }
                         if(existe_caja(M,i,j)){
                             tf.setForeground(Color.red);
                        }
                    }
                 
                    if(verif(M)){
                        JOptionPane.showMessageDialog(null,"Buen trabajo!! GANASTE");  
                    }
                
            
            }
             
        };

        K[f][c].addKeyListener(eventoTeclado);
    }
}