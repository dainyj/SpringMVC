package hello.itemservice.validation;

import org.junit.jupiter.api.Test;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.FieldError;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.ObjectError;

import static org.assertj.core.api.Assertions.*;

public class MessageCodesResolverTest {

    MessageCodesResolver codesResolver = new DefaultMessageCodesResolver();

    @Test
    void messageCodesResolverObject() {
        String[] messageCodes = codesResolver.resolveMessageCodes("required", "item");

//        for (String messageCode :
//                messageCodes) { // 결과가 어떻게 나오는지 확인
//            System.out.println("messageCode = " + messageCode);
//        }
        //결과-> messageCode = required.item / messageCode = required

//        new ObjectError("item" new String[]{"required.item", "required"})
//                                        위의 결과로 나온 순서대로 {자세하게 먼저, 범용적인건 뒤로}

        //검증
        assertThat(messageCodes).containsExactly("required.item", "required");
    }

    @Test
    void messageCodesResolverField() {
        String[] messageCodes = codesResolver.resolveMessageCodes("required", "item", "itemName", String.class);
        //                                                                         필드명, 필드타입
        for (String messageCode :
                messageCodes) {
            System.out.println("messageCode = " + messageCode);
        }
        //결과 -> messageCode = required.item.itemName
        //결과 -> messageCode = required.itemName
        //결과 -> messageCode = required.java.lang.String
        //결과 -> messageCode = required

//        bindingResult.rejectValue("itemName", "required");
//        new FieldError("item", "itemName", null, false, messageCodes, null, null);
//                                         위 결과들을 차례대로 넣어준다.
        assertThat(messageCodes).containsExactly(
                "required.item.itemName",
                "required.itemName",
                "required.java.lang.String",
                "required"
        );
    }

}
