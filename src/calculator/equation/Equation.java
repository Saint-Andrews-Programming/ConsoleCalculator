package calculator.equation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Equation {

	private List<IEquationPart> equationParts;

	// Map<IEquationPart, Point2D.Double> hm = new HashMap<Integer, Point2D>();

	public Equation(List<IEquationPart> equationParts) {
		this.equationParts = equationParts;
	}

	public Double value() {

		Map<EquationValue, Integer> equationValues = new HashMap<EquationValue, Integer>();

		Map<Operators, Integer> operators = new HashMap<Operators, Integer>();

		for (IEquationPart part : equationParts) {
			if (part instanceof EquationValue) {

				equationValues.put((EquationValue) part,
						equationParts.indexOf(part));
			}

			if (part instanceof EquationSegment) {
				part = (EquationSegment) part;
			}

			if (part instanceof Operators) {
				operators.put((Operators) part, equationParts.indexOf(part));
			}
		}

		Operators operator;
		Integer min = Integer.valueOf(Integer.MAX_VALUE);

		for (Entry<Operators, Integer> entry : operators.entrySet()) {
			if (min.compareTo(entry.getValue()) > 0) {
				operator = entry.getKey();
				min = entry.getKey().getOrder();
			}
		}

		return min.doubleValue();
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
