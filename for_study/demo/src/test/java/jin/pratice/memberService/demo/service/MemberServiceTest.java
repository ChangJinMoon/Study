package jin.pratice.memberService.demo.service;

import jin.pratice.memberService.demo.domain.Member;
import jin.pratice.memberService.demo.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

//Test 껍데기 만들기 단축키 -> command + shift + t
public class MemberServiceTest {
    MemberService memberService = new MemberService();
    MemoryMemberRepository memoryMemberRepository =
            new MemoryMemberRepository();

    @BeforeEach
    void beforeEach(){
        //각 Test 함수가 실행 되기전 실행되는 코드
    }

    @AfterEach
    void afterEach(){
        memoryMemberRepository.setClear();
        /**
         * 중요 !!!
         분명 MemberService내에 있는 MemoryMemberRepository 객체와
         Test내에 있는 MemoryMemberRepository 객체는 다른 객체이다
         하지만 Test에서의 MemoryMemberRepository의 인스턴스에서의
         repoistory를 초기화 하면 emberService내에 있는 MemoryMemberRepository
         의 객체에 영향을 미친다 이유는 무엇일까?
         이유는 간단하다 두 객체는 static으로 선언한 repository가 공유되기 때문이다
         static으로 선언한 변수 인스턴스 등은 단 하나만 존재하며 다른 객체에서 호출해도
         프로그램내에 있는 유일하게 하나 있는 static 인스턴스가 공유되는 것이다
         static void main을 잘 생각해보자
         */
    }
    /*
    내가 짠 Testcode
    @Test
    void join(){
        Member member = new Member();
        member.setName("Jin");
        memberService.joinInClass(member);

        //중복 확인 Testcode
        Member member2 = new Member();
        member2.setName("Bin");
        memberService.joinInClass(member2);
    }
     */

    @Test
    void 회원가입_중복_확인(){
        //given
        Member member = new Member();
        member.setName("Jin");
        Member member1 = new Member();
        member1.setName("Jin");
        //when
        memberService.joinInClass(member);
        IllegalStateException illegalStateException =
                org.junit.jupiter.api.Assertions.assertThrows(IllegalStateException.class,
                () -> memberService.joinInClass(member1)
        );
        /* try catch룰 아용한 방법
        try{
            memberService.joinInClass(member2);
            fail(); //수동으로 catch문으로 이동(강제로 실패하게 하기)
        }catch(IllegalException e){
                assertThat(e).isEqualsTo("이미 존재");
        }
        * */
        //then
        assertThat(illegalStateException.getMessage())
                .isEqualTo("이미 존재");
    }

    @Test
    void findAll(){
        Member member = new Member();
        member.setName("Jin");
        memberService.joinInClass(member);

        Member member2 = new Member();
        member2.setName("Bin");
        memberService.joinInClass(member2);

        assertThat(2)
                .isEqualTo(memberService.findAll().size());
    }

    @Test
    void findById(){
        Member member = new Member();
        member.setName("Jin");
        memberService.joinInClass(member);

        Optional<Member> result =
                memberService.findById(member.getId());

        result.ifPresent(m->{
            System.out.println("조회 Id:" + member.getId());
            System.out.println("검색 Id:" + m.getId());
            System.out.println("조회 일치");
        });
    }
}
