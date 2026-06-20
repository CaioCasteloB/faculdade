import pandas as pd

# Leitura do CSV:
dados = pd.read_csv(
    "pico_web.csv",
    sep=";",
    engine="python",
    encoding="utf-8"
)

# Variável - subconjunto:
subconjunto = None

# Selecionando 3 colunas do conjunto original:
subconjunto = dados[["Duration", "Pulse", "Calories"]]

# Exibir o subconjunto:
print(subconjunto)
