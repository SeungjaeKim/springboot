package kr.co.springboot.domain.posts;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @AfterEach
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    @DisplayName("게시글저장 불러오기")
    public void postInsertAndGetList(){

        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("test@gmail.com")
                .build());

        //when
        List<Posts> postList = postsRepository.findAll();

        //then
        Posts post = postList.get(0);
        assertThat(post.getTitle()).isEqualTo(title);
    }

    @Test
    @DisplayName(value = "BaseTimeEntity 등록")
    public void BaseTimeEntityInsert(){
        //given
        LocalDateTime now = LocalDateTime.of(2020, 11, 9, 0, 0, 0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("test@gmail.com")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts post = postsList.get(0);

        System.out.println(">>>>>>>>>> createdDate=" + post.getCreateDate() + ", modifiedDate=" + post.getModifiedDate());

        Assertions.assertThat(post.getCreateDate()).isAfter(now);
        Assertions.assertThat(post.getModifiedDate()).isAfter(now);
    }
}
