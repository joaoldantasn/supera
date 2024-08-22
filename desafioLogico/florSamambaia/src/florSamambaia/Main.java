package florSamambaia;

public class Main {

	/*
	 * Circulo desenhado pelo caçador precisa ser maior que o círculo da área da
	 * flor
	 */
	public static String tentativaDesenhar(int r1, int x1, int y1, int r2, int x2, int y2) {

		double distancia = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));

		if (distancia + r2 <= r1) {
			return "RICO";
		} else {
			return "MORTO";
		}
	}

	public static void main(String[] args) {
		System.out.println(tentativaDesenhar(10, 2, 3, 4, 4, 6)); // Saída esperada: "RICO"
		System.out.println(tentativaDesenhar(7, 5, 5, 3, 8, 9)); // Saída esperada: "MORTO"
		System.out.println(tentativaDesenhar(15, 6, 8, 5, 10, 12)); // Saída esperada: "RICO"
		System.out.println(tentativaDesenhar(8, 3, 4, 3, 5, 6)); // Saída esperada: "RICO"
	}
}
