import static org.junit.Assert.*;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class InformacoesUsuarioTest {

    @Test
    public void testAdicionarUmaInfoAdicionalUsuario(){
        System.setProperty("webdriver.chrome.driver","/home/usertqi/ambiente/Drivers/chromedriver");
        WebDriver navegador = new ChromeDriver();

        navegador.get("http://www.juliodelima.com.br/taskit");

        assertEquals(1,1);
    }
}
