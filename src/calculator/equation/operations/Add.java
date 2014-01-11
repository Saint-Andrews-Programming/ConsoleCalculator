package calculator.equation.operations;

public class Add extends Operator {

	@Override
	public int getOrderValue() {
		return 5;
	}

	@Override
	public String getValueString() {
		return "+";
	}
}
