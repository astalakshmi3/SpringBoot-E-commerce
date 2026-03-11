package se.lexicon.springboot.service;

import se.lexicon.springboot.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryService {
     CategoryResponse create (String name);
     List<CategoryResponse> findAll();
}
