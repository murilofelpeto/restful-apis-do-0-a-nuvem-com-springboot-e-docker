package br.com.murilo.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.murilo.calculadora.Calculadora;
import br.com.murilo.converters.OperationConverters;
import br.com.murilo.enums.Operation;
import br.com.murilo.exception.UnsupportedMathOperationException;

@RestController
public class MathController {

	private final String NUMERIC = "Please set a numeric value";

	@RequestMapping(value = "/{operation}/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double sum(@PathVariable("operation") Operation operation, @PathVariable("numberOne") String numberOne,
			@PathVariable("numberTwo") String numberTwo) throws Exception {

		if (!OperationConverters.isNumeric(numberOne) || !OperationConverters.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException(NUMERIC);
		}
		
		Calculadora calculadora = new Calculadora(operation, numberOne, numberTwo);
		return calculadora.resultado();
		
	}
}
