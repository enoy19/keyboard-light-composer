package org.enoy.klc.control.utils;

import java.util.List;

public class ListItemSwapUtil {

	public static <E> void swapItems(List<E> listFrom, List<E> listTo, E from,
			E to, boolean above) {

		int indexFrom = listFrom.indexOf(from);
		int indexTo = listTo.indexOf(to);
		int index;

		if (listFrom.equals(listTo)) {
			if (indexFrom == indexTo) {
				return;
			}

			if (above) {
				if (indexFrom > indexTo) {
					index = indexTo;
				} else {
					index = indexTo - 1;
				}
			} else {
				if (indexFrom > indexTo) {
					index = indexTo + 1;
				} else {
					index = indexTo;
				}
			}
		} else {
			if (above) {
				index = indexTo;
			} else {
				index = indexTo + 1;
			}
		}

		index = Math.max(0, index);

		int index1 = index;

		listFrom.remove(indexFrom);
		DelayedExecuter.execute(100, () -> listTo.add(index1, from));

	}

}
