import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class InformacoesUsuarioTest {

    private WebDriver navegador;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","/home/usertqi/Ambiente/udemy/drivers/chromedriver");
        navegador = new ChromeDriver();
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        navegador.get("http://www.juliodelima.com.br/taskit");
        navegador.findElement(By.linkText("Sign in")).click();
        navegador.findElement(By.id("signinbox")).findElement(By.name("login")).sendKeys("julio0001");
        navegador.findElement(By.id("signinbox")).findElement(By.name("password")).sendKeys("123456");
        navegador.findElement(By.linkText("SIGN IN")).click();
        String login = navegador.findElement(By.className("me")).getText();
        assertEquals("Hi, Julio",login);
        navegador.findElement(By.className("me")).click();
        navegador.findElement(By.linkText("MORE DATA ABOUT YOU")).click();

    }

    @Test
    public void testAdicionarUmaInfoAdicionalUsuario(){
        //exemplo de xpath: //tag[@variable='value']//
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

    @Test
    public void removerUmContatoDeUsuario(){
        //+551199999999
        // following-sibling, é usado para identificar a proxima tag a, depois do span especificado.
        // palavras chave: following e preceding
        navegador.findElement(By.xpath("//span[text()='+5521999999999']/following-sibling::a")).click();

        // janela javascript
        navegador.switchTo().alert().accept();

        WebElement pop = navegador.findElement(By.id("toast-container"));
        String mensagem = pop.getText();
        assertEquals("Rest in peace, dear phone!",mensagem);

        // espera explicita, até que o elemento pop "staleness" da tela
        WebDriverWait aguardar = new WebDriverWait(navegador, 10);
        aguardar.until(ExpectedConditions.stalenessOf(pop));

        navegador.findElement(By.linkText("Logout")).click();
    }

    @After
    public void tearDown(){
        navegador.quit();
    }
}
