package inhatc.cse.spring.controller;

import inhatc.cse.spring.controller.dto.BookDTO;
import inhatc.cse.spring.service.BookService;
import inhatc.cse.spring.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:300/bookList"})
@Slf4j
public class BookApiController {

    private final BookService bookService;

    @Autowired
    private FileUploadService fileUploadService;

    @PostMapping("/save")
    public ResponseEntity<?> saveBook(@RequestParam("title") String title,
                                      @RequestParam("writher") String writher,
                                      @RequestParam("publisher") String publisher,
                                      @RequestParam("year") String year,
                                      @RequestParam("bookImage") MultipartFile bookImage) {
        // 요청에서 받은 데이터를 검증하는 부분이 필요합니다.
        if (title == null || title.isEmpty() ||
                writher == null || writher.isEmpty() ||
                publisher == null || publisher.isEmpty() ||
                year == null || year.isEmpty() ||
                bookImage == null || bookImage.isEmpty()) {
            System.out.println("요청 데이터가 올바르지 않습니다.");
            return new ResponseEntity<>("모든 필드를 채워주세요", HttpStatus.BAD_REQUEST);
        }

        // 로그 추가
        System.out.println("Received title: " + title);
        System.out.println("Received writher: " + writher);
        System.out.println("Received publisher: " + publisher);
        System.out.println("Received year: " + year);
        System.out.println("Received bookImage: " + bookImage.getOriginalFilename());

        // 파일 업로드 서비스를 사용하여 파일을 저장합니다.
        String uploadedImagePath;
        try {
            uploadedImagePath = fileUploadService.uploadFile(bookImage);
        } catch (IOException e) {
            System.out.println("파일 저장 실패: " + e.getMessage());
            return new ResponseEntity<>("파일 저장 실패", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // BookDTO 객체 생성 및 설정
        BookDTO bookDto = new BookDTO();
        bookDto.setTitle(title);
        bookDto.setWrither(writher);
        bookDto.setPublisher(publisher);
        bookDto.setYear(Integer.parseInt(year));
        bookDto.setBookImage(uploadedImagePath); // 파일 경로 설정


        // 책 저장
        int result = bookService.save(bookDto);
        if (result > 0) {
            System.out.println("책이 성공적으로 저장되었습니다");
            return new ResponseEntity<>("책이 성공적으로 저장되었습니다", HttpStatus.OK);
        } else {
            System.out.println("책 저장 실패");
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
