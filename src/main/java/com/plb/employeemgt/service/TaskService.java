package com.plb.employeemgt.service;

import com.plb.employeemgt.entity.Task;
import com.plb.employeemgt.repository.TaskRepository;
import com.plb.employeemgt.service.dto.TaskDTO;
import com.plb.employeemgt.service.dto.VinylDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<TaskDTO> getAll() {
        // Etape 1
        List<Task> allTasks = taskRepository.findAll();
        // Instanciation de ma liste de DTO
        List<TaskDTO> taskDTOS = new ArrayList<>();
        // Boucle sur les entites tasks
        // Etape 2
        for (Task task : allTasks) {
            // Creation de mes DTOs
            TaskDTO taskDTO = new TaskDTO();
            taskDTO.setDescription(task.getDescription());
            taskDTO.setTitle(task.getTitle());
            // Ajout dans le tableau de sorti
            taskDTOS.add(taskDTO);
        }

        return taskDTOS;
    }
}
