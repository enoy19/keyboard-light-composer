package org.enoy.klc.common.layers;

import java.io.Serializable;

import org.enoy.klc.common.Activatable;
import org.enoy.klc.common.Deletable;
import org.enoy.klc.common.properties.KlcPropertyContainer;

public abstract class LayerBase implements RenderableLayer, KlcPropertyContainer, Activatable, Deletable, Serializable {

	private static final long serialVersionUID = -7298234944858763982L;

}
