package parallell;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import io.github.bonigarcia.wdm.managers.FirefoxDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.open;

public class UiSteps {

    public WebDriverRunner driverSelenide;
    public static ThreadLocal<WebDriverRunner> tdriverSelenide = new ThreadLocal<WebDriverRunner>();

    public static synchronized WebDriverRunner getSeleideDriver() {
        return tdriverSelenide.get();
    }

    public WebDriverRunner initialize_driver_selenide(String browser) {

        if (browser.toLowerCase().contains("firefox")) {
            Configuration.browser = "firefox";
//            System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
//            FirefoxDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--remote-allow-origins=*");
            FirefoxDriverManager.firefoxdriver().arch64().setup();
            WebDriverRunner.setWebDriver(new FirefoxDriver(options));
            driverSelenide = new WebDriverRunner();
            tdriverSelenide.set(driverSelenide);
        } else if (browser.toLowerCase().contains("chrome")) {
            Configuration.browser = "chrome";
//            System.setProperty("webdriver.chrome.driver", "/test/resosrcurces/chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            ChromeDriverManager.chromedriver().arch64().setup();
            WebDriverRunner.setWebDriver(new ChromeDriver(options));
            driverSelenide = new WebDriverRunner();
            tdriverSelenide.set(driverSelenide);
        }
        return getSeleideDriver();
    }

    @Test
    public void test() {
        initialize_driver_selenide("chrome");
        WebDriverRunner.getWebDriver().get("https://www.google.com/");
//        open("https://www.google.com/");
        WebDriverRunner.getWebDriver().manage().window().maximize();
    }

    public WebDriverRunner initialize_driver_selenium_gitlab(String browser) {
        System.out.println("\n Gitlab configuration launched in browser " + browser);
        if (browser.toLowerCase().contains("firefox")) {
            Configuration.browser = "firefox";

            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments("--disable-dev-shm-usage");
            firefoxOptions.addArguments("--no-sandbox");
            firefoxOptions.addArguments("--headless");
//            firefoxOptions.addArguments("--port=4444");
//            firefoxptions.addArguments("--whitelisted-ips");
//            firefoxOptions.addArguments("--allowed-origins=*");
            firefoxOptions.addArguments("--window-size=1920,1080");

            firefoxOptions.addArguments("webdriver.gecko.driver", "geckodriver");
//            FirefoxDriverManager.firefoxdriver().arch64().setup();
//            WebDriver driver = new FirefoxDriver(firefoxOptions);//for del
            WebDriverRunner.setWebDriver(new FirefoxDriver(firefoxOptions));
            driverSelenide = new WebDriverRunner();
            tdriverSelenide.set(driverSelenide);
        } else if (browser.toLowerCase().contains("chrome")) {
//            System.setProperty("webdriver.chrome.whitelistedIps", "");
            Configuration.browser = "chromium-browser";

            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--disable-dev-shm-usage");
            chromeOptions.addArguments("--no-sandbox");
            chromeOptions.addArguments("--headless");
            chromeOptions.addArguments("--port=4444");
            chromeOptions.addArguments("--whitelisted-ips");
            chromeOptions.addArguments("--allowed-origins=*");
            chromeOptions.addArguments("--window-size=1920,1080");

            chromeOptions.addArguments("chromedriverExecutable", "chromedriver");
//            WebDriver driver = new ChromeDriver(chromeOptions);
            WebDriverRunner.setWebDriver(new ChromeDriver(chromeOptions));
            driverSelenide = new WebDriverRunner();
            tdriverSelenide.set(driverSelenide);
        }

        return getSeleideDriver();
    }

    public WebDriverRunner initialize_driver_selenide_old(String browser) {
        System.out.println("\n Non Gitlab configuration launched in browser " + browser);
        if (browser.toLowerCase().contains("firefox")) {
            Configuration.browser = "firefox";

            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments("--headless");//lock for debugging
            firefoxOptions.addArguments("--window-size=1920,1080");

            firefoxOptions.addArguments("webdriver.gecko.driver", "geckodriver");
            FirefoxDriverManager.firefoxdriver().arch64().setup();
            WebDriverRunner.setWebDriver(new FirefoxDriver(firefoxOptions));
            driverSelenide = new WebDriverRunner();
            tdriverSelenide.set(driverSelenide);
        } else if (browser.toLowerCase().contains("chrome")) {
            Configuration.browser = "chrome";

            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--headless");//lock for debugging
            chromeOptions.addArguments("--window-size=1920,1080");

            chromeOptions.addArguments("chromedriverExecutable", "chromedriver");
            ChromeDriverManager.chromedriver().arch64().setup();
            WebDriverRunner.setWebDriver(new ChromeDriver(chromeOptions));
            driverSelenide = new WebDriverRunner();
            tdriverSelenide.set(driverSelenide);
        }

        return getSeleideDriver();
    }


}
