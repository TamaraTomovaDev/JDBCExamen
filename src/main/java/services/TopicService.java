package services;

import models.Topic;
import repositories.TopicRepository;

import java.sql.SQLException;
import java.util.List;

public class TopicService {
    private final TopicRepository topicRepository = new TopicRepository();

    public long addTopic(Topic topic) {
        try {
            return topicRepository.create(topic);
        } catch (SQLException e){
            System.out.println("Fout bij toevoegen van topic: " + e.getMessage());
            return -1;
        }
    }

    public List<Topic> getAllTopics() {
        try {
            return topicRepository.read();
        } catch (SQLException e){
            System.out.println("Fout bij ophalen van topic: " + e.getMessage());
            return List.of();
        }
    }

    public boolean updateTopic(int id, Topic topic) {
        try {
            return topicRepository.update(id, topic) > 0;
        } catch (SQLException e){
            System.out.println("Fout bij ophalen van topic: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteTopic(int id) {
        try {
            return topicRepository.delete(id) > 0;
        } catch (SQLException e){
            System.out.println("Fout bij verwijderen van topic: " + e.getMessage());
            return false;
        }
    }
}

