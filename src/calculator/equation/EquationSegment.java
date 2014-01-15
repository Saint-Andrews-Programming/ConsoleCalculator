package calculator.equation;

public class EquationSegment extends EquationPart {

	private Equation segment;
	private int OriginalSize;

	public EquationSegment(Equation segment) {
		this.segment = segment;
		this.OriginalSize = segment.getSize();
	}

	@Override
	public Double doubleValue() {
		return this.segment.value().get(0).doubleValue();
	}

	public Equation getEquation() {
		return this.segment;
	}

	public int getOriginalSize() {
		return this.OriginalSize;
	}
}
