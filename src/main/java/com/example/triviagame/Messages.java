package com.example.triviagame;


public class Messages {
    private String title;
    private String header;
    private String content;


    public String getTitle() {
        return title;
    }

    public String getHeader() {
        return header;
    }

    public String getContent() {
        return content;
    }


    public static class MessagesBuilder {
        private String title;
        private String header;
        private String content;

        public MessagesBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public MessagesBuilder setHeader(String header) {
            this.header = header;
            return this;
        }

        public MessagesBuilder setContent(String content) {
            this.content = content;
            return this;
        }

        public Messages build() {
            Messages messages = new Messages();
            messages.title = this.title;
            messages.content = this.content;
            messages.header = this.header;
            return messages;
        }
    }
}
