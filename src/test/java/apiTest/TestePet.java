//Nome do pacote
package apiTest;

//Bibliotecas
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is; //função de é (is)



public class TestePet {

    static String ct = "application/json"; //content type
    static String uriPet = "https://petstore.swagger.io/v2/pet/";

    public static String lerArquivoJson (String arquivoJson) throws IOException {
        return new String (Files.readAllBytes(Paths.get(arquivoJson)));
    }

@Test
    public void testarIncluirPet() throws IOException {

    String jsonBody = lerArquivoJson("src/test/resources/json/pet1.json");

    int petId = 1374420004;

        given()
                .contentType(ct)
                .log().all()
                .body(jsonBody)
        .when()
                .post(uriPet)
        .then()
                .log().all()
                .statusCode(200)
                .body("id", is(petId))
                .body("category.name", is("mingau"))
                .body("category.id", is(1))
                .body("tags.name[0]", is("castrado"))
                .body("tags.id[0]", is(0))
                .body("status", is("disponivel"))
        ;
    }

@Test
    public void testarConsultarPet(){
        String petName = "mingau"; //configuração

        //resultados esperados
        int petId = 1374420004;
        int categoriaId = 1;
        int tagsId = 0;
        String tagsName = "castrado";
        String status = "disponivel";

        given()
                .contentType(ct)
                .log().all()
        .when()
                .get(uriPet + petId)
        .then()
                .log().all()
                .statusCode(200)
                .body("petId", is(petId))
                .body("categoriaId", is(categoriaId))
                .body("tagsId", is(tagsId))
                .body("tagsName", is(tagsName))
                .body("status", is(status))
        ;
    }

@Test
    public void testarAlterarPet() throws IOException {
        String jsonBody = lerArquivoJson("src/test/resources/json/pet1.json");

        String petId = "1374420004";
        String namePet = "mingau";
        int categoriaId = 1;
        int tagsId = 0;
        String tagsName = "castrado";
        String status = "disponivel";


        given()
                .contentType(ct)
                .log().all()
                .body(jsonBody)
        .when()
                .put(uriPet + petId)
        .then()
                .log().all()
                .statusCode(200)
                .body("petId", is(petId))
                .body("categoriaId", is(categoriaId))
                .body("tagsId", is(tagsId))
                .body("tagsName", is(tagsName))
                .body("status", is(status))
        ;

}

@Test
    public void testarExcluiPet(){
        String petId = "1374420004";

        given()
                .contentType(ct)
                .log().all()
        .when()
                .delete(uriPet + petId)
       .then()
                .statusCode(200)
                .body("code", is(200))
                .body("type", is("unknown"))
                .body("message", is(1374420004))
        ;
}


}
