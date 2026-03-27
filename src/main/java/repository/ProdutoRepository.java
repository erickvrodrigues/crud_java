package com.exemple.repository;

import com.exemple.database.DatabaseConnection;
import com.exemple.model.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoRepository implements Repository<Produto>{

    private final Connection connection;

    public ProdutoRepository() throws SQLException {
        this.connection = DatabaseConnection.getInstance().getInstance().getConnection();

        criarTabelaSeNaoExistir();
    }

    private void criarTabelaSeNaoExistir() throws SQLException {

        String sql = """
                CREATE TABLE IF NOT EXISTS produtos (
                    id          SERIAL PRIMARY KEY,
                    nome        VARCHAR(100) NOT NULL,
                    preco       NUMERIC(10,2) NOT NULL CHECK (preco >= 0),
                    quantidade INTEGER NOT NULL CHECK (quantidade >= 0)
                );
                """;
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        }
    }

    @Override
    public void criar(Produto produto) throws SQLException {
        String sql = "INSERT INTO produtos (nome, preco, quantidade) VALUES (?,?,?)";

        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, produto.getNomePrd());
            ps.setDouble(2, produto.getPreco());
            ps.setInt(3, produto.getQtd());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    produto.setId(rs.getInt(1));
                }
            }
        }
    }

    @Override
    public Produto buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM produtos WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearProduto(rs);
                }
            }
        }
        return null;
    }

    @Override
    public List<Produto> listarTodosPrd() throws SQLException {
        String sql = "SELECT * FROM produtos ORDER BY id";
        List<Produto> lista = new ArrayList<>();

        try (Statement stmt = connection.createStatement();
             ResultSet rs   = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(mapearProduto(rs));
            }
        }
        return lista;
    }

    @Override
    public void atualizar(Produto produto) throws SQLException {
        String sql = "UPDATE produtos SET nome = ?, preco = ?, quantidade = ? WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, produto.getNomePrd());
            ps.setDouble(2, produto.getPreco());
            ps.setInt(3, produto.getQtd());
            ps.setInt(4, produto.getId());

            int linhas = ps.executeUpdate();
            if (linhas == 0) {
                throw new SQLException("Nenhum produto encontrado com id = " + produto.getId());
            }
        }

    }

    @Override
    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM produtos WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);

            int linhas = ps.executeUpdate();
            if (linhas == 0) {
                throw new SQLException("Nenhum produto encontrado com id = " + id);
            }
        }

    }

    private Produto mapearProduto(ResultSet rs) throws SQLException {
        return new Produto(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getDouble("preco"),
                rs.getInt("quantidade")
        );
    }

}
