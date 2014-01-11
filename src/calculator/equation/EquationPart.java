package calculator.equation;

public abstract class EquationPart implements IEquationPart {

	@Override
	public String toString() {
		return getValueDouble().toString();
	}

	@Override
	public abstract Double getValueDouble();
}
