# Sistema de Vendas - Backend

Este é o backend de um sistema de vendas desenvolvido em Java, utilizando Spring Boot, Hibernate, e MongoDB, com serviços separados em microsserviços e gerenciados em contêineres Docker. O projeto possui uma configuração de Swagger para visualização e teste dos endpoints.

## Tecnologias Utilizadas

- **Java 11**: Linguagem de programação principal.
- **Spring Boot**: Framework para desenvolvimento de aplicações web.
- **Hibernate**: ORM para gerenciar o acesso ao banco de dados.
- **MongoDB**: Banco de dados NoSQL utilizado no microsserviço de clientes.
- **Swagger**: Interface para documentação e teste dos endpoints REST.
- **Docker**: Contêineres para garantir consistência e fácil implantação.

## Arquitetura

O projeto está dividido em três microsserviços principais:

1. **Cliente Service**: Gerencia informações dos clientes, utilizando MongoDB.
2. **Produto Service**: Gerencia dados dos produtos.
3. **Venda Service**: Responsável pela lógica de vendas e gestão de pedidos.

Cada serviço está empacotado em uma imagem Docker, o que permite o isolamento e a escalabilidade de cada componente.

## Pré-requisitos

- **Docker**: Para rodar os microsserviços.
- **Java 11** ou superior: Necessário para desenvolvimento.
- **MongoDB**: Banco de dados NoSQL, necessário para o microsserviço de clientes.

## Documentação e Testes dos Endpoints

Para visualizar e testar os endpoints, acesse o Swagger configurado em cada microsserviço. Em um navegador, vá para:

### Vendas
- [http://localhost:8083/swagger-ui/index.html](http://localhost:8083/swagger-ui/index.html#/)

### Produtos
- [http://localhost:8082/swagger-ui/index.html](http://localhost:8082/swagger-ui/index.html#/)

### Clientes
- [http://localhost:8081/swagger-ui/index.html](http://localhost:8081/swagger-ui/index.html#/)

## Estrutura de Arquivos

- `venda-service/`: Código para o serviço de vendas.
- `produto-service/`: Código para o serviço de produtos.
- `cliente-service/`: Código para o serviço de clientes.
- `config-service/`: Código para a configuração dos microsserviços.
- `service-discovery/`: Código para o configuração do servidor.
- `docker-compose.yml`: Arquivo de configuração do Docker Compose para subir todos os serviços.

## Licença

Este projeto é licenciado sob a [MIT License](LICENSE).
