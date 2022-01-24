package jin.pratice.memberService.demo.service;

import jin.pratice.memberService.demo.domain.Member;
import jin.pratice.memberService.demo.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    MemoryMemberRepository memoryMemberRepository =
            new MemoryMemberRepository();
    /*
    회원 가입
     */
    public Long join(Member member){
        //중복 이름 가입 불가능 비지니스 로직 삽입
        if(catchDoubleName(member.getName()))
            return null;
        memoryMemberRepository.save(member);
        return member.getId();
        //hashmap에 삽입된 member는 주소를 공유하므로 바로 사용가능
    }

    private boolean catchDoubleName(String newName){
        //리턴값에 맞추어 자동 객체 변수 선언 단축키 command + option + v
        Optional<Member> byId = memoryMemberRepository.findByName(newName);
        if(memoryMemberRepository.findByName(newName).isEmpty())
            return false;
        else
            return true;
    }

    public Long joinInClass(Member member){
        //중복 이름 가입 불가능 비지니스 로직 삽입
        Optional<Member> result = memoryMemberRepository.findByName(member.getName());
        /*
            Consummer 타입의 함수 조사하기
            매개변수로 람다함수 사용가능한 이유 알아내기
         */
        result.ifPresent(m ->{
            System.out.println("존재하는 이름:"+m.getName());
            throw new IllegalStateException("이미 존재");
        });

        memoryMemberRepository.save(member);
        return member.getId();
        //hashmap에 삽입된 member는 주소를 공유하므로 바로 사용가능
    }

    /**
     * 전체회원 조회
     */

    public List<Member> findAll(){
        return memoryMemberRepository.findAll();
    }

    public Optional<Member> findById(Long id){
        return memoryMemberRepository.findById(id);
    }

}
