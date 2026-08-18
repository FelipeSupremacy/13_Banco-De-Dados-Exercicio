package factory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class ConnectionFactory {

    private static final Properties PROPS = carregarPropriedades();

    private ConnectionFactory() { } // impede a instanciação

    public static Connection obterConexao() throws SQLException {
        return DriverManager.getConnection(
                PROPS.getProperty("db.url"),
                PROPS.getProperty("db.user"),
                PROPS.getProperty("db.password"));
    }

    private static Properties carregarPropriedades() {
        Properties props = new Properties();
        try (InputStream in = ConnectionFactory.class
                .getClassLoader()
                .getResourceAsStream("database.properties")) {
            if (in == null) {
                throw new IllegalStateException(
                        "Arquivo não encontrado.");
            }
            props.load(in);
            return props;
        } catch (IOException e) {
            throw new IllegalStateException("Falha na leitura.", e);
        }
    }
}
