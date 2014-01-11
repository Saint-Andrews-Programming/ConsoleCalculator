package calculator.equation;


public enum Operators implements EquationPart {
	ADD(5, "+"), DIVIDE(3, "/"), MULTIPLY(3, "*"), SUBTRACT(5, "-");

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
	public String getValueString() {
		return this.operationString;
	}

	@Override
	public Double getValueDouble() {
		return null;
	}
}
