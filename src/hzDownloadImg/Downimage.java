package hzDownloadImg;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author lenovo
 *	网络图片下载
 */
public class Downimage {

	public static void saveToFile(String destUrl) {
		FileOutputStream fos = null;
		BufferedInputStream bis = null;
		HttpURLConnection httpUrl = null;
		URL url = null;
		int BUFFER_SIZE = 1024;
		byte[] buf = new byte[BUFFER_SIZE];
		int size = 0;
		try {
			url = new URL(destUrl);
			httpUrl = (HttpURLConnection) url.openConnection();
			httpUrl.connect();
			String name = getFileName(destUrl);
			bis = new BufferedInputStream(httpUrl.getInputStream());
			fos = new FileOutputStream("d:\\kekeimage\\"+name);
			while ((size = bis.read(buf)) != -1) {
				fos.write(buf, 0, size);
			}
			fos.flush();
			System.out.println("=====成功=====");
		} catch (IOException e) {
			System.out.println("=====IOException下载失败=====");
		} catch (ClassCastException e) {
			System.out.println("=====ClassCastException下载失败=====");
		} finally {
			try {
				fos.close();
				bis.close();
				httpUrl.disconnect();
			} catch (IOException e) {
			} catch (NullPointerException e) {
				System.out.println("=====NullPointerException下载失败=====");
			}
		}
	}
	
	public static String getFileName(String path){
		return path.substring(path.lastIndexOf("/")+1);
	}

	public static void main(String[] args) {
		/*
		for (int i = 1; i < 365; i++) {
			final int j = i;
			new Thread(){
				public void run() {
					Downimage.saveToFile("https://www.mengkedu.com/resources/images/bg/"+j+".jpg");
				};
			}.start();
		}*/
	}
}