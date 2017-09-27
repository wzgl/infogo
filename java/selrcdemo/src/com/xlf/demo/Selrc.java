package com.xlf.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Selrc {
	private static WebDriver  driver;
	private static String userName="xialfiei1";
	private static String passWord="1";

	public static void main(String[] args)
	{
		System.setProperty("webdriver.chrome.driver", "E:\\Mycode\\chromedriver.exe");
		 driver = new ChromeDriver();
		driver.manage().window().maximize(); // ×î´ó»¯ä¯ÀÀÆ÷
		for(int x=0;x<10;x++){
		driver.get("http://172.20.46.143"); // ²âÊÔµÄÍøÕ¾
		
		System.out.println("1 Page title is: " + driver.getTitle());
		mySleep(1);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(0);
		WebElement workBtn = driver.findElement(By.xpath("//*[@id='workBtn']"));
		workBtn.click();
		//	System.out.println(workBtn);
		
		login(userName,passWord);
		int i=3;
		mySleep(3);
		while(driver.findElement(By.className("aui_inner")) != null){	//µÇÂ¼´íÎó
			driver.switchTo().defaultContent();
			String alert = driver.findElement(By.xpath("//*[@id='index_body']/div[1]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td[2]/div")).getText();
			System.out.println("afd");
			driver.findElement(By.xpath("//*[@id='index_body']/div[1]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[3]/td/div/button")).click();
			
			if(i<0)
				login("xialifei","xialifei");
			else login("xialifei1","xialifei");
			i--;
			mySleep(20);
		}
		}
	}

	public static void mySleep(long time) {
		try {
			System.out.println("Ë¯Ãß" + time + "Ãë");
			Thread.sleep(time * 1000);
			System.out.println("Ë¯Ãß" + time + "ÃëÍê³É");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void login(String user_name1, String password1) {
		mySleep(2);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(0);
		WebElement user_name = driver.findElement(By.id("user_name"));
		user_name.clear();
		user_name.sendKeys(user_name1);

		WebElement password = driver.findElement(By.id("password"));
		password.clear();
		password.sendKeys(password1);

		WebElement user_submit = driver.findElement(By.id("user_submit"));
		user_submit.click();
		mySleep(5);
	}
}