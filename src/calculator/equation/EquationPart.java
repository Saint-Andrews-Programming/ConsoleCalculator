package calculator.equation;

public abstract class EquationPart implements IEquationPart {

	@Override
	public String toString() {
		return doubleValue().toString();
	}

	@Override
	public abstract Double doubleValue();
}
