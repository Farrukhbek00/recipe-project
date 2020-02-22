package com.jafton.recipeproject.services;

import com.jafton.recipeproject.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();

}
