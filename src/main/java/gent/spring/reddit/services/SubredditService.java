package gent.spring.reddit.services;


import gent.spring.reddit.dto.SubredditDto;
import gent.spring.reddit.model.Subreddit;
import gent.spring.reddit.repositories.SubredditRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
public class SubredditService {

    private final SubredditRepository subredditRepository;

    @Transactional
    public SubredditDto save(SubredditDto subredditDto){
      Subreddit save =subredditRepository.save(mapSubredditDto(subredditDto));
      subredditDto.setId(subredditDto.getId());
      return subredditDto;
    }

    @Transactional(readOnly = true)
    public List<SubredditDto> getAll() {
       return subredditRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(toList());
    }

    private SubredditDto mapToDto(Subreddit subreddit){
            return SubredditDto.builder()
                    .name(subreddit.getName())
                    .id(subreddit.getId())
                    .numberOfPosts(subreddit.getPosts().size())
                    .build();
    }

    private Subreddit mapSubredditDto(SubredditDto subredditDto) {
        return Subreddit.builder()
                .name(subredditDto.getName())
                .description(subredditDto.getDescription())
                .build();
    }

}
