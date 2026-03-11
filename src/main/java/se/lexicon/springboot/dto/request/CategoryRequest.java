package se.lexicon.springboot.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequest(
        Long id,
        @NotBlank(message = "Category name is required")
        String name)
{

}
