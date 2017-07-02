package br.com.devpi.novocalendar.enums;

/**
 * Created by esdraspinheiro on 01/07/17.
 */

public enum EnumWebService {

    WEB_SERVICE("https://web-service-bruno.herokuapp.com/");

    private String webService;

    EnumWebService(String webService) {
        this.webService = webService;
    }

    public String getWebService(){
        return webService;
    }
}
