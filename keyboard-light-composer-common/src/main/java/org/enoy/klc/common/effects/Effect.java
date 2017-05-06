package org.enoy.klc.common.effects;

import java.io.Serializable;

import org.enoy.klc.common.Deletable;

public interface Effect extends Renderable, Deletable, Serializable {
	
	public String getName();

	// TODO: author
	
}
