package model;

public class No {
    public String chave;
    public String valor;
    public No proximo;
    public int contador;

    public No(String chave, String valor) {
        this.chave = chave;
        this.valor = valor;
        contador = 0;
    }

    public No() {
    }

}
