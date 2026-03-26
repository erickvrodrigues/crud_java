package com.exemple.repository;

import com.exemple.model.Produto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProdutoRepository implements Repository<Produto>{

    private final Connection connection;

    public ProdutoRepository() throws SQLException {
        this.connection = this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public void criar(Produto entidade) throws SQLException {

    }

    @Override
    public Produto buscarPorId(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Produto> listarTodosPrd() throws SQLException {
        return List.of();
    }

    @Override
    public void atualizar(Produto entidade) throws SQLException {

    }

    @Override
    public void deletar(int id) throws SQLException {

    }

}
