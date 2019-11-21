/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dinamico;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.StringTokenizer;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author monic
 */
public class Herramientas {
    
    public static ArrayList<Item> items;
    
    public static void leerDatos(){
        items = new ArrayList<>();
        String texto, aux;
        
        LinkedList<String> lista = new LinkedList();

           try {
               //llamamos el metodo que permite cargar la ventana
               JFileChooser file = new JFileChooser();
               file.setCurrentDirectory(new File("./"));
               file.showOpenDialog(file);
               
               //abrimos el archivo seleccionado
               File abre = file.getSelectedFile();

               //recorremos el archivo y lo leemos
               if (abre != null) {
                   FileReader archivos = new FileReader(abre);
                   BufferedReader lee = new BufferedReader(archivos);

                   while ((aux = lee.readLine()) != null) {
                       texto = aux;
                       lista.add(texto);
                   }
                   lee.close();
                   
                   //System.out.println(lista.size());

                   ArrayList<String> lista2 = new ArrayList<>();
                   //String clase = "";
                   for (int i = 0; i < lista.size(); i++) {
                       StringTokenizer st = new StringTokenizer(lista.get(i), ",");

                       while (st.hasMoreTokens()) {
                           lista2.add(st.nextToken());
                       }

                       
                       int[] vector = new int[lista2.size()];

                       for (int x = 0; x < lista2.size(); x++) {
                           vector[x] = Integer.parseInt(lista2.get(x));
                       }
                       //clase = lista2.get(lista2.size()-1);
                       
                       // a la coleccion de patrones se agrega un nuevo patron
                       Item iaux = new Item(vector[0],vector[1]);
                       items.add(iaux);
                      // patrones.add();
                       lista2.clear();

                   }

               }
           } catch (IOException ex) {
               JOptionPane.showMessageDialog(null, ex + ""
                       + "\nNo se ha encontrado el archivo",
                       "ADVERTENCIA!!!", JOptionPane.WARNING_MESSAGE);

           }



    }
    
    public static void guardar (ArrayList<Item> items){
        File f;
        FileWriter w;
        BufferedWriter bw;
        PrintWriter wr;
        JFileChooser guardar= new JFileChooser();
        guardar.setApproveButtonText("Guardar");
        guardar.showSaveDialog(null);
    
        try{
            f=new File(guardar.getSelectedFile()+".txt");
            w=new FileWriter(f);
            bw=new BufferedWriter (w);
            wr=new PrintWriter(bw);
            String peso=null;
            String valor=null;
            for (int x=0;x<items.size();x++){
                peso=String.valueOf(items.get(x).getPeso());
                valor=String.valueOf(items.get(x).getValor());
                wr.append(valor+","+peso+"\n");
            }
            wr.close();
            bw.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Ha sucedido un error"+ e);
        } 
        
    }
    
    
    
}
