package vn.techmaster.blog.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostAPI {
    private String title;
    private String content;
    private Long user_id;
    private Set<Integer> tag_id;
}
