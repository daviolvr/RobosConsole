package robo;

import exception.MovimentoInvalidoException;

public class Robo {
    private int x;
    private int y;
    private String cor;
    private int movimentosValidos = 0;
    private int movimentosInvalidos = 0;

    public Robo(String cor){
        this.cor = cor;
        x = 0;
        y = 0;
    }

    public void mover(String sentido) throws MovimentoInvalidoException, InterruptedException{
    	System.out.println("Jogada do " + getClass().getSimpleName() + " " + getCor() + ": " + sentido);
        if(sentido.equals("up")){
            if(y>=4){ //considerando a área fixa de um quadrado com 5 unidades de lado, como solicitado na questão
                movimentosInvalidos++;
                throw new MovimentoInvalidoException("Movimento é inválido, pois excede a matriz");
            } else{
                setY(y+1);
                System.out.println("Posição atual do robo " + getCor() + ": " + getX() + ", " + getY());
                movimentosValidos++;
            }
        } 
        else if(sentido.equals("down")){
            if(y<=0){
                movimentosInvalidos++;
                throw new MovimentoInvalidoException("Movimento inválido, pois coordenadas negativas não são permitidas");
            } else{
                setY(y-1);
                System.out.println("Posição atual do robo " + getCor() + ": " + getX() + ", " + getY());
                movimentosValidos++;
            }
        }
        else if(sentido.equals("right")){
            if(x>=4){
                movimentosInvalidos++;
                throw new MovimentoInvalidoException("Movimento inválido, pois excede a matriz");
            } else{
                setX(x+1);
                System.out.println("Posição atual do robo " + getCor() + ": " + getX() + ", " + getY());
                movimentosValidos++;
            }
        }
        else if(sentido.equals("left")){
            if(x<=0){
                movimentosInvalidos++;
                throw new MovimentoInvalidoException("Movimento inválido, pois coordenadas negativas não são permitidas");
            } else{
                setX(x-1);
                System.out.println("Posição atual do robo " + getCor() + ": " + getX() + ", " + getY());
                movimentosValidos++;
            }
        }
    }

    public void mover(int inteiro) throws MovimentoInvalidoException, InterruptedException{
        if(inteiro==1){
            mover("up");
        }
        else if(inteiro==2){
            mover("down");
        }
        else if(inteiro==3){
            mover("right");
        }
        else if(inteiro==4){
            mover("left");
        }
        else if(inteiro>4  || inteiro<=0){
            movimentosInvalidos++;
            throw new MovimentoInvalidoException("Comando (inteiro inserido) inválido, apenas é possível 1 (up), 2 (down), 3 (right) e 4 (left)");
        }
    }

    public boolean encontrouAlimento(int xAlimento, int yAlimento){
        if(getX() == xAlimento) {
        	if(getY() == yAlimento) {
        		return true;
        	}
        }
        return false;
    }

    public char[][] getMatriz(int xAlimento, int yAlimento) {
        char[][] matriz = new char[5][5]; // Matriz de 5x5 
        
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i == y && j == x) {
                    matriz[i][j] = 'R'; // robô
                } else if (i == yAlimento && j == xAlimento) {
                    matriz[i][j] = 'A'; // alimento
                } else {
                    matriz[i][j] = '.'; // espaço vazio
                }
            }
        }
    
        return matriz;
    }
    
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public int getMovimentosValidos() {
        return movimentosValidos;
    }

    public void setMovimentosValidos(int movimentosValidos) {
        this.movimentosValidos = movimentosValidos;
    }

    public int getMovimentosInvalidos() {
        return movimentosInvalidos;
    }

    public void setMovimentosInvalidos(int movimentosInvalidos) {
        this.movimentosInvalidos = movimentosInvalidos;
    }

}