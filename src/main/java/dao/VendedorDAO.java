package dao;

import factory.ConnectionFactory;
import model.Vendedor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VendedorDAO implements GenericDAO<Vendedor, Integer> {
    @Override
    public void inserir(Vendedor entidade) {
        String sql = "Insert into java_vendedor(nome) values(?)";

        try(Connection connection = ConnectionFactory.obterConexao();
            PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, entidade.getNome());
            ps.execute(); // ps.executeUpdate();
        }
        catch(SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public List<Vendedor> listar() {
        List<Vendedor>lista = new ArrayList<>();
        String sql = "select * from java_vendedor";
        try(Connection connection = ConnectionFactory.obterConexao();
            PreparedStatement ps = connection.prepareCall(sql);
            ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Vendedor vendedor = new Vendedor();
                vendedor.setId(rs.getInt("id"));
                vendedor.setNome(rs.getString("Nome"));
                lista.add(vendedor);
            }
        }
        catch(SQLException e){

        }
        return lista;
    }

    public void atualizar(Vendedor vendedor) {
        String sql = "update java_vendedor set nome = ? where id = ?";
        try(Connection connection = ConnectionFactory.obterConexao();
            PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, vendedor.getNome());
            ps.setInt(2, vendedor.getId());
            ps.execute(); // ps.executeUpdate();
        }
        catch(SQLException e) {
            System.out.println(e);
        }
    }

    public void excluir(Integer id) {
        String sql = "delete from java_vendedor where id = ?";
        try(Connection connection = ConnectionFactory.obterConexao();
            PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.execute(); // ps.executeUpdate();
        }
        catch(SQLException e) {
            System.out.println(e);
        }
    }
}
