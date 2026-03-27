package com.exemple.repository;

import java.sql.SQLException;
import java.util.List;

public interface Repository<T>{

    void criar (T entidade)        throws SQLException;
    T    buscarPorId(int id)       throws SQLException;
    List<T> listarTodosPrd()       throws SQLException;
    void atualizar(T entidade)     throws SQLException;
    void deletar(int id)           throws SQLException;

}
