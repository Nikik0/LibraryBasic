package org.nikik0.dao;

import org.nikik0.models.Book;
import org.nikik0.models.Customer;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class CustomerDao {
    private final JdbcTemplate jdbcTemplate;

    public CustomerDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Customer> index(){
        return jdbcTemplate.query("SELECT * from Customers", new BeanPropertyRowMapper<>(Customer.class));
    }

    public Customer show (int id){
        return jdbcTemplate.query("select * from customers where customerid = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Customer.class)).stream().findAny().orElse(null);
    }

    public void save(Customer customer){
        jdbcTemplate.update("insert into customers(name, age) values (?,?)", customer.getName(), customer.getAge());
    }

    public void update(int id, Customer customer){
        jdbcTemplate.update("update Customers set name=?, age = ? where customerid = ?", customer.getName(), customer.getAge(), id);
    }

    public void delete(int id){
        jdbcTemplate.update("delete from Customers where customerid = ?", id);
    }
}
