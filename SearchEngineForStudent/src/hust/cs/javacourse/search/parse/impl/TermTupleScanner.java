package hust.cs.javacourse.search.parse.impl;

import hust.cs.javacourse.search.index.AbstractTerm;
import hust.cs.javacourse.search.index.AbstractTermTuple;
import hust.cs.javacourse.search.index.impl.Term;
import hust.cs.javacourse.search.index.impl.TermTuple;
import hust.cs.javacourse.search.parse.AbstractTermTupleScanner;
import hust.cs.javacourse.search.util.Config;
import hust.cs.javacourse.search.util.StringSplitter;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TermTupleScanner extends AbstractTermTupleScanner {
    private int position;
    private List<String> buf;
    private StringSplitter stringSplitter = new StringSplitter();

    /**
     * 缺省构造函数
     */
    public TermTupleScanner() {
        super();
        this.position = 0;      //记录单词的全局位置
        this.buf = new ArrayList<>();
        stringSplitter.setSplitRegex(Config.STRING_SPLITTER_REGEX);
    }

    /**
     * 构造函数
     *
     * @param input ：指定输入流对象，应该关联到一个文本文件
     */
    public TermTupleScanner(BufferedReader input) {
        super(input);
        this.position = 0;      //记录单词的全局位置
        this.buf = new ArrayList<>();
        stringSplitter.setSplitRegex(Config.STRING_SPLITTER_REGEX);
    }

    /**
     * 实现父类AbstractTermTupleStream的close方法，关闭流
     */
    @Override
    public void close() {
        super.close();
    }

    /**
     * 获得下一个三元组
     * 从
     *
     * @return: 下一个三元组；如果到了流的末尾，返回null
     */
    @Override
    public AbstractTermTuple next() {
        try {
            if (buf.isEmpty()) {
                String s;
                StringBuilder sb = new StringBuilder();
                while ((s = input.readLine()) != null) {
                    sb.append(s).append("\n");
                }
                s = sb.toString().trim().toLowerCase();
                buf = stringSplitter.splitByRegex(s);
            }
            if (buf.size() == 0)
                return null;
            AbstractTerm term = new Term(buf.get(0));
            buf.remove(0);
            TermTuple tempTermTuple = new TermTuple();
            tempTermTuple.term = term;
            tempTermTuple.curPos = position++;
            return tempTermTuple;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
