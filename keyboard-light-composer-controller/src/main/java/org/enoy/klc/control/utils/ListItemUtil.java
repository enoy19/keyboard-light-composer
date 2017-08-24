package org.enoy.klc.control.utils;

import javafx.application.Platform;

import java.util.List;

public class ListItemUtil {

	public static <E> void swapItems(List<E> listFrom, List<E> listTo, E from,
			E to, boolean above) {

		int indexFrom = listFrom.indexOf(from);
		int indexTo = listTo.indexOf(to);

		final int index = getSwappedIndex(listFrom, listTo, above, indexFrom,
				indexTo);

		if (index == -1) {
			return;
		}

		listFrom.remove(indexFrom);
		DelayedExecuter.execute(100, () -> listTo.add(index, from));

	}

	public static <E> void swapItemsJavaFxThread(List<E> listFrom,
			List<E> listTo, E from, E to, boolean above) {

		int indexFrom = listFrom.indexOf(from);
		int indexTo = listTo.indexOf(to);

		final int index = getSwappedIndex(listFrom, listTo, above, indexFrom,
				indexTo);

		if (index == -1) {
			return;
		}

		listFrom.remove(indexFrom);
		DelayedExecuter.execute(100,
				() -> Platform.runLater(() -> listTo.add(index, from)));

	}

	private static <E> int getSwappedIndex(List<E> listFrom, List<E> listTo,
			boolean above, int indexFrom, int indexTo) {
		int index;
		if (listFrom.equals(listTo)) {
			if (indexFrom == indexTo) {
				return -1;
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
		return index;
	}

	public static <E> void insertItem(List<E> listTo, E item, E to,
			boolean above) {

		int indexTo = listTo.indexOf(to);

		if (above) {
			listTo.add(indexTo, item);
		} else {
			listTo.add(indexTo + 1, item);
		}

	}

}
