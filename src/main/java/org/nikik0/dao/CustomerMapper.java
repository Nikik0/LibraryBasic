package org.nikik0.dao;

import org.nikik0.models.Customer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerMapper implements RowMapper<Customer> {
    @Override
    public Customer mapRow(ResultSet resultSet, int i) throws SQLException {
        Customer customer = new Customer();
        customer.setCustomerid(resultSet.getInt("customerid"));
        customer.setName(resultSet.getString("name"));
        customer.setAge(resultSet.getInt("age"));
        return customer;
    }
}
