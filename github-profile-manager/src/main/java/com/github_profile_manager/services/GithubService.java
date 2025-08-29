package com.github_profile_manager.services;

import com.github_profile_manager.entities.User;
import com.github_profile_manager.repositories.UserRepository;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class GithubService {

    private UserRepository userRepository;
    private WebClient webClient;

    public GithubService(UserRepository userRepository, WebClient webClient) {
        this.userRepository = userRepository;
        this.webClient = webClient;
    }

    @PostConstruct
    @Transactional
    public void getGitHubUsers() {
        List<User> githubUsers = webClient.get()
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Map<String, Object>>>() {})
                .onErrorMap(ex -> new RuntimeException("Failed to retrieve users", ex))
                .block()
                .stream()
                .limit(30)
                .map(userData -> new User(
                        (String) userData.get("login"),
                        (String) userData.get("url")
                ))
                .collect(Collectors.toList());

        userRepository.saveAll(githubUsers);
    }
}