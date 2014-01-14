package calculator.equation;

import java.util.ArrayList;
import java.util.List;

public class Equation {

	private List<IEquationPart> equationParts;

	public Equation(List<IEquationPart> equationParts) {
		this.equationParts = equationParts;
	}

	public List<IEquationPart> value() {

		int proirityLevel = 1;
		while (proirityLevel <= 5) {

			boolean calculating = true;
			while (calculating) {
				calculating = false;
				// The index of equationParts we are currently in
				int index = 0;
				for (IEquationPart part : equationParts) {
					if (part instanceof Operators) {
						Operators operator = (Operators) part;

						if (operator.toString().equalsIgnoreCase("(")
								|| operator.toString().equalsIgnoreCase(")")) {

						}

						if (operator.getOrder() == proirityLevel) {
							this.equationParts = makeNewList(operator, index);
							calculating = true;
							break;
						}
					}

					if (part instanceof EquationValue) {

					}

					if (part instanceof EquationSegment) {

					}

					index++;
				}

				proirityLevel++;
			}
		}

		return equationParts;
	}

	private List<IEquationPart> makeNewList(Operators operator, int index) {

		List<IEquationPart> newEquationParts = new ArrayList<IEquationPart>();

		int i = 0;
		int x = 0;
		while (i + x < equationParts.size()) {
			IEquationPart part = equationParts.get(i + x);
			if (i == index - 1) {
				newEquationParts.add(
						i,
						new EquationValue(operator.mathWithOperator(
								equationParts.get(index - 1).doubleValue(),
								equationParts.get(index + 1).doubleValue())));
				i++;
				x = x + 3;

				if (i + 2 != this.equationParts.size())
					continue;
				else
					break;
			}

			newEquationParts.add(i, part);
			i++;
		}

		return newEquationParts;
	}
}
