import React from "react";
import { BrowserRouter, Routes, Route, Link } from "react-router-dom";
import LivroLista from "./LivroLista";
import LivroDados from "./LivroDados";

const App: React.FC = () => (
  <BrowserRouter>
    <nav>
      <Link to="/">Listagem</Link> | <Link to="/dados">Cadastro</Link>
    </nav>
    <Routes>
      <Route path="/" element={<LivroLista />} />
      <Route path="/dados" element={<LivroDados />} />
    </Routes>
  </BrowserRouter>
);

export default App;