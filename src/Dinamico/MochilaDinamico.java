/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dinamico;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author monica
 */


public class MochilaDinamico{
    
    private ArrayList<Item> items;
    private ArrayList<Item> itemsSolucion;
    private double[][] mBeneficios;
    private int _W;
    private int maxBenefit;

    public MochilaDinamico(ArrayList<Item> items, int _W) {
        this.items = items;
        this._W = _W;
        construirMatrizBeneficios();

    }

    public static void main (String args[]){
        int n = 20;//cantidad de items
        int p = 8;//peso maximo
        int v = 100;//valor maximo
        ArrayList<Item> items= new ArrayList<>();
        for(int i=0; i<n; i++){
             Random rndp = new Random();
             Random rndv = new Random();
             Item it= new Item(rndv.nextInt(v)+1,rndp.nextInt(p)+1);
             items.add(it);
        }
       // Herramientas.guardar(items);
        
        MochilaDinamico m = new MochilaDinamico(items,12);
       // m.construirMatrizBeneficios();
       
       System.out.println("Items Creados Aleatoriamente");
       for(int x=0;x<items.size();x++)
           System.out.println(items.get(x));
       
       System.out.println("--------------------------------");
       System.out.println("Items Respuesta");
        m.buscarSolucion();
       
        
        Herramientas.leerDatos();
        MochilaDinamico j = new MochilaDinamico(Herramientas.items,8);
        j.construirMatrizBeneficios();
        
        System.out.println("Items Lectura");
        for(int x=0;x<items.size();x++)
           System.out.println(items.get(x));
        
       System.out.println("--------------------------------");
       System.out.println("Items Respuesta");
        j.buscarSolucion();
        System.out.print(j);
    }

    private void construirMatrizBeneficios() {
        // construimos la matriz de beneficios 
        this.mBeneficios = new double[this.items.size()+1][this._W+1];
        // agregar en la primer columna puros ceros
        for (int x=0;x <= this.items.size();x++)
            this.mBeneficios[x][0] = 0;
        // agregar en la primer fila puros ceros
        
        for (int x=0;x <= this._W;x++)
            this.mBeneficios[0][x] = 0;
     
        
        
    }
    
    
    public void buscarSolucion(){
        
               // calculamos la matriz de beneficios
       for (int i=1;i <= this.items.size();i++)
           for(int w=0; w<= this._W;w++){
           // verificamos si el item puede ser parte de la solucion
               if  (this.items.get(i-1).getPeso()<= w){
               
                   if ((this.items.get(i-1).getValor()+
                           this.mBeneficios[i-1][w-this.items.get(i-1).getPeso()])
                           >this.mBeneficios[i-1][w]){
                   
                       this.mBeneficios[i][w] = this.items.get(i-1).getValor()+
                               this.mBeneficios[i-1][w-this.items.get(i-1).getPeso()];
                       
                   }else{
                   
                        this.mBeneficios[i][w] = this.mBeneficios[i-1][w];
                   
                   }
               
               }else{
               this.mBeneficios[i][w] = this.mBeneficios[i-1][w];
               }
           
           }
       this.maxBenefit = (int)this.mBeneficios[items.size()][_W];
       this.itemsSolucion = new ArrayList<>();
       // calcular los elementos utilizados para _W
       
       int i = this.items.size();
       int j = this._W;

       while (i > 0 && j > 0){
           double val = this.mBeneficios[i][j];
          if( val != this.mBeneficios[i-1][j]){
              this.itemsSolucion.add(this.items.get(i-1));
              // imprimir el articulo
              String aux =this.items.get(i-1).toString();
              System.out.println(aux);
              i--;
              j = j - this.items.get(i).getPeso();
          } else {
            i--;
          }
                   
       }      
       
       System.out.println("Matriz de Beneficios empleada en la respuesta ");
       for(int x=0;x<=this.items.size();x++){
            for(int y=0;y<=this._W;y++){
                System.out.print(this.mBeneficios[x][y]+"   ");
            }
            System.out.println();
        }
       System.out.println("************************************************* ");
           
       System.out.println();
        }

   
    
}
