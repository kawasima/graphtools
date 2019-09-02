package net.unit8.graphtool;

import java.time.LocalDate;

public class Friend extends EdgeEntity {
    private LocalDate since;

    public LocalDate getSince() {
        return since;
    }

    public static class Builder {
        private Friend friend;

        public Builder() {
            friend = new Friend();
        }

        public Builder since(LocalDate since) {
            friend.since = since;
            return this;
        }

        public Friend build() {
            return friend;
        }
    }
}
