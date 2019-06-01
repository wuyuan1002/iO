package fileDeleteDemo;

import java.io.File;

/**
 *
 * 需求：删除给定文件或文件夹的全部内容
 *
 *
 *
 * @author wuyuan
 * @version 1.0
 * @date 2019/3/17 9:06
 */
public class FiledeleteCode {
    public static void main(String[] args) {
        /*
         * 封装数据地址
         */
        File file = new File("D:\\demo");
        deletefolder(file);
    }

    private static void deletefolder(File file) {
        if (!file.exists()) {
            System.out.println("文件或文件夹不存在！！！");
            System.exit(0);
        }
        if (file.isFile()) {
            System.out.println("已删除："+file.getAbsolutePath());
            file.delete();
        } else {
            File[] files = file.listFiles();
            for (File file1 : files) {
                deletefolder(file1);
            }
            System.out.println("已删除："+file.getAbsolutePath());
            file.delete();
        }
    }
}
