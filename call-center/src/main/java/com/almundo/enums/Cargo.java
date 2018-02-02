package com.almundo.enums;

public enum Cargo {

    OPERADOR ("1"),
    SUPERVISOR ("2"),
    DIRECTOR ("3");

    private final String id;

    private Cargo(final String id) {
        this.id = id;
    }

    public String getValue() { 
    	return id; 
	}
}
