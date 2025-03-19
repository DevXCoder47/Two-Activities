package com.javaandroid.severalactivities.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contact implements Serializable {
    private String name;
    private String number;

    @NonNull
    @Override
    public String toString(){
        return "[" + name + "; " + number + "]";
    }

}
