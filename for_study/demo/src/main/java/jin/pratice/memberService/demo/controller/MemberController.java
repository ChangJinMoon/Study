package jin.pratice.memberService.demo.controller;

import jin.pratice.memberService.demo.domain.Member;
import jin.pratice.memberService.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * Componet(Controller,Service등등) 스캔 범위
 * @SpringBootApplication으로 등록된 클래스 기준 동일 혹은 하위 디렉토리까지 spring에서 인식가능
 * @Component 가 있으면 스프링에서 자동으로 빈으로 등록한다(컴포넌트 스캔 및 등록)
 * 빈 등록은 싱글톤(하나만 등록하여 공유하는 것)
 */

/**
 * 추가로 공부할 것
 * 1.Form에 대해 알아보기
 * 2.httpmethod 개념 다시보기
 */
@Controller
public class MemberController {
    MemberService memberService;
    @Autowired//-> spring이 container에 담겨져 있는 memberservice (빈)인스턴스를 자동으로 연결해준다(의존성 주입)
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMembersForm";
    }

    @PostMapping("/members/new")//createMembersForm 페이지에서 보낸 Post형식의 데이터 처리
    //받은 데이터는 자동으로 MemberFrom객체에 들어간다. -> name키의 값이 MemberForm의 변수 name에 자동 저장
    public String createMember(MemberForm memberForm){
        Member newMember = new Member();
        newMember.setName(memberForm.getName());
        memberService.join(newMember);
        return "redirect:/";
    }
    @GetMapping("/members")
    public String findall(Model model){
        List<Member> result = memberService.findAll();
        model.addAttribute("members",result);
        return "members/allMembers";
    }
}
