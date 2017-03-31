package com.example.arifhaider.nupuit;

/**
 * Created by absak on 3/31/2017.
 */

public class Contacts {
    Contacts(){};

    Contacts(String name, String phone){
        this.name = name;
        this.number = phone;
    }


    private int id;
    private String number;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }



}
