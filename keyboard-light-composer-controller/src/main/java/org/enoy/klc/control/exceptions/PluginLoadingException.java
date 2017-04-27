package org.enoy.klc.control.exceptions;

public class PluginLoadingException extends Exception {

	private static final long serialVersionUID = -586569014146583098L;

	public PluginLoadingException(String message, Throwable cause) {
		super(message, cause);
	}

	public PluginLoadingException(String message) {
		super(message);
	}

}
