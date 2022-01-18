package com.rlawns.springboot.service.posts;



import com.rlawns.springboot.domain.posts.*;
import com.rlawns.springboot.web.dto.PostsResponseDto;
import com.rlawns.springboot.web.dto.PostsSaveRequestDto;
import com.rlawns.springboot.web.dto.PostsUpdateRequestDto;
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

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto responseDto){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new
                IllegalAccessError("해당 게시글이 없습니다. id="+ id));

        posts.update(responseDto.getTitle(), responseDto.getContent());

        return id ;
    }

    public PostsResponseDto findById (Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new
                IllegalArgumentException("해당 게시글이 없습니다. id="+ id));

        return new PostsResponseDto(entity);
    }
}

