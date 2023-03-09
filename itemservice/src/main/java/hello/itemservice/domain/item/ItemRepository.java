package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {
    private static final Map<Long, Item> store = new HashMap<>(); //static 사용
    //만약 멀티쓰레드 환경에서 store에 동시에 접근하게 된다면 HashMap<>();을 사용하면 안된다.
    //대신 ConcurrentHashMap<>(); 사용
    //item id가 Long이므로 key를 Long타입으로 
    private static long sequence = 0L; //static 사용
    // sequence도 마찬가지 어토믹? 등 다른걸 사용해야함.

    public Item save(Item item) { //저장
        item.setId(++sequence);
        store.put(item.getId(), item); // 아이템 넣어주고 
        return item; //반환
    }

    public Item findById(Long id) { //하나 조회
        return store.get(id);
    }

    public List<Item> findAll() {// 전체 조회, 항상 java.util.List;로 임포트 해야함.
        return new ArrayList<>(store.values());
        //그냥 반환해도 되지만 ArrayList에 넣어서 반환하면 안전하기 때문에 넣어서 반환
    }

    public void update(Long itemId, Item updateParam) {
        //정석으로 하려면 ItemDTO를 만들어서 따로 관리해야한다. 프로젝트 규모가 작아서 이렇게 간단하게
        Item findItem = findById(itemId); // 먼저 업데이트할 아이템 찾기
        findItem.setItemName(updateParam.getItemName()); // 파라미터 값을 넣어준다.
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStore() { //test용
        store.clear();
    }
}