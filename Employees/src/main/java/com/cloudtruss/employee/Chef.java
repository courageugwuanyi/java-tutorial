package com.cloudtruss.employee;

public interface Chef {

    String favouriteFood = "Pizza";

    default void cook(String food) {
        System.out.println("I cook " + food);
    }

    default String cleanUp() {
        return "I am done cleaning up";
    }

    default String getFavouriteFood(){
        return favouriteFood;
    }

}
