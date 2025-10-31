package controllers;

import models.Topic;
import services.TopicService;

import java.util.List;

public class TopicController {
    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    public long createTopic(String name, String slug) {
        Topic topic = new Topic();
        topic.setName(name);
        topic.setSlug(slug);
        return topicService.addTopic(topic);
    }

    public List<Topic> getAllTopics() {
        return topicService.getAllTopics();
    }

    public boolean updateTopic(int id, String name, String slug) {
        Topic topic = new Topic();
        topic.setName(name);
        topic.setSlug(slug);
        return topicService.updateTopic(id, topic);
    }

    public boolean deleteTopic(int id) {
        return topicService.deleteTopic(id);
    }
}