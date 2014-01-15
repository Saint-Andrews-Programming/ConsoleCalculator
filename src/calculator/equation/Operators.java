package calculator.equation;

public enum Operators implements IEquationPart {
	ADD(5, "+"), DIVIDE(3, "/"), MULTIPLY(3, "*"), SUBTRACT(5, "-"), PARENTHESES_OPEN(
			1, "("), PARENTHESES_CLOSE(1, ")");

	private int order;
	private String operationString;

	private Operators(int order, String operationString) {
		this.order = order;
		this.operationString = operationString;
	}

	public int getOrder() {
		return this.order;
	}

	@Override
	public String toString() {
		return this.operationString;
	}

	@Override
	public Double doubleValue() {
		return null;
	}

	public Double mathWithOperator(Double d1, Double d2) {
		if (this == Operators.ADD)
			return d1 + d2;

		if (this == Operators.SUBTRACT)
			return d1 - d2;

		if (this == Operators.MULTIPLY)
			return d1 * d2;

		if (this == Operators.DIVIDE)
			return d1 / d2;

		return null;
	}
}
