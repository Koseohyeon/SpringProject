package inhatc.cse.spring.controller;

import inhatc.cse.spring.controller.dto.BookDTO;
import inhatc.cse.spring.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:300/bookList"})
@Slf4j
public class BookApiController {

    private final BookService bookService;

    @PostMapping("/save")
    public ResponseEntity<?> saveBook(@RequestBody BookDTO bookDto) {
        log.info("Saving book with imageUrl: {}", bookDto.getBookImage()); // 이 줄을 추가
        int result = bookService.save(bookDto);
        if (result > 0) {
            return new ResponseEntity<>("Book saved successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to save book", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<BookDTO> books = bookService.findAll();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable int id) {
        BookDTO book = bookService.findById(id);
        if (book != null) {
            return new ResponseEntity<>(book, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
