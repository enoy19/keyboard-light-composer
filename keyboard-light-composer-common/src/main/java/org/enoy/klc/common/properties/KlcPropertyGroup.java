package org.enoy.klc.common.properties;

public class KlcPropertyGroup {

	private String groupName;
	private KlcWritableProperty<?>[] properties;
	
	public KlcPropertyGroup(String groupName, KlcWritableProperty<?>[] properties) {
		this.groupName = groupName;
		this.properties = properties;
	}
	
	public String getGroupName() {
		return groupName;
	}
	
	public KlcWritableProperty<?>[] getProperties() {
		return properties;
	}
	
}
