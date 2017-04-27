package org.enoy.klc.common.device;

public class DeviceInformation {

	private String name;
	private int width;
	private int height;

	public DeviceInformation(String name, int width, int height) {
		this.name = name;
		this.width = width;
		this.height = height;
	}
	public String getName() {
		return name;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}

}
