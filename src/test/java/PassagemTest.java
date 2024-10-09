// 1 - bibliotecas / imports
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

// 2 - classe
public class PassagemTest {
    // 2.1 - atributos
    private WebDriver driver; //objeto do Selenuim

    // 2.2 - funções e métodos

    // antes do teste
    @BeforeEach
    public void iniciar() {

        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        driver.manage().window().maximize(); // maximiza a janela do browser
    }

    // depois do teste
    @AfterEach
    public void finalizar() {

       //driver.quit();
    }

    // test
    @Test
    public void comprarPassagem() {

       driver.get("https://www.blazedemo.com");

       driver.findElement(By.name("fromPort")).click();

       {
        WebElement dropdown = driver.findElement(By.name("fromPort"));
        dropdown.findElement(By.xpath("//option[. = 'São Paolo']")).click();;
       }

       {
        WebElement dropdown = driver.findElement(By.name("toPort"));
        dropdown.click();
        dropdown.findElement(By.xpath("//option[. = 'Cairo']")).click();
       }

       //Clica no botão
       driver.findElement(By.cssSelector("input.btn.btn-primary")).click();

       //Verifica se foi realizado o login e as pesquisas dos voos
       assertEquals("Flights from São Paolo to Cairo:", driver.findElement(By.cssSelector("h3")).getText());

       //Clica no botão do voo desejado
       driver.findElement(By.cssSelector("tr:nth-child(1) .btn")).click();

       //Verifica se está na página de compra
       assertEquals("Your flight from TLV to SFO has been reserved.", driver.findElement(By.cssSelector("h2")).getText());

       //Preencher campos
       driver.findElement(By.id("inputName")).sendKeys("Fernanda");

       driver.findElement(By.id("address")).sendKeys("Rua Josefina, 320");

       driver.findElement(By.id("city")).sendKeys("Salvador");

       driver.findElement(By.id("state")).sendKeys("BA");

       driver.findElement(By.id("zipCode")).sendKeys("041680060");

       driver.findElement(By.name("cardType")).click();
       WebElement dropdown = driver.findElement(By.name("cardType"));
       dropdown.findElement(By.xpath("//*[@id=\"cardType\"]/option[2]")).click();
       
       driver.findElement(By.id("creditCardNumber")).sendKeys("234890982098");
       
       driver.findElement(By.id("creditCardMonth")).clear();
       driver.findElement(By.id("creditCardMonth")).sendKeys("01");

       driver.findElement(By.id("creditCardYear")).clear();
       driver.findElement(By.id("creditCardYear")).sendKeys("2030");

       driver.findElement(By.id("nameOnCard")).sendKeys("Fernanda V Bonfat");

       driver.findElement(By.name("rememberMe")).click();

       driver.findElement(By.xpath("/html/body/div[2]/form/div[11]/div/input")).click();

       //Verifica se a compra foi realizada com sucesso
       //assertEquals(dropdown, dropdown);

 
    }
}