package tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import util.SeleniumUtils;
import util.views.PO_HomeView;
import util.views.PO_LoginView;
import util.views.PO_NavView;
import util.views.PO_Properties;
import util.views.PO_RegisterView;
import util.views.PO_View;

//Ordenamos las pruebas por el nombre del método
//	@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ThirtiTest {
	// En Windows (Debe ser la versión 46.0 y desactivar las actualizacioens
	// automáticas)):
	static String PathFirefox = "C:\\Path\\FirefoxPortable.exe";
	// En MACOSX (Debe ser la versión 46.0 y desactivar las actualizaciones
	// automáticas):
	// static String PathFirefox =
	// "/Applications/Firefox.app/Contents/MacOS/firefox-bin";
	// Común a Windows y a MACOSX
	static WebDriver driver = getDriver(PathFirefox);
	static String URL = "http://localhost:8090";

	public static WebDriver getDriver(String PathFirefox) {
		// Firefox (Versión 46.0) sin geckodriver para Selenium 2.x.
		System.setProperty("webdriver.firefox.bin", PathFirefox);
		WebDriver driver = new FirefoxDriver();
		return driver;
	}

	// Antes de cada prueba se navega al URL home de la aplicaciónn
	@Before
	public void setUp() {
		driver.navigate().to(URL);
	}

	// Después de cada prueba se borran las cookies del navegador
	@After
	public void tearDown() {
		driver.manage().deleteAllCookies();
	}

	// Antes de la primera prueba
	@BeforeClass
	static public void begin() {
	}

	// Al finalizar la última prueba
	@AfterClass
	static public void end() {
		// Cerramos el navegador al finalizar las pruebas
		driver.quit();
	}

	@Test
	public void PR011() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		PO_RegisterView.fillForm(driver, "pruebaDefinitiva@prueba.com", "César", "55555", "55555");
		PO_View.checkElement(driver, "text", "Esta es una zona privada de la web");
	}

	public void PR012() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		PO_RegisterView.fillForm(driver, "pruebaDefinitiva@prueba.com", "César", "55555", "666666");
		PO_RegisterView.checkKey(driver, "Error.signup.password.duplicate", PO_Properties.getSPANISH());
	}

	public void PR021() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "pruebaDefinitiva@prueba.com", "55555");
		PO_View.checkElement(driver, "text", "Esta es una zona privada de la web");
		PO_NavView.clickOption(driver, "logout", "text", "Desconectar");
	}

	public void PR022() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "pruebaDefinitiva1@prueba.com", "55555");
		PO_View.checkElement(driver, "text", "Identifícate");
	}

	public void PR031() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "pruebaDefinitiva@prueba.com", "55555");
		PO_View.checkElement(driver, "text", "Esta es una zona privada de la web");
		PO_NavView.clickOption(driver, "Usuarios", "text", "Ver Usuarios");
		// Comprobamos que los elementos son 5 dado que la pagina esta llena.
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		assertTrue(elementos.size() == 5);
		
		PO_NavView.clickOption(driver, "logout", "text", "Desconectar");
	}

	public void PR032() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "pruebaDefinitiva2@prueba.com", "55555");
		PO_View.checkElement(driver, "text", "Identifícate");

		driver.navigate().to("http://localhost:8090/user/list");
		PO_View.checkElement(driver, "text", "Identifícate");
	}

	public void PR041() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "pruebaDefinitiva@prueba.com", "55555");
		PO_View.checkElement(driver, "text", "Esta es una zona privada de la web");
		PO_NavView.clickOption(driver, "user/list", "text", "Ver Usuarios");
		// Comprobamos que los elementos son 5 dado que la pagina esta llena.
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		assertTrue(elementos.size() == 5);

		// Añadir el apartado de la busqueda (Buscar 1)
		
//		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
//				PO_View.getTimeout());
//		assertTrue(elementos.size() == 1);
		
		PO_NavView.clickOption(driver, "logout", "text", "Desconectar");
	}

	public void PR042() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "pruebaDefinitiva2@prueba.com", "55555");
		PO_View.checkElement(driver, "text", "Identifícate");

		driver.navigate().to("http://localhost:8090/user/list?searchtext=1");
		PO_View.checkElement(driver, "text", "Identifícate");
	}

	public void PR051() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "pruebaDefinitiva@prueba.com", "55555");
		PO_View.checkElement(driver, "text", "Esta es una zona privada de la web");
		PO_NavView.clickOption(driver, "user/list", "text", "Ver Usuarios");

		// Añadir a alguien
		
		PO_NavView.clickOption(driver, "logout", "text", "Desconectar");
	}

	public void PR052() {
		fail("Not yet implemented");
	}

	public void PR061() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "prueba1@email.com", "123456");
		PO_View.checkElement(driver, "text", "Esta es una zona privada de la web");
		PO_NavView.clickOption(driver, "Solicitudes", "text", "Ver Solicitudes de amistad");

		// Posee una, la invitacion previamente mandada
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		assertTrue(elementos.size() == 1);
		PO_NavView.clickOption(driver, "logout", "text", "Desconectar");
	}

	public void PR071() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "prueba1@email.com", "123456");
		PO_View.checkElement(driver, "text", "Esta es una zona privada de la web");
		PO_NavView.clickOption(driver, "request/list", "text", "Ver Solicitudes de amistad");
		
		//Aceptar la solicitud
		
		
		PO_NavView.clickOption(driver, "logout", "text", "Desconectar");
	}

	public void PR081() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "pruebaDefinitiva@prueba.com", "55555");
		PO_View.checkElement(driver, "text", "Esta es una zona privada de la web");
		PO_NavView.clickOption(driver, "user/friends", "text", "Ver Amigos");
		
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		assertTrue(elementos.size() == 1);//Tiene el que le agrego previamente
		
		PO_NavView.clickOption(driver, "logout", "text", "Desconectar");
	}
}
