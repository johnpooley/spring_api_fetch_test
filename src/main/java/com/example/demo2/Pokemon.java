package com.example.demo2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Pokemon {
    private int base_experience;

    public Pokemon(){
    };

    public int getBase_experience() {
        return base_experience;
    }

    public void setBase_experience(int base_experience) {
        this.base_experience = base_experience;
    }

    @Override
    public String toString(){
        return "Pokemon{" +
                "XP='" + base_experience +
                '}';
    }
}
