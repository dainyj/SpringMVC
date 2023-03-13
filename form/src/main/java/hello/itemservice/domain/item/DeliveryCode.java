package hello.itemservice.domain.item;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * FAST: 빠른 배송
 * NORMAL: 일반 배송
 * SLOW: 느린 배송
 */
@Data
@AllArgsConstructor
public class DeliveryCode { //배송 바익
    private String code;
    // fast, normal, slow 을 시스템에 전달하는 값
    private String displayName;
    // 빠른 배송, 일반 배송, 느린 배송 을 고객에게 보여주는 값
}
