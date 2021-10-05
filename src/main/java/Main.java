import com.olx.FileID;
import com.olx.Page;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        String pathChromeDriver = System.getProperty("user.dir") + "\\lib\\chromedriver94.exe";
        System.setProperty("webdriver.chrome.driver", pathChromeDriver);

        Page page = new Page();
        page.setDriver(new ChromeDriver());
        page.getDriver().get("https://www.olx.ua/uk/nedvizhimost/q-оренда/");
        Thread.sleep(2000);

        String lastId = FileID.lastID();

        List<String> newIds = new ArrayList<>();
        int count = 0;

        while (!page.getDriver().findElements(By.xpath("//span[@class='fbold next abs large']")).isEmpty()) {
            // add new item IDs in list if they exist
            List<String> newAds = page.getNewAds(lastId);
            //output to the console links to new ads
            if (!newAds.isEmpty()) {
                count += newAds.size();
                page.outputLinks(newAds);
                newIds.addAll(newAds);
            }
            //if the last saved ID is found then stop to find ads
            if (page.isFindLast()){
                break;
            }
            // check whether the page is not the last (if page is last then break)
            if (!page.getDriver().findElements(By.xpath("//span[@class='fbold next abs large']//a[@href]")).isEmpty()) {
                page.nextPage();
            } else {
                break;
            }
        }

        if (!newIds.isEmpty()) {
            FileID.writeId(newIds);
        }

        System.out.println("New ads: " + count);
        page.getDriver().quit();
    }
}
