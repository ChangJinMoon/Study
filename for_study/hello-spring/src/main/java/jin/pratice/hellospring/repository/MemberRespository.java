package jin.pratice.hellospring.repository;

import jin.pratice.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRespository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    //Optional -> Null 값을 처리 해주는 일종의 래퍼 클래스
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
