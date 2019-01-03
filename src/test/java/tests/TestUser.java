package tests;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import suporte.Web;

import static org.junit.Assert.assertEquals;


@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths="src/test/resource/TestUser.csv")
public class TestUser {
    private WebDriver navegador;

    @Before
    public void setUp(){
        navegador = Web.createChrome();
    }

    @Test
    public void testAddInfo(
            @Param(name = "login") String login,
            @Param(name = "password") String password,
            @Param(name = "type") String type,
            @Param(name = "contact") String contact,
            @Param(name = "message") String message){

        String mensagemEsperada =
                new LoginPage(navegador).
                        clickSignIn().
                        fazerLogin(login, password).
                        clickMe().
                        clickNaAbaMoreDataAbout().
                        clicarNoBotaoAddMoreDataAbout().
                        adicionarSalvar(type ,contact).
                        capturarTextoToast();

        assertEquals(mensagemEsperada,message);
    }

    @After
    public void tearDown(){
        navegador.quit();
    }
}
