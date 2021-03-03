package gent.spring.reddit.services;

import gent.spring.reddit.Mapper.PostMapper;
import gent.spring.reddit.dto.PostResponse;
import gent.spring.reddit.model.Post;
import gent.spring.reddit.repositories.PostRepository;
import gent.spring.reddit.repositories.SubredditRepository;
import gent.spring.reddit.repositories.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @Mock
    private PostRepository postRepository;
    @Mock
    private SubredditRepository subredditRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private AuthService authService;
    @Mock
    private PostMapper postMapper;

    @Test
    @DisplayName("Should find post by id")
    void shouldFindPostById() {
        PostService postService = new PostService(subredditRepository,postRepository,
                userRepository, authService,postMapper);
        Post post = new Post(123L,"First Post","http://url.site","Test",0,
                null, Instant.now(),null);
        PostResponse expectedPostResponse = new PostResponse(123L, "First Post", "http://url.site", "Test",
                "Test User", "Test Subredit", 0, 0, "1 Hour Ago");

        Mockito.when(postRepository.findById(123L)).thenReturn(Optional.of(post));
        Mockito.when(postMapper.mapToDto(Mockito.any(Post.class))).thenReturn(expectedPostResponse);

        PostResponse actualPostResponse = postService.getPost(123L);

        Assertions.assertThat(actualPostResponse.getId()).isEqualTo(expectedPostResponse.getId());
        Assertions.assertThat(actualPostResponse.getPostName()).isEqualTo(expectedPostResponse.getPostName());
    }
}