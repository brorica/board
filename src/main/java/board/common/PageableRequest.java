package board.common;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageableRequest {

    private static final int DEFAULT_SIZE = 10;

    public static Pageable createPageRequest(int page, int size, Sort.Direction direction, String... properties) {
        if (size <= 0) {
            size = DEFAULT_SIZE;
        }
        return PageRequest.of(page, size, direction, properties);
    }
}
