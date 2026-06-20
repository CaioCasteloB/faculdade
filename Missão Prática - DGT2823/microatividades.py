import pandas as pd

# Ler arquivo CSV usando Pandas

# Variável - dataframe:
dados = None

# Leitura do arquivo CSV:
dados = pd.read_csv(
    "pico_web.csv",
    sep=";",           # separador de colunas
    engine="python",   # engine solicitada
    encoding="utf-8"   # encoding (opcional)
)

# Impressão dos dados lidos:
print("=== Dados do CSV ===")
print(dados)

# Criar subconjunto de dados

# Nova variável para o subconjunto (3 colunas):
subconjunto = dados[["Duration", "Pulse", "Calories"]]

# Exibir o subconjunto:
print("\n=== Subconjunto (3 colunas) ===")
print(subconjunto)

# Configurar número máximo de linhas exibidas

# Definir novo valor para max_rows:
pd.set_option("display.max_rows", 9999)

# Exibir conjunto de dados completo com to_string():
print("\n=== Dados completos (max_rows=9999) ===")
print(dados.to_string())

# Exibir primeiras e últimas N linhas

print("\n=== Primeiras 10 linhas ===")
print(dados.head(10))

print("\n=== Últimas 10 linhas ===")
print(dados.tail(10))

# Informações gerais sobre o conjunto de dados

print("\n=== Informações gerais ===")
dados.info()

# Total de linhas e colunas:
linhas, colunas = dados.shape
print("\nTotal de linhas:", linhas)
print("Total de colunas:", colunas)

# Quantidade de valores nulos por coluna:
print("\nQuantidade de valores nulos por coluna:")
print(dados.isna().sum())

# Tipo de dado de cada coluna:
print("\nTipos de dados das colunas:")
print(dados.dtypes)

# Quantidade de memória utilizada:
print("\nUso de memória por coluna (bytes):")
print(dados.memory_usage())

print("\nUso total de memória (bytes):")
print(dados.memory_usage().sum())
