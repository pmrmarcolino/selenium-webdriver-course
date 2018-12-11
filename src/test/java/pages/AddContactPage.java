package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class AddContactPage extends BasePage{
    public AddContactPage(WebDriver navegador) {
        super(navegador);
    }

    public AddContactPage escolherTipoDeContato( String tipo){
       WebElement campo =  navegador.findElement(By.id("addmoredata")).findElement(By.name("type"));
       new Select(campo).selectByVisibleText(tipo);
        return this;
    }

    public AddContactPage digitarContato(String contato){
        navegador.findElement(By.id("addmoredata")).findElement(By.name("contact")).sendKeys(contato);
        return this;
    }

    public MePage clicarSalvar(){
        navegador.findElement(By.linkText("SAVE")).click();
        return new MePage(navegador);

    }

    public MePage adicionarSalvar(String tipo, String contato){
        escolherTipoDeContato(tipo);
        digitarContato(contato);
        clicarSalvar();

        return new MePage(navegador);
    }
}
