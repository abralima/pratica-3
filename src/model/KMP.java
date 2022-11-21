package model;

public class KMP {

    public int tabelaPrefixo[];

    public boolean KMPSearch(String padrao, String texto) {
        int tamPadrao = padrao.length();
        int tamTexto = texto.length();

        int quantAchado = 0;
        int comparacoes = 0;

        tabelaPrefixo = new int[tamPadrao];
        int j = 0;

        criaTabelaPrefixo(padrao, tamPadrao);

        int i = 0;
        while ((tamTexto - i) >= (tamPadrao - j)) {
            if (padrao.charAt(j) == texto.charAt(i)) {
                j++;
                i++;
                comparacoes++;
            } else
                comparacoes++;
            if (j == tamPadrao) {
                // System.out.println("Padrão encontrado no indice: " + (i - j));
                quantAchado++;
                j = tabelaPrefixo[j - 1];
            }

            else if (i < tamTexto && padrao.charAt(j) != texto.charAt(i)) {

                if (j != 0)
                    j = tabelaPrefixo[j - 1];
                else
                    i = i + 1;
            }
        }
        if (quantAchado > 0)
            return true;

        return false;
        // System.out.println("KMP Quantidade de padrão encontrados: " + quantAchado);
        // System.out.println("KMP Quantidade de comparacoes: " + comparacoes);
    }

    void criaTabelaPrefixo(String padrao, int tamPadrao) {

        int tam = 0;
        int i = 1;
        tabelaPrefixo[0] = 0;

        while (i < tamPadrao) {
            if (padrao.charAt(i) == padrao.charAt(tam)) {
                tam++;
                tabelaPrefixo[i] = tam;
                i++;
            } else {

                if (tam != 0) {
                    tam = tabelaPrefixo[tam - 1];

                } else {
                    tabelaPrefixo[i] = tam;
                    i++;
                }
            }
        }
    }

}
