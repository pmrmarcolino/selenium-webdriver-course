import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

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
        navegador.findElement(By.className("me")).click();
        navegador.findElement(By.linkText("MORE DATA ABOUT YOU")).click();
        navegador.findElement(By.xpath("//button[@data-target='addmoredata']")).click();
        WebElement modal = navegador.findElement(By.id("addmoredata"));
        // selecionar combobox
        WebElement campoType =  modal.findElement(By.name("type"));
        new Select(campoType).selectByVisibleText("Phone");

        modal.findElement(By.name("contact")).sendKeys("+553411111111");
        navegador.findElement(By.linkText("SAVE")).click();

        //validar mensagem de sucesso

        String mensagem = navegador.findElement(By.id("toast-container")).getText();
        assertEquals("Your contact has been added!",mensagem);

    }

    @After
    public void tearDown(){
        navegador.quit();
    }
}
