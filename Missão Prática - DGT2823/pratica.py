import pandas as pd
import numpy as np

# Passos 3-4: Leitura do CSV e atribuição à variável
df = pd.read_csv(
    "pico_web.csv",
    sep=";",
    engine="python",
    encoding="utf-8"
)

# Passo 5: Verificar se os dados foram importados adequadamente
print("=== Passo 5a - Informações gerais ===")
print(df.info())

print("\n=== Passo 5b - Primeiras linhas ===")
print(df.head())

print("\n=== Passo 5b - Últimas linhas ===")
print(df.tail())

# Passo 6: Criar cópia do dataset original
df2 = df.copy()

# Passo 7a: Substituir valores nulos da coluna 'Calories' por 0
df2["Calories"] = df2["Calories"].fillna(0)

print("\n=== Passo 7b - Calories após tratar NaN → 0 ===")
print(df2)

# Pré-processamento: remover aspas simples das datas lidas do CSV
df2["Date"] = df2["Date"].str.replace("'", "", regex=False)

# Passo 8a: Substituir valores nulos da coluna 'Date' por '1900/01/01'
df2["Date"] = df2["Date"].fillna("1900/01/01")

print("\n=== Passo 8b - Date após preencher NaN com '1900/01/01' ===")
print(df2)

# Passo 8c: Tentar converter Date para datetime (encontrará erro)
print("\n=== Passo 8c - Tentativa de converter Date para datetime ===")
try:
    df2["Date"] = pd.to_datetime(df2["Date"], format="%Y/%m/%d")
except Exception as e:
    print(f"Erro encontrado: {e}")
    print("→ Valor com formato incompatível detectado. Prosseguindo para o passo 9.")

# Passo 9a: Substituir '1900/01/01' por NaN
df2["Date"] = df2["Date"].replace("1900/01/01", np.nan)

# Passo 9b: Tentar converter Date para datetime novamente (novo erro)
print("\n=== Passo 9b - Segunda tentativa de converter Date para datetime ===")
try:
    df2["Date"] = pd.to_datetime(df2["Date"], format="%Y/%m/%d")
except Exception as e:
    print(f"Erro encontrado: {e}")
    print("→ O valor '20201226' não corresponde ao formato '%Y/%m/%d'. Prosseguindo para o passo 10.")

print("\n=== Passo 9c - Dataset após substituir '1900/01/01' por NaN ===")
print(df2)

# Passo 10: Corrigir '20201226' combinando replace + to_datetime
df2["Date"] = df2["Date"].replace(
    "20201226",
    pd.to_datetime("20201226", format="%Y%m%d")
)

# Passo 11: Converter toda a coluna 'Date' para datetime
df2["Date"] = pd.to_datetime(df2["Date"], format="%Y/%m/%d", errors="coerce")

print("\n=== Passo 11 - Date após conversão completa para datetime ===")
print(df2)

# Passo 12: Remover registros com Date nulo (linha 22)
df2 = df2.dropna(subset=["Date"])

# Passo 13: Imprimir dataset final
print("\n=== Passo 13 - Dataset final após todas as transformações ===")
print(df2)
