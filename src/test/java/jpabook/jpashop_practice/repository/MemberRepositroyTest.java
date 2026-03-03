package jpabook.jpashop_practice.repository;

import jpabook.jpashop_practice.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberRepositroyTest {

    @Autowired
    MemberRepositroy memberRepositroy;

    @Test
    void 회원_save() {
        //given
        Member member = new Member();
        member.setName("hello");

        //when
        memberRepositroy.save(member);

        //then
        assertThat(member).isEqualTo(memberRepositroy.findOne(member.getId()));
    }

    @Test
    void 회원_findAll() {
        //given
        Member member1 = new Member();
        Member member2 = new Member();
        member1.setName("spring1");
        member2.setName("spring2");
        memberRepositroy.save(member1);
        memberRepositroy.save(member2);

        //when
        List<Member> memberList = memberRepositroy.findAll();

        //then
        assertThat(memberList.size()).isEqualTo(2);
    }

    @Test
    void 회원_findByName() {
        //given
        Member member = new Member();
        member.setName("spring");
        memberRepositroy.save(member);

        //when
        List<Member> memberList = memberRepositroy.findByName(member.getName());

        //then
        assertThat(memberList.get(0).getName()).isEqualTo("spring");
    }
}