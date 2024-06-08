
package com.childcaresolutions.childcare_app.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;



public enum RatingEnums {
       ONE_STARS(1),
    TWO_STARS(2),
    THREE_STARS(3),
    FOUR_STARS(4),
    FIVE_STARS(5);
    
        private final int value;

           RatingEnums(int value) {
        this.value = value;
    }
        
        
          public int getValue() {
        return value;
    }


    public static RatingEnums fromValue(int value) {
               for (RatingEnums rating : RatingEnums.values()) {
                   if(rating.getValue() == value){
                       return rating;
                   }
               }
               throw new IllegalArgumentException("Invalid value: " + value);    }

    public static boolean isValidRating (int value) {
               for (RatingEnums rating : RatingEnums.values()) {
                   if(rating.getValue() == value){
                       return true;
                   }
               }
               return false;
    }



}
