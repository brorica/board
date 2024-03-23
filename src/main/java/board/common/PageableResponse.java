package board.common;

import java.util.Collections;
import java.util.List;

public class PageableResponse<T> {

    private final List<T> content;
    private final int currentPage;
    private final boolean first;
    private final boolean last;
    private final List<Integer> pageNumbers;

    public PageableResponse(final List<T> content, int currentPage, boolean first, boolean last, List<Integer> pageNumbers) {
        this.content = Collections.unmodifiableList(content);
        this.currentPage = currentPage;
        this.first = first;
        this.last = last;
        this.pageNumbers = Collections.unmodifiableList(pageNumbers);
    }

    public List<T> getContent() {
        return content;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public boolean isFirst() {
        return first;
    }

    public boolean isLast() {
        return last;
    }

    public List<Integer> getPageNumbers() {
        return pageNumbers;
    }
}
