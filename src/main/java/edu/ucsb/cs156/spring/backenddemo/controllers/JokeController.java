package edu.ucsb.cs156.spring.backenddemo.controllers;

import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;

import edu.ucsb.cs156.spring.backenddemo.services.JokeQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="Joke creater")
@Slf4j
@RestController
@RequestMapping("/api/jokes")
public class JokeController {
    @Autowired
    JokeQueryService jokeQueryService;

    @Operation(summary = "Get a number of jokes given a certain category", description = "JSON return format documented here: https://v2.jokeapi.dev/joke/{category}?amount={numJokes}")
    @GetMapping("/get")
    public ResponseEntity<String> getJokes(
        @Parameter(name="category", description="category of jokes", example="Programming") @RequestParam String category,
        @Parameter(name="numJokes", description="number of jokes requested", example="2") @RequestParam int numJokes
    ) throws JsonProcessingException {
        log.info("getJokes: category={} numJokes={}", category, numJokes);
        String result = jokeQueryService.getJSON(category, numJokes);
        return ResponseEntity.ok().body(result);
    }

}
