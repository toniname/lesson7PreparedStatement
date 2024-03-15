package org.example;

import java.sql.Date;

public class PersonEntity {
    private int id;
    private String name;
    private Date birthday;
    private String level;
    private int salary;

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public PersonEntity(int id, String name, Date birthday, String level, int salary) {
        this.id = id;
        this.name = name;

        this.birthday = birthday;
        this.level = level;
        this.salary = salary;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

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

}
