package hello.login.domain.login;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    /**
     * @return null 로그인 실패
     */
    public Member login(String loginId, String password) {
/* 1.       Optional<Member> findMemberOptional = memberRepository.findByLoginId(loginId);
        Member member = findMemberOptional.get();
        if (member.getPassword().equals(password)) {
            return member;
        } else {
            return null;
        }
        아래의 식으로 간단하게
   2.           Optional<Member> byLoginId = memberRepository.findByLoginId(loginId);
        return byLoginId.filter(m -> m.getPassword().equals(password)).orElse(null);

        return 뒤 byLoginId에 마우스커서 둔 뒤에 ctrl + alt + n 하면 줄여줌.
     */

        return memberRepository.findByLoginId(loginId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }
}
