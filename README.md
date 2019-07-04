[![API](https://img.shields.io/badge/API-23%2B-red.svg?style=flat)](https://android-arsenal.com/api?level=23) [![Versão](https://img.shields.io/badge/Versão-1.0-yellow.svg)]()

# PokemonIF

# Estrutura das tabelas

[![Tabela Pokemons](https://img.shields.io/badge/Tabela-Pokemons-brightgreen.svg)]() [![Tabela Pokemons](https://img.shields.io/badge/Dupla-Diego-blue.svg)]()

| Nome   | Tipo                              |
| ------ | --------------------------------- |
| \_id   | INTEGER PRIMARY KEY AUTOINCREMENT |
| nome   | VARCHAR(50) NOT NULL              |
| tipo   | INTEGER NOT NULL                  |
| imagem | VARCHAR(100) NOT NULL             |

[![Tabela Pokemons](https://img.shields.io/badge/Tabela-Itens-brightgreen.svg)]() [![Tabela Pokemons](https://img.shields.io/badge/Dupla-Douglas-blue.svg)]()

| Nome      | Tipo                              |
| --------- | --------------------------------- |
| \_id      | INTEGER PRIMARY KEY AUTOINCREMENT |
| nome      | VARCHAR(50) NOT NULL              |
| descricao | VARCHAR(100) NOT NULL             |
| preço     | DOUBLE NOT NULL                   |
| imagem    | VARCHAR(100)                      |

[![Tabela Pokemons](https://img.shields.io/badge/Tabela-Itens_Jogador-brightgreen.svg)]() [![Tabela Pokemons](https://img.shields.io/badge/Dupla-Douglas-blue.svg)]()

| Nome       | Tipo                              |
| ---------- | --------------------------------- |
| \_id       | INTEGER PRIMARY KEY AUTOINCREMENT |
| id_usuario | INTEGER NOT NULL                  |
| id_item    | INTEGER NOT NULL                  |
| quantidade | INTEGER                           |

[![Tabela Pokemons](https://img.shields.io/badge/Tabela-Pokedex-brightgreen.svg)]() [![Tabela Pokemons](https://img.shields.io/badge/Dupla-Rodrigo-blue.svg)]()

| Nome       | Tipo                              |
| ---------- | --------------------------------- |
| \_id       | INTEGER PRIMARY KEY AUTOINCREMENT |
| id_pokemon | INTEGER NOT NULL                  |
| id_usuario | INTEGER NOT NULL                  |
| visto      | INTEGER                           |

[![Tabela Pokemons](https://img.shields.io/badge/Tabela-Pokemons_Jogador-brightgreen.svg)]() [![Tabela Pokemons](https://img.shields.io/badge/Dupla-Diego-blue.svg)]()

| Nome       | Tipo                              |
| ---------- | --------------------------------- |
| \_id       | INTEGER PRIMARY KEY AUTOINCREMENT |
| id_pokemon | INTEGER NOT NULL                  |
| id_usuario | INTEGER NOT NULL                  |
| hp         | INTEGER NULL                      |
| level      | INTEGER NOT NULL                  |
| ataque     | INTEGER NOT NULL                  |
| defesa     | INTEGER NOT NULL                  |
| velocidade | INTEGER NOT NULL                  |
| xp         | DOUBLE NOT NOT NULL               |
| critico    | INTEGER NOT NULL                  |
| vida       | INTEGER NOT NULL                  |
| data       | Captura DATE NOT NULL             |

[![Tabela Pokemons](https://img.shields.io/badge/Tabela-Jogador-brightgreen.svg)]() [![Tabela Pokemons](https://img.shields.io/badge/Dupla-Rodrigo-blue.svg)]()

| Nome               | Tipo                              |
| ------------------ | --------------------------------- |
| \_id               | INTEGER PRIMARY KEY AUTOINCREMENT |
| id_pokemon_jogador | INTEGER NOT NULL                  |
| nome               | VARCHAR(50) NOT NULL              |
| apelido            | VARCHAR(20) NOT NULL              |
| email              | VARCHAR(100) NOT NULL             |
| senha              | VARCHAR(20) NOT NULL              |
| carisma            | INTEGER NOT NULL                  |
| inteligencia       | INTEGER NOT NULL                  |
| sorte              | INTEGER NOT NULL                  |
| dinheiro           | DOUBLE                            |
| clã                | VARCHAR(10) NOT NULL              |
