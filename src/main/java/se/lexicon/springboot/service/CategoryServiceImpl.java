package se.lexicon.springboot.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.springboot.dto.request.CategoryRequest;
import se.lexicon.springboot.dto.response.CategoryResponse;
import se.lexicon.springboot.entity.Category;
import se.lexicon.springboot.mapper.CategoryMapper;
import se.lexicon.springboot.repository.CategoryRepository;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }


    @Override
    public CategoryResponse create(String name) {
        if (categoryRepository.existsByNameIgnoreCase(name)) {
            throw new RuntimeException("Category already exists");
        }
        Category category = new Category();
        category.setName(name);
        categoryRepository.save(category);
        return CategoryMapper.toResponse(category);
    }

    @Override
    public List<CategoryResponse> findAll() {
        return categoryRepository.findAll()
                .stream()
                . map(CategoryMapper::toResponse)
                .toList();
    }
}
