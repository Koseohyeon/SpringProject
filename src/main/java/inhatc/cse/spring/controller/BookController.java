package inhatc.cse.spring.controller;

import inhatc.cse.spring.controller.dto.BookDTO;
import inhatc.cse.spring.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    @GetMapping("/save")
    public String save() {
        log.info("===================도서 등록 처리중");
        return "book/save";
    }

    @PostMapping("/save")
    public String bookInsert(BookDTO bookDto) {
        log.info("================" + bookDto);
        int result = bookService.save(bookDto);
        if (result > 0) {
            return "redirect:/book/list";
        } else {
            return "book/save";
        }
    }

    @GetMapping("/list")
    public String bookList(Model model) {
        List<BookDTO> bookList = bookService.findAll();
        log.info("========>" + bookList);
        model.addAttribute("bookList", bookList);
        return "book/list";
    }
}