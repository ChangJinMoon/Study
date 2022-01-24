package jin.pratice.hellospring.repository;

import jin.pratice.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository memoryMemberRepository =
            new MemoryMemberRepository();

    @AfterEach // 콜백 함수 -> 하나의 test가 끝나면 자동적으로 호출
    void afterEach(){
        //클래스 전체를 테스트 할 경우 클래스 내에서 선언된 객체를 사용할 경우
        //충돌 가능성이 있으므로 Test method가 끝날때 마다 초기화 해주는 코드 삽입(중요)
        memoryMemberRepository.setClear();
    }

    @Test
    void save(){
        Member testMember = new Member();
        testMember.setName("Moon Chang Jin");

        memoryMemberRepository.save(testMember);

        Member result = memoryMemberRepository.findById(testMember.getId()).get();
        assertThat(testMember).isEqualTo(result);
    }

    @Test
    void findByname(){
        //Test Case 작성
        Member member = new Member();
        member.setName("Moon Chang Jin");
        memoryMemberRepository.save(member);
        //Tip: 복사 후 객체명 변환 단축키 -> shift + f6
        Member member2 = new Member();
        member2.setName("Test Name");
        memoryMemberRepository.save(member2);

        Member result =
                memoryMemberRepository.findByName(member2.getName()).get();
        //Assertions.assertThat()이 본문 -> import static org.assertj.core.api.Assertions.*;를 선언하여
        //Assertions 생략 -> 옵션 enter로 작성 가능 클래스명 생략
        assertThat(member2).isEqualTo(result);
    }

    @Test
    void findAll(){
        Member member = new Member();
        member.setName("Moon Chang Jin");
        memoryMemberRepository.save(member);

        Member member2 = new Member();
        member2.setName("Test Name");
        memoryMemberRepository.save(member2);

        //Test code
        List<Member> memberList =
                memoryMemberRepository.findAll();

        assertThat(2).isEqualTo(memberList.size());

    }
}
