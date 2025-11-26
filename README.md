# 🛒 API de E-commerce (Spring Boot)

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white)

Projeto de estudo focado no desenvolvimento de uma API RESTful robusta para um sistema de E-commerce, aplicando boas práticas de arquitetura e regras de negócio complexas.

---

## 🚀 Tecnologias Utilizadas

* **Java 21**
* **Spring Boot 3**
* **Spring Data JPA (Hibernate)**
* **H2 Database** (Banco em memória para testes rápidos)
* **Maven** (Gerenciamento de dependências)
* **Lombok** (Redução de boilerplate)

## 🏗️ Arquitetura e Padrões

O projeto segue a arquitetura em camadas (**Layered Architecture**) para garantir a separação de responsabilidades e facilidade de manutenção:

* **Controller:** Camada REST que recebe as requisições HTTP e gerencia os DTOs.
* **Service:** Camada de lógica de negócio, transações e validações (`@Service`, `@Transactional`).
* **Repository:** Camada de acesso a dados (`JpaRepository`).
* **Model:** Entidades JPA mapeadas para o banco de dados.
* **Handler:** Tratamento global de exceções (`@ControllerAdvice`) para respostas de erro limpas e padronizadas.

## ✨ Funcionalidades Principais

### 📦 Gestão de Pedidos
* Criação de pedidos com múltiplos itens.
* **Regra de Negócio:** Validação automática de estoque antes da venda.
* **Regra de Negócio:** Baixa automática de estoque após confirmação.
* **Regra de Negócio:** Cálculo automático do valor total do pedido no Back-End.
* Uso de chave composta (`@EmbeddedId`) para a relação Pedido-Produto.

### 👤 Gestão de Clientes
* CRUD completo.
* Validação de CPF duplicado.

### 🏷️ Gestão de Produtos
* CRUD completo.

### 🛡️ Tratamento de Erros
* Retornos HTTP adequados e semânticos (400 Bad Request, 404 Not Found, 204 No Content).

---

## 🔌 Como Testar (Endpoints)

A aplicação roda por padrão em `http://localhost:8080`. Você pode utilizar ferramentas como Postman ou Insomnia para realizar as requisições.

### 1. Criar Cliente
**POST** `/clientes`

```json
{
  "nome": "Dev Java",
  "cpf": "12345678900"
}
```

### 2. Criar Produto
**POST** `/produtos`

```json
{
  "nome": "Notebook Gamer",
  "descricao": "i7, 16GB RAM",
  "preco": 5000.00,
  "qntdEstoque": 10
}
```

### 3. Realizar Pedido (A Mágica 🪄)
**POST** `/pedidos`

Neste endpoint, o sistema valida o estoque, calcula o total e atualiza a quantidade disponível do produto.

```json
{
  "cliente": { "cpf": "12345678900" },
  "itens": [
    {
      "id": { "produtoId": 1 },
      "quantidade": 2
    }
  ]
}
```

> **Resultado Esperado:** O sistema retornará o pedido com o valor total calculado (ex: **R$ 10.000,00**) e o estoque do produto será reduzido no banco de dados.

---

## 💻 Como Rodar o Projeto

1. Clone o repositório:
   ```bash
   git clone [https://github.com/raoni-silla/api-ecommerce-springboot.git](https://github.com/raoni-silla/api-ecommerce-springboot.git)
   ```
2. Acesse a pasta do projeto:
   ```bash
   cd api-ecommerce-springboot
   ```
3. Execute com Maven:
   ```bash
   mvn spring-boot:run
   ```

---

## 👨‍💻 Autor

Desenvolvido por **[Raoni Silla](https://github.com/raoni-silla)** durante estudos de aprofundamento em Spring Boot.
