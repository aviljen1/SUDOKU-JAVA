package sudoku;
   
import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JTextField;


public class TABLEROS {
    public int EASY[][]={
        {0,0,7,0,0,3,0,0,0},
        {0,5,6,0,0,0,0,0,0},
        {0,0,0,9,0,0,6,0,4},
        {0,0,0,2,0,4,0,0,0},
        {6,0,9,0,0,5,0,8,3},
        {3,4,0,0,0,0,2,7,0},
        {0,3,0,4,7,0,8,6,9},
        {0,0,2,0,8,9,3,4,0},
        {9,8,0,0,0,6,0,1,0}};
     
    public int SOLEASY[][]={
        {4,9,7,5,6,3,1,2,8},
        {2,5,6,8,4,1,9,3,7},
        {8,1,3,9,2,7,6,5,4},
        {1,7,8,2,3,4,5,9,6},
        {6,2,9,7,1,5,4,8,3},
        {3,4,5,6,9,8,2,7,1},
        {5,3,1,4,7,2,8,6,9},
        {7,6,2,1,8,9,3,4,5},
        {9,8,4,3,5,6,7,1,2}};
     
    public int MEDIO[][]={
        {0,0,0,0,0,8,0,0,0},
        {0,0,8,0,6,0,9,7,3},
        {0,6,0,0,0,9,8,5,0},
        {0,0,0,0,0,5,7,0,1},
        {0,0,4,0,0,2,0,3,0},
        {0,7,9,0,0,3,0,0,0},
        {0,0,6,8,0,0,0,0,0},
        {3,9,0,2,0,7,0,1,0},
        {4,8,0,9,0,0,0,2,7}
        };
    
      public int SOLMEDIO[][]={
        {9,4,3,5,7,8,1,6,2},
        {2,5,8,1,6,4,9,7,3},
        {1,6,7,3,2,9,8,5,4},
        {8,3,2,6,9,5,7,4,1},
        {6,1,4,7,8,2,5,3,9},
        {5,7,9,4,1,3,2,8,6},
        {7,2,6,8,3,1,4,9,5},
        {3,9,5,2,4,7,6,1,8},
        {4,8,1,9,5,6,3,2,7}
        };
    
    public int DIFICIL[][]= {
        {0,6,0,2,4,0,0,0,0},
        {0,0,0,6,3,0,0,7,0},
        {8,3,0,0,0,0,2,0,0},
        {9,0,0,0,0,0,3,5,0},
        {0,0,0,0,7,0,0,6,2},
        {0,0,8,0,6,0,0,0,4},
        {0,0,3,0,0,0,5,0,7},
        {5,8,0,7,9,0,6,2,0},
        {0,2,1,0,5,0,0,0,0}};
    
    public int SOLDIFICIL[][]= {
        {1,6,9,2,4,7,8,3,5},
        {4,5,2,6,3,8,9,7,1},
        {8,3,7,9,1,5,2,4,6},
        {9,7,6,1,2,4,3,5,8},
        {3,4,5,8,7,9,1,6,2},
        {2,1,8,5,6,3,7,9,4},
        {6,9,3,4,8,2,5,1,7},
        {5,8,4,7,9,1,6,2,3},
        {7,2,1,3,5,6,4,8,9}};
    
    
    //para escribir un archivo
    String ruta = "C:\\Users\\Jen\\Desktop\\SOLUCIONES.txt";
    File archivo = new File(ruta);
    BufferedWriter bw;

    //Escribir archivo:
    public void escribirArchivo() throws IOException {
        if(archivo.exists()) {
            bw = new BufferedWriter(new FileWriter(archivo));
            int i,j;
            bw.write("SOLUCION EASY:\n");
            for(i=0;i<9;i++){
                for(j=0;j<9;j++){
                    bw.write ("pos["+i+"]["+j+"]="+String.valueOf(SOLEASY[i][j])+"\n");  
                }
            } 
            bw.write ("\n");
            bw.write("SOLUCION MEDIO\n");
            for(i=0;i<9;i++){
                for(j=0;j<9;j++){
                    bw.write ("pos["+i+"]["+j+"]="+String.valueOf(SOLMEDIO[i][j])+"\n");  
                }
            }
            bw.write ("\n");
            bw.write("SOLUCION DIFICIL:\n");
            for(i=0;i<9;i++){
                for(j=0;j<9;j++){
                    bw.write ("pos["+i+"]["+j+"]="+String.valueOf(SOLDIFICIL[i][j])+"\n");  
                }
            }   
        bw.close();
        }
    }
   
    //metodo para abrir archivo 
    public void abrirarchivo(String archivo){
        try {
            File objetofile = new File (archivo);
            Desktop.getDesktop().open(objetofile);

        }catch(IOException ex) {
            System.out.println(ex);
        }
    }                         

    public void iniciarValoresEASY(JTextField K[][]){
        int i,j;
        for(i=0;i<9;i++){
            for(j=0;j<9;j++){
                K[i][j].setText(String.valueOf(EASY[i][j]));  
            }
        } 
    }
    
    public void iniciarValoresMEDIO(JTextField K[][]){
        int i,j;
        for(i=0;i<9;i++){
            for(j=0;j<9;j++){
                K[i][j].setText(String.valueOf(MEDIO[i][j]));
            }
        }  
    }
    
    public void iniciarValoresDIFICIL(JTextField K[][]){
        int i,j;
        for(i=0;i<9;i++){
            for(j=0;j<9;j++){
                K[i][j].setText(String.valueOf(DIFICIL[i][j]));   
            }
        }   
    } 
}

 


