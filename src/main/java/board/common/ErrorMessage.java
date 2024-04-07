package board.common;

public class ErrorMessage {

    private final String title;       // "You do not have enough credit.",
    private final String detail;      // "Your current balance is 30, but that costs 50.",
    private final String instance;    // /account/12345/msgs/abc",

    public ErrorMessage(final String title, final String detail, final String instance) {
        this.title = title;
        this.detail = detail;
        this.instance = instance;
    }

    public ErrorMessage(final Builder builder) {
        this.title = builder.title;
        this.detail = builder.detail;
        this.instance = builder.instance;
    }
    // Builder
    public static class Builder {
        private String title;
        private String detail;
        private String instance;

        public Builder() {
        }

        public Builder title(final String title) {
            this.title = title;
            return this;
        }

        public Builder detail(final String detail) {
            this.detail = detail;
            return this;
        }

        public Builder instance(final String instance) {
            this.instance = instance;
            return this;
        }

        public ErrorMessage build() {
            return new ErrorMessage(this);
        }
    }
    // getter
    public String getTitle() {
        return title;
    }

    public String getDetail() {
        return detail;
    }

    public String getInstance() {
        return instance;
    }
}
