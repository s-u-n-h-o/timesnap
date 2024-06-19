package com.example.timesnap.web.project;

import com.example.timesnap.domain.project.model.Project;
import com.example.timesnap.domain.project.service.ProjectRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/project")
@Validated
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectRepository projectRepository;

    @GetMapping("/list")
    public ResponseEntity<List<Project>> list(@RequestParam(required = false) Long userid) {

        //그룹존재하는지 확인
        final List<Project> projectList = projectRepository.findAllById(userid);

       return ResponseEntity.ok()
               .body(projectList);
    }
}
