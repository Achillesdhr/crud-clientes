# Projeto Lume

Sistema de gerenciamento de clientes com autenticação JWT, desenvolvido com Spring Boot (Backend) e React (Frontend).

## 📋 Pré-requisitos

- Docker Desktop instalado e em execução
- Git (para clonar o repositório)
- Portas 3000 (frontend) e 8080 (backend) disponíveis

## 🚀 Como executar o projeto

### Usando Docker (Recomendado)

1. **Clone o repositório** (se ainda não o fez):
```bash
git clone <url-do-repositorio>
cd projeto-lume
```

2. **Execute o comando Docker Compose**:
```bash
docker-compose up --build
```

Este comando irá:
- Construir as imagens do backend e frontend
- Iniciar os containers
- O backend estará disponível em `http://localhost:8080`
- O frontend estará disponível em `http://localhost:3000`

3. **Para parar os containers**:
```bash
docker-compose down
```

### Executando localmente (sem Docker)

#### Backend

1. **Navegue até a pasta do backend**:
```bash
cd backend
```

2. **Execute o projeto com Maven** (Windows):
```bash
mvnw.cmd spring-boot:run
```

Ou (Linux/Mac):
```bash
./mvnw spring-boot:run
```

O backend estará disponível em `http://localhost:8080`

#### Frontend

1. **Navegue até a pasta do frontend**:
```bash
cd frontend
```

2. **Instale as dependências**:
```bash
npm install
```

3. **Execute o projeto**:
```bash
npm start
```

O frontend será aberto automaticamente em `http://localhost:3000`

## 📱 Como acessar a aplicação

### Frontend
- **URL**: http://localhost:3000
- A aplicação abrirá na tela de login
- Após o login, você terá acesso ao gerenciamento de clientes

### Backend API
- **URL Base**: http://localhost:8080
- **H2 Console** (Banco de dados em memória): http://localhost:8080/h2-console
  - JDBC URL: `jdbc:h2:mem:projeto-lume-db`
  - Username: `sa`
  - Password: _(deixar em branco)_

### Swagger/OpenAPI Documentation

A documentação interativa da API está disponível através do Swagger UI:

- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8080/v3/api-docs

#### Como usar o Swagger:

1. Acesse http://localhost:8080/swagger-ui.html
2. Explore os endpoints disponíveis organizados por categorias:
   - **Autenticação**: Login e refresh token
   - **Usuários**: Gerenciamento de usuários
   - **Clientes**: Gerenciamento de clientes (requer autenticação)

3. **Para testar endpoints protegidos**:
   - Primeiro, execute o endpoint `POST /auth/login` para obter o token
   - Clique no botão **"Authorize"** no topo da página (ícone de cadeado)
   - Cole o token JWT no campo (sem o prefixo "Bearer")
   - Clique em "Authorize" e depois "Close"
   - Agora você pode testar os endpoints protegidos

## 🔑 Credenciais de login

Como o sistema usa banco de dados em memória (H2), você precisará **criar um usuário** antes de fazer login.

### Criar um novo usuário

**Opção 1: Via Frontend**
1. Acesse http://localhost:3000
2. Na tela de login, clique em "Registrar" ou "Criar conta"
3. Preencha os dados:
   - Nome: `Admin`
   - Email: `admin@example.com`
   - Senha: `senha123`
4. Após o registro, faça login com as credenciais criadas

**Opção 2: Via API (usando Swagger, Postman, cURL ou Insomnia)**

```bash
curl -X POST http://localhost:8080/usuario ^
  -H "Content-Type: application/json" ^
  -d "{\"nome\": \"Admin\",\"email\": \"admin@example.com\",\"senha\": \"senha123\"}"
```

**Opção 3: Via Swagger UI**
1. Acesse http://localhost:8080/swagger-ui.html
2. Localize o endpoint `POST /usuario`
3. Clique em "Try it out"
4. Cole o seguinte JSON:
```json
{
  "nome": "Admin",
  "email": "admin@example.com",
  "senha": "senha123"
}
```
5. Clique em "Execute"

### Fazer login

Após criar o usuário, use as credenciais:
- **Email**: `admin@example.com`
- **Senha**: `senha123`

## 📚 Endpoints da API

