package inhatc.cse.spring.repository;

import inhatc.cse.spring.controller.dto.BookDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookRepository {

    private final SqlSessionTemplate sqlSession;

    public int save(BookDTO bookDto) {
        return sqlSession.insert("Book.save", bookDto);
    }

    public List<BookDTO> findAll() {
        return sqlSession.selectList("Book.findAll");
    }

    public BookDTO findById(int id) {
        return sqlSession.selectOne("Book.findById", id);
    }
}