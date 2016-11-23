/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fp.rriverag1.pkgfinal;
import java.io.*;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author Riverto
 */
public class FPRRiveraG1Final {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        menu();
    }
    public static void menu(){
        List<String> optionList = new ArrayList<String>();
        optionList.add("Inventario");
        optionList.add("Venta");
        optionList.add("Cierre del dia");
        optionList.add("Salir");

        Object[] options = optionList.toArray();
        int value = JOptionPane.showOptionDialog(null,"Selecciona la opcion que deseas","Menu",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,optionList.get(0));
        
        switch(value){
            case 0:
                llenarInventario();
                break;
            case 1:
                System.out.println("2");
                break;
            case 2:
                System.out.println("3");
                break;
            case 3:
                mensajeSalida();
                break;
            default:
                System.out.println("ka!?");
                break;
        }
    }
    public static void llenarInventario(){
         ArrayList<Integer> cant = new ArrayList<>();
         ArrayList<String> clave = new ArrayList<>();
         ArrayList<String> desc = new ArrayList<>();
         ArrayList<Float> precio = new ArrayList<>();
         ArrayList<String> clavant = new ArrayList<>();
         clavant = leerClaves(clavant);
         //Scanner teclado = new Scanner(System.in);
         boolean a=true;
         int c;
         String s;
         float f;
         final JPanel panel = new JPanel();
         while(a){
            while(true){
                //System.out.println("Ingrese la cantidad del articulo.");
                try {
                    c = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad del articulo."));
                    //c = teclado.nextInt();
                    cant.add(c);
                    break;
                } catch (Exception e) {
                    //System.err.println("Debe de ingresar un numero entero.");
                    //teclado.next();
                    JOptionPane.showMessageDialog(panel, "Debe de ingresar un numero entero.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            while(true){
                //System.out.println("Ingrese la clave del articulo.");
                try {    
                    s = JOptionPane.showInputDialog("Ingrese la clave del articulo.");
                    //s = teclado.nextLine();
                    for(int i=0;i<clave.size();i++){
                        while(clave.get(i).contains(s)){
                            JOptionPane.showMessageDialog(panel, "Ya a usado esta clave de articulo.", "Error", JOptionPane.ERROR_MESSAGE);
                            s = JOptionPane.showInputDialog("Ingrese la clave del articulo.");
                        }
                    }
                    for(int i=0;i<clavant.size();i++){
                        while(clavant.get(i).contains(s)){
                            JOptionPane.showMessageDialog(panel, "Ya a usado esta clave de articulo.", "Error", JOptionPane.ERROR_MESSAGE);
                            s = JOptionPane.showInputDialog("Ingrese la clave del articulo.");
                        }
                    }
                    clave.add(s);
                    break;
                } catch (Exception e) {
                    //System.err.println("Muestre este error al programador.");
                    //teclado.next();
                    JOptionPane.showMessageDialog(panel, "Muestre este error al programador.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }  
            while(true){
                //System.out.println("Ingrese la descripción del articulo.");
                try {    
                    s = JOptionPane.showInputDialog("Ingrese la descripción del articulo.");
                    //s = teclado.nextLine();
                    desc.add(s);
                    break;
                } catch (Exception e) {
                    //System.err.println("Muestre este error al programador.");
                    //teclado.next();
                    JOptionPane.showMessageDialog(panel, "Muestre este error al programador.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            while(true){
                try {
                    //System.out.println("Ingrese la precio del articulo.");
                    //f = teclado.nextFloat();
                    f = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el precio del articulo."));
                    precio.add(f);
                    break;
                } catch (Exception e) {
                    //System.err.println("Ingrese el precio del articulo con 2 decimales Ej: XX.XX.");
                    //teclado.next();
                    JOptionPane.showMessageDialog(panel, "Ingrese el precio del articulo con 2 decimales Ej: XX.XX.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            List<String> optionList = new ArrayList<>();
            optionList.add("Si");
            optionList.add("No");

            Object[] options = optionList.toArray();
            int value = JOptionPane.showOptionDialog(null,"Desea continuar con el programa?","Menu",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,optionList.get(0));
            if(value==1)a=false;
            }
         crearArchivo(cant, clave, desc, precio);
         registroClaves(clave);
    }
    public static void registroClaves(ArrayList<String> clave){
        File archivo = new File("claves.txt");
                if (!archivo.exists())
                {   try {  
                    archivo.createNewFile();
                    } catch (IOException ex) {
                       ex.printStackTrace();
                    }
                }
                try {
                    FileWriter fw = new FileWriter(archivo,true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter escribir = new PrintWriter (bw);
                    for(int i=0;i<clave.size();i++){
                         escribir.println(clave.get(i));
                    }
                    escribir.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
    }
    public static void crearArchivo(ArrayList<Integer> cantidad,ArrayList<String> clave,ArrayList<String> descripcion,ArrayList<Float> precio){    //Metodo que crea el archivo
        File archivo = new File("inventario.txt");
        if (!archivo.exists())
        {   try {  
            archivo.createNewFile();
            PrintWriter escribir = new PrintWriter (archivo,"utf-8");
            escribir.println("Cant.\t| Clave\t|Desc.\t|Precio ");
            escribir.close();
            } catch (IOException ex) {
               ex.printStackTrace();
            }
        }
        try {
            FileWriter fw = new FileWriter(archivo,true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter escribir = new PrintWriter (bw);
            for(int i=0;i<cantidad.size();i++){
                 escribir.println(cantidad.get(i)+"\t| "+clave.get(i)+"\t| "+descripcion.get(i)+"\t| "+precio.get(i));
            }
            escribir.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static ArrayList<String> leerClaves(ArrayList<String> clave){
        File archivo = new File ("claves.txt");
        String cadena="";
        while(true){
            try {
               FileReader lectura = new FileReader(archivo);
               BufferedReader bufferL = new BufferedReader(lectura);
               while (cadena!=null){ 
                   cadena = bufferL.readLine();
                   if(cadena!=null) { 
                       clave.add(cadena);
                   }
               }
               bufferL.close();
               lectura.close();
               break;
            } catch (Exception e) {
                 try{
                     archivo.createNewFile();
                 } catch (Exception d) {
                     System.out.println(d);}
            }  
        }
        return clave;
    }
    public static void mensajeSalida(){     //Metodo que muestra un mensaje de salida en caso de que el usuario
        JOptionPane.showMessageDialog(null,"Gracias por usar el programa"); //ejecute alguna acción que de por terminado el programa
        System.exit(0);
    }
    public static void cont(){      //Metodo que da una salida al programa, si el usuario lo quiere, despues de ejecutar 1 o 2 en el menu principal.
        int resp;
        Scanner entrada = new Scanner(System.in);
        System.out.println("Desea continuar?");
        System.out.println("1. Si");
        System.out.println("0. No");        
        resp = entrada.nextInt();
        while (resp<0 || resp>1){
            System.err.print("Valor Incorrecto, Intente de nuevo: ");
            resp = entrada.nextInt();
        }
            switch(resp){
                case 0:
                    main(null);
                    break;
                default:
                    mensajeSalida();
                    break;
            }
        }
}