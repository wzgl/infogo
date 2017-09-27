package com.xlf.demo;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.exec.ExecuteException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class asmback {
	private static WebDriver driver;
	private static  String WebSite = "172.20.46.143";

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "E:\\Mycode\\chromedriver.exe");
		/*driver = new ChromeDriver();*/
		//ȥ��Chrome���յ��Զ���������Ŀ��� ��ʾ��
		ChromeOptions options = new ChromeOptions();
		options.addArguments("disable-infobars");
		driver = new ChromeDriver(options);
		
		driver.manage().window().maximize(); // ��������
		driver.get("http://"+WebSite+"/admin"); // ���Ե���վ

		System.out.println("1 Page title is: " + driver.getTitle());
		login("admin", "888888");//��¼
		
		mySleep(5);
		driver.switchTo().defaultContent();
		WebElement zhuye = driver.findElement(By.className("ion-ios-home-outline"));
		zhuye.click();		//������ҳ
		
		String tittle[]={"home_bg","networkinfo_bg","userinfo_bg","query_report_bg","alarmcenter_bg","regulation_bg",
						     "mobile_bg","system_set_bg"};
		//String n="";//�Զ���д�˵�����
		for (int i = 0; i < tittle.length; i++) {
			driver.switchTo().defaultContent();
			zhuye = driver.findElement(By.className(tittle[i]));
			zhuye.click();
			List<WebElement>leftmenu  = driver.findElements(By.tagName("dt"));
			System.out.println("�˵�������"+leftmenu.size());
			
			for (int j=0;j<leftmenu.size();j++) {
				WebElement webElement  =  leftmenu.get(j);
				if(!isRefresh(webElement)){
					mySleep(1);
					driver.switchTo().defaultContent();
					leftmenu  = driver.findElements(By.tagName("dt"));
					webElement  =  leftmenu.get(j);
				}
				webElement.click();
				//n+="\n case  \""+webElement.getText()+"\" : \n break;";
				System.out.println(webElement.getText());
				menu(webElement.getText(),webElement);	//�����Ӧ�Ĳ˵�
				mySleep(1);
			}
			mySleep(3);
		}
		//System.out.println(n);
		driver.quit();
	}
	/**
	 * ҳ��ˢ�µ���Ԫ��ʧЧ
	 * @param webElement
	 * @return
	 */
	private static boolean isRefresh(WebElement webElement) {
		// TODO Auto-generated method stub
		try{
			webElement.getText();
			return true;
		}catch(Exception e){
			return false;
		}
	}
	/**
	 * ���ݲ˵�����
	 * @param string
	 */
	private static void menu(String string,WebElement webElement) {
		// TODO Auto-generated method stub
		driver.switchTo().defaultContent();
		//ȡ�˵���Ӧ��iframe
		if(!isElementAtrribute(webElement, "onmouseout"))
			return;
		String mouseOut= webElement.getAttribute("onmouseout");
		if(mouseOut==null)
			return;
		String webID="";
		if(mouseOut.contains("fav")){
		String[] s = mouseOut.split("'");
		webID=s[1].split("v")[1];
		}else{
			String[] s = mouseOut.split(",");
			webID =s[0].split("\\(")[1];
		}
		String checkinfo ="return $('li[menuid=\"menuid_"+webID+"\"]').attr('id').replace('jerichotab','jerichotabiframe');";
		if(!isJsRun(checkinfo))
			return;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String frameid = (String) js.executeScript(checkinfo);
		if(!isElementPresent(By.id(frameid)))
			return ;
		//System.out.println(frameid);
		driver.switchTo().frame(driver.findElement(By.id(frameid)));
		
		switch(string){
			 case  "����ʹ����" : 
				 //ɶ������
			 break;
			 case  "�½�����" : 
				 WebElement w = driver.findElement(By.xpath("//*[@id='ItemName']"));
				 w.sendKeys("baidu");
				 WebElement web = driver.findElement(By.xpath("//*[@id='ItemValue']"));
				 web.sendKeys("http://www.baidu.com");
				 WebElement submit = driver.findElement(By.xpath("//*[@id='btn_sub']"));
				 submit.click();
			 break;
			 case  "ȫ������" : 
				 mySleep(1);
				 WebElement device_type = driver.findElement(By.xpath("//*[@id='device_type']/a"));
				 device_type.click();//��ʾ����
				 driver.switchTo().defaultContent();
				 if(isElementPresent(By.name("Opendevice_type_show"))){
					 driver.switchTo().frame(driver.findElement(By.name("Opendevice_type_show")));
					 int seed  = ((int)(100*Math.round(100))+1)%18;
					 device_type = driver.findElement(By.id("device_type"+seed));
					 device_type.click();
					 device_type = driver.findElement(By.id("introset_sub"));
					 device_type.click();
				 }
				 mySleep(3);
			 break;
			 case  "�豸��ͼ": 
			/*�����豸
			�û��豸
			PC(20)
			�Զ�����ͼ" :
*/			 break;
			 case  "�豸���ٶ�λ" : 
			 break;
			 case  "Զ��ά��" : 
			 break;
			 case  "����豸" : 
			 break;
			 case  "�����豸" : 
			 break;
			 case  "�豸����ģ�����" : 
			 break;
			 case  "�Զ������豸" : 
			 break;
			 case  "IPʹ�����" : 
			 break;
			 case  "IP��ַ��" : 
			 break;
			 case  "IP/MAC��" : 
			 break;
			 case  "IP/MAC��״̬" : 
			 break;
			 case  "ȫ���豸ָ��" : 
			 break;
			 case  "ָ�ư�" : 
			 break;
			 case  "�߽��������" : 
			 break;
			 case  "NAT�豸����" : 
			 break;
			 case  "HUB�豸����" : 
			 break;
			 case  "��������֯�ܹ�" : 
			 break;
			 case  "��֯�ܹ���" : 
			 break;
			 case  "������֯�ܹ�" : 
			 break;
			 case  "���ӽ�ɫ" : 
			 break;
			 case  "��ɫ�б�" : 
			 break;
			 case  "�����û��˻�" : 
			 break;
			 case  "�û��˻��б�" : 
			 break;
			 case  "�����û�" : 
			 break;
			 case  "ҵ������˻��б�" : 
			 break;
			 case  "AD�����������" : 
			 break;
			 case  "LDAP����������" : 
			 break;
			 case  "WEB����������" : 
			 break;
			 case  "�ʼ�����������" : 
			 break;
			 case  "RADIUS����������" : 
			 break;
			 case  "�߽��豸ȫ��ͳ��" : 
			 break;
			 case  "��ʷIPʹ�ü�¼" : 
			 break;
			 case  "�豸�����¼" : 
			 break;
			 case  "�豸δ�ػ�ͳ��" : 
			 break;
			 case  "�豸�����¼" : 
			 break;
			 case  "USB�豸ʹ�ü�¼" : 
			 break;
			 case  "�����Ȩ�����" : 
			 break;
			 case  "�ƹ�ɹ�" : 
			 break;
			 case  "�����豸����ͳ��" : 
			 break;
			 case  "Υ������" : 
			 break;
			 case  "��ȫ�ӹ�" : 
			 break;
			 case  "������б�" : 
			 break;
			 case  "Υ���޸���¼" : 
			 break;
			 case  "��ȫ�ӹ̼���¼" : 
			 break;
			 case  "ÿ����������" : 
			 break;
			 case  "ÿ����������" : 
			 break;
			 case  "ÿ����������" : 
			 break;
			 case  "��ȫ�����������" : 
			 break;
			 case  "�ʲ��䶯����" : 
			 break;
			 case  "�û���֤��¼" : 
			 break;
			 case  "������֤�����ɼ�¼" : 
			 break;
			 case  "�����û���֤��¼" : 
			 break;
			 case  "�����û������¼" : 
			 break;
			 case  "Υ������" : 
			 break;
			 case  "Υ�����" : 
			 break;
			 case  "Υ������" : 
			 break;
			 case  "�����豸��ȫ״��" : 
			 break;
			 case  "����ϵͳ����" : 
			 break;
			 case  "�����ͳ��" : 
			 break;
			 case  "�����֤ͳ��" : 
			 break;
			 case  "��ȫ�������" : 
			 break;
			 case  "Ӳ���䶯ͳ��" : 
			 break;
			 case  "����䶯ͳ��" : 
			 break;
			 case  "�豸ָ��ͳ��" : 
			 break;
			 case  "Ӳ���ʲ�" : 
			 break;
			 case  "����ʲ�" : 
			 break;
			 case  "Ӳ���䶯��¼" : 
			 break;
			 case  "����䶯��¼" : 
			 break;
			 case  "��������¼" : 
			 break;
			 case  "�����ն�����" : 
			 break;
			 case  "�ܹ����ն�����" : 
			 break;
			 case  "����ͳ��" : 
			 break;
			 case  "ʵʱ��������" : 
			 break;
			 case  "ȫ������" : 
			 break;
			 case  "�Զ��屨������" : 
			 break;
			 case  "������������" : 
			 break;
			 case  "�ʼ�����֪ͨ" : 
			 break;
			 case  "���ű���֪ͨ" : 
			 break;
			 case  "Syslog����ת��" : 
			 break;
			 case  "ģ�������б�" : 
			 break;
			 case  "��ȫ�淶�б�" : 
			 break;
			 case  "�淶���ɫӳ��" : 
			 break;
			 case  "�Զ��尲��������" : 
			 break;
			 case  "�������̹���" : 
			 break;
			 case  "׼�����������" : 
			 break;
			 case  "��ȫ��Ȩ�޷���" : 
			 break;
			 case  "��������" : 
			 break;
			 case  "DHCP����" : 
			 break;
			 case  "IPV6׼�����" : 
			 break;
			 case  "�������" : 
			 break;
			 case  "ARP׼��" : 
			 break;
			 case  "�˿ڰ�ȫ����" : 
			 break;
			 case  "802.1x׼��" : 
			 break;
			 case  "USBȫ�ֲ�������" : 
			 break;
			 case  "��ע��USB�б�" : 
			 break;
			 case  "USB�����б�" : 
			 break;
			 case  "����USB�б�" : 
			 break;
			 case  "�豸���" : 
			 break;
			 case  "USB�豸���" : 
			 break;
			 case  "������ҵ�����" : 
			 break;
			 case  "С����ȷ��������" : 
			 break;
			 case  "���ⰲװ������" : 
			 break;
			 case  "�������" : 
			 break;
			 case  "������������" : 
			 break;
			 case  "ҵ���������" : 
			 break;
			 case  "�����������" : 
			 break;
			 case  "�ƶ��ն�" : 
			 break;
			 case  "�ƶ��ն˿��Ź���" : 
			 break;
			 case  "�ƶ���ȫ�淶�б�" : 
			 break;
			 case  "�ƶ��淶��ɫӳ��" : 
			 break;
			 case  "�ƶ���֤����" : 
			 break;
			 case  "�ƶ��豸ע�����" : 
			 break;
			 case  "�ƶ��������涨��" : 
			 break;
			 case  "΢�Ź����˺��ƹ�" : 
			 break;
			 case  "��Ʒ���Ի�����" : 
			 break;
			 case  "ƽ̨ϵͳ����" : 
			 break;
			 case  "��ƷSNMP��Ϣ����" : 
			 break;
			 case  "IP��ַ����" : 
			 break;
			 case  "˫��HA����" : 
			 break;
			 case  "�ʼ���������" : 
			 break;
			 case  "���ŷ�������" : 
			 break;
			 case  "����������" : 
			 break;
			 case  "��������" : 
			 break;
			 case  "�ϴ��������" : 
			 break;
			 case  "ƽ̨������Ϣ" : 
			 break;
			 case  "�ӿ���������" : 
			 break;
			 case  "���ñ��ݻָ��뵼��" : 
			 break;
			 case  "ϵͳ������־" : 
			 break;
			 case  "������Ϲ���" : 
			 break;
			 case  "�ػ�������" : 
			 break;
			 case  "ϵͳ�鵵����" : 
			 break;
			 case  "������Ϣ�޸�" : 
			 break;
			 case  "�½�ϵͳ����Ա" : 
			 break;
			 case  "����׼ϵͳ����Ա" : 
			 break;
			 case  "ϵͳ����Ա�б�" : 
			 break;
		}
	}
	private static boolean isJsRun(String checkinfo) {
		// TODO Auto-generated method stub
		try{
			JavascriptExecutor js = (JavascriptExecutor) driver;
			 js.executeScript(checkinfo);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	/**
	 * ˯��
	 * @param time
	 */
	public static void mySleep(long time) {
		try {
			//System.out.println("˯��" + time + "��");
			Thread.sleep(time * 1000);
			//System.out.println("˯��" + time + "�����");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��¼
	 * @param user_name1
	 * @param password1
	 */
	public static void login(String user_name1, String password1) {
		mySleep(2);
		driver.switchTo().defaultContent();
		WebElement user_name = driver.findElement(By.id("login"));
		user_name.clear();
		user_name.sendKeys(user_name1);

		WebElement password = driver.findElement(By.id("password"));
		password.clear();
		password.sendKeys(password1);
		WebElement user_submit = driver.findElement(By.xpath("//*[@id='opform']/div/table/tbody/tr/td[4]/div/img"));
		
		if(isElementPresent(By.xpath("//*[@id='code_img']")) && isElementPresent(By.xpath("//*[@id='textfield']")) ){
		WebElement textfield = driver.findElement(By.xpath("//*[@id='textfield']"));
		//WebElement code_img = driver.findElement(By.xpath("//*[@id='code_img']"));
	/*try {
 		//ͨ��docr��ʶ����ͨ��ͼƬ������ʶ�����ѩ����������֤��
 		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			Point p=code_img.getLocation();
			int width = code_img.getSize().getWidth();
			int hight  = code_img.getSize().getHeight();
			Rectangle rect = new Rectangle(width,hight);
			 BufferedImage img;
				img = ImageIO.read(scrFile);
	            BufferedImage dest = img.getSubimage(p.getX(), p.getY(), width, hight);
	            ImageIO.write(dest, "png", scrFile);
	            mySleep(1);
	            File fng = new File("E:\\yzm.png");
	            if(fng.exists()){
	                fng.delete();
	            }
	            FileUtils.copyFile(scrFile, fng);
			
		 ITesseract instance = new Tesseract(); // JNA Interface Mapping
		 
		 File file = new File("E:\\yzm1.jpg");
			String result = instance.doOCR(file);
			System.out.println("--------------"+result+"----------------");
			mySleep(5);
		} catch (TesseractException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		try {
			System.out.println("��ʼʶ����֤�� " + new SimpleDateFormat("yy-MM-ss hh:mm:ss").format(System.currentTimeMillis()));
			for (int i = 1000; i < 10000; i++) {
				textfield.sendKeys(i + "");
				user_submit.click();
				if (!isElementPresent(By.id("login"))) {
					break;
				}
			}
			
			System.out
					.println("ʶ����֤����� " + new SimpleDateFormat("yy-MM-dd hh:mm:ss").format(System.currentTimeMillis()));
		} catch (Exception e) {
			System.out.println(
					"ҳ��ˢ�µ���Ԫ�ػ�ȡ������Exception in thread ��main�� org.openqa.selenium.StaleElementReferenceException: stale element reference: element is not attached to the page document");
		}
		}
		else {//����֤��
			user_submit.click();

		}
	}
	/**
	 * ���Ԫ���Ƿ����
	 * @param by
	 * @return
	 */
	private static boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	/**
	 * ���Ԫ���Ƿ����
	 * @param by
	 * @return
	 */
	private static boolean isElementAtrribute(WebElement web,String attr) {
		try {
			web.getAttribute(attr);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
