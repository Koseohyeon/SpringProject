package inhatc.cse.spring.controller;

import inhatc.cse.spring.controller.dto.MemberDto;
import inhatc.cse.spring.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j //로그 찍어주는 어노테이션
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


   @GetMapping("/save")
    public String save(){

        log.info("==================회원 가입 처리중");
        return "member/save";
    }

    @PostMapping("/save")
    public String memberInsert(@ModelAttribute MemberDto memberDto){
        System.out.println("============="+memberDto);
        int result=memberService.save(memberDto);
        if(result>0){
            return "member/login";
        }else{
            return "member/save";
        }
    }

}
