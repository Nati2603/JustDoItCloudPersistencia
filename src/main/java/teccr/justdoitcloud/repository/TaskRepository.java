package teccr.justdoitcloud.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import teccr.justdoitcloud.data.Task;

import java.util.List;

/**
 * Repository abstraction for task operations.
 */
public interface TaskRepository extends CrudRepository<Task, Long> {

    /**
     * Return the list of tasks for a given username. If user doesn't exist, return empty list.
     */
    List<Task> findByUserId(Long userId);


    @Query("""
    SELECT *
    FROM task
    WHERE user_id = :userId
      AND status = :status
      AND due_time >= CURRENT_TIME
    ORDER BY due_time ASC
    LIMIT 5
    """)
    List<Task> findTop5UpcomingByUserAndStatus(@Param("userId") Long userId,
                                               @Param("status") String status);

}
