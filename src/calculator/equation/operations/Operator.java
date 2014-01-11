package calculator.equation.operations;

import calculator.equation.EquationPart;

public abstract class Operator implements EquationPart {

	public abstract int getOrderValue();

	@Override
	public abstract String getValueString();

	@Override
	public Double getValueDouble() {
		return null;
	};

}