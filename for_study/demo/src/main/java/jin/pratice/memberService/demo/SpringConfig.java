package jin.pratice.memberService.demo;

import jin.pratice.memberService.demo.repository.MemberRespository;
import jin.pratice.memberService.demo.repository.MemoryMemberRepository;
import jin.pratice.memberService.demo.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 자바로 빈 등록 및 DI(의존성 삽입) 진행
 * Configuration 어노테이션이 붙은 클래스를 spring이 찾아 빈을 등록한다.
 * 빈 등록은 중복이 불가능하다
 */
@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRespository());
    }

    @Bean
    public MemberRespository memberRespository(){
        return new MemoryMemberRepository();
    }
    /**
     * 의존성 주입(DI)
     * 1. 생성자 주입 방법
     *  ex) @Bean
     *     public MemberService memberService(){
     *         return new MemberService(memberRespository());
     *     }
     *
     * 2. 필드 주입
     *  ex)
     * @Autowired private MemberService memberservice;
     *
     * 3.Getter를 이용한 삽입
     * @Autowired
     * public void getMemberService(MemberService memberService){
     *     this.memberService = memberService;
     * }
     *
     * 생성자 주입 추천 -> 한번만 실행되고 이후 변경이 불가능 하므로 좋다
     * @Autowired -> Bean으로 등록되지 않은 클래스에 사용할 수 없다.
     */
}
