package main.java.com.lnstark.pp.tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zaoji_lai
 * @since 2024/5/29 13:36
 */
public class YnxwBase64 {
    // Base64 字符集
    private static final String BASE64_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

    // 状态变量
    private int state = 0;  // 解码器的状态
    private int buffer = 0; // 用于存储部分结果的缓冲区
    private int outputLength = 0; // 输出长度
    private byte[] outputBuffer = new byte[1024]; // 输出缓冲区
    private int[] base64IndexTable = new int[256]; // 用于将 Base64 字符映射到索引值

    public YnxwBase64() {
        // 初始化 base64IndexTable，将 Base64 字符集的字符映射到索引值
        for (int i = 0; i < BASE64_CHARS.length(); i++) {
            base64IndexTable[BASE64_CHARS.charAt(i)] = i;
        }
    }

    public static void main(String[] args) {

        File folder = new File("D:\\ynxw\\sources");

        // 正则表达式，匹配 C0000a.m0c("") 中的内容
        String regex = "C0000a\\.m0c\\(\"([^\"]*)\"\\)";

        // 编译正则表达式
        Pattern pattern = Pattern.compile(regex);

        // 递归遍历文件夹
        traverseFolder(folder, pattern);

    }

    public static void traverseFolder(File folder, Pattern pattern) {
        if (folder.isDirectory()) {
            // 获取文件夹中的所有文件和子文件夹
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    // 递归遍历子文件夹
                    traverseFolder(file, pattern);
                }
            }
        } else if (folder.isFile()) {
            System.out.println("文件: " + folder.getName());
            // 处理文件
            try (BufferedReader reader = new BufferedReader(new FileReader(folder))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    // 创建匹配器
                    Matcher matcher = pattern.matcher(line);
                    // 查找并打印匹配的内容
                    while (matcher.find()) {
                        String extracted = matcher.group(1);
//                        System.out.println("文件: " + folder.getPath() + " 提取的内容: " + extracted);
                        decode(extracted);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String decode(String str) {
//        System.out.println(str);
        byte[] input = str.getBytes();
        YnxwBase64 decoder = new YnxwBase64();
        boolean result = decoder.decode(input, 0, input.length, true);
//        System.out.println(result);
        byte[] decodedBytes = decoder.getOutput();
        int length = decodedBytes.length;
        byte[] bArr = "Netease".getBytes();
        int i = 0;
        int i2 = 0;
        while (i < length) {
            if (i2 >= 7) {
                i2 = 0;
            }
            decodedBytes[i] = (byte) (decodedBytes[i] ^ bArr[i2]);
            i++;
            i2++;
        }
        System.out.println(new String(decodedBytes));
        return new String(decodedBytes);
    }

    public boolean decode(byte[] input, int offset, int length, boolean finish) {
        int inputIndex = offset;
        int end = offset + length;
        int state = this.state;
        int buffer = this.buffer;
        int outputIndex = this.outputLength;
        byte[] output = this.outputBuffer;
        int[] base64Index = this.base64IndexTable;

        while (inputIndex < end) {
            int index = base64Index[input[inputIndex++] & 255];
            if (index == -1) {
                continue; // 忽略无效字符
            }
            switch (state) {
                case 0:
                    if (index >= 0) {
                        buffer = index;
                        state = 1;
                    } else if (index != -1) {
                        this.state = 6;
                        return false;
                    }
                    break;
                case 1:
                    if (index >= 0) {
                        buffer = (buffer << 6) | index;
                        state = 2;
                    } else if (index == -1) {
                        this.state = 6;
                        return false;
                    }
                    break;
                case 2:
                    if (index >= 0) {
                        buffer = (buffer << 6) | index;
                        state = 3;
                    } else if (index == -2) {
                        output[outputIndex++] = (byte) (buffer >> 4);
                        state = 4;
                    } else if (index == -1) {
                        this.state = 6;
                        return false;
                    }
                    break;
                case 3:
                    if (index >= 0) {
                        buffer = (buffer << 6) | index;
                        output[outputIndex + 2] = (byte) buffer;
                        output[outputIndex + 1] = (byte) (buffer >> 8);
                        output[outputIndex] = (byte) (buffer >> 16);
                        outputIndex += 3;
                        state = 0;
                    } else if (index == -2) {
                        output[outputIndex + 1] = (byte) (buffer >> 2);
                        output[outputIndex] = (byte) (buffer >> 10);
                        outputIndex += 2;
                        state = 5;
                    } else if (index == -1) {
                        this.state = 6;
                        return false;
                    }
                    break;
                case 4:
                    if (index == -2) {
                        state++;
                    } else if (index != -1) {
                        this.state = 6;
                        return false;
                    }
                    break;
                case 5:
                    if (index != -1) {
                        this.state = 6;
                        return false;
                    }
                    break;
            }
        }

        if (finish) {
            switch (state) {
                case 0:
                    break;
                case 1:
                    this.state = 6;
                    return false;
                case 2:
                    output[outputIndex++] = (byte) (buffer >> 4);
                    break;
                case 3:
                    output[outputIndex++] = (byte) (buffer >> 10);
                    output[outputIndex++] = (byte) (buffer >> 2);
                    break;
                case 4:
                case 5:
                    this.state = 6;
                    return false;
            }
            this.state = 0;
        } else {
            this.state = state;
            this.buffer = buffer;
        }
        this.outputLength = outputIndex;
        return true;
    }

    public byte[] getOutput() {
        int outputLength = this.outputLength;
//        outputLength = this.outputBuffer.length;
        byte[] output = new byte[outputLength];
        System.arraycopy(this.outputBuffer, 0, output, 0, outputLength);
        return output;
    }
}
