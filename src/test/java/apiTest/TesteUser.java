//Nome do pacote
package apiTest;

//Bibliotecas
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is; //função de é (is)

//Classe
public class TesteUser {
    //Atributos
    static String ct = "application/json"; //content type
    static String uriUser = "https://petstore.swagger.io/v2/user/";

    //Funções e Métodos
    //Funções de Apoio
    public static String lerArquivoJson (String arquivoJson) throws IOException {
        return new String (Files.readAllBytes(Paths.get(arquivoJson)));
    }


    //Funções de Teste
    @Test

        public void testarIncluirUser() throws IOException {
        //carregar os dados do nosso json
        String jsonBody = lerArquivoJson("src/test/resources/json/user1.json");

        String userId = "1374421005";

        //realizar o teste
        given()                                              //Dado que
                .contentType(ct)                            //o tipo do conteúdo
                .log().all()                               //mostre tudo na ida
                .body(jsonBody)                           //corpo da requisição
        .when()                                          //Quando
                .post(uriUser) //Endpoint / Onde vai chamar
        .then()                                                //Então
                .log().all()                                  //mostre tudo na volta
                .statusCode(200)                           // comunicação ida e volta ok
                .body("code", is(200))              // tag code é 200
                .body("type", is("unknown"))       // tag type é "unknown"
                .body("message", is(userId))            // message é o userId
        ;
    }//fim do post


    @Test

    public void testarConsultarUser(){
        String username = "charlie";  //configuração

        // resultados esperados
        int userId = 1374421005; //código do usuário
        String email = "charlie@teste.com";
        String senha = "123456";
        String telefone = "11999998888";


        given()
                    .contentType(ct)
                    .log().all()
        .when()
                    .get(uriUser + username)
        .then()
                    .log().all()
                    .statusCode(200)
                    .body("id", is(userId))
                    .body("email", is(email))
                    .body("password", is(senha))
                    .body("phone", is(telefone))
        ;
    }// fim do Get User


    @Test

    public void testarAlterarUser() throws IOException {
        String jsonBody = lerArquivoJson("src/test/resources/json/user2.json");

        String userId = "1374421005";
        String username = "charlie";

        given()
                .contentType(ct)
                .log().all()
                .body(jsonBody)
        .when()
                .put(uriUser + username)
        .then()
                .log().all()
                .statusCode(200)
                .body("code", is(200))
                .body("type", is("unknown"))
                .body("message", is(userId))

        ;
    }

    @Test

    public void testarExcluirUser(){ //início do Delete User
        String username = "charlie";

        given()
                .contentType(ct)
                .log().all()
        .when()
                .delete(uriUser + username)
        .then()
                .statusCode(200)
                .body("code", is(200))
                .body("type", is("unknown"))
                .body("message", is(username))
        ;

    }//fim do Delete User

}

