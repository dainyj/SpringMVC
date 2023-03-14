package hello.itemservice.web.validation;

import hello.itemservice.web.validation.form.ItemSaveForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/validation/api/items")
public class ValidationItemApiController {

    @PostMapping("/add")
    public Object addItem(@RequestBody @Validated ItemSaveForm form, BindingResult bindingResult) {
        //ItemSaveForm form을 json형식으로 받고 싶다. 검증을 추가하면서 BindingResult도 같이 넣어줘야함.
        log.info("API 컨트롤러 호출");

        if (bindingResult.hasErrors()) { // json API 받음.
            log.info("검증 오류 발생 errors={}", bindingResult);
            return bindingResult.getAllErrors();// json으로 반환됨.
        }

        log.info("성공 로직 실행");
        return form;
    }
}
