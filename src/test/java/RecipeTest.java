import io.qameta.allure.Feature;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Feature("Test Recipes")
public class RecipeTest extends BaseTest2 {
    String apiKey = "e50b64651133412f89bc24ff1faad1a9";
    String hash = "afac8da18a6acc44bd3146984653c415c30abcab";
    String username = "vera0";
    String title = "Spaghetti+Aglio+et+Olio";

    @Test(description = "Search Recipes")
    public void SearchRecipesTest(){
        given ()
                .queryParam("apiKey",apiKey)
                .log().ifValidationFails()
                .when()
                .get("complexSearch")
                .then()
                .log().ifValidationFails()
                .statusCode(200);
    }
    @Test(description = "Search Recipes Random")
    public void SearchRecipesRandomTest() {
        given()
                .queryParam("apiKey", apiKey)
                .log().ifValidationFails()
                .when()
                .get("random")
                .then()
                .log().ifValidationFails()
                .statusCode(200);
    }
    @Test(description = "Guess Nutrition")
    public void guessNutritionTest() {
        given()
                .queryParam("apiKey", apiKey)
                .queryParam("title",title)
                .log().ifValidationFails()
                .when()
                .get("random")
                .then()
                .log().ifValidationFails()
                .statusCode(200);
    }

    @Test(description = "Analyze Recipes")
    public void AnalyzeRecipesTest(){
        String requestBody = "{\n" +
                "    \"title\": \"Spaghetti Carbonara\",\n" +
                "    \"servings\": 2,\n" +
                "    \"ingredients\": [\n" +
                "        \"1 lb spaghetti\",\n" +
                "        \"3.5 oz pancetta\",\n" +
                "        \"2 Tbsps olive oil\",\n" +
                "        \"1  egg\",\n" +
                "        \"0.5 cup parmesan cheese\"\n" +
                "    ],\n" +
                "    \"instructions\": \"Bring a large pot of water to a boil and season generously with salt. Add the pasta to the water once boiling and cook until al dente. Reserve 2 cups of cooking water and drain the pasta. \"\n" +
                "}";
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .queryParam("apiKey",apiKey)
                .body(requestBody)
                .log().ifValidationFails()
                .when()
                .post("analyze")
                .then()
                .log().ifValidationFails()
                .statusCode(200)
                .body("title",equalTo("Spaghetti Carbonara"));
    }

}
