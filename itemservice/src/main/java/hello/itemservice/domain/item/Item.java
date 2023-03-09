package hello.itemservice.domain.item;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//@Data//를 사용해도 되지만
//@Getter 나 @Setter 외에 다른 것들도 포함하고 있어 핵심 도메인에 사용하기 위험하므로
@Getter
@Setter //만 사용하자
public class Item {

    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }


}
