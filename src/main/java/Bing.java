import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.concurrent.TimeUnit;




public class Bing {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "D:/ТЕСТИРОВАНИЕ/Инструменты/Для АВТОМАТИЗАЦИИ/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://www.bing.com/");


        // шаг 2
        WebElement Images = driver.findElement(By.cssSelector("a#scpl1"));
        Actions builder = new Actions(driver);
        builder.click(Images).build().perform();
        WebElement myDynamicElement = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[text()='Лента изображений Bing']/..")));



        // шаг 3
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollTo(0,document.body.scrollHeight)");
        jse.executeScript("window.scrollTo(0,0)");


        // шаг 4
        WebElement Search = driver.findElement(By.cssSelector("input#sb_form_q.b_searchbox"));
        Search.sendKeys("automatio");
        WebElement full = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='n']/..")));
        List<WebElement> elements = driver.findElements(By.className("sa_tm"));
        for (WebElement n : elements) {
            WebElement result = n.findElement(By.xpath("//*[text()='n']/.."));
            builder.click(result).build().perform();


            // шаг 5
            WebElement element1 = driver.findElement(By.xpath(".//*[text()='Дата']/.."));
            builder.click().build().perform();
            element1.click();
            List<WebElement> filter1 = driver.findElements(By.xpath("//span[@class='ftrP12']"));
            for (WebElement data : filter1) {
                WebElement result3 = data.findElement(By.xpath(".//*[text()='В прошлом месяце']/.."));
                builder.click(result3).build().perform();
            }
            WebElement lastMonth = (new WebDriverWait(driver, 10))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[text()='В прошлом месяце']/..")));


            // шаг 6
            WebElement element2 = driver.findElement(By.cssSelector(".imgres > div"));
            builder.click(element2).build().perform();
            WebElement frame = (new WebDriverWait(driver, 10))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[text()='Следующий результат поиска изображений']/..")));


            // шаг 7
            driver.switchTo().frame("OverlayIFrame");
            WebElement Next = driver.findElement(By.xpath(".//*[text()='Следующий результат поиска изображений']/.."));
            builder.click(Next).build().perform();
        }

            // шаг 8
            WebElement Slide = driver.findElement(By.className("mainImage accessible nofocus"));
            builder.click(Slide).build().perform();



        }
    }

