
[JAVA_BADGE]:https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white
[SPRING_BADGE]: https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white


<h1 align="center" style="font-weight: bold;">Challenge Fórum hub 💻</h1>




![spring][SPRING_BADGE]
![java][JAVA_BADGE]


<p align="center">
 <a href="#started">Getting Started</a> • 
  <a href="#routes">API Endpoints</a> •
 <a href="#colab">Collaborators</a> •
 <a href="#contribute">Contribute</a>
</p>

<p >
<b>
Este é o desafio chamado de FórumHub: nele, vamos replicar o processo de criação de um fórum no nível do backend e, para isso, criaremos uma API REST usando Spring.
Nossa API se concentrará especificamente nos tópicos, e deve permitir aos usuários:

É o que conhecemos normalmente como CRUD (CREATE, READ, UPDATE, DELETE)* e é muito parecido com o que desenvolvemos no LiterAlura, mas agora de forma completa, agregando as operações de UPDATE e DELETE, e usando um framework que facilitará muito o nosso trabalho.

* Tradução (em ordem): Criar, Consultar, Atualizar e Deletar.

Em resumo, nosso objetivo com este challenge é implementar uma API REST com as seguintes funcionalidades:
</b>
</p>


- Criar um novo tópico;
- Mostrar todos os tópicos criados;
- Mostrar um tópico específico;
- Atualizar um tópico;
- Eliminar um tópico.
<br> </br>
[![dqAoZ8l.md.png](https://iili.io/dqAoZ8l.md.png)](https://freeimage.host/i/dqAoZ8l)


<h2 id="started">🚀 Vamos começar</h2>


<h3>Pré-requisitos</h3>


- [Java 17](https://www.oracle.com/java/technologies/downloads/#java17)
- [MYSQL](https://www.mysql.com/downloads/)
- [Git](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git)

<h3>Clonando o repositório</h3>


```bash
git clone git@github.com:olvictor/challenge-forum_hub.git
```

<h3> Variáveis de ambiente</h2>

Use o `application.properties` como referência para criar seu arquivo de configuração  com as suas credenciais.

```yaml
spring.datasource.url=jdbc:mysql://localhost:${porta_myqsql}/${nome_database}
spring.datasource.username=${mysql_admin}
spring.datasource.password=${mysql_password}
api.security.token.secret = ${SECRET_KEY}
```

<h3>Iniciando...</h3>


```bash
cd challenge-forum-hub
``````


<h2 id="routes">📍 API Endpoints</h2>

​
| route               | description                                          
|----------------------|-----------------------------------------------------
| <kbd>POST /usuario</kbd>     | retrieves user info see [response details](#post-register-detail)
| <kbd>POST /usuario/login</kbd>     | authenticate user into the api see [request details](#post-auth-detail)
| <kbd>GET /usuario/id</kbd>     | authenticate user into the api see [request details](#post-auth-detail)
| <kbd>POST /topicos</kbd>     | authenticate user into the api see [request details](#post-auth-detail)
| <kbd>GET /topicos</kbd>     | authenticate user into the api see [request details](#post-auth-detail)
| <kbd>GET /topicos/id</kbd>     | authenticate user into the api see [request details](#post-auth-detail)
| <kbd>DEL /topicos/id</kbd>     | authenticate user into the api see [request details](#post-auth-detail)
| <kbd>PUT /topicos/id</kbd>     | authenticate user into the api see [request details](#post-auth-detail)



<h3 id="post-register-detail">POST /login</h3>

**REQUEST**
```json
{
  "nome": "nome",
  "email": "a@a.com",
  "senha": "senha"
}
```

**RESPONSE**
```json
{
  "id": 4,
  "nome": "nome",
  "email": "a@a.com"
}
```

<h3 id="post-auth-detail">POST /usuario/login </h3>

**REQUEST**
```json
{
  "usuario": "a@a.com",
  "senha": "senha"
}
```

**RESPONSE**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJmb3J1bV9odWIiLCJzdWIiOiJhQGEuY29tIiwiaWQiOjQsImV4cCI6MTcyMDczMTk4Nn0.8dJSIl-nfAnaTSoKWL7Q5AHohVs8VY1_5uV2fJ265Xs"
}
```

<h3 id="post-auth-detail">GET /usuario/id</h3>

**REQUEST**
```json
"/usuario/{id}"
```

**RESPONSE**
```json
{
	"id": 4,
	"nome": "nome",
	"email": "a@a.com"
}
```

<h2 id="colab">🤝 Colaboradores</h2>


<table>
  <tr>
    <td align="center">
      <a href="#">
        <img src="https://avatars.githubusercontent.com/u/119861032?v=4" width="100px;" alt="Vitor Oliveira Profile Picture"/><br>
        <sub>
          <b>Victor Oliveira</b>
        </sub>
      </a>
    </td>
  </tr>
</table>
