package model;

import java.io.BufferedReader;
//import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Cliente {
    TabelaHash tabela;
    int qntVc = 0;
    String[] padrao = { ".com", ".net", ".me" };

    public Cliente() {
        tabela = new TabelaHash();
    }

    public void start() throws IOException {
        inserirVinteCinco();

        while (true) {
            System.out.println("1 - Inserir um endereço");
            System.out.println("2 - Buscar um endereço");
            System.out.println("3 - Inserir 25 Endereços (" + (4 - qntVc) + " restantes)");
            System.out.println("4 - Atualizar um Endereço");
            System.out.println("5 - Quantidade de Endereços");
            System.out.println("6 - Remover um endereço");
            System.out.println("7 - Sair");
            int op = Integer.parseInt(System.console().readLine());

            if (op == 3 && qntVc == 4) {
                System.out.println("Quantidade zerada, selecione outra opção!");
                continue;
            }
            switch (op) {

                case 1:
                    inserirUsuario();
                    break;
                case 2:
                    buscar();
                    break;
                case 3:
                    inserirVinteCinco();
                    break;
                case 4:
                    atualizar();
                    break;
                case 5:
                    getTotalSites();
                    break;
                case 6:
                    remover();
                    break;
                case 7:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }

    }

    public void inserirVinteCinco() throws IOException {
        String pathSite = "";
        String pathIp = "";
        switch (qntVc) {
            case 0: {
                pathSite = "/home/abraaolima/Faculdade/Ed2/pratica-3/sites1.txt";
                pathIp = "/home/abraaolima/Faculdade/Ed2/pratica-3/ip1.txt";
                qntVc++;
                break;
            }
            case 1: {
                pathSite = "/home/abraaolima/Faculdade/Ed2/pratica-3/sites2.txt";
                pathIp = "/home/abraaolima/Faculdade/Ed2/pratica-3/ip2.txt";
                qntVc++;
                break;
            }
            case 2: {
                pathSite = "/home/abraaolima/Faculdade/Ed2/pratica-3/sites3.txt";
                pathIp = "/home/abraaolima/Faculdade/Ed2/pratica-3/ip3.txt";
                qntVc++;
                break;
            }
            case 3: {
                pathSite = "/home/abraaolima/Faculdade/Ed2/pratica-3/sites4.txt";
                pathIp = "/home/abraaolima/Faculdade/Ed2/pratica-3/ip4.txt";
                qntVc++;
                break;
            }
            default:
                break;
        }
        ;
        BufferedReader buffReadSite = new BufferedReader(new FileReader(pathSite));
        BufferedReader buffReadIp = new BufferedReader(new FileReader(pathIp));
        String tempSite = "";
        String tempIp = "";

        for (int i = 0; i < 25; i++) {
            tempSite = buffReadSite.readLine();
            tempIp = buffReadIp.readLine();
            No temp = new No(tempSite, tempIp);
            tabela.inserir(temp);
        }

        buffReadSite.close();
        buffReadIp.close();

    }

    public void inserirUsuario() {
        System.out.print("Digite a URL do site a ser inserido: ");
        String site = System.console().readLine();
        System.out.print("Digite o ip do site a ser inserido: ");
        String ip = System.console().readLine();
        No temp = null;
        boolean invalido = true;
        KMP kmp = new KMP();
        for (int i = 0; i < 3; i++) {
            if (kmp.KMPSearch(padrao[i], site))
                invalido = false;
        }
        if (invalido) {
            System.out.println("Site Inválido!");
            return;
        }

        temp = tabela.buscar(site);

        if (temp == null) {
            temp = new No(site, ip);
            tabela.inserir(temp);
        } else {
            System.out.println("Site Já Existente!");
            return;
        }
    }

    public void buscar() {
        System.out.print("Digite a URL do site que deseja buscar: ");
        String site = System.console().readLine();
        boolean invalido = true;
        KMP kmp = new KMP();
        for (int i = 0; i < 3; i++) {
            if (kmp.KMPSearch(padrao[i], site))
                invalido = false;
        }
        if (invalido) {
            System.out.println("Site Inválido!");
            return;
        }

        No temp = tabela.buscar(site);

        if (temp != null) {
            System.out.println("Site: " + temp.chave);
            System.out.println("IP: " + temp.valor);
        } else
            System.out.println("Site não encontrado!");

    }

    public void atualizar() {
        System.out.print("Digite a URL do site a ser atualizado: ");
        String site = System.console().readLine();
        System.out.print("Digite o novo IP do site: ");
        String ip = System.console().readLine();
        No temp = tabela.buscar(site);
        if (temp != null) {
            temp.valor = ip;
            tabela.atualizar(temp);
            System.out.println("IP atualizado!");
        } else
            System.out.println("Site não encontrado!");
    }

    public void remover() {
        System.out.print("Digite a URL do site a ser removido: ");
        String site = System.console().readLine();
        No temp = tabela.buscar(site);
        if (temp != null) {
            tabela.remover(site);
            System.out.println("Site removido!");
        } else
            System.out.println("Site não encontrado!");
    }

    public void getTotalSites() {
        System.out.println("Total de sites: " + tabela.getTotalInserido());
    }

}
