package com.smdev.gof.structural.bridge.example_2;

import com.smdev.gof.structural.bridge.example_2.actor.Customer;
import com.smdev.gof.structural.bridge.example_2.actor.Employee;
import com.smdev.gof.structural.bridge.example_2.document.Document;
import com.smdev.gof.structural.bridge.example_2.document.Letter;

public class _Main {

    public static void main(String[] args) {
        Customer customer = new Customer();
        Employee employee = new Employee();

        Document letterToEmployee = new Letter(customer);
        letterToEmployee.sendTo(employee);

        Document letterToCustomer = new Letter(employee);
        letterToCustomer.sendTo(customer);
    }

}
