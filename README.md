Solução do desafio backend da [FCamara](https://github.com/fcamarasantos/backend-test-java?tab=readme-ov-file)

## Tecnologias

- [Spring Boot](https://spring.io/projects/spring-boot)
- [MySQL](https://www.mysql.com/)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Spring Scheduler](https://spring.io/guides/gs/scheduling-tasks)
- [JUnit5](https://junit.org/junit5/)
- [Mockito](https://site.mockito.org/)

## Como Executar

- Clonar repositório git
```
git clone git@github.com:igorbarret0/FCamara-challenge.git
```

- Construir o projeto:
```
./mvnw clean package
```

A API poderá ser acessada em [localhost:8080](http://localhost:8080).

## API Endpoints

Para fazer as requisições HTTP abaixo, foi utilizada a ferramenta [Postman](https://www.postman.com/)

-  ESTABLISHMENT
```
POST /establishment - Registrar estabelecimento

{
    "name": "Establishment 2",
    "CNPJ": "111111",
    "address": "other address",
    "phone": "123131237",
    "carParkingSpaces": 24,
    "motorcycleParkingSpaces": 15
}
```

```
GET /establishment - Obter todos os estabelecimentos
```

```
PUT /establishment/{establishmentId} - Atualizar estabelecimento

{
    "name": "name updated",
    "address": "",
    "phone": "",
    "addCarParkingSpaces": 24,
    "addMotorcycleParkingSpaces": 15
}
```

```
DELETE /establishment/{establishmentId} - Deletar um estabelecimento
```

- VEHICLE
```
POST /vehicle/{establishmentId} - Registrar um veiculo

{
    "brand": "brand",
    "model": "model",
    "color": "color",
    "plate": "some-plate",
    "type": "MOTORCYCLE"
}
```

```
PUT /vehicle/{establishmentId} - Atualizar um veiculo

{
    "brand": "brand updated",
    "model": "model updated",
    "color": "color",
    "plate": "some-plate",
    "type": "MOTORCYCLE"
}
```

```
GET /vehicle - Obter todos os veiculos
```

```
DELETE /vehicle/{vehicleId} - Deletar um veiculo
```
