
package Dinamico;


public class Map {
    private String nombre;
    private int valor;

    public Map(String nombre, int valor) {
        this.nombre = nombre;
        this.valor = valor;
    }
    
    public Map(Map map){
        this.nombre = map.getNombre();
        this.valor = map.getValor();
    }
    
    public Map(String nombre){
        this.nombre = nombre;
        this.valor = 0;
    }
    
    public Map(int valor){
        this.nombre = "";
        this.valor = valor;
    }
    
    public Map(){
        this.nombre = "";
        this.valor = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public int getValor() {
        return valor;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Camino: " + nombre + ", valor=" + valor + "\n";
    }
    
}