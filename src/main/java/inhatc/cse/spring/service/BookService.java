package inhatc.cse.spring.service;

import inhatc.cse.spring.controller.dto.BookDTO;
import inhatc.cse.spring.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public int save(BookDTO bookDto) {
        return bookRepository.save(bookDto);
    }

    public List<BookDTO> findAll() {
        return bookRepository.findAll();
    }

    public BookDTO findById(int id) {
        return bookRepository.findById(id);
    }
}