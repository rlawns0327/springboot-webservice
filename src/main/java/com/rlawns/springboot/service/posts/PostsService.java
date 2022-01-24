package com.rlawns.springboot.service.posts;


import com.rlawns.springboot.domain.posts.Posts;
import com.rlawns.springboot.domain.posts.PostsRepository;
import com.rlawns.springboot.web.dto.PostsListResponseDto;
import com.rlawns.springboot.web.dto.PostsResponseDto;
import com.rlawns.springboot.web.dto.PostsSaveRequestDto;
import com.rlawns.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;


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

    @Transactional
    public void delete (Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" +id));
        postsRepository.delete(posts);
    }

    public PostsResponseDto findById (Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new
                IllegalArgumentException("해당 게시글이 없습니다. id="+ id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }



}

