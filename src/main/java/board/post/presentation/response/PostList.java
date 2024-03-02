package board.post.presentation.response;

import java.util.Collections;
import java.util.List;

public class PostList {

    private final List<PostListEntry> postList;

    private long lastId = Long.MAX_VALUE;

    public PostList(final List<PostListEntry> postListEntryList) {
        this.postList = Collections.unmodifiableList(postListEntryList);
        if (!postList.isEmpty()) {
            this.lastId = postList.get(postList.size() - 1).getPostId();
        }
    }

    public List<PostListEntry> getPostList() {
        return postList;
    }

    public long getLastId() {
        return lastId;
    }
}
