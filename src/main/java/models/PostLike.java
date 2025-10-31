package models;

public class PostLike {
    private int id;
    private int user_id;
    private int post_id;

    public PostLike() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    @Override
    public String toString() {
        return "PostLike{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", post_id=" + post_id +
                '}';
    }
}
