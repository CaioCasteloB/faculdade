import pandas as pd

# Leitura do CSV:
dados = pd.read_csv(
    "pico_web.csv",
    sep=";",
    engine="python",
    encoding="utf-8"
)

print("Informações gerais do conjunto de dados:")
dados.info()

# Total de linhas e colunas:
linhas, colunas = dados.shape
print("\nTotal de linhas:", linhas)
print("Total de colunas:", colunas)

# Quantidade de nulos por coluna:
print("\nQuantidade de valores nulos por coluna:")
print(dados.isna().sum())

# Tipos de dados por coluna:
print("\nTipos de dados das colunas:")
print(dados.dtypes)

# Uso de memória:
print("\nUso de memória por coluna:")
print(dados.memory_usage())

print("\nUso total de memória (bytes):")
print(dados.memory_usage().sum())
