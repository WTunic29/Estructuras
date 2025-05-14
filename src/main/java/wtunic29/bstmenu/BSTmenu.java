package wtunic29.bstmenu;

import java.util.Scanner;

/**
 *
 * @author poeta
 */

public class BSTmenu {

    public static void main(String[] args) {
        
        BST<String, Integer> bst = new BST<>();
        Scanner sc = new Scanner(System.in);

        while (true) {
            
            System.out.println("\n===== MENÚ BST =====");
            System.out.println("1. Insertar clave-valor");
            System.out.println("2. Obtener valor por clave");
            System.out.println("3. Eliminar clave");
            System.out.println("4. Verificar si contiene clave");
            System.out.println("5. Obtener mínimo");
            System.out.println("6. Obtener máximo");
            System.out.println("7. Eliminar mínimo");
            System.out.println("8. Eliminar máximo");
            System.out.println("9. Tamaño total");
            System.out.println("10. ¿Está vacío?");
            System.out.println("11. Obtener clave floor (menor o igual)");
            System.out.println("12. Obtener clave ceiling (mayor o igual)");
            System.out.println("13. Obtener claves en rango");
            System.out.println("14. Rank (número de claves menores)");
            System.out.println("15. Select (clave de un rango específico)");
            System.out.println("16. Recorrido inorder");
            System.out.println("17. Recorrido preorder");
            System.out.println("18. Recorrido postorder");
            System.out.println("19. Salir");
            System.out.print("Selecciona una opción: ");
            
            int opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                
                case 1:
                    
                    System.out.print("Clave: ");
                    String key1 = sc.nextLine();
                    System.out.print("Valor (entero): ");
                    int val = sc.nextInt();
                    sc.nextLine();
                    bst.put(key1, val);
                    System.out.println("Insertado.");
                    break;
                    
                case 2:
                    
                    System.out.print("Clave: ");
                    String key2 = sc.nextLine();
                    Integer result = bst.get(key2);
                    System.out.println(result != null ? "Valor: " + result : "Clave no encontrada.");
                    break;
                    
                case 3:
                    
                    System.out.print("Clave: ");
                    String key3 = sc.nextLine();
                    bst.delete(key3);
                    System.out.println("Eliminado si existía.");
                    break;
                    
                case 4:
                    
                    System.out.print("Clave: ");
                    String key4 = sc.nextLine();
                    System.out.println(bst.contains(key4) ? "Sí contiene." : "No contiene.");
                    break;
                    
                case 5:
                    
                    System.out.println("Mínimo: " + bst.min());
                    break;
                    
                case 6:
                    
                    System.out.println("Máximo: " + bst.max());
                    break;
                    
                case 7:
                    
                    bst.deleteMin();
                    System.out.println("Mínimo eliminado.");
                    break;
                    
                case 8:
                    
                    bst.deleteMax();
                    System.out.println("Máximo eliminado.");
                    break;
                    
                case 9:
                    
                    System.out.println("Tamaño: " + bst.size());
                    break;
                    
                case 10:
                    
                    System.out.println(bst.isEmpty() ? "Sí, está vacío." : "No, contiene elementos.");
                    break;
                    
                case 11:
                    
                    System.out.print("Clave: ");
                    String key5 = sc.nextLine();
                    System.out.println("Floor: " + bst.floor(key5));
                    break;
                    
                case 12:
                    
                    System.out.print("Clave: ");
                    String key6 = sc.nextLine();
                    System.out.println("Ceiling: " + bst.ceiling(key6));
                    break;
                    
                case 13:
                    
                    System.out.print("Desde clave: ");
                    String lo = sc.nextLine();
                    System.out.print("Hasta clave: ");
                    String hi = sc.nextLine();
                    System.out.println("Claves en rango:");
                    
                    for (String k : bst.keys(lo, hi)) {
                        System.out.println(k + " => " + bst.get(k));
                    }
                    
                    break;
                    
                case 14:
                    System.out.print("Clave: ");
                    String key7 = sc.nextLine();
                    System.out.println("Rank: " + bst.rank(key7));
                    break;
                case 15:
                    System.out.print("Índice (rank): ");
                    int idx = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Clave de ese rango: " + bst.select(idx));
                    break;
                case 16:
                    System.out.println("Recorrido InOrder:");
                    for (String k : bst.inorder()) {
                        System.out.println(k + " => " + bst.get(k));
                    }
                    break;
                case 17:
                    System.out.println("Recorrido PreOrder:");
                    for (String k : bst.preorder()) {
                        System.out.println(k + " => " + bst.get(k));
                    }
                    break;
                case 18:
                    System.out.println("Recorrido PostOrder:");
                    for (String k : bst.postorder()) {
                        System.out.println(k + " => " + bst.get(k));
                    }
                    break;
                case 19:
                    System.out.println("¡Hasta pronto!");
                    sc.close();
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}