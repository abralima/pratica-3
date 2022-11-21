package model;

public class TabelaHash {
    ListaAuto[] tabela;
    int tamanho = 20;
    int preenchido = 0;

    public TabelaHash() {
        this.tabela = new ListaAuto[tamanho];
        for (int i = 0; i < tamanho; i++) {
            tabela[i] = null;
        }
    }

    public int hash(int ch, int k) {
        return (ch % this.tamanho + k * (1 + ch % (this.tamanho - 1))) % this.tamanho;
    }

    public int getValue(String s) {
        // s = s.replace(" ", "");
        char[] c = s.toCharArray();
        int um = 0;
        for (Character ss : c)
            um += ss;
        return um;
    }

    public void inserir(No no) {
        int tentativa = 0;
        int chaveInt = getValue(no.chave);

        int h = hash(chaveInt, tentativa);

        while (tabela[h] != null) {
            if (tabela[h].buscar(no.chave) != null) {
                // System.out.println("Chave já existe!");
                return;
            }
            boolean sucesso = tabela[h].inserir(no);
            if (sucesso)
                return;
            h = hash(chaveInt, ++tentativa);
        }

        if (tabela[h] == null) {
            tabela[h] = new ListaAuto();
            preenchido++;
            if (preenchido >= tamanho) {
                tamanho *= 2;
                ListaAuto[] novaTabela = new ListaAuto[tamanho];
                for (int i = 0; i < tabela.length; i++) {
                    novaTabela[i] = tabela[i];
                }
                tabela = novaTabela;
            }

            tabela[h].inserir(no);
        }
    }

    public No buscar(String chave) {
        int tentativa = 0;
        int chaveInt = getValue(chave);
        int h = hash(chaveInt, 0);
        int contador = 0;
        while (tabela[h] != null) {
            No no = tabela[h].buscar(chave);
            if (no != null)
                return no;
            h = hash(chaveInt, ++tentativa);
            contador++;
            if (contador > tamanho)
                break;
        }
        return null;
    }

    public void remover(String chave) {
        int tentativa = 0;
        int chaveInt = getValue(chave);
        int h = hash(chaveInt, 0);
        while (tabela[h] != null) {
            boolean sucesso = tabela[h].remover(chave);
            if (sucesso)
                return;
            h = hash(chaveInt, ++tentativa);
        }
    }

    public void atualizar(No no) {
        No temp = buscar(no.chave);
        temp.valor = no.valor;
    }

    public void imprimir() {
        for (int i = 0; i < tabela.length; i++) {
            if (tabela[i] != null) {
                System.out.println("Tabela " + i);
                tabela[i].imprimir();
            }

        }
    }

    public int getTotalInserido() {
        int retorno = 0;
        for (int i = 0; i < tamanho; i++) {
            if (tabela[i] != null) {
                retorno += tabela[i].cont;
            }
        }

        return retorno;
    }

}
