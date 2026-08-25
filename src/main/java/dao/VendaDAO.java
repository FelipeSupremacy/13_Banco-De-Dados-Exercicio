package dao;

import factory.ConnectionFactory;
import model.Venda;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
}
