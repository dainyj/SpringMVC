package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//@Slf4j //설정시 아래의 private final Logger log를 직접 추가 하지 않아도 자동으로 추가됨.
@RestController// 문자를 return하면 String 그래도 반환된다.
//@Controller // 뷰 이름이 return
public class LogTestController {

    //    private final Logger log = LoggerFactory.getLogger(LogTestController.class());
    private final Logger log = LoggerFactory.getLogger(getClass());
    // 둘 중 편한걸 사용하면 된다.

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        System.out.println("name = " + name);

        log.trace("trace log={}", name);
        log.debug("debug log={}", name);
        log.info(" info log={}", name);
        log.warn(" warn log={}", name);
        log.error("error log={}", name);
        log.info("info log={}", name);

        //log.debug("data="+data) //  연산이 발생해서 로그가
        //로그 출력 레벨을 info로 설정해도 해당 코드에 있는 "data="+data가 실제 실행이 되어 버린다.
        //결과적으로 문자 더하기 연산이 발생한다.

        return "ok";
    }
}
