package calculator.equation;

public class EquationValue extends EquationPart {

	private Double value;

	public EquationValue(Double value) {
		this.value = value;
	}

	public EquationValue(double value) {
		this.value = value;
	}

	@Override
	public Double getValueDouble() {
		return this.value;
	}

}
