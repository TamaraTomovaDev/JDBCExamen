package services;

import models.PostTopic;
import repositories.PostTopicRepository;

import java.sql.SQLException;
import java.util.List;

public class PostTopicService {
    private final PostTopicRepository postTopicRepository = new PostTopicRepository();

    public int addPostTopic(PostTopic postTopic) {
        try {
            return postTopicRepository.create(postTopic);
        } catch (SQLException e) {
            System.out.println("Fout bij toevoegen van post_topic: " + e.getMessage());
            return -1;
        }
    }

    public List<PostTopic> getAllPostTopics() {
        try {
            return postTopicRepository.read();
        } catch (SQLException e) {
            System.out.println("Fout bij ophalen van post_topic: " + e.getMessage());
            return List.of();
        }
    }

    public boolean updatePostTopic(int id, PostTopic postTopic) {
        try {
            return postTopicRepository.update(id, postTopic) > 0;
        } catch (SQLException e) {
            System.out.println("Fout bij updaten van post_topic: " + e.getMessage());
            return false;
        }
    }

    public boolean deletePostTopic(int id) {
        try {
            return postTopicRepository.delete(id) > 0;
        } catch (SQLException e) {
            System.out.println("Fout bij verwijderen van posts: " + e.getMessage());
            return false;
        }
    }
}
