import { Livro } from "../modelo/Livro";

const livros: Array<Livro> = [
  new Livro(1, 1, "Livro A", "Resumo A", ["Autor A1", "Autor A2"]),
  new Livro(2, 2, "Livro B", "Resumo B", ["Autor B1"]),
  new Livro(3, 3, "Livro C", "Resumo C", ["Autor C1", "Autor C2"])
];

export default class ControleLivro {
  obterLivros(): Array<Livro> {
    return livros;
  }

  incluir(livro: Livro): void {
    const maxCodigo = Math.max(...livros.map(l => l.codigo), 0);
    livro.codigo = maxCodigo + 1;
    livros.push(livro);
  }

  excluir(codigo: number): void {
    const index = livros.findIndex(l => l.codigo === codigo);
    if (index >= 0) livros.splice(index, 1);
  }
}