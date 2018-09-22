package com.example.chanly.dummy_equran;

public class Surah {
    private int number;
    private String name_arabic;
    private String name_latin;
    private String name_trans;
    private int number_ayah;

    public Surah(int number, String name_arabic, String name_latin, String name_trans, int number_ayah) {
        this.number = number;
        this.name_arabic = name_arabic;
        this.name_latin = name_latin;
        this.name_trans = name_trans;
        this.number_ayah = number_ayah;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName_arabic() {
        return name_arabic;
    }

    public void setName_arabic(String name_arabic) {
        this.name_arabic = name_arabic;
    }

    public String getName_latin() {
        return name_latin;
    }

    public void setName_latin(String name_latin) {
        this.name_latin = name_latin;
    }

    public String getName_trans() {
        return name_trans;
    }

    public void setName_trans(String name_trans) {
        this.name_trans = name_trans;
    }

    public int getNumber_ayah() {
        return number_ayah;
    }

    public void setNumber_ayah(int number_ayah) {
        this.number_ayah = number_ayah;
    }
}
