package matrizCaracol;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static List<Integer> extrairEmCaracol(int[][] matriz) {
        List<Integer> resultado = new ArrayList<>();
        
        if (matriz == null || matriz.length == 0 || matriz[0].length == 0) {
            return resultado;
        }
        
        int topo = 0;
        int base = matriz.length - 1;
        int esquerda = 0;
        int direita = matriz[0].length - 1;
        
        while (topo <= base && esquerda <= direita) {
           
            for (int j = esquerda; j <= direita; j++) {
                resultado.add(matriz[topo][j]);
            }
            topo++;
            
            
            for (int i = topo; i <= base; i++) {
                resultado.add(matriz[i][direita]);
            }
            direita--;
            
            
            if (topo <= base) {
                
                for (int j = direita; j >= esquerda; j--) {
                    resultado.add(matriz[base][j]);
                }
                base--;
            }
            
            
            if (esquerda <= direita) {
                
                for (int i = base; i >= topo; i--) {
                    resultado.add(matriz[i][esquerda]);
                }
                esquerda++;
            }
        }
        
        return resultado;
    }

    public static void main(String[] args) {
       
        int[][] matriz1 = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        int[][] matriz2 = {
            {1, 2, 3},
            {4, 10, 12},
            {6, 7, 33}
        };

        System.out.println(extrairEmCaracol(matriz1)); // Saída: [1, 2, 3, 6, 9, 8, 7, 4, 5]
        System.out.println(extrairEmCaracol(matriz2)); // Saída: [1, 2, 3, 12, 33, 7, 6, 4, 10]

        // Testando matriz vazia
        int[][] matrizVazia = {};
        System.out.println(extrairEmCaracol(matrizVazia)); // Saída: []
    }
}
