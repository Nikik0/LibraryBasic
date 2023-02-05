package org.nikik0.dao;

import org.nikik0.models.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
        Book book = new Book();
        System.out.println(resultSet.toString());
        book.setBookid(resultSet.getInt("bookid"));
        book.setName(resultSet.getString("name"));
        book.setAuthor(resultSet.getString("author"));
        book.setTaken(resultSet.getInt("customerid"));
        return book;
    }
}
