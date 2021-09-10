package entidad;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;

public class NewTest {
	private WebDriver driver;
	
	  @Test
	  public void f() {
		  WebElement cuadroBusqueda = driver.findElement(By.name("q"));
		  
		  cuadroBusqueda.sendKeys("pagina principal iplacex");
		  cuadroBusqueda.click();
		  
		  cuadroBusqueda.submit();
		  
		  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		  assertEquals("pagina principal iplacex - Google search", driver.getTitle());
	  }
	  @BeforeTest
	  public void beforeTest() {
		  System.setProperty("webdriver.chrome.driver","C:\\Users\\Digital\\Downloads\\chromedriver.exe");
		  driver = new ChromeDriver();
		  driver.manage().window().maximize();
		  driver.get("https://www.google.com/");
	  }

	  @AfterTest
	  public void afterTest() {
		  driver.quit();
	  }

	}
