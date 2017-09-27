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
		//去掉Chrome正收到自动测试软件的控制 提示字
		ChromeOptions options = new ChromeOptions();
		options.addArguments("disable-infobars");
		driver = new ChromeDriver(options);
		
		driver.manage().window().maximize(); // 最大化浏览器
		driver.get("http://"+WebSite+"/admin"); // 测试的网站

		System.out.println("1 Page title is: " + driver.getTitle());
		login("admin", "888888");//登录
		
		mySleep(5);
		driver.switchTo().defaultContent();
		WebElement zhuye = driver.findElement(By.className("ion-ios-home-outline"));
		zhuye.click();		//进入首页
		
		String tittle[]={"home_bg","networkinfo_bg","userinfo_bg","query_report_bg","alarmcenter_bg","regulation_bg",
						     "mobile_bg","system_set_bg"};
		//String n="";//自动编写菜单代码
		for (int i = 0; i < tittle.length; i++) {
			driver.switchTo().defaultContent();
			zhuye = driver.findElement(By.className(tittle[i]));
			zhuye.click();
			List<WebElement>leftmenu  = driver.findElements(By.tagName("dt"));
			System.out.println("菜单总数："+leftmenu.size());
			
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
				menu(webElement.getText(),webElement);	//进入对应的菜单
				mySleep(1);
			}
			mySleep(3);
		}
		//System.out.println(n);
		driver.quit();
	}
	/**
	 * 页面刷新导致元素失效
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
	 * 根据菜单进行
	 * @param string
	 */
	private static void menu(String string,WebElement webElement) {
		// TODO Auto-generated method stub
		driver.switchTo().defaultContent();
		//取菜单对应的iframe
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
			 case  "初次使用向导" : 
				 //啥都不干
			 break;
			 case  "新建链接" : 
				 WebElement w = driver.findElement(By.xpath("//*[@id='ItemName']"));
				 w.sendKeys("baidu");
				 WebElement web = driver.findElement(By.xpath("//*[@id='ItemValue']"));
				 web.sendKeys("http://www.baidu.com");
				 WebElement submit = driver.findElement(By.xpath("//*[@id='btn_sub']"));
				 submit.click();
			 break;
			 case  "全网拓扑" : 
				 mySleep(1);
				 WebElement device_type = driver.findElement(By.xpath("//*[@id='device_type']/a"));
				 device_type.click();//显示类型
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
			 case  "设备视图": 
			/*网络设备
			用户设备
			PC(20)
			自定义视图" :
*/			 break;
			 case  "设备快速定位" : 
			 break;
			 case  "远程维护" : 
			 break;
			 case  "添加设备" : 
			 break;
			 case  "导入设备" : 
			 break;
			 case  "设备发现模板管理" : 
			 break;
			 case  "自动发现设备" : 
			 break;
			 case  "IP使用情况" : 
			 break;
			 case  "IP地址池" : 
			 break;
			 case  "IP/MAC绑定" : 
			 break;
			 case  "IP/MAC绑定状态" : 
			 break;
			 case  "全网设备指纹" : 
			 break;
			 case  "指纹绑定" : 
			 break;
			 case  "边界管理配置" : 
			 break;
			 case  "NAT设备管理" : 
			 break;
			 case  "HUB设备管理" : 
			 break;
			 case  "增加新组织架构" : 
			 break;
			 case  "组织架构树" : 
			 break;
			 case  "导入组织架构" : 
			 break;
			 case  "增加角色" : 
			 break;
			 case  "角色列表" : 
			 break;
			 case  "增加用户账户" : 
			 break;
			 case  "用户账户列表" : 
			 break;
			 case  "导入用户" : 
			 break;
			 case  "业务防护账户列表" : 
			 break;
			 case  "AD域服务器设置" : 
			 break;
			 case  "LDAP服务器设置" : 
			 break;
			 case  "WEB服务器设置" : 
			 break;
			 case  "邮件服务器设置" : 
			 break;
			 case  "RADIUS服务器设置" : 
			 break;
			 case  "边界设备全局统计" : 
			 break;
			 case  "历史IP使用记录" : 
			 break;
			 case  "设备清除记录" : 
			 break;
			 case  "设备未关机统计" : 
			 break;
			 case  "设备隔离记录" : 
			 break;
			 case  "USB设备使用记录" : 
			 break;
			 case  "软件授权检查结果" : 
			 break;
			 case  "推广成果" : 
			 break;
			 case  "网络设备操作统计" : 
			 break;
			 case  "违规外联" : 
			 break;
			 case  "安全加固" : 
			 break;
			 case  "检查结果列表" : 
			 break;
			 case  "违规修复记录" : 
			 break;
			 case  "安全加固检查记录" : 
			 break;
			 case  "每日入网报告" : 
			 break;
			 case  "每周入网报告" : 
			 break;
			 case  "每月入网报告" : 
			 break;
			 case  "安全检查评估报告" : 
			 break;
			 case  "资产变动报告" : 
			 break;
			 case  "用户认证记录" : 
			 break;
			 case  "短信验证码生成记录" : 
			 break;
			 case  "防护用户认证记录" : 
			 break;
			 case  "过期用户清除记录" : 
			 break;
			 case  "违规检查项" : 
			 break;
			 case  "违规次数" : 
			 break;
			 case  "违规项数" : 
			 break;
			 case  "入网设备安全状况" : 
			 break;
			 case  "操作系统安检" : 
			 break;
			 case  "检查项统计" : 
			 break;
			 case  "身份认证统计" : 
			 break;
			 case  "安全检查趋势" : 
			 break;
			 case  "硬件变动统计" : 
			 break;
			 case  "软件变动统计" : 
			 break;
			 case  "设备指纹统计" : 
			 break;
			 case  "硬件资产" : 
			 break;
			 case  "软件资产" : 
			 break;
			 case  "硬件变动记录" : 
			 break;
			 case  "软件变动记录" : 
			 break;
			 case  "攻击检测记录" : 
			 break;
			 case  "攻击终端排名" : 
			 break;
			 case  "受攻击终端排名" : 
			 break;
			 case  "攻击统计" : 
			 break;
			 case  "实时报警监视" : 
			 break;
			 case  "全部报警" : 
			 break;
			 case  "自定义报警管理" : 
			 break;
			 case  "报警生成设置" : 
			 break;
			 case  "邮件报警通知" : 
			 break;
			 case  "短信报警通知" : 
			 break;
			 case  "Syslog报警转发" : 
			 break;
			 case  "模板配置列表" : 
			 break;
			 case  "安全规范列表" : 
			 break;
			 case  "规范与角色映射" : 
			 break;
			 case  "自定义安检项样例" : 
			 break;
			 case  "入网流程管理" : 
			 break;
			 case  "准入控制器管理" : 
			 break;
			 case  "安全域权限分配" : 
			 break;
			 case  "补丁管理" : 
			 break;
			 case  "DHCP管理" : 
			 break;
			 case  "IPV6准入控制" : 
			 break;
			 case  "镜像管理" : 
			 break;
			 case  "ARP准入" : 
			 break;
			 case  "端口安全控制" : 
			 break;
			 case  "802.1x准入" : 
			 break;
			 case  "USB全局参数设置" : 
			 break;
			 case  "已注册USB列表" : 
			 break;
			 case  "USB类型列表" : 
			 break;
			 case  "例外USB列表" : 
			 break;
			 case  "设备审核" : 
			 break;
			 case  "USB设备审核" : 
			 break;
			 case  "第三方业务管理" : 
			 break;
			 case  "小助手确认码生成" : 
			 break;
			 case  "特殊安装包生成" : 
			 break;
			 case  "任务管理" : 
			 break;
			 case  "桌面联动管理" : 
			 break;
			 case  "业务防护管理" : 
			 break;
			 case  "攻击检测设置" : 
			 break;
			 case  "移动终端" : 
			 break;
			 case  "移动终端可信管理" : 
			 break;
			 case  "移动安全规范列表" : 
			 break;
			 case  "移动规范角色映射" : 
			 break;
			 case  "移动认证参数" : 
			 break;
			 case  "移动设备注册审核" : 
			 break;
			 case  "移动入网界面定制" : 
			 break;
			 case  "微信公众账号推广" : 
			 break;
			 case  "产品个性化定制" : 
			 break;
			 case  "平台系统设置" : 
			 break;
			 case  "产品SNMP信息定制" : 
			 break;
			 case  "IP地址配置" : 
			 break;
			 case  "双机HA管理" : 
			 break;
			 case  "邮件发送配置" : 
			 break;
			 case  "短信发送配置" : 
			 break;
			 case  "负载器配置" : 
			 break;
			 case  "数据漫游" : 
			 break;
			 case  "上传软件管理" : 
			 break;
			 case  "平台基本信息" : 
			 break;
			 case  "接口流量曲线" : 
			 break;
			 case  "配置备份恢复与导出" : 
			 break;
			 case  "系统调试日志" : 
			 break;
			 case  "网络诊断工具" : 
			 break;
			 case  "关机与重启" : 
			 break;
			 case  "系统归档设置" : 
			 break;
			 case  "个人信息修改" : 
			 break;
			 case  "新建系统管理员" : 
			 break;
			 case  "待批准系统管理员" : 
			 break;
			 case  "系统管理员列表" : 
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
	 * 睡眠
	 * @param time
	 */
	public static void mySleep(long time) {
		try {
			//System.out.println("睡眠" + time + "秒");
			Thread.sleep(time * 1000);
			//System.out.println("睡眠" + time + "秒完成");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 登录
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
 		//通过docr能识别普通的图片，不能识别加了雪花线条的验证码
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
			System.out.println("开始识别验证码 " + new SimpleDateFormat("yy-MM-ss hh:mm:ss").format(System.currentTimeMillis()));
			for (int i = 1000; i < 10000; i++) {
				textfield.sendKeys(i + "");
				user_submit.click();
				if (!isElementPresent(By.id("login"))) {
					break;
				}
			}
			
			System.out
					.println("识别验证码完成 " + new SimpleDateFormat("yy-MM-dd hh:mm:ss").format(System.currentTimeMillis()));
		} catch (Exception e) {
			System.out.println(
					"页面刷新导致元素获取不到：Exception in thread ‘main’ org.openqa.selenium.StaleElementReferenceException: stale element reference: element is not attached to the page document");
		}
		}
		else {//无验证码
			user_submit.click();

		}
	}
	/**
	 * 检查元素是否存在
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
	 * 检查元素是否存在
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
