package org.example;

import java.sql.Date;

public class Projects {
    private int id;
    private int clientId;
    private Date StartDate;
    private Date FinishDate;
    private int salary;

    public Projects(int id, int clientId, Date startDate, Date finishDate, int salary) {
        this.id = id;
        this.clientId = clientId;
        StartDate = startDate;
        FinishDate = finishDate;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public Date getStartDate() {
        return StartDate;
    }

    public void setStartDate(Date startDate) {
        StartDate = startDate;
    }

    public Date getFinishDate() {
        return FinishDate;
    }

    public void setFinishDate(Date finishDate) {
        FinishDate = finishDate;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
