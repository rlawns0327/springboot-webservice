package com.rlawns.springboot.service.posts;



import com.rlawns.springboot.domain.posts.*;
import com.rlawns.springboot.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        //return postsRepository.count();
        return postsRepository.save(requestDto.toEntity()).getId();
    }
}

