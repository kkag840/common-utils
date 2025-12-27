package com.common.enums;

public enum RegistrationType {

	MANUAL("MANUAL"), SOCIAL("SOCIAL");

	private String name;

	public String getName() {
		return name;
	}

	RegistrationType(String name) {
		this.name = name;
	}

}
