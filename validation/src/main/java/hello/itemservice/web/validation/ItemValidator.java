package hello.itemservice.web.validation;

import hello.itemservice.domain.item.Item;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component // 스프링이 등록
public class ItemValidator implements Validator { // 아이템 검증

    @Override
    public boolean supports(Class<?> clazz) {
//파라미터로 넘어오는 클래스가 아이템에 지원이 되는가?
        return Item.class.isAssignableFrom(clazz);
        //item == clazz
        //item == subItem
    }

    @Override
    public void validate(Object target, Errors errors) { // errors는 bindingResult가 바뀐것 
        Item item = (Item) target; // 타겟

        if (!StringUtils.hasText(item.getItemName())) {
            errors.rejectValue("itemName", "required");
        }
        if (item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000) {
            errors.rejectValue("price", "range", new Object[]{1000, 10000000}, null);
        }
        if (item.getQuantity() == null || item.getQuantity() >= 9999) {
            errors.rejectValue("quantity", "max", new Object[]{9999}, null);
        }

        //특정 필드가 아닌 복합 룰 검증
        if (item.getPrice() != null && item.getQuantity() != null) {
            int resultPrice = item.getPrice() * item.getQuantity();
            if (resultPrice < 10000) {
                errors.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
            }
        }
    }
}
