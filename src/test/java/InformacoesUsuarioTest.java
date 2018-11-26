import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class InformacoesUsuarioTest {

    private WebDriver navegador;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","/home/usertqi/Ambiente/udemy/drivers/chromedriver");
        navegador = new ChromeDriver();
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void testAdicionarUmaInfoAdicionalUsuario(){


        navegador.get("http://www.juliodelima.com.br/taskit");
        navegador.findElement(By.linkText("Sign in")).click();
        navegador.findElement(By.id("signinbox")).findElement(By.name("login")).sendKeys("julio0001");
        navegador.findElement(By.id("signinbox")).findElement(By.name("password")).sendKeys("123456");
        navegador.findElement(By.linkText("SIGN IN")).click();
        String login = navegador.findElement(By.className("me")).getText();

        assertEquals("Hi, Julio",login);

    }

    @After
    public void tearDown(){
        navegador.quit();
    }
}
