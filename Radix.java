
import java.util.Arrays;
        
/**
 *
 * @author poeta
 */
public class Radix {
    
    //Realiza el counting sort basado en el digito actual
    private static void countingSort(int[] arr, int exp){
        
        int n = arr.length;
        int[] salida = new int[n]; // Almacena los numeros ordenados
        int[] contador = new int[10];// array para contar las ocurrencias de cada digito
        
        for(int i = 0 ; i < n ; i++){
            //Cuenta las veces que aparece el exponente  en la posicion
        
            int indice = (arr[i] / exp) % 10; //Extrae el digito en la pos actual
            contador[indice]++; //aumenta el contador de ese digito
            
        }
        
        for(int i = 1 ; i < 10 ; i++){ 
            //convierte al contador[i] en la pos real del digito en el arreglod de salida 
        
            contador[i] += contador[i - 1];  //suma las ocurrencias acumuladas
                       
        }
        
        for(int i = n - 1 ; i >= 0 ; i--){
            // arreglo de salida de ordenamiento los nums segun el digito actual
        
            int indice = (arr[i] / exp) % 10; //Extrae el digito nuevamente
            salida[contador[indice] - 1] = arr[i]; //coloca el numero en la posicion correcta
            contador[indice]--; //Reduce el conteo de ese digito
            
        }
        
        //copia el arreglo ordenado y devuelve el original
        System.arraycopy(salida, 0, arr, 0, n);
        
    }
    
    public static void radixSort(int[] arr){
        //metodo principal
    
        int max = Arrays.stream(arr).max().orElse(0); 
        //Array.stream(arr) convierte el arreglo en una secuencia de elementos de filtrado
        //encontramos el num max para saber la cantidad de digitos
    
        for(int exp = 1 ; max / exp > 0 ; exp *= 10){
            // se aplica el countingsort para cada pos (u, d, c...)
    
            countingSort(arr, exp);
            // ordena por el num actual
        
        }
        
    }
    
    public static void main(String[] args) 
    
    {
    
    int[] arr = {235, 45, 22, 73, 90, 803, 2, 66};
    System.out.println("Arreglo antes de ordenar: " + Arrays.toString(arr));

    radixSort(arr); // llama para ordenar el arreglo
    
    System.out.println("Arreglo depues de ordenar: " + Arrays.toString(arr));
    
    }
}    
