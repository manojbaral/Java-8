package com.manoj.lamda;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Manoj Baral on 12/7/2020.
 */
public class Department {
    private String name;
    private List<Employee> employees;

    public Department(String name) {
        this.name = name;
        employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee){
        employees.add(employee);
    }

    public List<Employee> getEmployees(){
        return  employees;
    }
}
