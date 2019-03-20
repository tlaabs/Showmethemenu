package io.tlaabs.github.Enum;

public enum ServiceType {
    PREMINUM("P"),
    NORMAL("N");

    private String type;

    private ServiceType(String type){
        this.type = type;
    }

    public String getServiceType(){
        return type;
    }
}
