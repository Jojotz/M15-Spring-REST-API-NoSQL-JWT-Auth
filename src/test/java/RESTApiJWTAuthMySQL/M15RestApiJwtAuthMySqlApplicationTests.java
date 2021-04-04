package RESTApiJWTAuthMySQL;

import RESTApiJWTAuthMySQL.model.Player;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class M15RestApiJwtAuthMySqlApplicationTests {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@Test
	void createNewPlayer() throws Exception {

		Player player = new Player(null, "Alice", LocalDateTime.now());
		mockMvc.perform(post("/players")
					   .content(objectMapper.writeValueAsString(player))
					   .contentType(MediaType.APPLICATION_JSON))
			   .andExpect(status().isCreated())
			   .andDo(print());
	}

}