### Autenticação

#### POST /auth/login
Realiza o login e retorna o token JWT.

**Request:**
```json
{
  "email": "admin@example.com",
  "senha": "senha123"
}
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "refreshToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "user": "admin@example.com"
}
```

#### POST /auth/refresh
Renova o token de acesso usando o refresh token.

**Request:**
```json
{
  "refreshToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

### Usuários

#### POST /usuario
Cria um novo usuário (não requer autenticação).

#### GET /usuario
Lista todos os usuários (requer autenticação).

#### GET /usuario/{id}
Busca um usuário por ID (requer autenticação).

#### GET /usuario/email/{email}
Busca um usuário por email (requer autenticação).

### Clientes

#### POST /cliente
Cria um novo cliente (requer autenticação).

#### GET /cliente
Lista todos os clientes (requer autenticação).

#### GET /cliente/{id}
Busca um cliente por ID (requer autenticação).

#### PUT /cliente/{id}
Atualiza um cliente (requer autenticação).

#### DELETE /cliente/{id}
Remove um cliente (requer autenticação).

## 🔧 Tecnologias utilizadas

### Backend
- Java 21
- Spring Boot 3.5.7
- Spring Security
- Spring Data JPA
- H2 Database (em memória)
- JWT (JSON Web Token)
- Swagger/OpenAPI 3.0
- Maven

### Frontend
- React
- React Router
- Axios
- CSS3

### DevOps
- Docker
- Docker Compose


## 📄 Licença

Este é um projeto de demonstração.

## 👥 Contribuidores

- Desenvolvido como projeto exemplo para gerenciamento de clientes

Sistema de gerenciamento de clientes com autenticação JWT, desenvolvido com Spring Boot (Backend) e React (Frontend).

## 📋 Pré-requisitos

- Docker Desktop instalado e em execução
- Git (para clonar o repositório)
- Portas 3000 (frontend) e 8080 (backend) disponíveis

## 🚀 Como executar o projeto

### Usando Docker (Recomendado)

1. **Clone o repositório** (se ainda não o fez):
```bash
git clone <url-do-repositorio>
cd projeto-lume
```

2. **Execute o comando Docker Compose**:
```bash
docker-compose up --build
```

Este comando irá:
- Construir as imagens do backend e frontend
- Iniciar os containers
- O backend estará disponível em `http://localhost:8080`
- O frontend estará disponível em `http://localhost:3000`

3. **Para parar os containers**:
```bash
docker-compose down
```

### Executando localmente (sem Docker)

#### Backend

1. **Navegue até a pasta do backend**:
```bash
cd backend
```

2. **Execute o projeto com Maven** (Windows):
```bash
mvnw.cmd spring-boot:run
```

Ou (Linux/Mac):
```bash
./mvnw spring-boot:run
```

O backend estará disponível em `http://localhost:8080`

#### Frontend

1. **Navegue até a pasta do frontend**:
```bash
cd frontend
```

2. **Instale as dependências**:
```bash
npm install
```

3. **Execute o projeto**:
```bash
npm start
```

O frontend será aberto automaticamente em `http://localhost:3000`

## 📱 Como acessar a aplicação

### Frontend
- **URL**: http://localhost:3000
- A aplicação abrirá na tela de login
- Após o login, você terá acesso ao gerenciamento de clientes

### Backend API
- **URL Base**: http://localhost:8080
- **H2 Console** (Banco de dados em memória): http://localhost:8080/h2-console
  - JDBC URL: `jdbc:h2:mem:projeto-lume-db`
  - Username: `sa`
  - Password: _(deixar em branco)_

### Swagger/OpenAPI Documentation

A documentação interativa da API está disponível através do Swagger UI:

- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8080/v3/api-docs

#### Como usar o Swagger:

1. Acesse http://localhost:8080/swagger-ui.html
2. Explore os endpoints disponíveis organizados por categorias:
   - **Autenticação**: Login e refresh token
   - **Usuários**: Gerenciamento de usuários
   - **Clientes**: Gerenciamento de clientes (requer autenticação)

3. **Para testar endpoints protegidos**:
   - Primeiro, execute o endpoint `POST /auth/login` para obter o token
   - Clique no botão **"Authorize"** no topo da página
   - Cole o token JWT no campo (sem o prefixo "Bearer")
   - Clique em "Authorize" e depois "Close"
   - Agora você pode testar os endpoints protegidos

