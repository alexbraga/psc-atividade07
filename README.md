<h1 align="center">
  Sistema CRUD com Persistência em Arquivo
</h1>

<h3 align="center">
    Atividade Avaliativa 7 da disciplina de Programação de Soluções Computacionais
</h3>

<p align="center">
  <a href="https://github.com/alexbraga/psc-atividade07/commits/master"><img alt="GitHub last commit" src="https://img.shields.io/github/last-commit/alexbraga/psc-atividade07"></a>
</p>

<h4 align="center">
	 Status: Concluído
</h4>

<p align="center">
 <a href="#sobre">Sobre</a> •
 <a href="#instruções-da-atividade">Instruções da Atividade</a> •
 <a href="#características-técnicas">Características Técnicas</a> •
 <a href="#como-funciona">Como Funciona</a> •
 <a href="#critérios-de-avaliação">Critérios de Avaliação</a> •
 <a href="#unidade-curricular">Unidade Curricular</a> •
 <a href="#autor">Autor</a>
</p>

## Sobre

<p align="justify">Esta atividade tem como objetivo desenvolver as habilidades em programação Java, especificamente na criação de um programa do tipo console com um menu baseado em números e operações CRUD (Create, Read, Update, Delete). Além disso, será praticada a persistência de dados em um arquivo de texto.

A entidade escolhida para ser modelada foi um livro, incluindo um registro completo com título, autor, gênero, editora, edição, número de páginas e ISBN-13.</p>

---

## Instruções da Atividade

1. **Criar um programa Java do tipo console:** Este programa deve apresentar ao usuário um menu baseado em números, onde cada número representa uma ação diferente que o usuário pode realizar.

2. **Implementar operações CRUD:** O usuário deve ser capaz de criar, ler, atualizar e deletar registros de uma entidade.

3. **Persistir os dados em um arquivo de texto:** Sempre que uma operação CRUD for realizada, o estado atual dos dados deve ser salvo em um arquivo de texto. Da mesma forma, sempre que o programa for iniciado, ele deve carregar os dados a partir deste arquivo.

---

## Características Técnicas

A presente aplicação implementa os seguintes padrões de projeto e arquitetura:

- MVC (Model-View-Controller)
- Singleton
- DAO (Data Access Object)
- Service Layer

O SRP (Princípio da Responsabilidade Única) foi fortemente seguido em toda a aplicação.

---

## Como Funciona

1. <a href="#clonando-o-repositório">Clonando o repositório</a>
2. <a href="#executando-a-aplicação">Executando a aplicação</a>
3. <a href="#inserindo-dados-prontos">Inserindo dados prontos</a>

#### Pré-requisitos

Antes de começar, você precisará ter as seguintes ferramentas instaladas em sua máquina:

- [Git](https://git-scm.com)
- [Java JDK 17](https://www.oracle.com/java/technologies/downloads/)
- [Maven](https://maven.apache.org/)

Além disso, você talvez deseje utilizar uma IDE para trabalhar com o código, tal como o
[IntelliJ IDEA](https://www.jetbrains.com/idea/).

#### Clonando o repositório

Abra uma janela do terminal e execute o seguinte comando
```
git clone https://github.com/alexbraga/psc-atividade07.git
```

#### Executando a aplicação

Navegue até a raiz do projeto
```
cd psc-atividade07
```

Compile o código
```
mvn compile
```

Execute a aplicação
```
java -cp target/classes org.example.Main
```
- ATENÇÃO: para executar a aplicação no Windows use `java -cp target\classes org.example.Main`

- Alternativamente, abra `psc-atividade07` com sua IDE de preferência e execute `Main.java`

#### Inserindo dados prontos (opcional)

O programa irá criar um arquivo em branco `books.txt` na raíz do diretório em sua primeira utilização. A seguinte lista de livros pode ser utilizada como ponto de partida para testar algumas funcionalidades mais rapidamente sem a necessidade de incluir vários registros de forma manual:

```
Title: The Lord of the Rings; Author: J.R.R. Tolkien; Genre: High Fantasy; Publisher: George Allen & Unwin; Edition: 1; Number of Pages: 1178; ISBN-13: 9780618640157
Title: Dune; Author: Frank Herbert; Genre: Science Fiction; Publisher: Chilton Books; Edition: 1; Number of Pages: 412; ISBN-13: 9780441172719
Title: Foundation; Author: Isaac Asimov; Genre: Science Fiction; Publisher: Gnome Press; Edition: 1; Number of Pages: 255; ISBN-13: 9780553293357
Title: Neuromancer; Author: William Gibson; Genre: Cyberpunk; Publisher: Ace Books; Edition: 1; Number of Pages: 271; ISBN-13: 9780441569595
Title: Snow Crash; Author: Neal Stephenson; Genre: Science Fiction; Publisher: Bantam Books; Edition: 1; Number of Pages: 470; ISBN-13: 9780553380958
```

---

## Critérios de Avaliação

- **Correção do código:** O código deve compilar sem erros e realizar as operações CRUD conforme esperado.

- **Design do código:** O código deve ser bem estruturado, com métodos claros e concisos, e variáveis adequadamente nomeadas.

- **Persistência de dados:** O programa deverá, corretamente, salvar os dados em um arquivo e carregá-los quando iniciado.

---

## Unidade Curricular

### Programação de Soluções Computacionais
  - #### Centro Universitário de Belo Horizonte (UniBH)

#### Professores
  - Alexandre "Montanha" de Oliveira
  - Flávio Henrique Batista de Souza

---

## Autor

<h4>Alexandre Braga</h4>

<div>
<a href="https://www.linkedin.com/in/alexgbraga/" target="_blank"><img src="https://img.shields.io/badge/-LinkedIn-blue?style=for-the-badge&logo=Linkedin&logoColor=white" alt="LinkedIn"></a>&nbsp;
<a href="mailto:contato@alexbraga.com.br" target="_blank"><img src="https://img.shields.io/badge/-email-c14438?style=for-the-badge&logo=Gmail&logoColor=white" alt="E-Mail"></a>
</div>