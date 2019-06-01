package Test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 需求:测试字节流四种方式复制文件的效率
 *
 * 字节流的四种方式：
 *    1.基本字节流一次读写一个字节(FileInputStream类,FileOutputStream类)
 *    2.基本字节流一次读写一个字节数组(FileInputStream类,FileOutputStream类)
 *    3.高效字节流一次读写一个字节(BufferedInputStream类,BufferedOutputStream类)
 *    4.高效字节流一次读写一个字节数组(BufferedInputStream类,BufferedOutputStream类)
 *
 *
 * @author wuyuan
 * @version 1.0
 * @date 2019/3/20 10:32
 */
public class TestFourWay {
    public static void main(String[] args) throws IOException {

        long start = System.currentTimeMillis();
        method1("a.txt","copy1.txt");
//		method2("a.txt","copy2.txt");
//		method3("a.txt","copy3.txt");
//		method4("a.txt","copy4.txt");
        long end = System.currentTimeMillis();
        System.out.println("该方法耗时："+ (end - start) + "毫秒");
    }

    /**
     * 基本字节流一次读写一个字节
     */
    private static void method1(String srcString,String destString) throws IOException {

        FileInputStream fis = new FileInputStream(srcString);
        FileOutputStream fos = new FileOutputStream(destString);

        int by = -1;
        while((by = fis.read()) != -1) {
            fos.write(by);
        }
        fis.close();
        fos.close();
    }

    /**
     * 基本字节流一次读写一个字节数组
     */
    private static void method2(String srcString,String destString) throws IOException {

        FileInputStream fis = new FileInputStream(srcString);
        FileOutputStream fos = new FileOutputStream(destString);

        byte[] by = new byte[1024];
        int len = -1;
        while((len = fis.read(by)) != -1) {
            fos.write(by,0,len);
        }
        fis.close();
        fos.close();
    }

    /**
     * 字节缓冲流一次读写一个字节
     */
    private static void method3(String srcString,String destString) throws IOException {

        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(srcString));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destString));

        int by = -1;
        while((by = bis.read()) != -1) {
            bos.write(by);
        }

        bis.close();
        bos.close();
    }

    /**
     * 字节缓冲流一次读写一个字节数组
     */
    private static void method4(String srcString,String destString) throws IOException {

        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(srcString));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destString));

        byte[] by = new byte[1024];
        int len = -1;
        while((len = bis.read(by)) != -1) {
            bos.write(by,0,len);
        }

        bis.close();
        bos.close();
    }

}
