package com.doxacore.sideBar;

public class NavigationMdel {
    public static final String DASHBOARD_ECOMMERCE_ZUL = "/corezul/sidebar/ecommerce.zul";
    public static final String DASHBOARD_PROJECT_ZUL = "/corezul/sidebar/project.zul";
    public static final String BLANK_ZUL = "/blank.zul";

    private String contentUrl = DASHBOARD_ECOMMERCE_ZUL;

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }
}
