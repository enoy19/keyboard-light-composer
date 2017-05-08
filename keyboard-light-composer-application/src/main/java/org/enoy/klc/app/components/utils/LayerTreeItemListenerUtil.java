package org.enoy.klc.app.components.utils;

import org.enoy.klc.app.components.tree.LayerBaseContainer;
import org.enoy.klc.common.effects.Effect;
import org.enoy.klc.common.layers.EffectGroupLayer;
import org.enoy.klc.common.layers.EffectLayer;
import org.enoy.klc.common.layers.LayerBase;
import org.enoy.klc.common.updatables.Updatable;

import javafx.scene.control.TreeItem;

public class LayerTreeItemListenerUtil {

	public static void addListenersToTreeItem(TreeItem<LayerBaseContainer<?>> treeItem) {

		treeItem.parentProperty().addListener((v, o, n) -> {
			LayerBaseContainer<?> oldValue = o != null ? o.getValue() : null;
			LayerBaseContainer<?> newValue = n != null ? n.getValue() : null;
			LayerBase value = treeItem.getValue().getLayerBase();
			Effect effect = getEffect(value);
			
			if (oldValue != null) {
				EffectGroupLayer parentGroup = (EffectGroupLayer) oldValue.getLayerBase();
				parentGroup.remove(value);
				if (isUpdatable(effect)) {
					unregisterUpdatableEffect(effect);
				}
			}

			if (newValue != null) {
				EffectGroupLayer parentGroup = (EffectGroupLayer) newValue.getLayerBase();
				parentGroup.addChild(value);

				if (isUpdatable(effect)) {
					registerUpdatableEffect(effect);
				}

				// reorganize
				parentGroup.clearChildren();
				n.getChildren().forEach(ti -> {
					parentGroup.addChild(ti.getValue().getLayerBase());
				});
			}
		});

	}

	private static Effect getEffect(LayerBase value) {
		if (value instanceof EffectLayer) {
			return ((EffectLayer) value).getEffect();
		}
		return null;
	}

	private static boolean isUpdatable(Effect effect) {
		return effect != null && effect instanceof Updatable;
	}

	private static void registerUpdatableEffect(Effect effect) {
		((Updatable) effect).registerUpdatable();
	}

	private static void unregisterUpdatableEffect(Effect effect) {
		((Updatable) effect).unregisterUpdatable();
	}

}
