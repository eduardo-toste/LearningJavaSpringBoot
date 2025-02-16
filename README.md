# ScreenMatch

## Sobre o Projeto
O ScreenMatch é uma aplicação desenvolvida em Java com Spring Boot que consome a API do OMDB para buscar informações sobre séries, temporadas e episódios. O projeto permite filtrar episódios por diferentes critérios e calcular estatísticas detalhadas.

## Funcionalidades
- Buscar séries pelo nome;
- Exibir temporadas e episódios de uma série;
- Filtrar episódios por avaliação, data de lançamento e outros critérios;
- Calcular estatísticas como média, menor e maior avaliação, além da soma das avaliações.

## Tecnologias Utilizadas
- **Java 17** + **Spring Boot**;
- **Spring Web** para comunicação com a API OMDB;
- **Spring Data JPA** para persistência de dados;
- **PostgreSQL** como banco de dados relacional;
- **Jackson** para conversão de JSON;
- **Maven** para gerenciamento de dependências.

## Estrutura do Projeto
A aplicação segue uma estrutura organizada para facilitar a manutenção e evolução do código:

```
screenmatch
├── .idea
├── .mvn
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── br.com.alura.screenmatch
│   │   │   │   ├── model
│   │   │   │   │   ├── Categoria
│   │   │   │   │   ├── DadosEpisodio
│   │   │   │   │   ├── DadosSerie
│   │   │   │   │   ├── DadosTemporada
│   │   │   │   │   ├── Episodio
│   │   │   │   │   ├── Serie
│   │   │   │   ├── principal
│   │   │   │   │   ├── Principal
│   │   │   │   ├── repository
│   │   │   │   │   ├── SerieRepository
│   │   │   │   ├── service
│   │   │   │   │   ├── traducao
│   │   │   │   │   │   ├── ConsultaMyMemory
│   │   │   │   │   │   ├── DadosResposta
│   │   │   │   │   │   ├── DadosTraducao
│   │   │   │   │   ├── ConsumoApi
│   │   │   │   │   ├── ConverteDados
│   │   │   │   ├── ScreenmatchApplication
│   ├── resources
│   │   ├── application.properties
│   ├── test
```

## Como Executar o Projeto
1. Clone este repositório:
   ```bash
   git clone https://github.com/eduardo-toste/ScreenMatch.git
   ```
2. Acesse o diretório do projeto:
   ```bash
   cd ScreenMatch
   ```
3. Configure a chave da API do OMDB e os dados do PostgreSQL no `application.properties`;
4. Compile e execute o projeto:
   ```bash
   mvn spring-boot:run
   ```
5. Interaja com a aplicação para buscar e visualizar informações sobre séries e episódios.

## Contribuição
Contribuições são bem-vindas. Faça um fork do projeto, crie uma branch com suas alterações e envie um pull request.

