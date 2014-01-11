package calculator.equation;

public class EquationSegment implements EquationPart {

	private Equation segment;

	public EquationSegment(Equation segment) {
		this.segment = segment;
	}

	@Override
	public String getValueString() {
		return this.segment.solve().toString();
	}

	@Override
	public Double getValueDouble() {
		return this.segment.solve();
	}

}