## 🔑 Credenciais de login

Como o sistema usa banco de dados em memória (H2), você precisará **criar um usuário** antes de fazer login.

### Criar um novo usuário

**Opção 1: Via Frontend**
1. Acesse http://localhost:3000
2. Na tela de login, clique em "Registrar" ou "Criar conta"
3. Preencha os dados:
   - Nome: `Admin`
   - Email: `admin@example.com`
   - Senha: `senha123`
4. Após o registro, faça login com as credenciais criadas

**Opção 2: Via API (usando Postman, cURL ou Insomnia)**

```bash
curl -X POST http://localhost:8080/usuario \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Admin",
    "email": "admin@example.com",
    "senha": "senha123"
  }'
```

### Fazer login

Após criar o usuário, use as credenciais:
- **Email**: `admin@example.com`
- **Senha**: `senha123`

## 📚 Endpoints da API

### Autenticação

#### POST /auth/login
Realiza o login e retorna o token JWT.

**Request:**
```json
{
  "email": "admin@example.com",
  "senha": "senha123"
}
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "refreshToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "user": "admin@example.com"
}
```

#### POST /auth/refresh
Renova o token de acesso usando o refresh token.

**Request:**
```json
{
  "refreshToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

### Usuários

#### POST /usuario
Cria um novo usuário (não requer autenticação).

#### GET /usuario
Lista todos os usuários (requer autenticação).

#### GET /usuario/{id}
Busca um usuário por ID (requer autenticação).

#### GET /usuario/email/{email}
Busca um usuário por email (requer autenticação).

### Clientes

#### POST /cliente
Cria um novo cliente (requer autenticação).

#### GET /cliente
Lista todos os clientes (requer autenticação).

#### GET /cliente/{id}
Busca um cliente por ID (requer autenticação).

#### PUT /cliente/{id}
Atualiza um cliente (requer autenticação).

#### DELETE /cliente/{id}
Remove um cliente (requer autenticação).

## 🔧 Tecnologias utilizadas

### Backend
- Java 21
- Spring Boot 3.5.7
- Spring Security
- Spring Data JPA
- H2 Database (em memória)
- JWT (JSON Web Token)
- Maven

### Frontend
- React
- React Router
- Axios
- CSS3

### DevOps
- Docker
- Docker Compose

## 📝 Estrutura do projeto

```
projeto-lume/
├── backend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/example/projeto_lume/
│   │   │   │       ├── config/         # Configurações (CORS, Security)
│   │   │   │       ├── controller/     # Controllers REST
│   │   │   │       ├── dto/            # Data Transfer Objects
│   │   │   │       ├── model/          # Entidades JPA
│   │   │   │       ├── repository/     # Repositórios JPA
│   │   │   │       ├── service/        # Lógica de negócio
│   │   │   │       └── token/          # JWT Provider e Filter
│   │   │   └── resources/
│   │   │       └── application.properties
│   │   └── test/
│   ├── Dockerfile
│   └── pom.xml
├── frontend/
│   ├── public/
│   ├── src/
│   │   ├── components/     # Componentes React
│   │   └── services/       # Serviços de API
│   ├── Dockerfile
│   └── package.json
├── docker-compose.yml
└── README.md
```

## 🐛 Solução de problemas

### Erro: "Port is already in use"
- Certifique-se de que as portas 3000 e 8080 não estão sendo usadas por outros aplicativos
- No Windows, use `netstat -ano | findstr :8080` para verificar

### Erro: "Docker Desktop is not running"
- Inicie o Docker Desktop antes de executar o `docker-compose`

### Erro ao fazer login
- Verifique se você criou um usuário antes de tentar fazer login
- O banco de dados H2 é em memória, então os dados são perdidos quando o backend é reiniciado

### Frontend não consegue conectar ao backend
- Verifique se o backend está rodando em `http://localhost:8080`
- Verifique as configurações de CORS no backend

## 📄 Licença

Este é um projeto de demonstração.

## 👥 Contribuidores

- Desenvolvido como projeto exemplo para gerenciamento de clientes

