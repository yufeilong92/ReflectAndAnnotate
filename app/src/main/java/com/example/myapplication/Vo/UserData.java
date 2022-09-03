package com.example.myapplication.Vo;

import java.io.Serializable;

public class UserData implements Serializable {
    String name;
    int age;
    String cla;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCla() {
        return cla;
    }

    public void setCla(String cla) {
        this.cla = cla;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", cla='" + cla + '\'' +
                '}';
    }
}
