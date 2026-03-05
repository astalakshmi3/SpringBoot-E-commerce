package se.lexicon.springboot.dto.request;

public record ProductRequest(
        String name,
        String description,
        Double price,
        Long categoryId
) {
}
