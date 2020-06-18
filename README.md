# Aplicação backend de gerenciamento de cursos
Aplicação que gerencia informações de cursos

## Tecnologias utilizadas

* [Spring Boot](https://spring.io/projects/spring-boot)
* [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
* [JUnit 5](https://junit.org/junit5/)
* [H2](https://www.h2database.com/html/main.html)

## Requerimentos

Para realizar o build do projeto, é necessário:
* JDK 1.8
* Maven

## Parâmetros do Projeto

A configuracão da aplicação pode ser realizada em dois arquivos:
1. *application.properties* : parametros para execução da aplicação para testes manuais com o frontend.
2. *application-test.properties*  : parametros para execução dos testes integrados

## Execução dos testes

Para execução dos testes integrados:
```
mvn clean test
```

## Uso da aplicação

Para obter os dados de todos os cursos inicialmente populados:
```
GET /cursos
```
Para obter os dados de determinado curso pelo identificador:
```
GET /cursos/id/{id}
```
Para obter os dados de determinado curso pela descrição:
```
GET /cursos/descricao/{descricao}
```
Para adicionar um curso:
```
POST /cursos
```
Para remover um curso:
```
DELETE /cursos/{id}
```
Para atualizar as informações de um curso:
```
PUT /cursos/id/{id}
```




