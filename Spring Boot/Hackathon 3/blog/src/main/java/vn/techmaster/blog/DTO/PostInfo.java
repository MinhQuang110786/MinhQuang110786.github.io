package vn.techmaster.blog.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.techmaster.blog.model.Tag;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostInfo {
    private Long id;
    private String title;
    private String content;
    private Long user_id;
    private String userFullName;
    private LocalDateTime lastUpdate;
    private Set<TagPOJO> tags;
    private int comments;
}
