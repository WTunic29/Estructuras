package co.edu.unipiloto.estdatos.tallerheap.mundo;

import java.util.Comparator;
/**
 *
 * @author poeta
 */

public class ComparadorPrecio implements Comparator<Pedido> {
    
    @Override
    public int compare(Pedido o1, Pedido o2) {
        
        return Double.compare(o1.getPrecio(), o2.getPrecio());
        
    }
    
}
