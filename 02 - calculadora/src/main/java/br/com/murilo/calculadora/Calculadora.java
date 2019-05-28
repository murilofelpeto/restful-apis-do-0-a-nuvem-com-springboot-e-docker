package br.com.murilo.calculadora;

import br.com.murilo.converters.OperationConverters;
import br.com.murilo.enums.Operation;
import br.com.murilo.exception.UnsupportedMathOperationException;

public class Calculadora {

	private final String OPERATION = "Please provide a valid operation SUM / SUB / MULT / DIV / MEDIA / SQRT";
	private Operation operation;
	private String numberOne;
	private String numberTwo;

	public Calculadora(Operation operation, String numberOne, String numberTwo) {
		this.operation = operation;
		this.numberOne = numberOne;
		this.numberTwo = numberTwo;
	}

	public Double resultado() {
		switch (operation) {
		case SUM:
			return OperationConverters.convertToDouble(numberOne) + OperationConverters.convertToDouble(numberTwo);
		case SUB:
			return OperationConverters.convertToDouble(numberOne) - OperationConverters.convertToDouble(numberTwo);
		case MULT:
			return OperationConverters.convertToDouble(numberOne) * OperationConverters.convertToDouble(numberTwo);
		case DIV:
			return OperationConverters.convertToDouble(numberOne) / OperationConverters.convertToDouble(numberTwo);
		case MEDIA:
			return (OperationConverters.convertToDouble(numberOne) + OperationConverters.convertToDouble(numberTwo))
					/ 2;
		case SQRT:
			return Math.sqrt(
					(OperationConverters.convertToDouble(numberOne) + OperationConverters.convertToDouble(numberTwo)));
		default:
			throw new UnsupportedMathOperationException(OPERATION);
		}
	}

}
