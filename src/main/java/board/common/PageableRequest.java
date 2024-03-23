package board.common;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageableRequest {

    private static final int DEFAULT_SIZE = 10;

    public static Pageable createPageRequest(int page, int size, Sort.Direction direction, String... properties) {
        size = DEFAULT_SIZE; // 유동적으로 값을 받을까 했지만 일단은 이렇게 해놓음
        page = Math.max(0, page);
        return PageRequest.of(page, size, direction, properties);
    }
}
