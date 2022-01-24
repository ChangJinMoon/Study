package jin.pratice.hellospring.repository;

import jin.pratice.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRespository {

    private static Map<Long, Member> repository = new HashMap<>();
    private long sequence = 0L;

    public void setClear(){
        //Test code를 위한 함수
        repository.clear();
    }

    @Override
    public Member save(Member member) {
        member.setId(sequence++);
        repository.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(repository.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        /*
        * 람다 정리하기
        * stream().filter()에 관해 조사하기
        * */
        return repository.values().stream()
                        .filter(member -> member.getName().equals(name))
                        .findAny();
    }

    @Override
    public List<Member> findAll() {
        /*
        * Collection에 관해 조사하기
        * */
        return new ArrayList<>(repository.values());
    }
}
