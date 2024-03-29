package br.ce.ederwentz.tasks.prod;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class HealthCheckIT {
	
	@Test
	public void healthCheck() throws MalformedURLException {
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		// servidor local webdriver
		//WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
		WebDriver driver = new RemoteWebDriver(new URL("http://192.168.0.110:4444/wd/hub"), cap);
		try {
		// local
		//driver.navigate().to("http://localhost:9999/tasks");
		// servidor
		//driver.navigate().to("http://192.168.0.130:8004/tasks");
		driver.navigate().to("http://192.168.0.110:8004/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String version = driver.findElement(By.id("version")).getText();
		//System.out.println(version);
		Assert.assertTrue(version.startsWith("build"));
		} finally {
			driver.quit();
		}
	}
}
