package calculator.equation;

public class EquationValue implements EquationPart {

	private Double value;

	public EquationValue(Double value) {
		this.value = value;
	}

	public EquationValue(double value) {
		this.value = value;
	}

	@Override
	public String getValueString() {
		return this.value.toString();
	}

	@Override
	public Double getValueDouble() {
		return this.value;
	}

}
