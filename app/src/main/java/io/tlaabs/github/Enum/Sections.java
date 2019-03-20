package io.tlaabs.github.Enum;

public enum Sections {
    PREMIUM(0), NORMAL(1);
    private final int value;

    private Sections(int value){this.value = value;}

    public int getValue(){return value;}

    public static Sections getSection(int i){return Sections.values()[i];}
}
