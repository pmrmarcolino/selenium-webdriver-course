package tests;

import static org.junit.Assert.*;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import suporte.Screenshot;
import suporte.Gerador;
import suporte.Web;

import java.util.concurrent.TimeUnit;

@RunWith(DataDrivenTestRunner.class)
//@DataLoader(filePaths = "src/test/resource/testeInfo.csv")
public class InformacoesUsuarioTest {

    private WebDriver navegador;

    @Rule
    public TestName test = new TestName();

    @DataLoader()
    @Before
    public void setUp(){

        navegador = Web.createChrome();
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
    public void testAdicionarUmaInfoAdicionalUsuario(@Param(name = "tipo")String tipo, @Param(name = "contato") String contato, @Param(name= "mensagem")String mensagemEsperada){
        //exemplo de xpath: //tag[@variable='value']//
        navegador.findElement(By.xpath("//button[@data-target='addmoredata']")).click();
        WebElement modal = navegador.findElement(By.id("addmoredata"));
        // selecionar combobox
        WebElement campoType =  modal.findElement(By.name("type"));
        new Select(campoType).selectByVisibleText(tipo);

        modal.findElement(By.name("contact")).sendKeys(contato);
        navegador.findElement(By.linkText("SAVE")).click();

        //validar mensagem de sucesso
        String mensagem = navegador.findElement(By.id("toast-container")).getText();
        assertEquals(mensagemEsperada,mensagem);
    }

    @Test
    public void removerUmContatoDeUsuario(){
        // following-sibling, é usado para identificar a proxima tag a, depois do span especificado.
        // palavras chave: following e preceding
        navegador.findElement(By.xpath("//span[text()='+5511999999999']/following-sibling::a")).click();

        // janela javascript
        navegador.switchTo().alert().accept();

        WebElement pop = navegador.findElement(By.id("toast-container"));
        String mensagem = pop.getText();
        assertEquals("Rest in peace, dear phone!",mensagem);

        String arquivoScreenshot = "/home/usertqi/Ambiente/screenshot" + Gerador.dataHraParaArquivo() + test.getMethodName() + ".png";
        Screenshot.tirar(navegador, arquivoScreenshot);

        // espera explicita, até que o elemento pop "staleness" da tela
        WebDriverWait aguardar = new WebDriverWait(navegador, 10);
        aguardar.until(ExpectedConditions.stalenessOf(pop));

        navegador.findElement(By.linkText("Logout")).click();
    }

}
