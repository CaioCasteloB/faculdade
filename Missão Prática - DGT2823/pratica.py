import pandas as pd
import numpy as np

# Leitura do CSV:
df = pd.read_csv(
    "pico_web.csv",
    sep=";",
    engine="python",
    encoding="utf-8"
)

# Verificações iniciais:
print(df.info())
print(df.head())
print(df.tail())

# Criar cópia do dataset:
df2 = df.copy()

# Substituir nulos de Calories por 0:
df2["Calories"] = df2["Calories"].fillna(0)
print("\nDepois de tratar Calories (NaN -> 0):")
print(df2)

# REMOVER ASPAS da coluna Date:
df2["Date"] = df2["Date"].str.replace("'", "", regex=False)

print("\nDepois de remover aspas da coluna Date:")
print(df2)

# Substituir nulos da coluna Date por "1900/01/01":
df2["Date"] = df2["Date"].fillna("1900/01/01")
print("\nDepois de preencher NaN de Date com '1900/01/01':")
print(df2)

# Substituir '1900/01/01' por NaN:
df2["Date"] = df2["Date"].replace("1900/01/01", np.nan)

# Corrigir o valor "20201226" para "2020/12/26":
df2["Date"] = df2["Date"].replace("20201226", "2020/12/26")

print("\nDepois de corrigir '20201226' e voltar '1900/01/01' para NaN:")
print(df2)

# Converter toda a coluna Date para datetime:
df2["Date"] = pd.to_datetime(df2["Date"], format="%Y/%m/%d")
print("\nDepois de converter Date para datetime:")
print(df2)

# Remover entradas com datas nulas (NaT):
df2 = df2.dropna(subset=["Date"])
print("\nDepois de remover linhas com Date nulo:")
print(df2)
