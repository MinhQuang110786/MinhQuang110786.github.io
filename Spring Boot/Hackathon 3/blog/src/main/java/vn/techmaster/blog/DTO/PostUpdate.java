package vn.techmaster.blog.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostUpdate {
    private String title;
    private String content;
    private Set<Integer> tag_id;
}
