package tests;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import suporte.Web;

import static org.junit.Assert.assertEquals;


@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "src/test/resource/teste.csv")
public class InformacoesUsuarioPageObjectTest {
    private WebDriver navegador;

    @Before
    public void setUp(){
        navegador = Web.createChrome();
    }

    @Test
    public void testAdicionarUmaInfoAdicionalUsuario(
            @Param(name = "login") String login,
            @Param(name = "senha") String senha,
            @Param(name = "tipo") String tipo,
            @Param(name = "contato") String contato,
            @Param(name = "mensagem") String mensagem){

        String mensagemEsperada =
                new LoginPage(navegador).
                clickSignIn().
                fazerLogin(login, senha).
                clickMe().
                clickNaAbaMoreDataAbout().
                clicarNoBotaoAddMoreDataAbout().
                adicionarSalvar(tipo,contato).
                capturarTextoToast();
        assertEquals(mensagemEsperada,mensagem);

    }

    @After
    public void tearDown(){
        navegador.quit();
    }
}
