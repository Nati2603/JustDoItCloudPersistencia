package teccr.justdoitcloud.service;

import org.springframework.stereotype.Service;
import teccr.justdoitcloud.data.Task;
import teccr.justdoitcloud.data.User;
import teccr.justdoitcloud.repository.TaskRepository;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getTasksForUser(User user) {
        return taskRepository.findByUserId(user.getId());
    }

    public void addTaskToUser(User user, Task task) {
        task.setUserId(user.getId());
        taskRepository.save(task);
    }
    public void advanceTask(Long taskId) {

        Task task = taskRepository.findById(taskId).orElseThrow();

        Task.Status newStatus = task.getStatus();

        if (task.getStatus() == Task.Status.PENDING) {
            newStatus = Task.Status.INPROGRESS;
        } else if (task.getStatus() == Task.Status.INPROGRESS) {
            newStatus = Task.Status.DONE;
        }

        Task updatedTask = new Task(
                task.getId(),
                task.getDescription(),
                task.getCreatedAt(),
                task.getDeadline(),
                newStatus
        );

        updatedTask.setUserId(task.getUserId());

        taskRepository.save(updatedTask);
    }
}
