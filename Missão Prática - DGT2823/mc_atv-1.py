import pandas as pd

# Variável - dataframe:
dados = None

# Leitura do arquivo CSV:
dados = pd.read_csv(
    "pico_web.csv",
    sep=";",           # separador de colunas
    engine="python",   # engine solicitada
    encoding="utf-8"   # encoding mais comum (opcional)
)

# Impressão dos dados lidos
print(dados)
