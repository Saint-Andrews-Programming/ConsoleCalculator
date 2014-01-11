package calculator.equation;

public enum Operators implements IEquationPart {
	ADD(5, "+"), DIVIDE(3, "/"), MULTIPLY(3, "*"), SUBTRACT(5, "-"), PARENTHESES_OPEN(1, "("), PARENTHESES_CLOSE(1, ")");

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
	public Double getValueDouble() {
		return null;
	}
}
