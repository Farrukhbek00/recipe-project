package com.jafton.recipeproject.bootstrap;

import com.jafton.recipeproject.domain.*;
import com.jafton.recipeproject.repositories.CategoryRepository;
import com.jafton.recipeproject.repositories.RecipeRepository;
import com.jafton.recipeproject.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
    }

    private List<Recipe> getRecipes(){

        List<Recipe> recipes = new ArrayList<>(2);

        // get UOMs
        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");

        if(!eachUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> tableSpoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");

        if(!tableSpoonUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> teaSpoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

        if(!teaSpoonUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByDescription("Dash");

        if(!dashUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> pintUomOptional = unitOfMeasureRepository.findByDescription("Pint");

        if(!pintUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> cupsUomOptional = unitOfMeasureRepository.findByDescription("Cup");

        if(!cupsUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        // get optionals
        UnitOfMeasure eachUom = eachUomOptional.get();
        UnitOfMeasure tableSpoonUom = tableSpoonUomOptional.get();
        UnitOfMeasure teaSpoonUom = tableSpoonUomOptional.get();
        UnitOfMeasure dashUom = dashUomOptional.get();
        UnitOfMeasure pintUom = pintUomOptional.get();
        UnitOfMeasure cupsUom = pintUomOptional.get();

        // get Categories
        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");

        if(!americanCategoryOptional.isPresent()){
            throw new RuntimeException("Expected Category Not Found");
        }

        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");

        if(!mexicanCategoryOptional.isPresent()){
            throw new RuntimeException("Expected Category Not Found");
        }

        Category americanCategory = americanCategoryOptional.get();
        Category mexicanCategory = mexicanCategoryOptional.get();

        // Yummy Guac
        Recipe guacRecipe = new Recipe();
        guacRecipe.setDescription("Perfect Guacamole");
        guacRecipe.setPreTime(10);
        guacRecipe.setCookTime(65);
        guacRecipe.setDifficulty(Difficulty.HARD);
        guacRecipe.setDirections("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem" +
                " Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took" +
                " a galley of type and scrambled it to make a type specimen book. It has survived not only five ");
        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem " +
                "Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took" +
                " a galley of type and scrambled it to make a type specimen book. It has survived not only five centu");

        guacNotes.setRecipe(guacRecipe);
        guacRecipe.setNotes(guacNotes);

        guacRecipe.getIngredients().add(new Ingredient("ripe avocados", new BigDecimal(2), eachUom, guacRecipe));
        guacRecipe.getIngredients().add(new Ingredient("Kosher salt", new BigDecimal(5), teaSpoonUom, guacRecipe));
        guacRecipe.getIngredients().add(new Ingredient("fresh time juice or lemon juice", new BigDecimal(2), tableSpoonUom, guacRecipe));
        guacRecipe.getIngredients().add(new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(2), eachUom, guacRecipe));
        guacRecipe.getIngredients().add(new Ingredient("serrano chiles, stems ans seeds removed, minced", new BigDecimal(2), eachUom, guacRecipe));
        guacRecipe.getIngredients().add(new Ingredient("Cilantro", new BigDecimal(2), tableSpoonUom, guacRecipe));
        guacRecipe.getIngredients().add(new Ingredient("freshly grated black pepper", new BigDecimal(2), dashUom, guacRecipe));
        guacRecipe.getIngredients().add(new Ingredient("ripe tomato, seeds and pulp removed, chopped", new BigDecimal(2), eachUom, guacRecipe));

        guacRecipe.getCategories().add(americanCategory);
        guacRecipe.getCategories().add(mexicanCategory);

        // add to return list
        recipes.add(guacRecipe);

        // Yummy Tacos

        Recipe tacosRecipe = new Recipe();
        tacosRecipe.setDescription("Perfect Guacamole");
        tacosRecipe.setPreTime(10);
        tacosRecipe.setCookTime(0);
        tacosRecipe.setDifficulty(Difficulty.EASY);
        tacosRecipe.setDirections("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem" +
                " Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took" +
                " a galley of type and scrambled it to make a type specimen book. It has survived not only five centuri");
        Notes tacosNotes = new Notes();
        tacosNotes.setRecipeNotes("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem " +
                "Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer tu");

        tacosNotes.setRecipe(tacosRecipe);
        tacosRecipe.setNotes(tacosNotes);


        tacosRecipe.getIngredients().add(new Ingredient("ripe avocados", new BigDecimal(2), eachUom, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("Kosher salt", new BigDecimal(5), teaSpoonUom, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("fresh time juice or lemon juice", new BigDecimal(2), tableSpoonUom, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(2), eachUom, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("serrano chiles, stems ans seeds removed, minced", new BigDecimal(2), eachUom, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("Cilantro", new BigDecimal(2), tableSpoonUom, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("freshly grated black pepper", new BigDecimal(2), dashUom, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("ripe tomato, seeds and pulp removed, chopped", new BigDecimal(2), eachUom, tacosRecipe));

        tacosRecipe.getCategories().add(americanCategory);
        tacosRecipe.getCategories().add(mexicanCategory);

        // add to return list
        recipes.add(tacosRecipe);

        return recipes;



    }
}
