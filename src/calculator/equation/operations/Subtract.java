package calculator.equation.operations;

public class Subtract extends Operator {

	@Override
	public int getOrderValue() {
		return 5;
	}

	@Override
	public String getValueString() {
		return "-";
	}

}
