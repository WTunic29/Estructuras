package co.edu.unipiloto.estdatos.tallerheap.interfaz;

import co.edu.unipiloto.estdatos.tallerheap.mundo.Pedido;
import co.edu.unipiloto.estdatos.tallerheap.mundo.Pizzeria;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

public class Main {

    static BufferedReader br;
    static Pizzeria pizzeria;

    static final int INTERFAZ_USUARIO = 1;
    static final int INGRESO_MANUAL = 2;
    static final int ARCHIVO_DE_PRUEBAS = 3;
    static final int SALIR = 4;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        boolean end = false;

        while (!end) {
            imprimirArchivo("./data/header.txt");
            Integer option = Integer.parseInt(br.readLine());

            switch (option) {
                case INTERFAZ_USUARIO:
                    interfazUsuario();
                    break;
                case INGRESO_MANUAL:
                    imprimirArchivo("./data/ingresoManual.txt");
                    ingresoManual();
                    break;
                case ARCHIVO_DE_PRUEBAS:
                    ingresoArchivoPruebas();
                    break;
                case SALIR:
                    System.out.println("SALIENDO");
                    end = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    static void interfazUsuario() throws Exception {
        pizzeria = new Pizzeria();
        boolean end = false;

        while (!end) {
            imprimirArchivo("./data/interfazUsuario.txt");
            System.out.println("Seleccione una opcion:");
            int option = Integer.parseInt(br.readLine());

            switch (option) {
                case 1:
                    agregarPedido();
                    break;
                case 2:
                    atenderPedido();
                    break;
                case 3:
                    despacharPedido();
                    break;
                case 4:
                    imprimirColas(pizzeria);
                    break;
                case 5:
                    end = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    static void agregarPedido() throws Exception {
        System.out.println("Escriba el primer nombre del autor del pedido:");
        String nombre = br.readLine();
        System.out.println("Escriba el precio del pedido:");
        double precio = Double.parseDouble(br.readLine());
        System.out.println("Escriba la cercania del pedido (1-5):");
        int cercania = Integer.parseInt(br.readLine());
        pizzeria.agregarPedido(nombre, precio, cercania);
        System.out.println("Pedido agregado.");
    }

    static void atenderPedido() {
        Pedido p = pizzeria.atenderPedido();
        if (p == null) {
            System.out.println("Cola vacía");
        } else {
            System.out.println("Pedido atendido: " + p.getAutorPedido() + " - " + p.getPrecio() + " - " + p.getCercania());
        }
    }

    static void despacharPedido() {
        Pedido p = pizzeria.despacharPedido();
        if (p == null) {
            System.out.println("Cola vacía");
        } else {
            System.out.println("Pedido despachado: " + p.getAutorPedido() + " - " + p.getPrecio() + " - " + p.getCercania());
        }
    }

    static void ingresoComandos(BufferedReader br) throws Exception {
        pizzeria = new Pizzeria();
        String ln;

        while ((ln = br.readLine()) != null && !ln.equalsIgnoreCase("SALIR")) {
            ln = ln.trim();

            if (ln.isEmpty()) {
                continue;
            }

            String[] partes = ln.split("\\s+");

            imprimirOpciones();

            if (partes.length == 0) {
                System.out.println("Comando no reconocido. Por favor ingrese un comando válido.");
                continue;
            }

            String comando = partes[0].toUpperCase();

            switch (comando) {
                case "RECIBIR":
                    recibirPedido(partes);
                    break;
                case "ATENDER":
                    atenderPedido();
                    break;
                case "DESPACHAR":
                    despacharPedido();
                    break;
                case "SALIR":
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Comando no reconocido.");
                    break;
            }

            imprimirColas(pizzeria);
        }
    }

    static void recibirPedido(String[] partes) {
        if (partes.length != 4) {
            System.out.println("Formato inválido. Usa: RECIBIR <nombre> <precio> <cercania>");
            return;
        }

        String nombre = partes[1];
        double precio;
        int cercania;
        try {
            precio = Double.parseDouble(partes[2]);
            cercania = Integer.parseInt(partes[3]);
            pizzeria.agregarPedido(nombre, precio, cercania);
            System.out.println("Pedido recibido.");
        } catch (NumberFormatException e) {
            System.out.println("Error: El precio o la cercanía no son números válidos.");
        }
    }

    static void imprimirOpciones() {
        System.out.println("\nComandos disponibles:");
        System.out.println("1. RECIBIR <nombre> <precio> <cercania>");
        System.out.println("2. ATENDER");
        System.out.println("3. DESPACHAR");
        System.out.println("4. SALIR");
    }

    static void ingresoManual() throws Exception {
        ingresoComandos(br);
    }

    static void ingresoArchivoPruebas() throws Exception {
        try (BufferedReader brArchivo = new BufferedReader(new FileReader("./data/tests.txt"))) {
            ingresoComandos(brArchivo);
        }
    }

    static void imprimirColas(Pizzeria pizzeria) {
        System.out.println("------ COLAS ACTUALES ------");
        System.out.println("No se puede imprimir la cola de pedidos recibidos completa (estructura no lineal).");
        try {
            Pedido peek = pizzeria.verPedido();
            if (peek != null) {
                System.out.println("Siguiente pedido por atender: " + peek.getAutorPedido() + " - $" + peek.getPrecio());
            } else {
                System.out.println("No hay pedidos en espera.");
            }
        } catch (Exception e) {
            System.out.println("Error accediendo al pedido de mayor prioridad.");
        }

        imprimirColaDespachos(pizzeria);
        System.out.println();
    }

    private static void imprimirColaDespachos(Pizzeria pizzeria) {
        System.out.println("      COLA DESPACHOS      ");
        int index = 1;

        try {
            for (Pedido p : pizzeria.colaDespachosList()) {
                System.out.printf("%d. %s (%d mts.)%n", index++, p.getAutorPedido(), p.getCercania());
            }
        } catch (Exception e) {
            System.out.println("Cola de despachos vacía.");
        }
    }

    static void imprimirArchivo(String nombre) {
        try (BufferedReader br = new BufferedReader(new FileReader(nombre))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
