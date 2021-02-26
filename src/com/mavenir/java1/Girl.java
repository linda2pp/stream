package com.mavenir.java1;

public class Girl {
	private String name;

	public Girl(String name) {
		super();
		this.name = name;
	}

	public Girl() {
		super();
	}

	@Override
	public String toString() {
		return "Girl [name=" + name + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
