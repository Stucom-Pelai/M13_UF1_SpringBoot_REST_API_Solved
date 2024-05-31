//package example.cashcard;
//
//import org.junit.jupiter.api.Test;
//import static org.assertj.core.api.Assertions.assertThat;
//
//class CashCardJsonTest {
//
//   @Test
//   void myFirstTest() {
//      assertThat(1).isEqualTo(42);
//   }
//}
package example.cashcard;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest // Jackson framework to test
class CashCardJsonTest {

    @Autowired // directs Spring to create an object of the requested type.
    private JacksonTester<CashCard> json;

    @Test // verify serialization, from java to json
    void cashCardSerializationTest() throws IOException {
        CashCard cashCard = new CashCard(99L, 123.45);
        assertThat(json.write(cashCard)).isStrictlyEqualToJson("expected.json");
        assertThat(json.write(cashCard)).hasJsonPathNumberValue("@.id");
        assertThat(json.write(cashCard)).extractingJsonPathNumberValue("@.id")
                .isEqualTo(99);
        assertThat(json.write(cashCard)).hasJsonPathNumberValue("@.amount");
        assertThat(json.write(cashCard)).extractingJsonPathNumberValue("@.amount")
             .isEqualTo(123.45);
    }
    
    @Test // verify deserialization, from json to java
    void cashCardDeserializationTest() throws IOException {
       String expected = """
               {
                   "id":99,
                   "amount":123.45
               }
               """;
       assertThat(json.parse(expected))
               .isEqualTo(new CashCard(99L, 123.45));
       assertThat(json.parseObject(expected).id()).isEqualTo(99);
       assertThat(json.parseObject(expected).amount()).isEqualTo(123.45);
    }
}
