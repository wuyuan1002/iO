package copyFoldersDemo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 需求：复制文件夹及里面的所有内容
 *
 * 分析：
 *      数据源：demo
 *      目的地：
 *
 *      操作：
 *      A:封装数据源File
 *      B:封装目的地File
 *      C:判断该File是文件夹还是文件
 *          是文件夹：就在目的地下创建该文件夹
 *                   获取该目录下的所有文件夹或文件的File数组
 *                   遍历得到每一个File对象
 *                   回到C  (递归)
 *
 *          是文件：复制它到目的地(使用字节流)
 *
 *
 *
 * @author wuyuan
 * @version 1.2
 * @date 2019/3/16 18:18
 */
public class CopyfoldersCode {
    public static void main(String[] args) throws IOException {
        /*
         *     封装数据源
         */
        File srcFile = new File("D:\\demo");

        /*
         *     封装目的地
         */
        File dextFile = new File("D:\\newdemo");

        /*
         * 复制文件
         */
        copyFolder(srcFile, dextFile);
    }

    private static void copyFolder(File srcFile, File dextFile) throws IOException {
        /*
         * 如果数据源不存在则退出程序
         */
        if (!srcFile.exists()) {
            System.out.println("数据源不存在！！！");
            System.exit(0);
        }
        /*
         * 如果目的地不存在则创建它
         */
        if (!dextFile.exists()){
            dextFile.mkdir();
        }
        /*
         * 如果数据源是文件，则得到它的每一个file对象
         */
        if (srcFile.isFile()){
            File newFile = new File(dextFile,srcFile.getName());
            copyFile(srcFile,newFile);

            //不放开是复制，放开后就是移动
//            srcFile.delete();
        } else {
            File newFolder = new File(dextFile,srcFile.getName());
            File[] files = srcFile.listFiles();
            for (File file : files) {
                copyFolder(file,newFolder);
            }

            //不放开是复制，放开后就是移动
//            srcFile.delete();
        }

    }

    private static void copyFile(File srcFile, File newFile) throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(srcFile));
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(newFile));

        byte[] bys = new byte[1024];
        int len;
        while ((len = bufferedInputStream.read(bys)) != -1){
            bufferedOutputStream.write(bys,0,len);
        }
        bufferedInputStream.close();
        bufferedOutputStream.close();
    }

}
