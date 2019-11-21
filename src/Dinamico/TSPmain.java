/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dinamico;


/**
 *
 * @author monic
 */
public class TSPmain {
    
    /**
     *
     * @param args
     */
    public static void main(String args[]){
    
        int [][] caminos = new int[][]{
            {0,     1,      2,      8},
            {1,     0,      3,     12},
            {2,     3,      0,      7},
            {8,    12,      7,      0}
        };
        TSP tsp = new TSP(caminos, 2);
        tsp.buscarSolucion();
 
    }
}
