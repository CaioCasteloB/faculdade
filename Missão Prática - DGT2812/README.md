# Missão Prática — DGT2812

Trabalho prático da disciplina **Desenvolvimento de Aplicativos Móveis com Flutter**.

## Informações do Aluno

| Autor       | Caio Viana Castelo Branco     |
| Matrícula   | 202307465925                  |
| Disciplina  | Desenvolvimento de Aplicativos Móveis com Flutter — DGT2812 |

## Sobre o Projeto

Aplicativo Flutter desenvolvido como parte do trabalho prático da disciplina DGT2812, seguindo o roteiro da agência de viagens fictícia **Explore Mundo**. O app demonstra os principais conceitos de layout e widgets do Flutter, organizando as informações de um destino turístico em uma interface responsiva.

## Funcionalidades implementadas

- **MaterialApp / Scaffold / AppBar** — estrutura base do aplicativo
- **Image.asset** — exibição de imagem de destino com `BoxFit.cover`
- **Seção de Título** — nome e localização do destino com ícone de avaliação em estrela
- **Seção de Botões** — linha de ações (CALL, ROUTE, SHARE) construída com método auxiliar `buildButtonColumn`
- **Seção de Texto** — descrição do destino com quebra de linha automática (`softWrap`)
- **ListView** — organização com suporte a rolagem em telas menores

## Estrutura do Projeto

```
lib/
  main.dart          # Código principal do aplicativo
assets/
  images/
    lake.jpg         # Imagem do destino turístico
documentacao_flutter.pdf  # Documentação do projeto
pubspec.yaml         # Configuração de dependências e assets
```

## Como executar

1. Certifique-se de ter o [Flutter SDK](https://flutter.dev) instalado.
2. Clone o repositório e acesse a pasta do projeto.
3. Instale as dependências:
   ```bash
   flutter pub get
   ```
4. Execute o aplicativo em um emulador ou dispositivo conectado:
   ```bash
   flutter run
   ```

## Tecnologias utilizadas

- [Flutter](https://flutter.dev) — framework de desenvolvimento mobile
- [Dart](https://dart.dev) — linguagem de programação
