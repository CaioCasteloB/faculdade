import pandas as pd

# Leitura do CSV:
dados = pd.read_csv(
    "pico_web.csv",
    sep=";",
    engine="python",
    encoding="utf-8"
)

# Exibir as primeiras 10 linhas:
print("Primeiras 10 linhas:")
print(dados.head(10))

# Exibir as últimas 10 linhas:
print("\nÚltimas 10 linhas:")
print(dados.tail(10))
