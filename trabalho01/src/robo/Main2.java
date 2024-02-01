package robo;

import java.util.Random;
import java.util.Scanner;

import exception.MovimentoInvalidoException;

public class Main2 {
    public static void main(String[] args) throws InterruptedException {
        Scanner leitor = new Scanner(System.in);
        Random aleatorio = new Random();

        System.out.println("Informe a posição 'x' que o alimento deve ficar (0-4): ");
        int xAlimento = leitor.nextInt();
        System.out.println("Informe a posição 'y' que o alimento deve ficar (0-4): ");
        int yAlimento = leitor.nextInt();

        System.out.println("Agora os robôs podem começar a se mover!");

        Robo r1 = new Robo("Preto");
        Robo r2 = new Robo("Cinza");
        char[][] matriz = getMatrizInicial(xAlimento, yAlimento, r1, r2);

        while (true) {
            exibirMatriz(matriz);
            int randomR1 = aleatorio.nextInt(4) + 1;
            int randomR2 = aleatorio.nextInt(4) + 1;

            try {
                r1.mover(randomR1);
                matriz = getMatrizAtualizada(xAlimento, yAlimento, r1, r2);
                if (r1.encontrouAlimento(xAlimento, yAlimento)) {
                    exibirMatriz(matriz);
                    System.out.println("O robô " + r1.getCor() + " encontrou o alimento!");
                    break;
                }
            } catch (MovimentoInvalidoException e) {
                System.out.println(e.getMessage());
            }

            Thread.sleep(1000);

            exibirMatriz(matriz);
            try {
                r2.mover(randomR2);
                matriz = getMatrizAtualizada(xAlimento, yAlimento, r1, r2);
                if (r2.encontrouAlimento(xAlimento, yAlimento)) {
                    exibirMatriz(matriz);
                    System.out.println("O robô " + r2.getCor() + " encontrou o alimento!");
                    break;
                }
            } catch (MovimentoInvalidoException e) {
                System.out.println(e.getMessage());
            }

            Thread.sleep(1000);
        }

        leitor.close();
        System.out.println("Número de movimentos inválidos do robô " + r1.getCor() + ": " + r1.getMovimentosInvalidos());
        System.out.println("Número de movimentos válidos do robô: " + r1.getCor() + ": " + r1.getMovimentosValidos());
        System.out.println("Número de movimentos inválidos do robô " + r2.getCor() + ": " + r2.getMovimentosInvalidos());
        System.out.println("Número de movimentos válidos do robô: " + r2.getCor() + ": " + r2.getMovimentosValidos());
    }

    public static char[][] getMatrizInicial(int xAlimento, int yAlimento, Robo r1, Robo r2) {
        char[][] matriz = new char[5][5];

        matriz[yAlimento][xAlimento] = 'A'; // marca a posição do alimento na matriz
        matriz[r1.getY()][r1.getX()] = 'P'; // marca a posição do robô 1 (cinza) na matriz
        matriz[r2.getY()][r2.getX()] = 'C'; // marca a posição do robô 2 (preto) na matriz

        return matriz;
    }

    public static char[][] getMatrizAtualizada(int xAlimento, int yAlimento, Robo r1, Robo r2) {
        char[][] matriz = new char[5][5];

        matriz[yAlimento][xAlimento] = 'A'; // marca a posição do alimento na matriz
        matriz[r1.getY()][r1.getX()] = 'P'; // marca a posição do robô 1 na matriz
        matriz[r2.getY()][r2.getX()] = 'C'; // marca a posição do robô 2 na matriz

        return matriz;
    }

    public static void exibirMatriz(char[][] matriz) {
        for (int i = 4; i >= 0; i--) {
            for (int j = 0; j < 5; j++) {
                if (matriz[i][j] == 'P' || matriz[i][j] == 'C' || matriz[i][j] == 'A') {
                    System.out.print(matriz[i][j] + " ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
