package view;

import dao.VendedorDAO;
import model.Vendedor;

import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.showInputDialog;

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
                case "venda" -> new MenuVenda().menu();
            }
        }
        while(!opcao.toLowerCase().equals("sair"));
    }

    private void inserir() {
        Vendedor vendedor = new Vendedor();
        String nome = showInputDialog("Nome");
        vendedor.setNome(nome);
        new VendedorDAO().inserir(vendedor);
    }
}
