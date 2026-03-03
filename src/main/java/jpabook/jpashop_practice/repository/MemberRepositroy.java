package jpabook.jpashop_practice.repository;

import jakarta.persistence.EntityManager;
import jpabook.jpashop_practice.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepositroy {

    private final EntityManager em;

    // 회원 등록
    public Long save(Member member) {
        em.persist(member);
        return member.getId();
    }

    // 회원 단건 조회
    public Member findOne(Long memberId) {
        return em.find(Member.class, memberId);
    }

    // 회원 전체 조회
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    // 이름으로 회원 조회
    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }

}

