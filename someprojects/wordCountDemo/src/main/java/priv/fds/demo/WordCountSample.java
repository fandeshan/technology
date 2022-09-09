package priv.fds.demo;

import com.kennycason.kumo.CollisionMode;
import com.kennycason.kumo.WordCloud;
import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.bg.CircleBackground;
import com.kennycason.kumo.font.KumoFont;
import com.kennycason.kumo.font.scale.SqrtFontScalar;
import com.kennycason.kumo.nlp.FrequencyAnalyzer;
import com.kennycason.kumo.nlp.tokenizers.ChineseWordTokenizer;
import com.kennycason.kumo.palette.LinearGradientColorPalette;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author fandeshan
 * @description //TODO 写点注释吧
 * @date 2022/9/7
 */
public class WordCountSample {
    public static void main(String[] args) {
        createWordCountPic();
    }
    private static void createWordCountPic(){
        FrequencyAnalyzer frequencyAnalyzer = new FrequencyAnalyzer();
        frequencyAnalyzer.setWordFrequenciesToReturn(600);
        frequencyAnalyzer.setMinWordLength(2);
        frequencyAnalyzer.setWordTokenizer(new ChineseWordTokenizer());
        // 可以直接从文件中读取
        //List<WordFrequency> wordFrequencies = frequencyAnalyzer.load(getInputStream("D:\\citydo-one\\技术\\Java_Note-master\\python\\tp\\Trump.txt"));
        List<WordFrequency> wordFrequencies = new ArrayList<>();
        // 用词语来随机生成词云
        String strValue = "菠萝=20, 草莓=20, 苹果=10, 西红柿=15, 榴莲=15,  西瓜=4, 猕猴桃=4, 火龙果=4";

        //以逗号为分割号
        String[] split = strValue.split(", ");
        String word = "";
        int count = 0;

        for (int i = 0; i < split.length; i++) {
            String[] wordInfo = split[i].split("=");
            word = wordInfo[0];
            count = Integer.valueOf(wordInfo[1]);
            wordFrequencies.add(new WordFrequency(word, count));
        }
        //加入分词并随机生成权重&#xff0c;每次生成得图片都不一样
        //test.stream().forEach(e-> wordFrequencies.add(new WordFrequency(e,new Random().nextInt(test.size()))));
        //此处不设置会出现中文乱码
        java.awt.Font font = new java.awt.Font("STSong-Light", 2, 18);
        //设置图片分辨率
        Dimension dimension = new Dimension(500, 500);
        //此处的设置采用内置常量即可&#xff0c;生成词云对象
        WordCloud wordCloud = new WordCloud(dimension, CollisionMode.PIXEL_PERFECT);
        //设置边界及字体
        wordCloud.setPadding(2);
        //因为我这边是生成一个圆形,这边设置圆的半径
        wordCloud.setBackground(new CircleBackground(255));
        wordCloud.setFontScalar(new SqrtFontScalar(12, 42));
        //设置词云显示的三种颜色&#xff0c;越靠前设置表示词频越高的词语的颜色
        wordCloud.setColorPalette(new LinearGradientColorPalette(Color.RED, Color.BLUE, Color.GREEN, 30, 30));
        wordCloud.setKumoFont(new KumoFont(font));
        wordCloud.setBackgroundColor(new Color(255, 255, 255));
        //因为我这边是生成一个圆形,这边设置圆的半径
        wordCloud.setBackground(new CircleBackground(255));
        wordCloud.build(wordFrequencies);
        //生成词云图路径
        wordCloud.writeToFile("E:\\词云.png");
    }
}
