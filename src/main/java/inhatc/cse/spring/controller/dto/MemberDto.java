package inhatc.cse.spring.controller.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor  //기본생성자
@AllArgsConstructor //전체 다 채워진 생성자
@Builder
public class MemberDto {
    private String email;
    private String password;
    private String name;
    private int age;
    private String mobile;
}
