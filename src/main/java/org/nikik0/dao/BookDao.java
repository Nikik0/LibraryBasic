package org.nikik0.dao;

import org.nikik0.models.Book;
import org.nikik0.models.Customer;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDao {
    private final JdbcTemplate jdbcTemplate;

    public BookDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index(){
        return jdbcTemplate.query("Select * from books", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id){
        return jdbcTemplate.query("select * from books where bookid = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }

    public void save(Book book){
        jdbcTemplate.update("INSERT INTO books(name, author) values (?,?)", book.getName(), book.getAuthor());
    }

    public void update(int id, Book book){
            jdbcTemplate.update("UPDATE books set name=?, author=? where bookid = ?", book.getName(), book.getAuthor(), id);
    }

    public void delete(int id){
        jdbcTemplate.update("delete from books where bookid = ?", id);
    }

    public List<Book> indexTaken(int customerid){
        return jdbcTemplate.query("select * from books where customerid=?", new Object[]{customerid},new BeanPropertyRowMapper<>(Book.class));
    }

    public Customer takenBy(int bookid){
        Book book = jdbcTemplate.query("select * from books where bookid = ?", new Object[]{bookid}, new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
        //System.out.println(book.getBookid()+" "+book.getName()+" "+book.getTaken());
        Customer cus = jdbcTemplate.query("Select * from customers where customerid = ?", new Object[]{book.getTaken()}, new BeanPropertyRowMapper<>(Customer.class)).stream().findAny().orElse(null);
        //System.out.println(cus.getCustomerid());
        //return jdbcTemplate.query("Select * from customers where customerid = ?", new Object[]{book.getTaken()}, new BeanPropertyRowMapper<>(Customer.class)).stream().findAny().orElse(null);
        return jdbcTemplate.query("WITH var1 as (select customerid from books where bookid = ?) Select * from customers where customerid = (select customerid from var1)", new Object[]{bookid}, new BeanPropertyRowMapper<>(Customer.class)).stream().findAny().orElse(null);
    }

    public void setTaken(int customerid, int bookid){
        jdbcTemplate.update("update books set customerid=? where bookid = ?",customerid, bookid);
    }

    public void releaseTaken(int bookid){
        jdbcTemplate.update("update books set customerid=null where bookid = ?", bookid);
    }
}
