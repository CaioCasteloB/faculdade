import pandas as pd

# Leitura do CSV:
dados = pd.read_csv(
    "pico_web.csv",
    sep=";",
    engine="python",
    encoding="utf-8"
)

# Configurar o número máximo de linhas exibidas:
pd.set_option("display.max_rows", 9999)

# Exibir o conjunto de dados completo:
print(dados.to_string())