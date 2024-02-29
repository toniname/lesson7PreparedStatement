package org.example;

public class MaxSalaryWorker {
    private String name;
    private int maxSalary;

    public MaxSalaryWorker(String name, int maxSalary) {
        this.name = name;
        this.maxSalary = maxSalary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(int maxSalary) {
        this.maxSalary = maxSalary;
    }
}
