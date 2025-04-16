package co.edu.unipiloto.estdatos.tallerheap.mundo;

public class Pedido implements Comparable<Pedido> {

    private double precio;
    private String autorPedido;
    private int cercania;

    public Pedido(String autorPedido, double precio, int cercania) {
        this.autorPedido = autorPedido;
        this.precio = precio;
        this.cercania = cercania;
    }

    public double getPrecio() {
        return precio;
    }

    public String getAutorPedido() {
        return autorPedido;
    }

    public int getCercania() {
        return cercania;
    }

    @Override
    public int compareTo(Pedido otro) {
        return Double.compare(this.precio, otro.precio);
    }
}

