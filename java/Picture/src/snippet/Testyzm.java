package snippet;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.color.ColorSpace;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.ColorModel;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class Testyzm {
	BufferedImage image;
	private int iw, ih;
	private int[] pixels;

	public Testyzm(BufferedImage image) {
		this.image = image;
		iw = image.getWidth();
		ih = image.getHeight();
		pixels = new int[iw * ih];
	}

	public BufferedImage changeGrey() {
		PixelGrabber pg = new PixelGrabber(image.getSource(), 0, 0, iw, ih, pixels, 0, iw);
		try {
			pg.grabPixels();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 设定二值化的域值，默认值为100
		int grey = 100;
		// 对图像进行二值化处理，Alpha值保持不变
		ColorModel cm = ColorModel.getRGBdefault();
		for (int i = 0; i < iw * ih; i++) {
			int red, green, blue;
			int alpha = cm.getAlpha(pixels[i]);
			if (cm.getRed(pixels[i]) > grey) {
				red = 255;
			} else {
				red = 0;
			}
			if (cm.getGreen(pixels[i]) > grey) {
				green = 255;
			} else {
				green = 0;
			}
			if (cm.getBlue(pixels[i]) > grey) {
				blue = 255;
			} else {
				blue = 0;
			}
			pixels[i] = alpha << 24 | red << 16 | green << 8 | blue; // 通过移位重新构成某一点像素的RGB值
		}
		
		// 将数组中的象素产生一个图像
		Image tempImg = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(iw, ih, pixels, 0, iw));
		image = new BufferedImage(tempImg.getWidth(null), tempImg.getHeight(null), BufferedImage.TYPE_INT_BGR);
		int h = image.getHeight();  
        int w = image.getWidth();  
		//去除干扰线条
        for(int y = 1; y < h-1; y++){
            for(int x = 1; x < w-1; x++){                   
                boolean flag = false ;
                if(isBlack(image.getRGB(x, y))){
                    //左右均为空时，去掉此点
                    if(isWhite(image.getRGB(x-1, y)) && isWhite(image.getRGB(x+1, y))){
                        flag = true;
                    }
                    //上下均为空时，去掉此点
                    if(isWhite(image.getRGB(x, y+1)) && isWhite(image.getRGB(x, y-1))){
                        flag = true;
                    }
                    //斜上下为空时，去掉此点
                    if(isWhite(image.getRGB(x-1, y+1)) && isWhite(image.getRGB(x+1, y-1))){
                        flag = true;
                    }
                    if(isWhite(image.getRGB(x+1, y+1)) && isWhite(image.getRGB(x-1, y-1))){
                        flag = true;
                    } 
                    if(flag){
                        image.setRGB(x,y,-1);                     
                    }
                }
            }
        }
		image.createGraphics().drawImage(tempImg, 0, 0, null);
		return image;
	}

	public BufferedImage getMedian() {
		PixelGrabber pg = new PixelGrabber(image.getSource(), 0, 0, iw, ih, pixels, 0, iw);
		try {
			pg.grabPixels();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 对图像进行中值滤波，Alpha值保持不变
		ColorModel cm = ColorModel.getRGBdefault();
		for (int i = 1; i < ih - 1; i++) {
			for (int j = 1; j < iw - 1; j++) {
				int red, green, blue;
				int alpha = cm.getAlpha(pixels[i * iw + j]);
				// int red2 = cm.getRed(pixels[(i - 1) * iw + j]);
				int red4 = cm.getRed(pixels[i * iw + j - 1]);
				int red5 = cm.getRed(pixels[i * iw + j]);
				int red6 = cm.getRed(pixels[i * iw + j + 1]);
				// int red8 = cm.getRed(pixels[(i + 1) * iw + j]);
				// 水平方向进行中值滤波
				if (red4 >= red5) {
					if (red5 >= red6) {
						red = red5;
					} else {
						if (red4 >= red6) {
							red = red6;
						} else {
							red = red4;
						}
					}
				} else {
					if (red4 > red6) {
						red = red4;
					} else {
						if (red5 > red6) {
							red = red6;
						} else {
							red = red5;
						}
					}
				}
				int green4 = cm.getGreen(pixels[i * iw + j - 1]);
				int green5 = cm.getGreen(pixels[i * iw + j]);
				int green6 = cm.getGreen(pixels[i * iw + j + 1]);
				// 水平方向进行中值滤波
				if (green4 >= green5) {
					if (green5 >= green6) {
						green = green5;
					} else {
						if (green4 >= green6) {
							green = green6;
						} else {
							green = green4;
						}
					}
				} else {
					if (green4 > green6) {
						green = green4;
					} else {
						if (green5 > green6) {
							green = green6;
						} else {
							green = green5;
						}
					}
				}
				// int blue2 = cm.getBlue(pixels[(i - 1) * iw + j]);
				int blue4 = cm.getBlue(pixels[i * iw + j - 1]);
				int blue5 = cm.getBlue(pixels[i * iw + j]);
				int blue6 = cm.getBlue(pixels[i * iw + j + 1]);
				// int blue8 = cm.getBlue(pixels[(i + 1) * iw + j]);
				// 水平方向进行中值滤波
				if (blue4 >= blue5) {
					if (blue5 >= blue6) {
						blue = blue5;
					} else {
						if (blue4 >= blue6) {
							blue = blue6;
						} else {
							blue = blue4;
						}
					}
				} else {
					if (blue4 > blue6) {
						blue = blue4;
					} else {
						if (blue5 > blue6) {
							blue = blue6;
						} else {
							blue = blue5;
						}
					}
				}
				pixels[i * iw + j] = alpha << 24 | red << 16 | green << 8 | blue;
			}
		}
		// 将数组中的象素产生一个图像
		Image tempImg = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(iw, ih, pixels, 0, iw));
		image = new BufferedImage(tempImg.getWidth(null), tempImg.getHeight(null), BufferedImage.TYPE_INT_BGR);
		image.createGraphics().drawImage(tempImg, 0, 0, null);
		return image;
	}
	 public static boolean isBlack(int colorInt)  
     {  
         Color color = new Color(colorInt);  
         if (color.getRed() + color.getGreen() + color.getBlue() <= 300)  
         {  
             return true;  
         }  
         return false;  
     }  

     public static boolean isWhite(int colorInt)  
     {  
         Color color = new Color(colorInt);  
         if (color.getRed() + color.getGreen() + color.getBlue() > 300)  
         {  
             return true;  
         }  
         return false;  
     }  

	public BufferedImage getGrey() {
		ColorConvertOp ccp = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
		return image = ccp.filter(image, null);
	}

	// Brighten using a linear formula that increases all color values
	public BufferedImage getBrighten() {
		RescaleOp rop = new RescaleOp(1.25f, 0, null);
		return image = rop.filter(image, null);
	}

	// Blur by "convolving" the image with a matrix
	public BufferedImage getBlur() {
		float[] data = { .1111f, .1111f, .1111f, .1111f, .1111f, .1111f, .1111f, .1111f, .1111f, };
		ConvolveOp cop = new ConvolveOp(new Kernel(3, 3, data));
		return image = cop.filter(image, null);
	}

	// Sharpen by using a different matrix
	public BufferedImage getSharpen() {
		float[] data = { 0.0f, -0.75f, 0.0f, -0.75f, 4.0f, -0.75f, 0.0f, -0.75f, 0.0f };
		ConvolveOp cop = new ConvolveOp(new Kernel(3, 3, data));
		return image = cop.filter(image, null);
	}

	// 11) Rotate the image 180 degrees about its center point
	public BufferedImage getRotate() {
		AffineTransformOp atop = new AffineTransformOp(
				AffineTransform.getRotateInstance(Math.PI, image.getWidth() / 2, image.getHeight() / 2),
				AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		return image = atop.filter(image, null);
	}

	public BufferedImage getProcessedImg() {
		return image;
	}
	public static BufferedImage reline(BufferedImage curImg) {
        if (curImg != null) {
            int width = curImg.getWidth();
            int height = curImg.getHeight();
            int px = 3;
            Map<Integer, Integer> map = new HashMap<Integer, Integer>();
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    int argb = curImg.getRGB(x, y);
                    int r = (int) (((argb >> 16) & 0xFF) * 1.1 + 30);
                    int g = (int) (((argb >> 8) & 0xFF) * 1.1 + 30);
                    int b = (int) (((argb >> 0) & 0xFF) * 1.1 + 30);
                    int sum = r + g + b;
                    if (!map.containsKey(sum)) {
                        map.put(sum, 1);
                    } else {
                        int num = map.get(sum);
                        map.remove(sum);
                        map.put(sum, num + 1);
                    }
                }
            }
            List<Integer> list = new ArrayList<Integer>();
            for (Integer in : map.keySet()) {
                // map.keySet()返回的是所有key的值
                Integer n = map.get(in);// 得到每个key多对用value的值
                list.add(n);
            }
            Collections.sort(list);
            // 四种颜色的rgb
            int num1 = 0;
            int num2 = 0;
            int num3 = 0;
            int num4 = 0;
            if (list.size() > 4) {
                num1 = list.get(list.size() - 5);
                num2 = list.get(list.size() - 4);
                num3 = list.get(list.size() - 3);
                num4 = list.get(list.size() - 2);
            }
            List<Integer> keylist = new ArrayList<Integer>();
            for (Integer key : map.keySet()) {
                if (map.get(key) == num1 || map.get(key) == num2 || map.get(key) == num3 || map.get(key) == num4) {
                    keylist.add(key);
                }
            }
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    int argb = curImg.getRGB(x, y);
                    int r = (int) (((argb >> 16) & 0xFF) * 1.1 + 30);
                    int g = (int) (((argb >> 8) & 0xFF) * 1.1 + 30);
                    int b = (int) (((argb >> 0) & 0xFF) * 1.1 + 30);
                    int sum = r + g + b;
                    int sum1 = 0;
                    int sum2 = 0;
                    int sum3 = 0;
                    int sum4 = 0;
                    int sum5 = 0;
                    int sum6 = 0;
                    boolean flag = true;
                    for (int i = 1; i <= px && y + i < height && y - i > 0 && x - i > 0 && x + i < width; i++) {
                        int upargb = curImg.getRGB(x, y - i);
                        int endargb = curImg.getRGB(x, y + i);
                        int rightupargb = curImg.getRGB(x + i, y + i);
                        int leftupargb = curImg.getRGB(x - i, y + i);
                        int leftdownargb = curImg.getRGB(x - i, y - i);
                        int rightdownargb = curImg.getRGB(x + i, y - i);
                        int r1 = (int) (((upargb >> 16) & 0xFF) * 1.1 + 30);
                        int g1 = (int) (((upargb >> 8) & 0xFF) * 1.1 + 30);
                        int b1 = (int) (((upargb >> 0) & 0xFF) * 1.1 + 30);
                        sum1 = r1 + g1 + b1;
                        int r2 = (int) (((endargb >> 16) & 0xFF) * 1.1 + 30);
                        int g2 = (int) (((endargb >> 8) & 0xFF) * 1.1 + 30);
                        int b2 = (int) (((endargb >> 0) & 0xFF) * 1.1 + 30);
                        sum2 = r2 + g2 + b2;
                        int r3 = (int) (((rightupargb >> 16) & 0xFF) * 1.1 + 30);
                        int g3 = (int) (((rightupargb >> 8) & 0xFF) * 1.1 + 30);
                        int b3 = (int) (((rightupargb >> 0) & 0xFF) * 1.1 + 30);
                        sum3 = r3 + g3 + b3;
                        int r4 = (int) (((leftupargb >> 16) & 0xFF) * 1.1 + 30);
                        int g4 = (int) (((leftupargb >> 8) & 0xFF) * 1.1 + 30);
                        int b4 = (int) (((leftupargb >> 0) & 0xFF) * 1.1 + 30);
                        sum4 = r4 + g4 + b4;
                        int r5 = (int) (((leftdownargb >> 16) & 0xFF) * 1.1 + 30);
                        int g5 = (int) (((leftdownargb >> 8) & 0xFF) * 1.1 + 30);
                        int b5 = (int) (((leftdownargb >> 0) & 0xFF) * 1.1 + 30);
                        sum5 = r5 + g5 + b5;
                        int r6 = (int) (((rightdownargb >> 16) & 0xFF) * 1.1 + 30);
                        int g6 = (int) (((rightdownargb >> 8) & 0xFF) * 1.1 + 30);
                        int b6 = (int) (((rightdownargb >> 0) & 0xFF) * 1.1 + 30);
                        sum6 = r6 + g6 + b6;

                        if (keylist.contains(sum1) || keylist.contains(sum2) || keylist.contains(sum3)
                                || keylist.contains(sum4) || keylist.contains(sum5) || keylist.contains(sum6)) {
                            flag = false;
                        }
                    }
                    if (!(keylist.contains(sum)) && flag) {
                        curImg.setRGB(x, y, Color.white.getRGB());
                    }
                }
            }

        }
        return curImg;
    }

	public static void main(String[] args) throws IOException {
		String imgfile = "E:\\yzm.png";
		FileInputStream fin = new FileInputStream(imgfile);
		BufferedImage bi = ImageIO.read(fin);
		//bi= reline(bi);
		Testyzm flt = new Testyzm(bi);
		flt.changeGrey();
		flt.getGrey();
		flt.getBrighten();
		bi = flt.getProcessedImg();
		String pname = imgfile.substring(0, imgfile.lastIndexOf("."));
		File file = new File(pname + "1.jpg");
		ImageIO.write(bi, "jpg", file);

		try {
			// 通过docr能识别普通的图片，不能识别加了雪花线条的验证码
			ITesseract instance = new Tesseract(); // JNA Interface Mapping

			File file1 = new File("E:\\yzm.png");
			String result = instance.doOCR(file1);
			System.out.println("--------------" + result + "----------------");
			File file2 = new File("E:\\yzm1.jpg");
			String result2 = instance.doOCR(file2);
			System.out.println("--------------" + result2 + "----------------");
		} catch (TesseractException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
