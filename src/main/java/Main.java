import database.DatabaseConnection;
import repository.ProdutoRepository;
import service.ProdutoService;
import model.Produto;

import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            // ── Setup ───────────────────────────────────
            ProdutoRepository repository = new ProdutoRepository();
            ProdutoService    service    = new ProdutoService(repository);

            System.out.println("         CRUD Java + PostgreSQL        ");

            // ── CREATE ──────────────────────────────────
            System.out.println("── CREATE ──────────────────────────────");
            Produto p1 = service.criarProduto("Notebook Gamer",  4500.00, 10);
            Produto p2 = service.criarProduto("Mouse Sem Fio",    150.00, 50);
            Produto p3 = service.criarProduto("Teclado Mecânico", 350.00, 30);

            // ── READ (listar todos) ─────────────────────
            System.out.println("\n── READ — Todos ────────────────────────");
            List<Produto> todos = service.listarTodosPrd();
            todos.forEach(System.out::println);

            // ── READ (por id) ───────────────────────────
            System.out.println("\n── READ — Por ID ───────────────────────");
            Produto encontrado = service.buscarPorId(p1.getId());
            System.out.println("Encontrado: " + encontrado);

            // ── UPDATE ──────────────────────────────────
            System.out.println("\n── UPDATE ──────────────────────────────");
            service.atualizarProduto(p2.getId(), "Mouse Bluetooth Pro", 199.90, 45);

            // ── READ após UPDATE ────────────────────────
            System.out.println("\n── READ após atualização ───────────────");
            service.listarTodosPrd().forEach(System.out::println);

            // ── DELETE ──────────────────────────────────
            System.out.println("\n── DELETE ──────────────────────────────");
            service.deletarProduto(p3.getId());

            // ── READ final ──────────────────────────────
            System.out.println("\n── READ final ──────────────────────────");
            service.listarTodosPrd().forEach(System.out::println);

            System.out.println("\n══════════════════════════════════════");
            System.out.println("         CRUD concluído com êxito!     ");
            System.out.println("══════════════════════════════════════\n");

        } catch (SQLException e) {
            System.err.println("❌ Erro de banco de dados: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("❌ Erro de validação: " + e.getMessage());
        } finally {
            try {
                DatabaseConnection.getInstance().closeConnection();
            } catch (SQLException ignored) {}
        }
    }
}