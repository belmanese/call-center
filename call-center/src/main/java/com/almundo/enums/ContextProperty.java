package com.almundo.enums;

public enum ContextProperty {
	
    // For Database Connection
    DB_DRIVER_CLASS_NAME("jdbc.driverClassName"),
    DB_URL("jdbc.url"),
    DB_USERNAME("jdbc.username"),
    DB_PASSWORD("jdbc.password"),
    HIBERNATE_DIALECT("hibernate.dialect"),
    HIBERNATE_SHOW_SQL("hibernate.show_sql"),
    HIBERNATE_FORMAT_SQL("hibernate.format_sql"),

    //For Context Path
    PATH_COMPONENT_SCAN("com.almundo"),
    PATH_ENTITY_SCAN("com.almundo.domain"),
    PATH_JPA_REPOSITORIES("com.almundo.repo"),
    CLASSPATH_HIBERNATE_PROPERTIES("classpath:hibernate.properties");

    private String propertie;

    ContextProperty(String propertie) {
        this.propertie = propertie;
    }

    public String getPropertie(){ return this.propertie; }
    
}