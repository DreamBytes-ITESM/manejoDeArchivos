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
        int a=0;
        String fn="";
        a = pedirDatos(a);
        String[][] arreglo = new String[a][2];
        arreglo = llenarArreglo(arreglo);
        fn = pedirDatos(fn);
        crearArchivo(fn,arreglo);
        leerArchivo(fn);   
    }
    public static int pedirDatos(int num){
        Scanner entrada = new Scanner(System.in);
        System.out.print("Ingrese el numero de alumnos: ");
        num=entrada.nextInt();
        return num;
    }
    public static String pedirDatos(String archivo){
        Scanner entrada = new Scanner(System.in);
        System.out.print("Ingrese el nombre del archivo donde desea guardar: ");
        archivo=entrada.nextLine();
        return archivo;
    }
    public static String[][] llenarArreglo(String[][] a){
        String n,m;
        Scanner entrada = new Scanner(System.in);
        for(int i=0;i<a.length;i++){
            for(int j=0;j<a[0].length;j++){
                if(j==0){
                    System.out.print("Ingrese el nombre del alumno: ");
                    n = entrada.nextLine();
                    for(int k=0;k<i;k++){
                        while(a[k][0].contains(n)){
                            System.out.print("Ya ingreso este nombre, ingrese uno diferente: ");
                            n = entrada.nextLine();
                        }
                    }
                a[i][j]=n;
                }
                if(j==1){
                    System.out.print("Ingrese la matricula del alumno: ");
                    m = entrada.nextLine();
                    for(int k=0;k<i;k++){
                        while(a[k][1].contains(m)){
                            System.out.print("Ya ingreso esta matricula, ingrese una diferente: ");
                            m = entrada.nextLine();
                        }
                    }
                a[i][j]=m;
                }
            }
        }
        return a;
    }
    public static void crearArchivo(String name,String[][] arr){
        //Paso 1 Instanciamos un objeto de la clase File 
        //al instanciar escribimos como parámetro 
        //el nombre del archivo para manipularlo
        File archivo = new File(name+".txt");
        
        //Paso 2. Si no existe el archivo
        if (!archivo.exists())
        {   try {  //try nos sirve para manejar excepciones. En caso de que algo
            //pueda salga mal.
            //Creamos un archivo nuevo. 
            archivo.createNewFile();
            } catch (IOException ex) {
               ex.printStackTrace();
            }
        } else {   
            System.out.println("Por favor introduzca un nombre de archivo diferente.");
            name = pedirDatos(name);
            crearArchivo(name,arr);
        }
        //Paso 3. Escritura en el archivo
        try {
            //Instanciamos un objeto de la clase PrintWriter
            //como parámetros enviamos el la instancia de File y el formato de
            //archivo de texto
            PrintWriter escribir = new PrintWriter (archivo,"utf-8");
            //Escribimos el contenido del archivo.
            escribir.println("Nombre\t| Matricula");
            for(int i=0;i<arr.length;i++){
                for(int j=0;j<arr[0].length;j++){
                escribir.print(arr[i][j]);
                if(j==0)escribir.print("\t| ");
                }
                escribir.println();
            }
            //Cerramos el archivo.
            escribir.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void leerArchivo(String name){
        // Paso 1. Instanciamos un objeto de la clase File y una variable cadena
        File archivo = new File (name+".txt");
        String cadena="";
        try {//Par el manejo de excepciones
            //Instanciamos un objeto de la clase FileReader y otro de la clase
            //BufferedReader, los cuales nos serviran para dar lectura al archivo
            //deben estar dentro de try.
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
            e.printStackTrace();
        }  
    }
}
