package view;

import dao.VendedorDAO;
import model.Vendedor;

import java.util.List;

import static javax.swing.JOptionPane.*;

public class MenuVendedor {
    public void menu() {
        String[] item = {"Inserir", "Pesquisar", "Listar",
                         "Atualizar", "Excluir", "Sair"};
        String opcao;

        do {
            opcao = (String) showInputDialog(null,
                    "Selecione uma opção",
                    "=== MENU VENDEDOR ===",
                    INFORMATION_MESSAGE,
                    null,
                    item,
                    item[0]);
            switch(opcao.toLowerCase()) {
                case "inserir" -> inserir();
                case "listar" -> listar();
            }
        }
        while(!opcao.toLowerCase().equals("sair"));
    }

    private void listar() {
        List<Vendedor> lista = new VendedorDAO().listar();
        String aux = "";
        for(Vendedor vendedor : lista) {
            aux += vendedor.getId() + "    | " + vendedor.getNome() + "\n";
        }
        showMessageDialog(null, aux);
    }

    private void inserir() {
        Vendedor vendedor = new Vendedor();
        String nome = showInputDialog("Nome");
        vendedor.setNome(nome);
        new VendedorDAO().inserir(vendedor);
    }
}
