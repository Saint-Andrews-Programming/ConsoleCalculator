package calculator.equation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Equation {

	private List<IEquationPart> equationParts;

	public Equation(List<IEquationPart> equationParts) {
		this.equationParts = equationParts;
	}

	public Double value() {

		Map<Integer, EquationValue> equationValues = new HashMap<Integer, EquationValue>();

		Map<Integer, Operators> operators = new HashMap<Integer, Operators>();

		Map<Integer, EquationOperator> storedOperations = new HashMap<Integer, EquationOperator>();

		for (IEquationPart part : equationParts) {
			if (part instanceof EquationValue) {

				equationValues.put(equationParts.indexOf(part),
						(EquationValue) part);
			}

			if (part instanceof EquationSegment) {
				part = (EquationSegment) part;
			}

			if (part instanceof Operators) {
				operators.put(equationParts.indexOf(part), (Operators) part);
			}
		}

		for (Entry<Integer, Operators> entryMainLoop : operators.entrySet()) {

			Operators operator = null;
			Integer operatorIndex = null;

			Integer min = Integer.valueOf(Integer.MAX_VALUE);
			for (Entry<Integer, Operators> entry : operators.entrySet()) {
				// Get the first operation according to the order of operations
				if (min.compareTo(entry.getKey()) > 0) {
					operator = entry.getValue();
					min = entry.getValue().getOrder();
					operatorIndex = entry.getKey();
				}
			}

			for (IEquationPart part : equationParts) {
				if (part instanceof Operators) {
					if (min.compareTo(part) > 0) {
						min = part.getOrder();
						operatorIndex = part;
					}
				}
			}

			if (operatorIndex == null || operator == null)
				return equationParts.get(0).doubleValue();

			if (!operator.toString().equalsIgnoreCase("(")
					&& !operator.toString().equalsIgnoreCase(")")) {

				if (equationParts.get(operatorIndex + 1) instanceof EquationValue
						&& equationParts.get(operatorIndex - 1) instanceof EquationValue)
					storedOperations.put(operatorIndex, new EquationOperator(
							operatorIndex - 1, operator, operatorIndex + 1,
							equationParts));

			}
		}

		return storedOperations.get(operatorIndex).solve();
	}
}
