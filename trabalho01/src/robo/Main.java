package robo;

import java.util.Scanner;

import exception.MovimentoInvalidoException;

public class Main{
    public static void main(String[] args) throws InterruptedException {
        Scanner leitor = new Scanner(System.in);
        System.out.println("Informe a posição 'x' que o alimento deve ficar (0-4): ");
        int xAlimento = leitor.nextInt();
        System.out.println("Informe a posição 'y' que o alimento deve ficar (0-4): ");
        int yAlimento = leitor.nextInt();
        
        System.out.println("Agora você pode começar a mover o robô!");

        Robo r1 = new Robo("Branco");
        char[][] matriz = r1.getMatriz(xAlimento, yAlimento);

        while (!r1.encontrouAlimento(xAlimento, yAlimento)) {
            exibirMatriz(matriz);
            System.out.println("Insira o comando desejado (1, 2, 3, 4): ");
            int comando = leitor.nextInt();
            System.out.println();

            try {
                r1.mover(comando);
                matriz = r1.getMatriz(xAlimento, yAlimento);
            } catch (MovimentoInvalidoException e) {
                System.out.println(e.getMessage());
            }

            Thread.sleep(1000); // pausa de 1 segundo pra visualizar
        }

        System.out.println("O robô " + r1.getCor() + " encontrou o alimento!");

        leitor.close();
        System.out.println();
        System.out.println("Numéro de movimentos inválidos do robô: " + r1.getMovimentosInvalidos());
        System.out.println("Número de movimentos válidos do robô: " + r1.getMovimentosValidos());
    }

    public static void exibirMatriz(char[][] matriz) {
        for (int i = 4; i >=0; i--) {
            for (int j = 0; j < 5; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}