package priv.fds.demo;

import java.io.*;

/**
 * @author fandeshan
 * @description //TODO 写点注释吧
 * @date 2022/9/7
 */
public class WeChatWordCount {
    public static void main(String[] args) throws Exception{
        loadMessageFiles();
    }
    private static void loadMessageFiles() throws Exception{
        File messageFile = new File("E:\\wordCount\\wc_msg.csv");
        File countFile = new File("E:\\wordCount\\countWord.txt");
        BufferedReader br = new BufferedReader(new FileReader(messageFile));
        PrintWriter pw = new PrintWriter(new FileOutputStream(countFile));
        String line;
        int count =0;
        while ((line = br.readLine() )!= null){
            String[] messages = line.split(",");
            System.out.println(count++);
            if (messages.length < 8){
                continue;
            }
            if (!"wxid_wbo41ashxq8k21".equals(messages[7])){
                continue;
            }
            //消息类型不为1，过滤该条数据
            if (!"1".equals(messages[2])){
                continue;
            }

            pw.println(messages[8]);
        }
        br.close();
        pw.close();
    }
    private static void createWordCountPic(File file){

    }
}
