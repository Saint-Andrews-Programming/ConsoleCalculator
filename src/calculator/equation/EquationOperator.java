package calculator.equation;

import java.util.List;

public class EquationOperator {

	public Operators operator;
	public int indexOfValue1;
	public int indexOfValue2;
	private List<IEquationPart> equationParts;

	public EquationOperator(int indexOfValue1, Operators operator,
			int indexOfValue2, List<IEquationPart> equationParts) {
		this.operator = operator;
		this.indexOfValue1 = indexOfValue1;
		this.indexOfValue2 = indexOfValue2;
		this.equationParts = equationParts;
	}

	public Double solve() {
		return mathWithOperator(equationParts.get(this.indexOfValue1)
				.doubleValue(), this.operator,
				equationParts.get(this.indexOfValue2).doubleValue());
	}

	private Double mathWithOperator(Double d1, Operators op, Double d2) {
		if (op == Operators.ADD)
			return d1 + d2;

		if (op == Operators.SUBTRACT)
			return d1 - d2;

		if (op == Operators.MULTIPLY)
			return d1 * d2;

		if (op == Operators.DIVIDE)
			return d1 / d2;

		return null;
	}
}
