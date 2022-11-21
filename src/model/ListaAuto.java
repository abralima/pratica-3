package model;

public class ListaAuto {
    public No[] lista;
    public static int max = 5;
    public int cont = 0;

    public ListaAuto() {
        lista = new No[max];
    }

    public Boolean inserir(No n) {
        if (cont < max) {
            lista[cont] = n;
            cont++;
            return true;
        }
        return false;
    }

    public void moverParaFrente(Integer p) {
        No temp1 = lista[p];
        No temp2 = null;
        Integer i = 0;

        while (i <= p) {
            if (i == 0) {
                temp2 = lista[i];
                lista[i] = temp1;
            } else {
                temp1 = lista[i];
                lista[i] = temp2;
                temp2 = temp1;
            }
            i++;
        }
    }

    public No buscar(String chave) {
        int i = 0;

        while (i < cont) {
            // System.out.println(lista[i].chave);
            if (lista[i].chave.equals(chave)) {
                // System.out.println("Chave encontrada");
                moverParaFrente(i);
                return lista[0];
            }
            i++;
        }
        // System.out.println("Não achou!");
        return null;
    }

    public boolean remover(String chave) {
        int i = 0;
        No temp = null;
        No temp2 = null;
        while (i < cont) {
            if (lista[i].chave.equals(chave)) {
                temp = lista[i];
                for (int j = i; j < cont - 1; j++) {
                    temp2 = lista[j + 1];
                    lista[j + 1] = lista[j];
                    lista[j] = temp2;
                }
                lista[cont - 1] = null;
                cont--;
                return true;
            }
            i++;
        }
        // System.out.println("Não existe!");
        return false;

    }

    public void imprimir() {
        Integer i = 0;
        while (i < cont) {
            if (lista[i] != null) {
                System.out.println(lista[i].chave + " " + lista[i].valor);
                i++;
            }
        }
    }
}
