package com.xlf.demo.test;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class jiangjiefuwu {

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		System.setProperty("webdriver.chrome.driver", "E:\\Mycode\\chromedriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize(); // ��������
		driver.get("http://beta.cd917.com/login"); // ���Ե���վ

		System.out.println("1 Page title is: " + driver.getTitle());
		WebElement element1 = driver.findElement(By.id("loginName1"));

		WebElement element2 = driver.findElement(By.id("password1"));
		WebElement element3 = driver.findElement(By.id("mainForm"));
		element1.clear();
		element1.sendKeys(new String[] { "�������" }); // �����������������û�����
		element2.sendKeys(new String[] { "1111" }); // ��������
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		element3.submit(); // �����¼

		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		WebElement element4 = driver.findElement(By.xpath("//*[@id='main-nav']/ul/li[4]/a")); // ѡ����������
		element4.click();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		WebElement element5 = driver.findElement(By.xpath("//*[@id='ContentCon']/article/section/h1/a[3]")); // �������
		element5.click();

		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement iframe = driver.findElement(By.xpath("//*[@id='Form1']/div[2]/table/tbody/tr[2]/td[5]/a")); // �������ѡ��
		iframe.click();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		WebElement frame = driver.findElement(By.id("frameDialog"));
		driver.switchTo().frame(frame);
		WebElement element10 = driver.findElement(By.xpath("//*[@id='btnSettle']")); // �������
		element10.click();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// ȷ�Ϲ���������
		Alert confirm = driver.switchTo().alert();
		String text1 = confirm.getText();
		System.out.println("confirm:" + text1);
		confirm.accept(); // ȷ�Ϲ��� ��ȷ�ϰ�ť

		try {
			Thread.sleep(60000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.switchTo().defaultContent();
		WebElement element11 = driver.findElement(By.xpath("//*[@id='main-nav']/ul/li[6]/a")); // ѡ�񶩵�����
		element11.click();

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement element12 = driver
				.findElement(By.xpath("//*[@id='table_form']/div[2]/div[1]/div/table[2]/tbody/tr[2]/td[8]/a[2]")); // �˿�
		element12.click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		WebElement frame1 = driver.findElement(By.id("frameDialog"));
		driver.switchTo().frame(frame1);

		WebElement element13 = driver.findElement(By.xpath("//*[@id='mainForm']/div[2]/button")); // ȷ���˿�
		element13.click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
		}
		// ȷ�Ϲ���������
		Alert confirm1 = driver.switchTo().alert();
		String text2 = confirm.getText();
		System.out.println("confirm:" + text2);
		confirm1.accept(); // ȷ�Ϲ��� ��ȷ�ϰ�ť
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		WebElement element14 = driver.findElement(By.xpath("html/body/div[8]/div[1]/button")); // ȷ���˿�
		element14.click();
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.quit();
	}

}

// �������򽲽���� ȷ�ϲ�����Ʊ
