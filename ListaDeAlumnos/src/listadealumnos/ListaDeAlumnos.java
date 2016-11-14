/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listadealumnos;
import java.util.*;
//Librarías necesarias para trabajar con archivos.
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.BufferedReader;
//Libraría para el manejo de excepciones.
import java.io.IOException;
/**
 *
 * @author Riverto
 */
public class ListaDeAlumnos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        menu();
    }
    public static void menu(){
        int resp=0; //Respuesta del usuario
        int a=0;//Numero de alumnos
        String fn="";//Nombre del archivo
        Scanner entrada = new Scanner(System.in);//Pedimos dato por teclado
        System.out.println("¿Que desea hacer?");
        System.out.println("1. Crear una lista de alumnos");
        System.out.println("2. Mostrar una lista de alumnos");   
        System.out.println("3. Salir");
        try {
            resp = entrada.nextInt();
        while(resp<1 || resp>3){    //Si el valor introducido por el usuario es mayor a 3 o menor a 1 se muestra mensaje de error
            System.err.println("Valor Incorrecto, debe ingresar un numero entre 1 y 3.");   //y se pide al usuario que introduzca un numero
            resp = entrada.nextInt();}                                                      //entre 1 y 3.
        } catch(Exception e){
            menu();
        } 
            
        switch(resp){
            case 1:
                a = pedirDatos(a); //Pide el tamaño del arreglo y numero de alumnos
                String[][] arreglo = new String[a][2];//Crea el arreglo
                arreglo = llenarArreglo(arreglo);//Llena el arreglo
                fn = pedirDatos(fn); //Pide el nombre del archivo
                crearArchivo(fn,arreglo); //Crea el archivo y lo llena con el arreglo
                System.out.println("El archivo fue creado exitosamente");
                cont();
                break;
            case 2:
                fn = pedirDatos(fn); //Pide el nombre del archivo
                leerArchivo(fn);   //Muestra el arreglo al usuario
                cont();
                break;
            case 3:
                mensajeSalida();
                break;
            default:
                mensajeSalida();
                break;
        }
    }
    public static int pedirDatos(int num){//Pide al usuario el número de alumnos, recibe un int y regresa un int
        Scanner entrada = new Scanner(System.in);
        System.out.print("Ingrese el numero de alumnos: ");
        num=entrada.nextInt();
        return num;
    }
    public static String pedirDatos(String archivo){//Pide al usuario el nombre del archivo, recibe un String y regresa un String
        Scanner entrada = new Scanner(System.in);
        System.out.print("Ingrese el nombre del archivo: ");
        archivo=entrada.nextLine();
        return archivo;
    }
    public static String[][] llenarArreglo(String[][] a){   //Metodo que llena un arreglo con nombres y matriculas de alumnos
        String n,m;
        Scanner entrada = new Scanner(System.in);
        for(int i=0;i<a.length;i++){
            for(int j=0;j<a[0].length;j++){//Pide los datos
                if(j==0){//Pide el nombre del alumno
                    System.out.print("Ingrese el nombre del alumno: ");
                    n = entrada.nextLine();
                    for(int k=0;k<i;k++){
                        while(a[k][0].contains(n)){//Comprueba si el nombre ya existe en la lista
                            System.err.print("Ya ingreso este nombre, ingrese uno diferente: ");
                            n = entrada.nextLine();
                        }
                    }
                a[i][j]=n;//Pasa el valor de la variable n al arreglo
                }
                if(j==1){//Pide la matricula
                    System.out.print("Ingrese la matricula del alumno: ");
                    m = entrada.nextLine();
                    for(int k=0;k<i;k++){
                        while(a[k][1].contains(m)){//Comprueba si la matricula ya existe en la lista
                            System.err.print("Ya ingreso esta matricula, ingrese una diferente: ");
                            m = entrada.nextLine();
                        }
                    }
                a[i][j]=m;//Pasa el valor de la variable m al arreglo
                }
            }
        }
        return a;//Regresa el arreglo
    }
    public static void crearArchivo(String name,String[][] arr){    //Metodo que crea el archivo
        //Paso 1 Instanciamos un objeto de la clase File 
        //al instanciar escribimos como parámetro 
        //el nombre del archivo para manipularlo
        File archivo = new File(name+".txt");
        
        //Paso 2. Si no existe el archivo
        if (!archivo.exists())
        {   try {  
            //Creamos un archivo nuevo. 
            archivo.createNewFile();
            } catch (IOException ex) {
               ex.printStackTrace();
            }
        } else {while(archivo.exists()){   
            System.err.println("El nombre de archivo introducido ya existe.");
            System.err.println("Por favor introduzca un nombre de archivo diferente.");
            name = pedirDatos(name);
            archivo = new File(name+".txt");
            }
        try {
            archivo.createNewFile();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
        }
        //Paso 3. Escritura en el archivo
        try {
            //Instanciamos un objeto de la clase PrintWriter
            //como parámetros enviamos la instancia de File y el formato de
            //archivo de texto
            PrintWriter escribir = new PrintWriter (archivo,"utf-8");
            //Escribimos el contenido del archivo.
            escribir.println("Nombre\t| Matricula");
            for(int i=0;i<arr.length;i++){
                for(int j=0;j<arr[0].length;j++){
                escribir.print(arr[i][j]);
                if(j==0)escribir.print("\t| ");//si es el primero en la linea de lista agrega una separacion en el texto
                }
                escribir.println();
            }
            //Cerramos el archivo.
            escribir.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void leerArchivo(String name){    //Metodo para leer el archivo
        // Paso 1. Instanciamos un objeto de la clase File y una variable cadena
        File archivo = new File (name+".txt");
        String cadena="";
        try {//Para el manejo de excepciones
            //Instanciamos un objeto de la clase FileReader y otro de la clase
            //BufferedReader, los cuales nos serviran para dar lectura al archivo
           FileReader lectura = new FileReader(archivo);
           BufferedReader bufferL = new BufferedReader(lectura);
           //Paso 2. Recorremos el archivo.
           while (cadena!=null){ //Mientras la cadena no sea nula
               cadena = bufferL.readLine(); //Leemos líena por línea el archivo.
               if(cadena!=null) { //Si no encontramos null dentro del archivo
                   System.out.println(cadena); //Lo muestra en pantalla.
               }
           }
           //Paso 3. Cerramos las instancias de BufferedReader y FileReader.
           bufferL.close();
           lectura.close();
        } catch (Exception e) {
            System.err.println("No existe un archivo con ese nombre.");
            menu();
        }  
    }
    public static void mensajeSalida(){     //Metodo que muestra un mensaje de salida en caso de que el usuario
        System.out.println("Gracias por usar el programa"); //ejecute alguna acción que de por terminado el programa
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
                case 1:
                    main(null);
                    break;
                default:
                    mensajeSalida();
                    break;
            }
        }
    }
