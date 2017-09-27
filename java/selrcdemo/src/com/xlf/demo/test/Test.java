package com.xlf.demo.test;

/**
 *  ━━━━━━神兽出没━━━━━━ 
 *　　　┏┓　　　┏┓ 
 *　　┏┛┻━━━┛┻┓ 
 *　    ┃　　　　　　　┃
 *　	  ┃　　　━　　　┃ 
 *　    ┃　┳┛　┗┳    ┃ 
 *　　┃　　　　　　　┃ 
 *　　┃　　　┻　　　┃ 
 *　　┃　　　　　　　┃ 
 *　　┗━┓　　　┏━┛Code is far away from bug with the animal protecting 
 *　　　　┃　　　┃    神兽保佑,代码无bug 
 *　　　　┃　　　┃ 
 *　　　　┃　　　┗━━━┓ 
 *　　　　┃　　　　　　　┣┓ 
 *　　　　┃　　　　　　　┏┛ 
 *　　　　┗┓┓┏━┳┓┏┛ 
 *　　　　　┃┫┫　┃┫┫ 
 *　　　　　┗┻┛　┗┻┛ 
 * 
 * ━━━━━━感觉萌萌哒━━━━━━ 
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * 类名称:Test.java
 * 类描述:
 * 作    者:why
 * 时    间:2017年3月17日
 */
public class Test {
	 public static synchronized String recognizeText(File imageFile, String imageFormat) throws Exception {
	    	String tessPath="D:\\ocr\\Tesseract-OCR";
	       File outputFile = new File(imageFile.getParentFile(), "output");
	       StringBuffer strB = new StringBuffer();
	      String[] cm=new String[]{tessPath+"/tesseract",imageFile.getAbsolutePath(),outputFile.getAbsolutePath(),"-l","normal"};
	      System.out.println("执行的命令是    ");
	      for(String str:cm){
	   	   System.out.print(str+" ");
	      }
	       Process pb = Runtime.getRuntime().exec(cm);
	       int w = pb.waitFor();
	       if (w == 0) {
	           BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(outputFile
	                  .getAbsolutePath()
	                  + ".txt"), "UTF-8"));
	           System.out.println("正在读取"+outputFile
	                  .getAbsolutePath()
	                  + ".txt 文件");
	           String str;
	           while ((str = in.readLine()) != null) {
	              strB.append(str);
	           }
	           System.out.println("读取完成 结果是 "+strB.toString());
	           in.close();
	       } else {
	           String msg;
	           switch (w) {
	           case 1:
	              msg = "Errors accessing files. There may be spaces in your image's filename.";
	              break;
	           case 29:
	              msg = "Cannot recognize the image or its selected region.";
	              break;
	           case 31:
	              msg = "Unsupported image format.";
	              break;
	           default:
	              msg = "Errors occurred.";
	           }
//	           tempImage.delete();
	           throw new RuntimeException(msg);
	       }
//	       new File(outputFile.getAbsolutePath() + ".txt").delete();
	       return strB.toString();
	    }
	    public static void main(String[] args) {
	    	try {
				System.out.println(recognizeText(new File("C:\\Users\\Administrator\\Desktop\\11.png"), "png"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

}
