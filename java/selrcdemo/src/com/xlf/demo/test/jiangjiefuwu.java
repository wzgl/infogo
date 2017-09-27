package com.xlf.demo.test;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class jiangjiefuwu {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		System.setProperty("webdriver.chrome.driver", "E:\\Mycode\\chromedriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize(); // 最大化浏览器
		driver.get("http://beta.cd917.com/login"); // 测试的网站

		System.out.println("1 Page title is: " + driver.getTitle());
		WebElement element1 = driver.findElement(By.id("loginName1"));

		WebElement element2 = driver.findElement(By.id("password1"));
		WebElement element3 = driver.findElement(By.id("mainForm"));
		element1.clear();
		element1.sendKeys(new String[] { "武侯朱磊" }); // 先清空输入框，再输入用户名，
		element2.sendKeys(new String[] { "1111" }); // 输入密码
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		element3.submit(); // 点击登录

		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		WebElement element4 = driver.findElement(By.xpath("//*[@id='main-nav']/ul/li[4]/a")); // 选择其他销售
		element4.click();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		WebElement element5 = driver.findElement(By.xpath("//*[@id='ContentCon']/article/section/h1/a[3]")); // 讲解服务
		element5.click();

		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement iframe = driver.findElement(By.xpath("//*[@id='Form1']/div[2]/table/tbody/tr[2]/td[5]/a")); // 讲解服务选择
		iframe.click();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		WebElement frame = driver.findElement(By.id("frameDialog"));
		driver.switchTo().frame(frame);
		WebElement element10 = driver.findElement(By.xpath("//*[@id='btnSettle']")); // 点击购买
		element10.click();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// 确认购买的填出框
		Alert confirm = driver.switchTo().alert();
		String text1 = confirm.getText();
		System.out.println("confirm:" + text1);
		confirm.accept(); // 确认购买 的确认按钮

		try {
			Thread.sleep(60000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.switchTo().defaultContent();
		WebElement element11 = driver.findElement(By.xpath("//*[@id='main-nav']/ul/li[6]/a")); // 选择订单管理
		element11.click();

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement element12 = driver
				.findElement(By.xpath("//*[@id='table_form']/div[2]/div[1]/div/table[2]/tbody/tr[2]/td[8]/a[2]")); // 退款
		element12.click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		WebElement frame1 = driver.findElement(By.id("frameDialog"));
		driver.switchTo().frame(frame1);

		WebElement element13 = driver.findElement(By.xpath("//*[@id='mainForm']/div[2]/button")); // 确认退款
		element13.click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
		}
		// 确认购买的填出框
		Alert confirm1 = driver.switchTo().alert();
		String text2 = confirm.getText();
		System.out.println("confirm:" + text2);
		confirm1.accept(); // 确认购买 的确认按钮
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		WebElement element14 = driver.findElement(By.xpath("html/body/div[8]/div[1]/button")); // 确认退款
		element14.click();
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.quit();
	}

}

// 正常购买讲解服务 确认不能退票
