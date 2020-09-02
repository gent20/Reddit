package gent.spring.reddit.Mapper;

import gent.spring.reddit.dto.PostRequest;
import gent.spring.reddit.dto.PostResponse;
import gent.spring.reddit.model.Post;
import gent.spring.reddit.model.Subreddit;
import gent.spring.reddit.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {


    @Mapping(target = "url", source = "postRequest.url")
    @Mapping(target = "postName", source = "postRequest.postName")
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "description", source = "postRequest.description")
    @Mapping(target = "subreddit", source = "subreddit")
    @Mapping(target = "user", source = "user")
    Post map(PostRequest postRequest, Subreddit subreddit, User user);

    @Mapping(target = "id", source = "postId")
    @Mapping(target = "subredditName", source = "subreddit.name")
    @Mapping(target = "userName", source = "user.username")
    PostResponse mapToDto(Post post);
}
