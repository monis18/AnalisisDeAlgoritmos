/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dinamico;

import java.util.ArrayList;

/**
 *
 * @author Valdo
 */
public class TSP {
    private int ciudades;
    private int caminos[][];
    private int ciudadInicio;
    private Map solucion;

    public TSP(int[][] caminos, int ciudadInicio) {
        this.ciudades = caminos.length;
        this.caminos = caminos;
        this.ciudadInicio = ciudadInicio;
        this.solucion = new Map();
    }
    
    public void buscarSolucion(){
        ArrayList<Map> listaCaminos = new ArrayList<>();
        
        //Repetiremos el ciclo hasta que el camino tenga todas las ciudades posibles
        do{
            //Si aun no hay elementos en la lista de caminos, creamos los base
            if (listaCaminos.size()==0)
                listaCaminos = crearLista();
            //En caso contrario, actualizamos la lista de caminos
            else
                listaCaminos = actualizarLista(listaCaminos);
        }while(listaCaminos.get(0).getNombre().length()<ciudades);
        //Agregamos el retorno a la ciudad inicial
        listaCaminos = volverACasa(listaCaminos);
        //Obtenemos la solucion, con el recorrido mas corto
        this.solucion = buscarCaminoSolucion(listaCaminos);
        
        System.out.println(this.solucion);
    }
    
    private ArrayList<Map> crearLista() {
        ArrayList<Map> lista = new ArrayList<>();
        //Creamos el caso base, con solo una ciudad y un valor de recorrido de cero
        for(int i=0; i<this.ciudades; i++){
            Map aux = new Map(Integer.toString(i), 0);
            lista.add(aux);
        }
        
        return lista;
    }

    private ArrayList<Map> actualizarLista(ArrayList<Map> listaAnterior) {
        ArrayList<Map> lista = new ArrayList<>();
        String nombreAux;
        int valorAux;
        Map elementoAux = new Map();
        
        //Obtenemos las nuevas combinaciones de caminos
        for(int i=0; i<listaAnterior.size(); i++){
            //Tomamos el camino y el valor del recorrido de la lista anterior
            nombreAux = listaAnterior.get(i).getNombre();
            valorAux = listaAnterior.get(i).getValor();
            //Comparamos y agregamos la nueva ciudad no visitada al camino
            //Tambien calculamos su nuevo valor, todo con ayuda de la lista anterior
            for(int j=0; j<this.ciudades; j++){
                //Si la nueva ciudad no esta en el camino anterior, se agrega
                if(!nombreAux.contains(String.valueOf(j))){
                    //Ultima ciudad visitada en la lista o recorrido anterior
                    int ultimaCiudad = Integer.valueOf(nombreAux.substring(nombreAux.length()-1, nombreAux.length()));
                    //Agregamos la nueva ciudad tanto al valor como en el nombre del recorrido
                    nombreAux+=String.valueOf(j);
                    valorAux+=this.caminos[ultimaCiudad][j];
                    //Agregamos el nuevo elemento
                    elementoAux.setNombre(nombreAux);
                    elementoAux.setValor(valorAux);
                    lista.add(new Map(elementoAux));
                }
                nombreAux = listaAnterior.get(i).getNombre();
                valorAux = listaAnterior.get(i).getValor();
            }
        }
        
        
        return lista;
    }
    
    private ArrayList<Map> volverACasa(ArrayList<Map> listaCaminos) {
        ArrayList<Map> lista = new ArrayList<>();
        Map aux = new Map();
        //Recorremos cada elemento en la lista de caminos para hacer su regreso a la ciudad de inicio
        for(Map elemento: listaCaminos){
            //Obtenemos la ultima ciudad visitada en el camino
            int ultimaCiudad = Integer.valueOf(elemento.getNombre().substring(
                    elemento.getNombre().length()-1, elemento.getNombre().length()));
            //Obtenemos la ciudad de inicio en ese camino
            int inicio = Integer.valueOf(elemento.getNombre().substring(0,1));
            //Obtenemos el nuevo camino y valor de recorrido con el regreso a la ciudad de inicio
            aux.setNombre(elemento.getNombre()+elemento.getNombre().substring(0, 1));
            aux.setValor(elemento.getValor()+this.caminos[ultimaCiudad][inicio]);
            
            lista.add(new Map(aux));
        }
        
        return lista;
    }

    private Map buscarCaminoSolucion(ArrayList<Map> listaCaminos) {
        Map aux = new Map(Integer.MAX_VALUE);
        //Buscamos los elementos que tengan un valor de recorrido menor
        //y que tengan una ciudad de inicio igual a la requerida en el problema
        for(Map elemento: listaCaminos){
            if(elemento.getValor()<aux.getValor() &&Integer.valueOf(
                    elemento.getNombre().substring(0, 1))==this.ciudadInicio)
                aux = elemento;
        }
        
        return aux;
    }
    
}