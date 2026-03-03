package jpabook.jpashop_practice.service;

import jpabook.jpashop_practice.domain.Member;
import jpabook.jpashop_practice.repository.MemberRepositroy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberRepositroy memberRepositroy;
    @Autowired
    MemberService memberService;

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("spring");
        memberService.join(member);

        //when
        Member findMember = memberRepositroy.findOne(member.getId());

        //then
        assertThat(member).isEqualTo(findMember);
    }

    @Test
    void 중복_회원가입_실패() {
        //given
        Member member1 = new Member();
        member1.setName("spring");
        memberService.join(member1);

        Member member2 = new Member();
        member2.setName("spring");

        assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));
    }

    @Test
    void 회원_전체_조회() {
        //given
        Member member1 = new Member();
        member1.setName("spring");
        memberService.join(member1);

        Member member2 = new Member();
        member2.setName("java");
        memberService.join(member2);

        //when
        List<Member> findMembers = memberService.findMembers();

        //then
        assertThat(findMembers.size()).isEqualTo(2);
    }

    @Test
    void 회원_단건_조회() {
        //given
        Member member1 = new Member();
        member1.setName("spring");
        memberService.join(member1);

        //when
        Member findMember = memberService.findOne(member1.getId());

        //then
        assertThat(findMember).isEqualTo(member1);
    }

}