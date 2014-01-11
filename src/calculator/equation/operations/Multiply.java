package calculator.equation.operations;

public class Multiply extends Operator {

	@Override
	public int getOrderValue() {
		return 3;
	}

	@Override
	public String getValueString() {
		return "*";
	}

}
