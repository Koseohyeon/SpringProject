package inhatc.cse.spring.controller.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDTO {
    private int id;
    private String title;
    private String writher;
    private String publisher;
    private int year;
    private String bookImage;
}