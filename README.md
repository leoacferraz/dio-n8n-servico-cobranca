<h1 align="center">💳 Microserviço de Cobrança — API REST para Integração com n8n</h1>

<p align="center">
  API criada em Java 21 + Spring Boot para auxiliar os estudos da turma da DIO.<br>
  Permite consultar <b>clientes</b> e <b>cobranças</b> com filtro por status,<br>
  ideal para automatizações no <b>n8n</b> e workflows de automação de cobrança.
</p>

---

## 🚀 Sobre o projeto

Este microserviço foi desenvolvido com propósito educacional para demonstrar:

- Construção de uma API REST moderna usando **Java 21 + Spring Boot 4**
- Persistência de dados usando **PostgreSQL**
- Execução via **Docker Compose**
- Integração prática com o **n8n**, substituindo a API de terceiros apresentada na aula
- Geração de dados automáticos via *Database Seeder*

A API fornece **150 clientes** e **150 cobranças** com status aleatórios, preparados para consumo via n8n.

---

## 📦 Tecnologias utilizadas

- **Java 21**
- **Spring Boot 4 (Web + Data JPA)**
- **PostgreSQL 16**
- **Docker Compose**
- **HikariCP**
- **Lombok**
- **n8n (integração externa)**

---

## 🏗 Arquitetura da aplicação

```text
cobranca-cliente
├── pom.xml
├── docker-compose.yml
└── src
    └── main
        ├── java
        │   └── com.apilacf.cobranca_cliente
        │       ├── api
        │       │   ├── dto
        │       │   │   ├── ListaClientesResponse.java
        │       │   │   └── ListaCobrancasResponse.java
        │       │   ├── ClienteController.java
        │       │   └── CobrancaController.java
        │       │
        │       ├── config
        │       │   └── DatabaseSeeder.java         # Seeder que cria 150 clientes + 150 cobranças
        │       │
        │       ├── domain
        │       │   ├── Cliente.java
        │       │   ├── Cobranca.java
        │       │   ├── StatusCobranca.java        # Enum de status (PENDENTE, PAGO, etc.)
        │       │   └── TipoPagamento.java         # Enum de tipo de pagamento (PIX, BOLETO, CARTAO_CREDITO...)
        │       │
        │       ├── repository
        │       │   ├── ClienteRepository.java
        │       │   └── CobrancaRepository.java
        │       │
        │       └── CobrancaClienteApplication.java # Classe principal (Spring Boot)
        │
        └── resources
            ├── application.properties              # Configuração do datasource, JPA, etc.
            ├── static/                             # (reservado para assets estáticos, se precisar)
            └── templates/                          # (reservado para templates, se precisar)
```

---

## 🐳 Como rodar com Docker

### 1️⃣ Suba o PostgreSQL:

```bash
docker-compose up -d
```
Isso iniciará o PostgreSQL na porta 5433, com database, usuário e senha já configurados.

Em seguida, abra no IntelliJ/VSCode e clique em Run
ou rode pelo terminal:
```bash
mvn spring-boot:run
```
A API iniciará em:
```arduino
http://localhost:8080
```

📡 Endpoints disponíveis
📍 GET /clientes

Retorna uma lista de clientes.

Parâmetros opcionais:
| Param | Tipo | Descrição                             |
| ----- | ---- | ------------------------------------- |
| limit | int  | Quantidade de registros (default: 50) |

Exemplo:
```bash
GET http://localhost:8080/clientes?limit=100
```

📍 GET /cobrancas
Retorna cobranças com filtro opcional.
| Param  | Tipo | Descrição               |
| ------ | ---- | ----------------------- |
| limit  | int  | Quantidade máxima       |
| status | enum | PENDENTE, PAGO, VENCIDO |

Exemplo:
```bash
GET http://localhost:8080/cobrancas?status=PENDENTE&limit=50
```

⚙️ DTOs de resposta
Lista de clientes:
```json
{
  "clientes": [
    {
      "id": "uuid",
      "nome": "Cliente 1",
      "email": "cliente1@teste.com"
    }
  ]
}
```

Lista de cobranças:
```json
{
  "cobrancas": [
    {
      "id": "uuid",
      "valor": 129.90,
      "status": "PENDENTE",
      "vencimento": "2025-12-10"
    }
  ]
}
```

🤖 Integrando com n8n
Uma das vantagens desse microserviço é poder usá-lo localmente no n8n rodando em Docker.

1️⃣ Use host.docker.internal como base URL:
No nó HTTP Request:
```bash
http://host.docker.internal:8080/clientes
```
Ou para cobranças:
```bash
http://host.docker.internal:8080/cobrancas?status=PENDENTE
```

2️⃣ Exemplo de nó no n8n
```text
Method: GET
URL: http://host.docker.internal:8080/clientes?limit=100
```

3️⃣ Use o nó “Split Out Items”
Configure assim:
| Field To Split | Valor               |
| -------------- | ------------------- |
| clientes       | ← campo da resposta |

📸 Print do Projeto Automatização de Processos no N8N
<br><br><img width="1318" height="593" alt="image" src="https://github.com/user-attachments/assets/cc6bd3e7-ba06-40ff-8ef7-cb2b2c84394f" /><br><br>

📌 Objetivo Educacional
Este repositório foi criado para:
- Ajudar estudantes da DIO que desejam testar automações com n8n
- Permitir que a turma substitua APIs externas por um serviço local, rápido e controlado
- Demonstrar microserviço simples com boas práticas e camadas organizadas

Sinta-se à vontade para abrir Issues, enviar PR ou estudar o código 👍


⭐ Gostou do projeto?
Deixe uma ⭐ no repositório
e compartilhe com a comunidade da DIO! 🚀

---
✍️ Autor
Leonardo Ferraz
Backend Java | Spring | Microservices | n8n


