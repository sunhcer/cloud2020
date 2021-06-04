package com.atguigu.springcloud.util;


import com.atguigu.springcloud.entities.FileTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.*;

public class FileUtils {

    private final static Logger log = LoggerFactory.getLogger(FileUtils.class);

    private final static int BUFFER = 2048;

    public static File createPath(String filePath) {
        File dir = new File(filePath);
        if (!dir.exists()) {
            dir.mkdirs(); //创建目录
        }
        return dir;
    }

    public static File createTempFile(String filePath, String fileName) {
        File dir = new File(filePath);
        if (!dir.exists()) {
            dir.mkdirs(); //创建目录
        }
        File file = new File(dir, fileName);
        if (!file.exists()) {
            try {
                // 文创建空文件，件路径不存在抛异常，文件存在返回false
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    public static String getHash(String fp) {
        File file = new File(fp);
        return String.valueOf(file.lastModified());
    }

    public static boolean outputString(File file, String content, String encode) {
        boolean flag = true;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(file), encode);
            BufferedWriter writer = new BufferedWriter(write);
            writer.write(content);
            writer.close();
        } catch (Exception e) {
            System.out.println("写文件内容操作出错");
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    /**
     * 获取文件内容
     *
     * @param file
     * @return
     */
    public static String getFileContent(File file) {
        StringBuffer buffer = new StringBuffer();
        InputStream is = null;
        BufferedReader br = null;
        try {
            is = new FileInputStream(file);
            br = new BufferedReader(new InputStreamReader(is, codeString(file)));
            String line = "";
            while ((line = br.readLine()) != null) {
                buffer.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return buffer.toString();
    }

    public List<String> readMsgFile(File file) throws Exception {
        List<String> listResult = new ArrayList<String>();
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        while ((line = br.readLine()) != null) {
            listResult.add(line.trim());
        }
        br.close();
        fr.close();
        return listResult;
    }

    /**
     * 删除目录下文件
     *
     * @param dir
     * @return
     */
    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            // 递归删除目录中的子目录下
            for (int i = 0; i < children.length; i++) {
                deleteDir(new File(dir, children[i]));
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }

    public static void deleteFile(File file) {
        if (file.exists()) {
            if (file.isFile()) {
                file.delete();
            } else {
                for (File f : file.listFiles()) {
                    deleteFile(f);
                }
                file.delete();
            }
        }
    }

    /**
     * 获取文件绝对路径集合
     *
     * @param filePath
     * @param filter
     * @param list
     */
    public static void listAllFilePath(String filePath, FileFilter filter, List<String> list) {
        File root = new File(filePath);
        File[] files = root.listFiles(filter);
        for (File file : files) {
            if (file.isDirectory()) {
                list.add(file.getAbsolutePath());
                listAllFilePath(file.getAbsolutePath(), filter, list);
            } else {
                list.add(file.getAbsolutePath());
            }
        }
    }

    /**
     * 根据（后缀、时间、文件名）过滤获取文件绝对路径集合
     *
     * @param filePath
     * @param suffix
     * @param lastModifyDate
     * @param ignoreFile
     * @param list
     */
    public static void listAllFilePath(String filePath, final String[] suffix, final Date lastModifyDate,
                                       final String[] ignoreFile, List<String> list) {
        listAllFilePath(filePath, new FileFilter() {
            @Override
            public boolean accept(File file) {
                if (file.isDirectory()) {
                    return true;
                }
                for (String string : suffix) {
                    if (file.getName().endsWith(string)) {
                        Date fileDate = new Date(file.lastModified());
                        if (fileDate.getTime() > lastModifyDate.getTime()) {
                            return true;
                        } else {
                            return false;
                        }
                    }
                }
                for (String string : ignoreFile) {
                    if (file.getName().equals(string)) {
                        return false;
                    }
                }
                return false;
            }
        }, list);
    }

    /**
     * 列出可用的文件系统根
     */
    public static void listSystemRoot() {
        File[] files = File.listRoots();
        for (File file : files) {
            log.info(file.getName());
        }
    }

    /**
     * 复制文件夹所有文件
     *
     * @param srcPath 源文件路径
     * @param desPath 目标文件路径
     * @param filter  源文件过滤
     */
    public static void moveFiles(String srcPath, String desPath, FileFilter filter) {
        try {
            File file = new File(srcPath);
            if (!file.isDirectory()) {
                copyFile(file, new File(file.getAbsolutePath().replace(srcPath, desPath)));
            } else {
                File[] fileList = file.listFiles(filter);
                for (int i = 0; i < fileList.length; i++) {
                    if (!fileList[i].isDirectory()) {
                        copyFile(fileList[i], new File(fileList[i].getAbsolutePath().replace(srcPath, desPath)));
                    } else {
                        moveFiles(fileList[i].getAbsolutePath(),
                                fileList[i].getAbsolutePath().replace(srcPath, desPath), filter);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 复制文件
     *
     * @param srcFile
     * 源文件
     * @param desFile
     * 目标文件
     */
    public static boolean readFast = true;

    public static File copyFile(File srcFile, File desFile) {
        desFile.getParentFile().mkdirs();
        FileInputStream input = null;
        FileOutputStream output = null;
        try {
            // 可替换为任何路径何和文件名
            input = new FileInputStream(srcFile);
            // 可替换为任何路径何和文件名
            output = new FileOutputStream(desFile);
            if (readFast) {
                int temp = 0;
                byte buffer[] = new byte[BUFFER];
                while ((temp = input.read(buffer, 0, buffer.length)) != -1) {
                    output.write(buffer, 0, temp);
                }
            } else {
                while (input.read() != -1) {
                    output.write(input.read());
                }
            }
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                input.close();
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return desFile;
    }

    /**
     * 获取properties的值
     *
     * @param propFilePath
     * @param strs
     * @return
     */
    public static String[] getProperties(String propFilePath, String[] strs) {
        String[] result = new String[strs.length];
        Properties prop = new Properties();
        InputStream in = Object.class.getResourceAsStream(propFilePath);
        try {
            prop.load(in);
            for (int i = 0; i < result.length; i++) {
                result[i] = prop.getProperty(strs[i]).trim();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Map<String, String> getProperties(String propFilePath) {
        Map<String, String> map = new LinkedHashMap<String, String>();
        Properties prop = new Properties();
        InputStream in = Object.class.getResourceAsStream(propFilePath);
        try {
            prop.load(in);
            Iterator<Map.Entry<Object, Object>> it = prop.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<Object, Object> entry = (Map.Entry<Object, Object>) it.next();
                map.put(entry.getKey().toString().trim(), entry.getValue().toString().trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static String codeString(File file) throws IOException {
        if (file == null || !file.exists()) {
            System.out.println("文件不存在..." + file.getAbsolutePath());
            return null;
        }

        BufferedInputStream bin = new BufferedInputStream(new FileInputStream(file));
        int p = (bin.read() << 8) + bin.read();
        String code = null;
        //其中的 0xefbb、0xfffe、0xfeff、0x5c75这些都是这个文件的前面两个字节的16进制数
        switch (p) {
            case 0xefbb:
                code = "UTF-8";
                break;
            case 0xfffe:
                code = "Unicode";
                break;
            case 0xfeff:
                code = "UTF-16BE";
                break;
            case 0x5c75:
                code = "ANSI|ASCII";
                break;
            default:
                if (isUTF8(file.getAbsolutePath())) {
                    log.info("getFileCodeString: 编码格式为无BOM的UTF-8");
                    code = "UTF-8";
                } else {
                    code = "GBK";
                }
        }

        return code;
    }

    /**
     * 判断文件是否是无BOM的UTF-8编码
     *
     * @param fileName
     * @return
     */
    public static boolean isUTF8(String fileName) {
        boolean state = true;
        BufferedInputStream bin = null;
        try {
            bin = new BufferedInputStream(new FileInputStream(fileName));
            int count = 10;//设置判断的字节流的数量
            int firstByte = 0;
            //根据字节流是否符合UTF-8的标准来判断
            while (true) {
                if (count-- < 0 || (firstByte = bin.read()) == -1) {
                    break;
                }
                //判断字节流
                if ((firstByte & 0x80) == 0x00) {
                    //字节流为0xxxxxxx
                    continue;
                } else if ((firstByte & 0xe0) == 0xc0) {
                    //字节流为110xxxxx 10xxxxxx
                    if ((bin.read() & 0xc0) == 0x80) {
                        continue;
                    }
                } else if ((firstByte & 0xf0) == 0xe0) {
                    //字节流为1110xxxx 10xxxxxx 10xxxxxx
                    if ((bin.read() & 0xc0) == 0x80 && (bin.read() & 0xc0) == 0x80) {
                        continue;
                    }
                } else if ((firstByte & 0xf8) == 0xf0) {
                    //字节流为11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
                    if ((bin.read() & 0xc0) == 0x80 && (bin.read() & 0xc0) == 0x80 && (bin.read() & 0xc0) == 0x80) {
                        continue;
                    }
                } else if ((firstByte & 0xfc) == 0xf8) {
                    //字节流为111110xx 10xxxxxx 10xxxxxx 10xxxxxx 10xxxxxx
                    if ((bin.read() & 0xc0) == 0x80 && (bin.read() & 0xc0) == 0x80 && (bin.read() & 0xc0) == 0x80
                            && (bin.read() & 0xc0) == 0x80) {
                        continue;
                    }
                } else if ((firstByte & 0xfe) == 0xfc) {
                    //字节流为1111110x 10xxxxxx 10xxxxxx 10xxxxxx 10xxxxxx 10xxxxxx
                    if ((bin.read() & 0xc0) == 0x80 && (bin.read() & 0xc0) == 0x80 && (bin.read() & 0xc0) == 0x80
                            && (bin.read() & 0xc0) == 0x80 && (bin.read() & 0xc0) == 0x80) {
                        continue;
                    }
                }
                state = false;
                break;
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            state = false;
            e.printStackTrace();
        } finally {
            try {
                if (bin != null) {
                    bin.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return state;
    }

    /**
     * 获取上传文件的md5：文件名称 + 文件内容
     *
     * @param file
     * @return
     * @throws Exception
     * @throws IOException
     */
    public static String getMd5(MultipartFile file) {
        try {
            // 文件内容
            byte[] uploadBytes = file.getBytes();
            // 文件名称
            byte[] fileNameBytes = (file.getName() + file.getOriginalFilename()).getBytes();
            // 合并
            byte[] result = new byte[uploadBytes.length + fileNameBytes.length];
            System.arraycopy(uploadBytes, 0, result, 0, uploadBytes.length);
            System.arraycopy(fileNameBytes, 0, result, uploadBytes.length, fileNameBytes.length);
            // MD5
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digest = md5.digest(result);
            String hashString = new BigInteger(1, digest).toString(16);
            return hashString;
        } catch (Exception e) {
            log.error(e.toString(), e);
        }
        return null;
    }

    public static void download(String path, OutputStream outputStream) throws IOException {
        // 发送给客户端的数据
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        // 读取filename
        bis = new BufferedInputStream(new FileInputStream(new File(path)));
        int i = bis.read(buff);
        while (i != -1) {
            outputStream.write(buff, 0, buff.length);
            outputStream.flush();
            i = bis.read(buff);
        }
    }

    /**
     * 将Byte数组转换成文件
     *
     * @param bytes
     * @param filePath
     * @param fileName
     */
    public static void getFileByBytes(byte[] bytes, String filePath, String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if (!dir.exists() && dir.isDirectory()) {// 判断文件目录是否存在
                dir.mkdirs();
            }
            file = new File(filePath + "\\" + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取文件后缀
     *
     * @param File
     * @return
     */
    public static String getFileExtension(MultipartFile File) {
        String originalFileName = File.getOriginalFilename();
        return originalFileName.substring(originalFileName.lastIndexOf("."));
    }

    /**
     * 将字节数组转换成16进制字符串
     *
     * @param src
     * @return
     */
    public static String bytesToHex(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * 通过魔数获取文件后缀
     * @param in InputStream
     * @return
     * @throws IOException
     */
    public static String getFileType(InputStream in) throws IOException {
        byte[] b = new byte[28];
        try {
            in.read(b, 0, 28);
        } finally {
            if (in != null) {
                in.close();
            }
        }
        String fileHead = bytesToHex(b);

        if (fileHead != null && fileHead.length() > 0) {
            fileHead = fileHead.toUpperCase();
            FileTypeEnum[] fileTypeEnums = FileTypeEnum.values();
            for (FileTypeEnum fileTypeEnum : fileTypeEnums) {
                if (fileHead.startsWith(fileTypeEnum.getHeadValue())) {
                    return fileTypeEnum.getCode();
                }
            }
        }
        return null;
    }

    /**
     * 通过魔数获取文件后缀
     * @param file File
     * @return
     */
    public static String getFileType(File file) {
        InputStream in = null;
        String extension = null;
        try {
            in = new FileInputStream(file);
            extension = getFileType(in);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return extension;
    }

    /**
     * 通过魔数获取文件后缀
     * @param file MultipartFile
     * @return
     */
    public static String getFileType(MultipartFile file) {
        InputStream in = null;
        String extension = null;
        try {
            in = file.getInputStream();
            extension = getFileType(in);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return extension;
    }

    public static void saveFile(InputStream inputStream, String filePath, String fileName) {
        createPath(filePath);
        DataInputStream in = new DataInputStream(inputStream);
        DataOutputStream os = null;
        try {
            os = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(filePath + File.separator + fileName)));
            byte[] bs = new byte[0];
            bs = new byte[in.available()];
            in.read(bs);
            os.write(bs);
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    //将文件转换成Byte数组
    public static byte[] getBytesByFile(String filePath) {
        File file = new File(filePath);
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            byte[] data = bos.toByteArray();
            bos.close();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {
        List<String> list = new ArrayList();
        listAllFilePath("D:\\mt\\wchot", null, list);
        for (String string : list) {
            log.info(string);
        }
    }

}
