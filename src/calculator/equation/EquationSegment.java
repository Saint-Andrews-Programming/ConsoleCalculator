package calculator.equation;

public class EquationSegment extends EquationPart {

	private Equation segment;

	public EquationSegment(Equation segment) {
		this.segment = segment;
	}

	@Override
	public Double getValueDouble() {
		return this.segment.solve();
	}

}
