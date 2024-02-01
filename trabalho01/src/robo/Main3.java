package robo;

import java.util.Scanner;

import exception.MovimentoInvalidoException;

import java.util.Random;

public class Main3 {
	public static void main(String[] args) throws InterruptedException {
        Scanner leitor = new Scanner(System.in);
        Random aleatorio = new Random();

        System.out.println("Informe a posição 'x' que o alimento deve ficar (0-4): ");
        int xAlimento = leitor.nextInt();
        System.out.println("Informe a posição 'y' que o alimento deve ficar (0-4): ");
        int yAlimento = leitor.nextInt();

        System.out.println("Agora os robôs podem começar a se mover!");

        Robo r1 = new Robo("Verde");
        RoboInteligente r2 = new RoboInteligente("Amarelo");
        char[][] matriz = getMatrizInicial(xAlimento, yAlimento, r1, r2);

        boolean encontrouRoboNormal = false;
        boolean encontrouRoboInteligente = false;

        while (!(encontrouRoboNormal && encontrouRoboInteligente)) {
            exibirMatriz(matriz);
            int randomR2 = aleatorio.nextInt(4) + 1;
            Thread.sleep(1000); // aguarda 1 segundo

            try {
                if (!encontrouRoboInteligente) {
                    r2.mover(randomR2);
                    matriz = getMatrizAtualizada(xAlimento, yAlimento, r1, r2);
                    if (r2.encontrouAlimento(xAlimento, yAlimento)) {
                        exibirMatriz(matriz);
                        System.out.println("Robô inteligente " + r2.getCor() + " encontrou o alimento");
                        encontrouRoboInteligente = true;
                    }
                }

                Thread.sleep(1000); // aguarda 1 segundo
                exibirMatriz(matriz);
                int randomR1 = aleatorio.nextInt(4) + 1;

                if (!encontrouRoboNormal) {
                    r1.mover(randomR1);
                    matriz = getMatrizAtualizada(xAlimento, yAlimento, r1, r2);
                    if (r1.encontrouAlimento(xAlimento, yAlimento)) {
                        exibirMatriz(matriz);
                        System.out.println("Robô normal " + r1.getCor() + " encontrou o alimento");
                        encontrouRoboNormal = true;
                    }
                }
            } catch (MovimentoInvalidoException e) {
                System.out.println(e.getMessage());
            }
            Thread.sleep(1000); // aguarda 1 segundo
        }

        System.out.println("Número de movimentos válidos do robô normal " + r1.getCor() + ": " + r1.getMovimentosValidos());
        System.out.println("Número de movimentos inválidos do robô normal " + r1.getCor() + ": " + r1.getMovimentosInvalidos());
        System.out.println("Número de movimentos válidos do robô inteligente " + r2.getCor() + ": " + r2.getMovimentosValidos());
        System.out.println("Número de movimentos inválidos do robô inteligente " + r2.getCor() + ": " + r2.getMovimentosInvalidos());

        leitor.close();
    }

    public static char[][] getMatrizInicial(int xAlimento, int yAlimento, Robo r1, Robo r2) {
        char[][] matriz = new char[5][5];

        matriz[yAlimento][xAlimento] = 'A'; // marca a posição do alimento na matriz
        matriz[r1.getY()][r1.getX()] = 'N'; // marca a posição do robô 1 (normal) na matriz
        matriz[r2.getY()][r2.getX()] = 'I'; // marca a posição do robô 2 (inteligente) na matriz

        return matriz;
    }

    public static char[][] getMatrizAtualizada(int xAlimento, int yAlimento, Robo r1, Robo r2) {
        char[][] matriz = new char[5][5];

        matriz[yAlimento][xAlimento] = 'A'; // marca a posição do alimento na matriz
        matriz[r1.getY()][r1.getX()] = 'N'; // marca a posição do robô 1 (normal) na matriz
        matriz[r2.getY()][r2.getX()] = 'I'; // marca a posição do robô 2 (inteligente) na matriz

        return matriz;
    }

    public static void exibirMatriz(char[][] matriz) {
        for (int i = 4; i >= 0; i--) {
            for (int j = 0; j < 5; j++) {
                if (matriz[i][j] == 'N' || matriz[i][j] == 'I' || matriz[i][j] == 'A') {
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

