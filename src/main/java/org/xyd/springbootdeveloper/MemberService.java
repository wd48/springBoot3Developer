package org.xyd.springbootdeveloper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    public void test() {
        // create
        memberRepository.save(new Member(1L, "A"));

        // read
        Optional<Member> member = memberRepository.findById(1L); // select one
        List<Member> allMembers = memberRepository.findAll();   // select list

        // delete
        memberRepository.deleteById(1L);
    }
}
