package entidad;

import org.junit.Test;

import junit.framework.TestCase;

public class TestBDTest extends TestCase {
	@Test
	public void test() {
		TestBD.conectar();
	}
}
