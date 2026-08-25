package view;

import dao.VendaDAO;
import dao.VendedorDAO;
import model.Venda;
import model.Vendedor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.lang.Double.parseDouble;
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
        DateTimeFormatter mascara = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Double total;
        String data;

        vendedor = (Vendedor) showInputDialog(null,
                "Selecione uma opção",
                "=== MENU VENDA ===",
                INFORMATION_MESSAGE,
                null,
                lista.toArray(),
                lista.get(0));

        total = parseDouble(showInputDialog("Total das vendas"));
        data = showInputDialog("Data da venda (dd/mm/aaaa)");
        Venda venda = new Venda();
        venda.setTotal(total);
        venda.setVendedor(vendedor);
        venda.setData(LocalDate.parse(data, mascara));
        new VendaDAO().inserir(venda);
    }
}
