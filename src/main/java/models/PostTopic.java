package models;

public class PostTopic {
    private int id;
    private int postId;
    private int topicId;

    public PostTopic() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    @Override
    public String toString() {
        return "PostTopic{" +
                "id=" + id +
                ", postId=" + postId +
                ", topicId=" + topicId +
                '}';
    }
}
