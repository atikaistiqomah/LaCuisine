package com.example.lacuisine;

public class Tambah {
    private String key;

    private String tnama;
    private String tjumlah;
    private String tnomor;

    public Tambah() {
    }

    public Tambah(String tnama, String tjumlah, String tnomor) {
        this.tnama = tnama;
        this.tjumlah = tjumlah;
        this.tnomor = tnomor;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTnama() {
        return tnama;
    }

    public void setTnama(String tnama) {
        this.tnama = tnama;
    }

    public String getTjumlah() {
        return tjumlah;
    }

    public void setTjumlah(String tjumlah) {
        this.tjumlah = tjumlah;
    }

    public String getTnomor() {
        return tnomor;
    }

    public void setTnomor(String tnomor) {
        this.tnomor = tnomor;
    }
}
