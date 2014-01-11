package calculator.equation;

public class EquationSegment extends EquationPart {

	private Equation segment;

	public EquationSegment(Equation segment) {
		this.segment = segment;
	}

	@Override
	public Double doubleValue() {
		return this.segment.value();
	}

}
