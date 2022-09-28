package com.plb.employeemgt.web.rest;

import com.plb.employeemgt.service.JobService;
import com.plb.employeemgt.service.TaskService;
import com.plb.employeemgt.service.dto.JobDTO;
import com.plb.employeemgt.service.dto.TaskDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskResource {

    private final TaskService taskService;

    public TaskResource(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAll() {
        // Etape 5 : Creation de ma resource (VinylResource)
        // Etape 6 : Quelle va etre la signature de ma methode ?
        // Etape 7 : Quelles sont les codes HTTP qui je dois renvoyer ?
        // Etape 8 : Ecriture du code de ma resource
        List<TaskDTO> allTasks = taskService.getAll();

        if (allTasks.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(allTasks);
        }
    }
}
