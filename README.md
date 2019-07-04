# PokemonIF

# Estrutura das tabelas

[![Tabela Pokemons](https://img.shields.io/badge/Tabela-Pokemons-brightgreen.svg)]() [![Tabela Pokemons](https://img.shields.io/badge/Dupla-Diego-blue.svg)]()

| Nome | Tipo |
| ------ | ------ |
| _id | INTEGER PRIMARY KEY AUTOINCREMENT |
| nome | VARCHAR(50) NOT NULL |
| tipo | INTEGER NOT NULL |
| imagem | VARCHAR(100) NOT NULL |

[![Tabela Pokemons](https://img.shields.io/badge/Tabela-Itens-brightgreen.svg)]() [![Tabela Pokemons](https://img.shields.io/badge/Dupla-Douglas-blue.svg)]()

| Nome | Tipo |
| ------ | ------ |
| _id | INTEGER PRIMARY KEY AUTOINCREMENT |
| nome | VARCHAR(50) |
| descricao | VARCHAR(100) |
| preço | DOUBLE NOT NULL |
| imagem| VARCHAR(100) |

[![Tabela Pokemons](https://img.shields.io/badge/Tabela-Itens_Jogador-brightgreen.svg)]() [![Tabela Pokemons](https://img.shields.io/badge/Dupla-Douglas-blue.svg)]()

| Nome | Tipo |
| ------ | ------ |
| _id | INTEGER PRIMARY KEY AUTOINCREMENT |
| id_usuario | INTEGER NOT NULL |
| id_item | INTEGER NOT NULL |
| quantidade | INTEGER |

[![Tabela Pokemons](https://img.shields.io/badge/Tabela-Pokedex-brightgreen.svg)]() [![Tabela Pokemons](https://img.shields.io/badge/Dupla-Rodrigo-blue.svg)]()

| Nome | Tipo |
| ------ | ------ |
|  _id | INTEGER PRIMARY KEY AUTOINCREMENT |
| id_pokemon | INTEGER NOT NULL |
| id_usuario | INTEGER NOT NULL |
| visto | INTEGER |

[![Tabela Pokemons](https://img.shields.io/badge/Tabela-Pokemons_Jogador-brightgreen.svg)]() [![Tabela Pokemons](https://img.shields.io/badge/Dupla-Diego-blue.svg)]()

| Nome | Tipo |
| ------ | ------ |
| _id | INTEGER PRIMARY KEY AUTOINCREMENT |
| id_pokemon | INTEGER NOT NULL |
| id_usuario | INTEGER NOT NULL |
| hp | INTEGER NULL |
| level | INTEGER NULL |
| ataque | INTEGER NULL |
| defesa | INTEGER NULL |
| velocidade | INTEGER NULL |
 | xp | DOUBLE NOT NULL |
| critico| INTEGER NULL |
| vida | INTEGER NULL |
| data | Captura DATE NOT NULL |

[![Tabela Pokemons](https://img.shields.io/badge/Tabela-Jogador-brightgreen.svg)]() [![Tabela Pokemons](https://img.shields.io/badge/Dupla-Rodrigo-blue.svg)]()

| Nome | Tipo |
| ------ | ------ |
| _id | INTEGER PRIMARY KEY AUTOINCREMENT |
| id_pokemon_jogador | INTEGER NOT NULL |
| nome | VARCHAR(50) NOT NULL |
| apelido | VARCHAR(20) NOT NULL |
| email | VARCHAR(100) NOT NULL |
| senha | VARCHAR(20) NOT NULL |
| carisma | INTEGER |
| inteligencia | INTEGER  |
| sorte | INTEGER |
| dinheiro | DOUBLE |
| clã | VARCHAR(10) |
