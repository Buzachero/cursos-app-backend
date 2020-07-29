# Aplicação backend de gerenciamento de cursos
Aplicação que gerencia informações de cursos

## Tecnologias utilizadas

* [Spring Boot](https://spring.io/projects/spring-boot)
* [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
* [JUnit 5](https://junit.org/junit5/)
* [H2](https://www.h2database.com/html/main.html)
* [Swagger](https://swagger.io/)

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





