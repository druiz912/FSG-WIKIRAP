## What is this project?
Wikirap is a project with Spring Boot v2.7.4 with the next dependencies:
+ [Spring Starter Web](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web)
+ [Spring Starter Validation](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-validation)
+ [Spring Security](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-security/3.0.0)
  + [JWT](https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-api/0.11.5)
+ [MySQL Driver]() 
+ [Lombok](https://mvnrepository.com/artifact/org.projectlombok/lombok/1.18.24)
+ [MapStruct](https://mvnrepository.com/artifact/org.mapstruct/mapstruct)
+ [SpringFox( Swagger )](https://mvnrepository.com/artifact/io.springfox/springfox-swagger2)

## Endpoints

### Person
- Person is a model with a one relationship:
  + @OneToOne with Artist


| Method | URL                 | DESCRIPTION            |
|--------|---------------------|------------------------|
| POST   | /person             | Add a person           |
| POST   | /person/add/list    | Add a multiple persons |
| GET    | /person             | Get all Persons        |
| GET    | /person/name/{name} | Get a Person by Name   |
| GET    | /person/id/{id}     | Get a Person by ID     |
| PUT    | /person/id/{id}     | Update a Person by ID  |
| DELETE | /person/id/{id}     | Delete a Person by ID  |



### Artist
- Artist is a model with two relationships:
  + @OneToOne with Person
  + @OneToMany with Album


| Method | URL                 | DESCRIPTION            |
|--------|---------------------|------------------------|
| POST   | /artist             | Add a artist           |
| POST   | /artist/add/list    | Add a multiple artists |
| GET    | /artist             | Get all Artists        |
| GET    | /artist/aka/{apodo} | Get a Person by A.K.A  |
| GET    | /artist/id/{id}     | Get a Artist by ID     |
| PUT    | /artist/id/{id}     | Update a Artist by ID  |
| DELETE | /artist/id/{id}     | Delete a Artist by ID  |

### Album
- Album is a model with two relationships:
  + @ManyToOne with Artist
  + @OneToMany with Song


| Method | URL                  | DESCRIPTION           |
|--------|----------------------|-----------------------|
| POST   | /album               | Add a Album           |
| POST   | /album/add/list      | Add a multiple Albums |
| GET    | /album               | Get all Albums        |
| GET    | /album/title/{title} | Get a Album by title  |
| GET    | /album/id/{id}       | Get a Album by ID     |
| PUT    | /album/id/{id}       | Update a Album by ID  |
| DELETE | /album/id/{id}       | Delete a Album by ID  |


### Song
- Song is a model only with one relationship:
  + @ManyToOne with Album


| Method | URL                 | DESCRIPTION          |
|--------|---------------------|----------------------|
| POST   | /song               | Add a Song           |
| POST   | /song/add/list      | Add a multiple Songs |
| GET    | /song               | Get all Songs        |
| GET    | /song/title/{title} | Get a Song by title  |
| GET    | /song/id/{id}       | Get a Song by ID     |
| PUT    | /song/id/{id}       | Update a Song by ID  |
| DELETE | /song/id/{id}       | Delete a Song by ID  |

