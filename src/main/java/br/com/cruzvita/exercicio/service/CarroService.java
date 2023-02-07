package br.com.cruzvita.exercicio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cruzvita.exercicio.model.Carro;
import br.com.cruzvita.exercicio.model.Filtro;
import br.com.cruzvita.exercicio.request.CarroRequest;

@Service
public class CarroService {

	public String receberLista(CarroRequest carro) {

		try {
			if (carro.getListaCarro().isEmpty()) {
				return "Lista Vazia";
			}
		} catch (Exception ex) {
			String msg = ex.getMessage();
			return "Tentar novamente" + msg;
		}
		return (verificarTipoFiltro(carro));
	}

	// Verificar tipo do filtro
	public String verificarTipoFiltro(CarroRequest carro) {
		try {
			if (carro.getFiltro().getTipoFiltro().equals("nome")) {
				return verificarCarroEncontrado(filtrarPorNome(carro));
			} else {
				return verificarCarroEncontrado(filtrarPorPreco(carro));

			}
		} catch (Exception ex) {
			String msg = ex.getMessage();
			return "Dados incorretos" + msg;
		}

	}

	// Carro não encontrado
	public String verificarCarroEncontrado(Carro carro) {
		if (carro.getNome() == null) {
			return "Carro não encontrado";
		}
		return carro.toString();
	}

	// Verifica se a lista está vazia
	public boolean verificaListaVazia(List<Carro> listaCarro) {
		try {
			if (listaCarro.isEmpty()) {
				return true;
			}

		} catch (Exception ex) {

			return false;
		}

		return false;
	}

	// Filtrar por preco
	public Carro filtrarPorPreco(CarroRequest carro) {

		for (Carro carroDaVez : carro.getListaCarro()) {
			if (carroDaVez.getPreco().equals(carro.getFiltro().getParametro())) {

				formartarNomeUpperCase(carroDaVez);
				return formatarPreco(carroDaVez);

			}
		}
		return new Carro();
	}

	// Filtrar Por nome
	public Carro filtrarPorNome(CarroRequest carro) {

		for (Carro carroDaVez : carro.getListaCarro()) {

			if (carroDaVez.getNome().equals(carro.getFiltro().getParametro())) {

				formatarPreco(carroDaVez);
				return formartarNomeUpperCase(carroDaVez);
			}
		}
		return new Carro();
	}

	// Formatar Preco
	public Carro formatarPreco(Carro carro) {

		if (carro.getPreco().length() == 5) {
			String precoFormatado = carro.getPreco().substring(0, 2) + "." + carro.getPreco().substring(2, 5);
			carro.setPreco(precoFormatado);
			return carro;

			// 70000 70.000
		} else {
			String precoFormatado = carro.getPreco().substring(0, 1) + "." + carro.getPreco().substring(1, 4);
			carro.setPreco(precoFormatado);
			return carro;
			// 7000 7.000
		}

	}

	// Formatar Nome
	public Carro formartarNomeUpperCase(Carro nomeCarro) {
		String nomeFormatado = nomeCarro.getNome().toUpperCase();
		nomeCarro.setNome(nomeFormatado);
		return nomeCarro;

	}
}