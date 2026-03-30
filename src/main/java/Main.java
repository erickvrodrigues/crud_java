/*import database.DatabaseConnection;

public class Main {

    public static void main(String[] args) {

        try {

            DatabaseConnection db =
                    DatabaseConnection.getInstance();

            System.out.println("Conectado com sucesso!");

            db.closeConnection();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}*/

import database.DatabaseConnection;
import repository.ProdutoRepository;
import service.ProdutoService;
import model.Produto;

import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {

            ProdutoRepository repository = new ProdutoRepository();
            ProdutoService    service    = new ProdutoService(repository);

            System.out.println("         CRUD Java + PostgreSQL        ");

            System.out.println("/////////////////// CREATE ///////////////////");
            Produto p1 = service.criarProduto("Notebook Gamer",  4500.00, 10);
            Produto p2 = service.criarProduto("Mouse Sem Fio",    150.00, 50);
            Produto p3 = service.criarProduto("Teclado Mecânico", 350.00, 30);

            System.out.println("\n////////////////////////  Todos ////////////////////////");
            List<Produto> todos = service.listarTodosPrd();
            todos.forEach(System.out::println);

            System.out.println("\n/////////////////// Por ID //////////////////////");
            Produto encontrado = service.buscarPorId(p1.getId());
            System.out.println("Encontrado: " + encontrado);

            System.out.println("\n/////////////////////// UPDATE ////////////////////////////");
            service.atualizarProduto(p2.getId(), "Mouse Bluetooth Pro", 199.90, 45);

            System.out.println("\n//////////////////// após atualização /////////////////////////");
            service.listarTodosPrd().forEach(System.out::println);


            System.out.println("\n//////////////////////////////// DELETE ///////////////////////////////////////");
            service.deletarProduto(p3.getId());

            System.out.println("\n//////////////////////////////// final ////////////////////////////////");
            service.listarTodosPrd().forEach(System.out::println);

            System.out.println("//////////// CRUD concluído com êxito! /////////////////");

        } catch (SQLException e) {
            System.err.println("Erro no banco de dados: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Erro de validação: " + e.getMessage());
        } finally {
            try {
                DatabaseConnection.getInstance().closeConnection();
            } catch (SQLException ignored) {}
        }
    }
}