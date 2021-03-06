package com.maneletorres.safebites.entities;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductNotFormatted {
    @SerializedName("product_name")
    @Expose
    private final String product_name;

    @SerializedName("code")
    @Expose
    private final String code;

    @SerializedName("nutriments")
    @Expose
    private final JsonObject nutriments;

    @SerializedName("image_small_url")
    @Expose
    private final String image_small_url;

    @SerializedName("ingredients_text")
    @Expose
    private final String ingredients_text;

    @SerializedName("serving_size")
    @Expose
    private final String serving_size;

    @SerializedName("allergens_hierarchy")
    @Expose
    private final JsonArray allergens_hierarchy;

    @SerializedName("traces_hierarchy")
    @Expose
    private final JsonArray traces_hierarchy;

    public ProductNotFormatted(String product_name, String code, JsonObject nutriments,
                               String image_small_url, String ingredients_text, String serving_size,
                               JsonArray allergens_hierarchy, JsonArray traces_hierarchy) {
        this.product_name = product_name;
        this.code = code;
        this.nutriments = nutriments;
        this.image_small_url = image_small_url;
        this.ingredients_text = ingredients_text;
        this.serving_size = serving_size;
        this.allergens_hierarchy = allergens_hierarchy;
        this.traces_hierarchy = traces_hierarchy;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getCode() {
        return code;
    }

    public JsonObject getNutriments() {
        return nutriments;
    }

    public String getImage_small_url() {
        return image_small_url;
    }

    public String getIngredients_text() {
        return ingredients_text;
    }

    public String getServing_size() {
        return serving_size;
    }

    public JsonArray getAllergens_hierarchy() {
        return allergens_hierarchy;
    }

    public JsonArray getTraces_hierarchy() {
        return traces_hierarchy;
    }
}