# Fuzzy Matcher

Uma biblioteca simples em Java/Groovy para cálculo de similaridade entre strings utilizando **distância de Levenshtein** e lógica fuzzy.

---

## 🚀 Objetivo

Comparar duas palavras (ou frases) e retornar uma **porcentagem de similaridade**, útil para:

* Validação de nomes
* Comparação de documentos
* Busca aproximada (fuzzy search)
* Correção de digitação

---

## 📦 Instalação

### Gradle

```groovy
implementation "io.github.ruanfonseca:fuzzy-matcher:1.0.0"
```

---

## 🧪 Uso

```groovy
import com.ruanfonseca.fuzzy.FuzzyMatcher

def similarity = FuzzyMatcher.similarity("Ruan", "Ruann")

println similarity // Ex: 90.0
```

---

## 🧠 Como funciona (Matemática)

### 📏 Distância de Levenshtein

A base do algoritmo é a **distância de Levenshtein**, que mede o número mínimo de operações necessárias para transformar uma string em outra.

As operações permitidas são:

* Inserção
* Remoção
* Substituição

---

### 🔢 Exemplo

Comparando:

```text
"casa"
"caso"
```

Apenas 1 operação (substituição de "a" por "o"):

```text
Distância = 1
```

---

### 📐 Fórmula

A distância é calculada usando programação dinâmica:

```
d(i, j) = min(
  d(i-1, j) + 1,
  d(i, j-1) + 1,
  d(i-1, j-1) + custo
)
```

Onde:

* `i` = posição na string A
* `j` = posição na string B
* `custo` = 0 se caracteres iguais, 1 se diferentes

---

### 📊 Conversão para Similaridade (%)

A distância é convertida para porcentagem com:

```
similaridade = (1 - distancia / tamanho_maximo) * 100
```

---

### 🔍 Exemplo completo

```text
"gato"
"gata"

distância = 1
tamanho = 4

similaridade = (1 - 1/4) * 100 = 75%
```

---

## 🧹 Normalização

Antes da comparação, as strings são normalizadas:

* Remoção de acentos (ASCII)
* Conversão para minúsculas
* Remoção de caracteres especiais

---

## 💡 Fuzzy Matching

Além da distância, a biblioteca aplica heurísticas para melhorar a comparação:

* Tolerância a erros de digitação
* Comparação aproximada
* Robustez contra pequenas variações

---

## 📁 Estrutura

```
fuzzy-matcher/
 ├── src/
 ├── build.gradle
 └── README.md
```

---

## 🛠️ Tecnologias

* Java / Groovy
* Gradle
* Algoritmos de similaridade de strings

---

## 🤝 Contribuição

Pull requests são bem-vindos!

---

## 📄 Licença

MIT License
