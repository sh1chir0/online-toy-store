package com.shop.shmakova.models.enums;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.List;

/**
 * @author sh1chiro 19.03.2023
 */

@AllArgsConstructor
public enum TypeOfProducts {
    ALL("Всі товари"),
    BEAR("Ведмедики"), BUNNY("Кролики"), DOG("Песики"),
    HEDGEHOG("Їжачки"), UNICORN("Єдинорожки"),
    DOLL("Ляльки"), DRAGON("Дракончики"), BIRD("Пташки"),
    DEER("Олень"), RATTLE("Брязкальце"), PLAID("Пледики"),
    OTHER("Інше");

    private String name;
    public String getName(){
        return name;
    }
    public static TypeOfProducts findByName(String name){
        List<TypeOfProducts> list = Arrays.asList(TypeOfProducts.values());
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getName().equals(name))
                return list.get(i);
        }
        return null;
    }
}
