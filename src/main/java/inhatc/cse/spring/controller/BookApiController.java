package inhatc.cse.spring.controller;

import inhatc.cse.spring.controller.dto.BookDTO;
import inhatc.cse.spring.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000"}, allowCredentials = "true")
@Slf4j
public class BookApiController {

    private final BookService bookService;
    private final String uploadDir = "uploads/";

    @PostMapping("/save")
    public ResponseEntity<?> saveBook(@RequestParam("title") String title,
                                      @RequestParam("writher") String writher,
                                      @RequestParam("publisher") String publisher,
                                      @RequestParam("year") String year,
                                      @RequestParam("bookImage") MultipartFile bookImage) {
        if (title == null || title.isEmpty() ||
                writher == null || writher.isEmpty() ||
                publisher == null || publisher.isEmpty() ||
                year == null || year.isEmpty() ||
                bookImage == null || bookImage.isEmpty()) {
            return new ResponseEntity<>("모든 필드를 채워주세요", HttpStatus.BAD_REQUEST);
        }

        String uploadedImagePath;
        try {
            // Ensure the upload directory exists
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // Create a unique file name
            String uniqueFileName = UUID.randomUUID().toString() + "_" + bookImage.getOriginalFilename();
            File uploadFile = new File(uploadDir + uniqueFileName);

            // Save the file
            bookImage.transferTo(uploadFile);

            uploadedImagePath = "/" + uploadDir + uniqueFileName;
        } catch (IOException e) {
            return new ResponseEntity<>("파일 저장 실패", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        BookDTO bookDto = new BookDTO();
        bookDto.setTitle(title);
        bookDto.setWrither(writher);
        bookDto.setPublisher(publisher);
        bookDto.setYear(Integer.parseInt(year));
        bookDto.setBookImage(uploadedImagePath);

        int result = bookService.save(bookDto);
        if (result > 0) {
            return new ResponseEntity<>("책이 성공적으로 저장되었습니다", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("책 저장 실패", HttpStatus.INTERNAL_SERVER_ERROR);
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
