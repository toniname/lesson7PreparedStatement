package org.example;

public class YoungestEldestWorker {
    private String name;
    private int birthday;

    public YoungestEldestWorker(String name, int birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirthday() {
        return birthday;
    }

    public void setBirthday(int birthday) {
        this.birthday = birthday;
    }
}
