package controllers;

import models.PostTopic;
import services.PostTopicService;

import java.util.List;

public class PostTopicController {
    private final PostTopicService postTopicService;

    public PostTopicController(PostTopicService postTopicService) {
        this.postTopicService = postTopicService;
    }

    public long createKoppeling(int postId, int topicId) {
        PostTopic pt = new PostTopic();
        pt.setPostId(postId);
        pt.setTopicId(topicId);
        return postTopicService.addPostTopic(pt);
    }

    public List<PostTopic> getAllKoppelingen() {
        return postTopicService.getAllPostTopics();
    }

    public boolean deleteKoppeling(int id) {
        return postTopicService.deletePostTopic(id);
    }
}