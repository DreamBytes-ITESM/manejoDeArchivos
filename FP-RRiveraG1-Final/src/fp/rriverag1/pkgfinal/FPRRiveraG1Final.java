/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fp.rriverag1.pkgfinal;
import java.io.*;
import java.time.*;
import java.time.format.*;
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
                venta();
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
        cont();
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
         String s="",d="";
         float f;
         final JPanel panel = new JPanel();
         while(a){
            while(true){
                try {
                    c = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad del articulo."));
                    cant.add(c);
                    break;
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(panel, "Debe de ingresar un numero entero.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            while(true){
                try {    
                    while(s.equals("")){
                    s = JOptionPane.showInputDialog("Ingrese la clave del articulo.");
                    if(s.equals("")){JOptionPane.showMessageDialog(panel, "Debes ingresar una clave.", "Error", JOptionPane.ERROR_MESSAGE);}
                    }
                        while(claveExiste(clave,s)){
                            JOptionPane.showMessageDialog(panel, "Ya a usado esta clave de articulo.", "Error", JOptionPane.ERROR_MESSAGE);
                            s = JOptionPane.showInputDialog("Ingrese la clave del articulo.");
                        }
                    clave.add(s);
                    s="";
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(panel, "Muestre este error al programador.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }  
            while(true){
                try {    
                    while(d.equals("")){
                    d = JOptionPane.showInputDialog("Ingrese la descripción del articulo.");
                    if(d.equals("")){JOptionPane.showMessageDialog(panel, "Debes ingresar una descripción.", "Error", JOptionPane.ERROR_MESSAGE);}
                    }
                    desc.add(d);
                    d="";
                    break;
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(panel, "Muestre este error al programador.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            while(true){
                try {
                    f = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el precio del articulo."));
                    precio.add(f);
                    break;
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(panel, "Ingrese el precio del articulo con 2 decimales Ej: XX.XX.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            List<String> optionList = new ArrayList<>();
            optionList.add("Si");
            optionList.add("No");

            Object[] options = optionList.toArray();
            int value = JOptionPane.showOptionDialog(null,"Desea agregar mas articulos?","Menu",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,optionList.get(0));
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
            escribir.println("Cant.\t|Clave\t|Desc.\t|Precio ");
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
                 escribir.println(cantidad.get(i)+"\t|"+clave.get(i)+"\t|"+descripcion.get(i)+"\t|"+precio.get(i));
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
    public static void venta(){
        File archivo = new File ("inventario.txt");
        int n=0;
        String cadena="";
        String[] splitted;
        String[] objeto = new String[4];
        String[][] nota = new String[20][5];
        String clave=pedirClave();
        String[] splitClave;
        while(true){
            if(clave.toLowerCase().equals("q")){
                break;
            }
            try {
               FileReader lectura = new FileReader(archivo);
               BufferedReader bufferL = new BufferedReader(lectura);
               while (cadena!=null){ 
                   cadena = bufferL.readLine();
                   if(cadena!=null) { 
                       splitted = cadena.split("\\\t\\|");
                        if(splitted[1].toLowerCase().equals(clave)){
                            for(int i=0;i<splitted.length;i++){
                            objeto[i]=splitted[i];
                            }
                        cadena=null;
                        }
                   }
               }
               bufferL.close();
               lectura.close();
               if(clave.contains("*")){
                    splitClave = clave.split("\\*");
                    nota[n][0]=splitClave[0];
               } else {
                   nota[n][0]="1";
               }
               nota[n][1]=objeto[1];
               nota[n][2]=objeto[2];
               nota[n][3]=objeto[3];
               nota[n][4]=String.valueOf(Integer.parseInt(nota[n][0])-Integer.parseInt(nota[n][3]));
               n++;
                llenarNota(nota);
            }   catch (Exception e) {
                try{
                    archivo.createNewFile();
                } catch (Exception d) {
                    System.out.println(d);}
            }
        }
    }    
    public static void llenarNota(String[][] datos){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM");
        File archivo = new File("nota"+dtf.format(LocalDateTime.now())+".txt");
        try{
            PrintWriter escribir = new PrintWriter (archivo,"utf-8");
        }
        catch(Exception e){
            System.out.println("lol?");
        }
        for(int i=0;i<datos.length;i++){
            for(int j=0;j<datos[0].length;j++){
            escribir.print([i][j]);
            if(j==0)escribir.print("\t| ");//si es el primero en la linea de lista agrega una separacion en el texto
            }
            escribir.println();
        }
    }
    public static boolean claveExiste(String s){
        boolean b=false;
        int j=0;
        ArrayList<String> clavant = new ArrayList<>();
        clavant = leerClaves(clavant);
        if(clavant.get(j).contains(s)){
            b=true;}
        while(!clavant.get(j).contains(s)&j<clavant.size()){
            ++j;
            if(clavant.get(j).contains(s)){
                b=true;}
        }
        return b;
    }
    public static boolean claveExiste(ArrayList<String> clave,String s){
        boolean b=false;
        int i=0,j=0;
        ArrayList<String> clavant = new ArrayList<>();
        clavant = leerClaves(clavant);
        if(!clave.isEmpty()){
            if(clave.get(i).contains(s)){
                b=true;
                return b;}
            while(i<clave.size()){
                if(!clave.get(i).contains(s)){
                    ++i;
                    if(i<clave.size()){
                        if(clave.get(i).contains(s)){
                            b=true;
                            return b;}
                        return b;}
                } else {b=true;
                            return b;}
            }
        }
        if(!clavant.isEmpty()){
            if(clavant.get(j).contains(s)){
                b=true;
                return b;}
                while(j<clavant.size()){
                    if(!clavant.get(j).contains(s)){
                        ++j;
                        if(i<clavant.size()){
                            if(clavant.get(j).contains(s)){
                               b=true;
                               return b;}
                        return b;}
                } else {b=true;
                            return b;}
            }
        }
        return b;
    }
    public static String pedirClave(){//Pide al usuario el nombre del archivo, recibe un String y regresa un String
        String c = null;
        final JPanel panel = new JPanel();
        while(true){
                try {
                    c = JOptionPane.showInputDialog("Ingrese la clave del articulo y cantidad del articulo en el siguiente formato.(4*clave)");
                    if(c.toLowerCase().equals("q")){
                        break;
                    }
                    if(c.contains("*")){
                        try{
                            String[] d=c.split("\\*");
                                while(!claveExiste(d[1])){
                                    c = JOptionPane.showInputDialog("El articulo que ingreso no existe");
                                }
                        } catch(Exception e){}
                        break;
                    }
                    if(!c.contains("*")){
                    while(!claveExiste(c)){
                            c = JOptionPane.showInputDialog("El articulo que ingreso no existe");
                        }
                    break;
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(panel, "Debe introducir un dato, si desea parar introduzca Q", "Error", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }
        }
        return c;
    }
    public static void mensajeSalida(){     //Metodo que muestra un mensaje de salida en caso de que el usuario
        JOptionPane.showMessageDialog(null,"Gracias por usar el programa"); //ejecute alguna acción que de por terminado el programa
        System.exit(0);
    }
    public static void cont(){      //Metodo que da una salida al programa, si el usuario lo quiere, despues de ejecutar 1 o 2 en el menu principal.
        List<String> optionList = new ArrayList<>();
        optionList.add("Si");
        optionList.add("No");
        Object[] options = optionList.toArray();
        int value = JOptionPane.showOptionDialog(null,"Desea continuar con el programa?","Menu",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,optionList.get(0));
        if(value==1){
            mensajeSalida();
        } else {menu();}
    }
}