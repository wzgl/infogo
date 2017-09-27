package com.xlf.demo.test;

/**
 *  ���������������޳�û������������ 
 *�������������������� 
 *���������ߩ��������ߩ� 
 *��    ������������������
 *��	  ������������������ 
 *��    �����ש�������    �� 
 *���������������������� 
 *�������������ߡ������� 
 *���������������������� 
 *����������������������Code is far away from bug with the animal protecting 
 *������������������    ���ޱ���,������bug 
 *������������������ 
 *�������������������������� 
 *�������������������������ǩ� 
 *���������������������������� 
 *�������������������ש����� 
 *�������������ϩϡ����ϩ� 
 *�������������ߩ������ߩ� 
 * 
 * �������������о������թ����������� 
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * ������:Test.java
 * ������:
 * ��    ��:why
 * ʱ    ��:2017��3��17��
 */
public class Test {
	 public static synchronized String recognizeText(File imageFile, String imageFormat) throws Exception {
	    	String tessPath="D:\\ocr\\Tesseract-OCR";
	       File outputFile = new File(imageFile.getParentFile(), "output");
	       StringBuffer strB = new StringBuffer();
	      String[] cm=new String[]{tessPath+"/tesseract",imageFile.getAbsolutePath(),outputFile.getAbsolutePath(),"-l","normal"};
	      System.out.println("ִ�е�������    ");
	      for(String str:cm){
	   	   System.out.print(str+" ");
	      }
	       Process pb = Runtime.getRuntime().exec(cm);
	       int w = pb.waitFor();
	       if (w == 0) {
	           BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(outputFile
	                  .getAbsolutePath()
	                  + ".txt"), "UTF-8"));
	           System.out.println("���ڶ�ȡ"+outputFile
	                  .getAbsolutePath()
	                  + ".txt �ļ�");
	           String str;
	           while ((str = in.readLine()) != null) {
	              strB.append(str);
	           }
	           System.out.println("��ȡ��� ����� "+strB.toString());
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
