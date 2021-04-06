package hust.cs.javacourse.search.parse.impl;

import hust.cs.javacourse.search.index.AbstractTermTuple;
import hust.cs.javacourse.search.index.impl.Term;
import hust.cs.javacourse.search.parse.AbstractTermTupleFilter;
import hust.cs.javacourse.search.parse.AbstractTermTupleStream;
import hust.cs.javacourse.search.util.Config;

import java.util.List;
import java.util.regex.Pattern;

public class PatternTermTupleFilter extends AbstractTermTupleFilter {
    private Pattern pattern = Pattern.compile(Config.TERM_FILTER_PATTERN);
    /**
     * kong构造函
     */
    public PatternTermTupleFilter(){
        super();
    }
    /**
     * 构造函数
     *
     * @param input ：Filter的输入，类型为AbstractTermTupleStream
     */
    public PatternTermTupleFilter(AbstractTermTupleStream input) {
        super(input);
        this.pattern = Pattern.compile(Config.TERM_FILTER_PATTERN);

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
     *
     * @return: 下一个三元组；如果到了流的末尾，返回null
     */
    @Override
    public AbstractTermTuple next() {
        AbstractTermTuple termTuple = input.next();
        if (termTuple == null)
            return null;
        while (!termTuple.term.getContent().matches(Config.TERM_FILTER_PATTERN)) {
            termTuple = input.next();
            if (termTuple == null)
                return null;
        }
        return termTuple;
    }
}
