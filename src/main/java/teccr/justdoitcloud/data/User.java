package teccr.justdoitcloud.data;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.List;

@Data
@RequiredArgsConstructor
@Table("users")
public class User {
    @Id
    private final Long id;
    @Column("user_name")
    private final String username;
    private final String name;
    private final String email;
    private final Type type;
    @Transient
    private List<Task> tasks;
    @Column("created_at")
    private LocalDateTime createdAt;

    public enum Type {
        ADMIN,
        REGULAR
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
