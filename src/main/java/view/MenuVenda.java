package view;

import dao.VendedorDAO;
import model.Vendedor;

import java.util.List;

import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.showInputDialog;

public class MenuVenda {
    public void menu() {
        String[] item = {"Inserir", "Pesquisar", "Listar",
                "Atualizar", "Excluir", "Sair"};
        String opcao;

        do {
            opcao = (String) showInputDialog(null,
                    "Selecione uma opção",
                    "=== MENU VENDA ===",
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
        List<Vendedor> lista = new VendedorDAO().listar();

        vendedor = (Vendedor) showInputDialog(null,
                "Selecione uma opção",
                "=== MENU VENDA ===",
                INFORMATION_MESSAGE,
                null,
                lista.toArray(),
                lista.get(0));
    }
}
