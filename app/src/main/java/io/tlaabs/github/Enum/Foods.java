package io.tlaabs.github.Enum;

public enum Foods {
    RICE(1), NOODLE(2), CAFE(3), PIZZA(4), CHICKEN(5), FASTFOOD(6);
    private final int value;

    private Foods(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }

    public static Foods getFood(int i){
        return Foods.values()[i];
    }
}
