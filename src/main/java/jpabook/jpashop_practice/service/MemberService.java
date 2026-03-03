package jpabook.jpashop_practice.service;

import jpabook.jpashop_practice.domain.Member;
import jpabook.jpashop_practice.exception.NotFoundMemberException;
import jpabook.jpashop_practice.repository.MemberRepositroy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepositroy memberRepositroy;

    // 회원 등록
    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member);
        return memberRepositroy.save(member);
    }

    //==중복 회원 검증 로직==//
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepositroy.findByName(member.getName());
        if(!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 회원 단건 조회
    public Member findOne(Long memberId) {
        return memberRepositroy.findOne(memberId);
    }

    // 전체 회원 조회
    public List<Member> findAll() {
        return memberRepositroy.findAll();
    }

    // 이름으로 회원 조회
    public List<Member> findByName(String name) {
        return memberRepositroy.findByName(name);
    }
}
