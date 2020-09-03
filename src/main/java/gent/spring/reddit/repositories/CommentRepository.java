package gent.spring.reddit.repositories;

import gent.spring.reddit.model.Comment;
import gent.spring.reddit.model.Post;
import gent.spring.reddit.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);

    List<Comment> findAllByUser(User user);
}
