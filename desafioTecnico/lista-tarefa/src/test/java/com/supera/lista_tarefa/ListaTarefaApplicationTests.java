package com.supera.lista_tarefa;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ListaTarefaApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
    void applicationStarts() {
        // Testa se a aplicação inicia corretamente
        ListaTarefaApplication.main(new String[] {});
    }
}
