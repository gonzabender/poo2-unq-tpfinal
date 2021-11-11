package tpfinal.test;

import org.junit.jupiter.api.BeforeEach;

import tpfinal.*;


public class InformaciónAlUsuarioTest {
	
	SEM sem;
	Celular iphone;
	AppUsuario app;
	
	@BeforeEach
	public void setUp() throws Exception {
		// Set up
		
		sem = new SEM();
		iphone = new Celular(app, 1157990244, 0);
		app = new AppUsuario(sem, "UNQ021", iphone);
	}

}
