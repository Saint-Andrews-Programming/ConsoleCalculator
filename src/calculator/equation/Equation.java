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

						if (operator.toString().equalsIgnoreCase("(")) {

							int oldEquationIndex = index + 1;
							int newEquationIndex = 0;
							List<IEquationPart> newEquationSegmentParts = new ArrayList<IEquationPart>();
							while (oldEquationIndex < equationParts.size()
									&& oldEquationIndex < findParenthesisIndexFromBack(1) - 1) {

								newEquationSegmentParts.add(newEquationIndex,
										equationParts.get(oldEquationIndex));

								oldEquationIndex++;
								newEquationIndex++;
							}
							this.equationParts = makeNewList(
									new EquationSegment(new Equation(
											newEquationSegmentParts)), index);
							proirityLevel = 0;
							break;
						}

						if (operator.getOrder() == proirityLevel) {
							this.equationParts = makeNewList(operator, index);
							calculating = true;
							break;
						}
					}

					index++;
				}
			}

			proirityLevel++;
		}

		return equationParts;
	}

	private List<IEquationPart> makeNewList(EquationSegment eSegment,
			int indexOfeSegment) {
		List<IEquationPart> newEquationParts = new ArrayList<IEquationPart>();

		int placeValue = 0;
		int getValue = 0;
		while (getValue < this.equationParts.size()) {
			IEquationPart part = this.equationParts.get(getValue);
			getValue++;

			if (placeValue == indexOfeSegment) {
				newEquationParts.add(new EquationValue(eSegment.doubleValue()));

				placeValue++;
				getValue = getValue + eSegment.getOriginalSize();
				continue;
			}

			newEquationParts.add(placeValue, part);
			placeValue++;
		}

		if (findParenthesisIndexFromBack(1) == this.getSize()) {
			newEquationParts.remove(newEquationParts.size() - 1);
		}

		return newEquationParts;
	}

	private List<IEquationPart> makeNewList(Operators operator, int index) {

		List<IEquationPart> newEquationParts = new ArrayList<IEquationPart>();

		int placeValue = 0;
		int getValue = 0;
		while (getValue < equationParts.size()) {
			IEquationPart part = equationParts.get(getValue);
			getValue++;

			if (placeValue == index - 1) {
				newEquationParts.add(
						placeValue,
						new EquationValue(operator.mathWithOperator(
								equationParts.get(index - 1).doubleValue(),
								equationParts.get(index + 1).doubleValue())));

				placeValue++;
				getValue = getValue + 2;
				continue;
			}

			newEquationParts.add(placeValue, part);
			placeValue++;
		}

		return newEquationParts;
	}

	private int findParenthesisIndexFromBack(int countFromBack) {
		int index = equationParts.size() - 1;
		int x = 0;
		while (index > 0) {
			if (equationParts.get(index) instanceof Operators) {
				x++;
				if (countFromBack == x) {
					return index + 1;
				}
			}

			index--;
		}
		return -1;
	}

	public int getSize() {
		return this.equationParts.size();
	}
}
