# CRUD Java + PostgreSQL com Encapsulamento

## 📁 Estrutura do Projeto

```
src/
├── Main.java                          ← Ponto de entrada
├── database/
│   └── DatabaseConnection.java        ← Singleton de conexão
├── model/
│   └── Produto.java                   ← Entidade com encapsulamento
├── repository/
│   ├── CrudRepository.java            ← Interface genérica
│   └── ProdutoRepository.java         ← Implementação com JDBC
└── service/
    └── ProdutoService.java            ← Regras de negócio
setup.sql                              ← Script de setup do banco
```

## 🏗️ Arquitetura em Camadas

| Camada       | Classe                  | Responsabilidade                        |
|-------------|-------------------------|-----------------------------------------|
| **Model**    | `Produto`               | Entidade com getters/setters validados  |
| **Repository**| `ProdutoRepository`    | Acesso direto ao banco (SQL / JDBC)     |
| **Service**  | `ProdutoService`        | Regras de negócio e orquestração        |
| **Database** | `DatabaseConnection`    | Singleton de conexão com PostgreSQL     |

## 🔒 Encapsulamento Aplicado

- Todos os atributos de `Produto` são **`private`**
- **Setters com validação** (nome não vazio, preço/quantidade não negativos)
- `DatabaseConnection` usa padrão **Singleton** com construtor privado
- `ProdutoRepository` expõe apenas a interface `CrudRepository`

## ⚙️ Pré-requisitos

- Java 17+
- PostgreSQL 13+
- Driver JDBC: [postgresql-42.x.x.jar](https://jdbc.postgresql.org/download/)

## 🚀 Como Executar

### 1. Configure o banco
```bash
psql -U postgres -f setup.sql
```

### 2. Ajuste as credenciais em `DatabaseConnection.java`
```java
private static final String URL      = "jdbc:postgresql://localhost:5432/crud_db";
private static final String USER     = "postgres";
private static final String PASSWORD = "sua_senha";   // ← altere aqui
```

### 3. Compile (com o driver JDBC no classpath)
```bash
javac -cp ".:postgresql-42.7.3.jar" -d out $(find src -name "*.java")
```

### 4. Execute
```bash
java -cp ".:out:postgresql-42.7.3.jar" Main
```

## 📦 Saída esperada

```
✅ Conexão com PostgreSQL estabelecida!
 
══════════════════════════════════════
         CRUD Java + PostgreSQL
══════════════════════════════════════
 
── CREATE ──────────────────────────────
✅ Produto criado: Produto { id=1 | nome='Notebook Gamer' | preço=R$ 4500,00 | quantidade=10 }
✅ Produto criado: Produto { id=2 | nome='Mouse Sem Fio' | preço=R$ 150,00 | quantidade=50 }
✅ Produto criado: Produto { id=3 | nome='Teclado Mecânico' | preço=R$ 350,00 | quantidade=30 }
 
── READ — Todos ────────────────────────
📦 Total de produtos: 3
Produto { id=1 | nome='Notebook Gamer' | preço=R$ 4500,00 | quantidade=10 }
...