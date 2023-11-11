# Sistema de crud usuários

Sistema que realiza as operaçẽs de um crud (Create, Read, Update, Delete) para usuários.

## Recursos Principais

1. **Cadastro de usuário** 

2. **Leitura de dados dos usuário** 

3. **Atualização de usuário** 

4. **deletação de usuário**
   
## Tecnologias Utilizadas

- **JAVA - SPRING BOOT**
- **DOCKER**
- **POSTGRESQL**
- **SWAGGER**
  
## Como Rodar o Projeto

Para rodar este projeto em seu ambiente local, siga estas etapas:

### Pré-requisitos

Certifique-se de ter as seguintes ferramentas instaladas em seu sistema:

- Java
- Mavem 
- Docker
- Docker Compose

### Passos de Instalação

1. Clone este repositório em seu computador

2. Configure as propriedades do banco de daods no arquivo application.properties

3. execute o contâiner do postgres

   ```bash
   docker-compose up -d
   
4. por fim execute o projeto

### Swagger

Para visualizar a documentação da api acesse:

```bash
   http://localhost:8080/swagger-ui/index.html

Você irá visualizar user-controller com os endpoints da api
