const swap = (vetor, i, j) => {
    const temp = vetor[i];
    vetor[i] = vetor[j];
    vetor[j] = temp;
};


const shuffle = (vetor, qtdTrocas) => {
    for (let i = 0; i < qtdTrocas; i++) {
        const pos1 = Math.floor(Math.random() * vetor.length);
        const pos2 = Math.floor(Math.random() * vetor.length);
        swap(vetor, pos1, pos2);
    }
    return vetor;
};


const bubble_sort = (vetor) => {
    const n = vetor.length;
    for (let i = 0; i < n - 1; i++) {
        for (let j = 0; j < n - i - 1; j++) {
            if (vetor[j] > vetor[j + 1]) {
                swap(vetor, j, j + 1);
            }
        }
    }
    return vetor;
};


const selection_sort = (vetor) => {
    const n = vetor.length;
    for (let i = 0; i < n - 1; i++) {
        let min_idx = i;
        for (let j = i + 1; j < n; j++) {
            if (vetor[j] < vetor[min_idx]) {
                min_idx = j;
            }
        }
        if (min_idx !== i) {
            swap(vetor, i, min_idx);
        }
    }
    return vetor;
};


const particionamento = (vetor, inicio, fim) => {
    const pivot = vetor[fim];
    let i = inicio - 1;
    
    for (let j = inicio; j < fim; j++) {
        if (vetor[j] <= pivot) {
            i++;
            swap(vetor, i, j);
        }
    }
    
    swap(vetor, i + 1, fim);
    return i + 1;
};


const quick_sort = (vetor, inicio, fim) => {
    if (inicio < fim) {
        const pi = particionamento(vetor, inicio, fim);
        quick_sort(vetor, inicio, pi - 1);
        quick_sort(vetor, pi + 1, fim);
    }
    return vetor;
};