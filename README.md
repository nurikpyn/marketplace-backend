# Projeto Marketplace Backend

  Este projeto faz parte da prova de conceito da conclusão do curso de pós-graduação em Arquitetura de Sistemas Distribuídos. Ele contempla o desenvolvimento do backend da aplicação.

## Objetivo

  A prova de conceito fui desenvolvida para apresentar a solução proposta para algumas restrições arquiteturais do projeto.

## Tecnologias Utilizadas

* Git
* Java 8
* Spring Boot
* Spring Data
* Spring Data ElasticSearch
* Spring Security
* Spring Web Services
* JWT
* Lombok
* Flyway
* SpringFox Swagger
* MySQL
* ElasticSearch
* Kibana
* Docker
* Docker Compose

## Pré Requisitos para executar o projeto

  A máquina deve possuir git, docker e docker compose instalados.

## Executando o projeto

* Efetue o clone do projeto através do comando: ```git clone https://github.com/viniciusufop/marketplace-backend.git```
* Acesse a pasta marketplace-backend criada.
* Execute o comando ```docker-compose up -d```
* O Docker vai subir todos os container do projeto.

## Estrutura de containers do projeto

  O arquivo docker-compose está configurado para criar as netwoks abaixo:

* net-backend
* net-frontend 

  O arquivo docker-compose está configurado para subir os containers abaixo:

* Container MySQL exposto na porta 3306 (associado a net-backend)
* Container ElasticSearch exposto nas portas 9200 e 9300 (associado a net-backend)
* Container Kibana exposto na porta 5106 (associado a net-backend)
* Container da aplicação marketplace-backend exposto na porta 9000 (associado a net-backend e net-frontend)
