package check;

import com.gargoylesoftware.htmlunit.javascript.host.Console;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.junit.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.lang.System;
import java.util.Random;

public class Check {

    private static WebDriver driver;



    //setup
    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\CD\\chromedriver.exe");
        if(driver == null){
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.get("http://www.wiley.com/WileyCDA/");
        }
    }

    @AfterClass
    public static void tearDown() {
     //  driver.quit();
    }


    @Test
    public void Test() {

            //1
            //get top menu
            //get first link from top menu HOME e.t.c
            System.out.print("1 check");
            Boolean  ltmHome = driver.findElement(By.xpath("//a[text()='Home']")).isDisplayed();
            Boolean  ltmSubject = driver.findElement(By.xpath("//a[text()='Subjects']")).isDisplayed();
            Boolean  ltmAbout = driver.findElement(By.xpath("//a[text()='About Wiley']")).isDisplayed();
            Boolean  ltmContactUs =    driver.findElement(By.xpath("//a[text()='Contact Us']")).isDisplayed();;
            Boolean  ltmHelp    =    driver.findElement(By.xpath("//a[text()='Help']")).isDisplayed();;

            if(ltmHome && ltmSubject && ltmAbout && ltmContactUs && ltmHelp)
                System.out.println("1 check: ok");
            else
                System.out.println("1 check: error, some elements corrupted");

            //2
            System.out.print("2 check");

            List<WebElement> li =   driver.findElements(By.xpath("id('homepage-links')/ul/li/a"));
            System.out.println(": " + li.size());

            for(int index = 0; index < li.size(); index++ )
                    System.out.println(li.get(index).getText());

             //3
            System.out.print("3 check");
            WebElement lStudents = driver.findElement(By.xpath("id('homepage-links')/ul/li/a[text()='Students']"));
            lStudents.click();
            boolean mVar =  driver.getCurrentUrl() == "http://www.wiley.com/WileyCDA/Section/id-404702.html";
            if (mVar)
            System.out.print(": ok");
                    else
                System.out.println(": wrong current url");

            //4
            System.out.print("4 check");
            List <WebElement> elements = driver.findElements(By.xpath("//ul[@class='autonavLevel1']/li"));

            int sizeElements  =  elements.size();//got el with our sections 'left menu'
            if( sizeElements != 8 )
                System.out.println(": wrong quantity of  left menu elements. Current quantity " + sizeElements);
            else
                System.out.println(": ok");

            for(int index = 0; index < elements.size(); index++ )
                System.out.println(elements.get(index).getText());


            //5
            boolean isClikable = false;

            System.out.print("5 check");
            try {
                String curUrl = driver.getCurrentUrl();
                driver.findElement(By.xpath("//ul[@class='autonavLevel1']//span[text()='Students']")).click();
                isClikable = true;
                if (curUrl ==  driver.getCurrentUrl()){
                    isClikable = false;
                }
            }catch (Exception e){

                isClikable = false;
            }
            String style = driver.findElement(By.xpath("//ul[@class='autonavLevel1']//span[text()='Students']")).getAttribute("class");
            System.out.println(": isEnable? " + isClikable + " : style " + style);
            //Smth wrong coz , in browser it's  is not enable real

            //6
            System.out.print("6 check");

                 try {
                     WebElement lHome = driver.findElement(By.xpath("id('links-site')/ul/li/a[text()='Home']"));
                     lHome.click();
                     System.out.println(": ok");
                }catch (Exception e){
                    System.out.println(": error");
                }

            //7
            System.out.print("7 check");
            WebElement bArrow = driver.findElement(By.xpath("id('id30')/div/a/button"));
            bArrow.click();

            try{

                Alert alert =  driver.switchTo().alert();
                String aText = alert.getText();
                System.out.print(": alert text   - " + aText);
                alert.dismiss();

            }catch (Exception e){
                System.out.print(": error get alert");
            }
            //close alert...
            //8
            System.out.print("8 check");
            WebElement inputElement = driver.findElement(By.xpath("id('EmailAddress')"));
            inputElement.sendKeys("invalidemailadres");
            bArrow.click();

            try {

                Alert alert =  driver.switchTo().alert();
                String aText = alert.getText();
                System.out.print(": alert text   - " + aText);
                alert.dismiss();

            }catch (Exception e){
                System.out.print(": error get alert");
            }
            //i know, that was bad
            //9
            System.out.print("9 check");
            WebElement sQuery = driver.findElement(By.xpath("id('query')"));
            sQuery.sendKeys("for dummies");
            WebElement lHome = driver.findElement(By.xpath("id('links-site')/form/fieldset/input[2]"));
            lHome.click();

            List<WebElement> resultElements = driver.findElements(By.xpath("id('search-results')/div"));
            if(resultElements.size() != 0 )
            System.out.println(": ok");
            else System.out.print(": list of result is empty");



            //10  //there are no elements with title="book"!!!
        System.out.print("10 check");

        List<WebElement> books = driver.findElements(By.xpath("//a[contains(.,book)]/../../div[@class='product-title']"));

            Random randomGenerator = new Random();
            int randomInt = randomGenerator.nextInt(books.size());
            WebElement bookToLink =  books.get(randomInt);
            String savingTitle = bookToLink.getText();

            bookToLink.click();

            String cTitle = driver.findElement(By.xpath("//h1")).getText();
            if(cTitle.toLowerCase().equals(savingTitle.toLowerCase()))
                System.out.println(": same book, ok");
            else {

                System.out.println(": it's another book, error");
            }
            ///11 same 6
                try {
                    lHome = driver.findElement(By.xpath("id('links-site')/ul/li/a[text()='Home']"));
                    lHome.click();
                    System.out.println(": ok");
                }catch (Exception e){
                    System.out.println(": error");
                }


                WebElement lInstitutions = driver.findElement(By.xpath("//a[text()='Institutions']"));

                Set<String> wHandlers = driver.getWindowHandles();
                lInstitutions.click();
                Set<String> nwHandlers = driver.getWindowHandles();
                nwHandlers.removeAll(wHandlers);
                driver.switchTo().window(nwHandlers.iterator().next());
                String cUrl = driver.getCurrentUrl();
                if (cUrl.equals("http://wileyedsolutions.com/")){
                    System.out.print("Opened correct url");
                }else
                    System.out.println("Oops..");
                    System.out.print(cUrl);
    }
}
