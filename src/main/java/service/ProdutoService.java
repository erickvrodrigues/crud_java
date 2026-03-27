package com.exemple.service;

import com.exemple.model.Produto;
import com.exemple.repository.ProdutoRepository;

import java.sql.SQLException;
import java.util.List;

public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public Produto criarProduto(String nomePrd, double preco, int qtd) throws SQLException {
         if (preco == 0 && qtd == 0) {
             throw new IllegalArgumentException("Produto com preço e quantidade zerados não é permitido.");
         }
         Produto produto = new Produto(nomePrd, preco, qtd);
         repository.criar(produto);
        System.out.println("Produto criado: " + produto);
        return produto;
    }

    public Produto buscarPorId(int id) throws SQLException {
        Produto produto = repository.buscarPorId(id);
        if (produto == null) {
            System.out.println("Produto com id= " + id + " não encontrado.");
        }
        return produto;
    }

    public List<Produto> listarTodosPrd() throws SQLException {
        List<Produto> lista = repository.listarTodosPrd();
        System.out.println("Total de produtos: " + lista.size());
        return lista;
    }

    public void atualizarProduto(int id, String novoNome, double novoPreco, int novaQtd)
        throws SQLException {

        Produto produto = repository.buscarPorId(id);
        if (produto == null){
            throw new IllegalArgumentException("Produto com id= " + id + " não existe");
        }
        produto.setNomePrd(novoNome);
        produto.setPreco(novoPreco);
        produto.setQtd(novaQtd);

        repository.atualizar(produto);
        System.out.println("Produto atualizado: " + produto);
    }

    public void deletarProduto(int id) throws SQLException {
        repository.deletar(id);
        System.out.println("Produto id=" +id+ " removido.");
    }

}
