package com.example.timesnap.web.project;

import com.example.timesnap.domain.project.model.Project;
import com.example.timesnap.domain.project.service.ProjectRepository;
import com.example.timesnap.web.config.auth.PrincipalDetails;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Validated
@AllArgsConstructor
@Slf4j
@RequestMapping("/project")
public class ProjectController {

    private final ProjectRepository projectRepository;

    @GetMapping("/list")
    public ResponseEntity<List<Project>> list(Authentication authentication) {
        //userid를 가져오기위해 Spring security Authentication 사용
        PrincipalDetails userDetails = (PrincipalDetails) authentication.getPrincipal();
        Long userid = userDetails.getId();

        //그룹존재하는지 확인
        final List<Project> projectList = projectRepository.findAllById(userid);

        return ResponseEntity.ok()
               .body(projectList);
    }

    @GetMapping("/groupForm")
    public String groupForm(){
        return "groupForm";
    }
}
