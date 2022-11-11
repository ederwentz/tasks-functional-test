package br.ce.ederwentz.tasks.functional;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TasksTest {
	
	public WebDriver acessarAplicacao() throws MalformedURLException {
//		WebDriver driver = new ChromeDriver();
		DesiredCapabilities cap = DesiredCapabilities.chrome();
//		WebDriver driver = new RemoteWebDriver(new URL("http://192.168.1.3:4444/wd/hub"), cap);
//		driver.navigate().to("http://192.168.1.3:8001/tasks");
		// server local
		WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
		driver.navigate().to("http://localhost:8001/tasks");
		// Server Linux
		//WebDriver driver = new RemoteWebDriver(new URL("http://192.168.1.113:4444/wd/hub"), cap);
		//driver.navigate().to("http://192.168.1.113:8001/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
	@Test
	public void deveSalvarTarefaComSucesso() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		try {
		
		//clicar no add todo
		driver.findElement(By.id("addTodo")).click();
		
		//escrever a descrição
		driver.findElement(By.id("task")).sendKeys("Teste via selenium");
		
		//escrever a data
		driver.findElement(By.id("dueDate")).sendKeys("27/03/2025");
		
		//clicar em salvar
		driver.findElement(By.id("saveButton")).click();
		
		//validar mensagem de sucesso
		String message = driver.findElement(By.id("message")).getText();
		Assert.assertEquals("Success!", message);

		} finally {
		//fechar o browser
		driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemDescricao() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		try {
		
		//clicar no add todo
		driver.findElement(By.id("addTodo")).click();
		
		//escrever a descrição
//		driver.findElement(By.id("task")).sendKeys("Teste via selenium");
		
		//escrever a data
		driver.findElement(By.id("dueDate")).sendKeys("27/03/2025");
		
		//clicar em salvar
		driver.findElement(By.id("saveButton")).click();
		
		//validar mensagem de sucesso
		String message = driver.findElement(By.id("message")).getText();
		Assert.assertEquals("Fill the task description", message);

		} finally {
		//fechar o browser
		driver.quit();
		}
	}

	@Test
	public void naoDeveSalvarTarefaSemData() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		try {
		
		//clicar no add todo
		driver.findElement(By.id("addTodo")).click();
		
		//escrever a descrição
		driver.findElement(By.id("task")).sendKeys("Teste via selenium");
		
		//escrever a data
		//driver.findElement(By.id("dueDate")).sendKeys("27/03/2025");
		
		//clicar em salvar
		driver.findElement(By.id("saveButton")).click();
		
		//validar mensagem de sucesso
		String message = driver.findElement(By.id("message")).getText();
		Assert.assertEquals("Fill the due date", message);

		} finally {
		//fechar o browser
		driver.quit();
		}
	}
	@Test
	public void naoDeveSalvarTarefaComDataPassada() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		try {
		
		//clicar no add todo
		driver.findElement(By.id("addTodo")).click();
		
		//escrever a descrição
		driver.findElement(By.id("task")).sendKeys("Teste via selenium");
		
		//escrever a data
		driver.findElement(By.id("dueDate")).sendKeys("27/03/2022");
		
		//clicar em salvar
		driver.findElement(By.id("saveButton")).click();
		
		//validar mensagem de sucesso
		String message = driver.findElement(By.id("message")).getText();
		Assert.assertEquals("Due date must not be in past", message);

		} finally {
		//fechar o browser
		driver.quit();
		}
	}
		
		@Test
		public void deveRemoverTarefaComSucesso() throws MalformedURLException {
			WebDriver driver = acessarAplicacao();
			try {
			
			//clicar no Remover
			driver.findElement(By.id("addTodo")).click();
			
			//escrever a descrição
			driver.findElement(By.id("task")).sendKeys("Teste via selenium");
			
			//escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("27/03/2025");
			
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			//validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Success!", message);

			//remover a tarefa
			driver.findElement(By.xpath("//a[@class='btn btn-outline-danger btn-sm']")).click();
			message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Success!", message);
			
			} finally {
			//fechar o browser
			driver.quit();
		}
	}
}
