package com.hana4.library.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hana4.library.dto.BookDTO;

@Mapper
public interface BookDAO {
	List<BookDTO> findAllBooks();
	BookDTO findBookById(int bno);
	void updateBookStatus(BookDTO book);
}
