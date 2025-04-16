package co.edu.unipiloto.estdatos.tallerheap.mundo;
import co.edu.unipiloto.estdatos.tallerheap.estructuras.IHeap;
import co.edu.unipiloto.estdatos.tallerheap.estructuras.MaxPQ;
import java.util.ArrayList;


public class Pizzeria {

    public final static String RECIBIR_PEDIDO = "RECIBIR";
    public final static String ATENDER_PEDIDO = "ATENDER";
    public final static String DESPACHAR_PEDIDO = "DESPACHAR";
    public final static String FIN = "FIN";

    private IHeap<Pedido> pedidosRecibidos;
    private ArrayList<Pedido> colaDespachos;

    public Pizzeria() {
        
        pedidosRecibidos = new MaxPQ<>();  // Usa tu implementación de MaxPQ
        colaDespachos = new ArrayList<>();
        
    }

    public void agregarPedido(String nombreAutor, double precio, int cercania) {
        
        Pedido nuevo = new Pedido(nombreAutor, precio, cercania);
        pedidosRecibidos.add(nuevo);
        
    }

    public Pedido atenderPedido() {
        
        if (pedidosRecibidos.isEmpty()) return null;
        Pedido p = pedidosRecibidos.poll(); // poll reemplaza eliminarMax
        colaDespachos.add(p);
        return p;
        
    }

    public Pedido despacharPedido() {
        
        if (colaDespachos.isEmpty()) return null;

        Pedido masCercano = colaDespachos.get(0);
        for (Pedido p : colaDespachos) {
            
            if (p.getCercania() < masCercano.getCercania()) {
                
                masCercano = p;
                
            }
            
        }
        
        colaDespachos.remove(masCercano);
        return masCercano;
        
    }

    public ArrayList<Pedido> colaDespachosList() {
        
        return new ArrayList<>(colaDespachos);
        
    }

    public Pedido verPedido() {
        
        if (pedidosRecibidos == null || pedidosRecibidos.isEmpty()) return null;
        return pedidosRecibidos.peek();
    }
    
}