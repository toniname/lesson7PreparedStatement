package org.example;

public class LongestProject {
    private String name;
    private int monthsBetween;

    public LongestProject(String name, int monthsBetween) {
        this.name = name;
        this.monthsBetween = monthsBetween;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMonthsBetween() {
        return monthsBetween;
    }

    public void setMonthsBetween(int monthsBetween) {
        this.monthsBetween = monthsBetween;
    }
}
