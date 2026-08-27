package dao;

import factory.ConnectionFactory;
import model.Venda;
import model.Vendedor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VendaDAO implements GenericDAO<Venda, Integer> {
    @Override
    public void inserir(Venda entidade) {
        String sql = "Insert into java_venda(id_vendedor, total, data) values(?, ?, ?)";
        try(Connection connection = ConnectionFactory.obterConexao();
            PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, entidade.getVendedor().getId());
            ps.setDouble(2, entidade.getTotal());
            ps.setDate(3, Date.valueOf(entidade.getData()));
            ps.execute();

        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Venda> listar() {
        return List.of();
    }

    public List<Venda> relatorio() {
        List<Venda> lista = new ArrayList<>();
        String sql = "select v.nome, vd.total, vd.data " +
                     "from java_vendedor v " +
                     "inner join java_venda vd " +
                     "on v.id = vd.id_vendedor " +
                     "order by v.nome asc;" ;
        try (Connection connection = ConnectionFactory.obterConexao();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Vendedor vendedor = new Vendedor();
                Venda venda = new Venda();
                vendedor.setNome(rs.getString("nome"));
                venda.setTotal(rs.getDouble("total"));
                venda.setData(rs.getDate("date").toLocalDate());
                venda.setVendedor(vendedor);
                lista.add(venda);
            }

        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lista;

    }
}
