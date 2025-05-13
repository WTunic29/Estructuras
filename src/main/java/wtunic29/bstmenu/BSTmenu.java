package wtunic29.bstmenu;

import java.util.Scanner;

/**
 *
 * @author poeta
 */

public class BSTmenu {

    public static void main(String[] args) {
        
        BST<String, Integer> bst = new BST<>();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            
            System.out.println("\n--- Menú BST de Vocales ---");
            System.out.println("1. Insertar vocal");
            System.out.println("2. Obtener valor de una vocal");
            System.out.println("3. Eliminar una vocal");
            System.out.println("4. Eliminar mínimo");
            System.out.println("5. Eliminar máximo");
            System.out.println("6. Mostrar mínimo y máximo");
            System.out.println("7. Buscar floor y ceiling");
            System.out.println("8. Buscar por índice (select)");
            System.out.println("9. Buscar índice de clave (rank)");
            System.out.println("10. Mostrar recorrido inorden");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                
                case 1:
                    
                    System.out.print("Ingrese una letra de la (A - Z): ");
                    String clave = scanner.nextLine().toUpperCase();
                    System.out.print("Ingrese el valor (1 dígito): ");
                    int valor = scanner.nextInt();
                    bst.put(clave, valor);
                    break;
                    
                case 2:
                    
                    System.out.print("Ingrese la letra: ");
                    String buscar = scanner.nextLine().toUpperCase();
                    Integer val = bst.get(buscar);
                    if (val != null) System.out.println("Valor: " + val);
                    else System.out.println("No existe.");
                    break;
                case 3:
                    
                    System.out.print("Ingrese la letra a eliminar: ");
                    String del = scanner.nextLine().toUpperCase();
                    bst.delete(del);
                    break;
                    
                case 4:
                    
                    bst.deleteMin();
                    System.out.println("Mínimo eliminado.");
                    break;
                    
                case 5:
                    
                    bst.deleteMax();
                    System.out.println("Máximo eliminado.");
                    break;
                    
                case 6:
                    
                    System.out.println("Mínimo: " + bst.min());
                    System.out.println("Máximo: " + bst.max());
                    break;
                    
                case 7:
                    
                    System.out.print("Clave para buscar floor y ceiling: ");
                    String k = scanner.nextLine().toUpperCase();
                    System.out.println("Floor: " + bst.floor(k));
                    System.out.println("Ceiling: " + bst.ceiling(k));
                    break;
                    
                case 8:
                    
                    System.out.print("Índice para select: ");
                    int idx = scanner.nextInt();
                    System.out.println("Clave: " + bst.select(idx));
                    break;
                    
                case 9:
                    
                    System.out.print("Clave para rank: ");
                    String claveRank = scanner.nextLine().toUpperCase();
                    System.out.println("Rank: " + bst.rank(claveRank));
                    break;
                    
                case 10:
                    
                    System.out.println("Recorrido inorden del árbol:");
                    for (String letra : bst.keys()) {
                        System.out.println(letra + " -> " + bst.get(letra));
                    }
                    break;
                    
                case 0:
                    System.out.println("Saliendo...");
                    break;
                    
                default:
                    System.out.println("Opción inválida.");
            }
            
        } while (opcion != 0);

        scanner.close();
    }
            
}
