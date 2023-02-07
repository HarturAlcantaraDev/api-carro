package br.com.cruzvita.exercicio.model;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Filtro {

	private String tipoFiltro;
	private String parametro;

	
	

}