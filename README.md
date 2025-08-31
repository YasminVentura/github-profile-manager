# Desafio Técnico: Serviço de Gerenciamento de Perfis do GitHub 

___

## Descrição
Este projeto implementa um serviço em Spring Boot para o gerenciamento de usuários do GitHub, disponibilizando APIs REST para controle de perfis

### Tecnologias utilizadas
**Principais:**
- Java 21
- Spring Boot 3.5
- PostgreSQL
- Spring Security
- JWT (JSON Web Token)

**Extras:**
- Flyway
- MapStruct
- Docker & Docker Compose

### Como executar

1. Clone o repositório:
   ```bash
   git clone https://github.com/YasminVentura/github-profile-manager.git
   cd github-profile-manager
    ```

2. Execute a aplicação com Docker Compose:

   ```bash
   docker-compose up --build
   ```

A API estará disponível em `http://localhost:8080`.

### Como testar a API

1. Importe a collection do Postman incluída (`gh-profile-manager.postman_collection.json`) e o arquivo de ambiente (`env.postman_environment.json`).

2. Antes de testar os endpoints, é necessário se autenticar:

    * Acesse a pasta `auth` na collection e efetue o login.
    * Copie o token JWT retornado.

3. No Postman, vá em **Authorization**, selecione o tipo **Bearer Token** e cole o token JWT obtido.

#### Endpoints protegidos

* `GET /api/v1/users/all` — Visualizar os usuários salvos no banco.
* `POST /api/v1/roles` — Criar uma nova role.
* `PATCH /api/v1/users/{{userId}}/roles?role=` — Atribuir uma role a um usuário.
