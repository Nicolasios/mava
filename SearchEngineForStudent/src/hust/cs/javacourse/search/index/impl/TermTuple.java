package hust.cs.javacourse.search.index.impl;

import hust.cs.javacourse.search.index.AbstractTermTuple;

public class TermTuple extends AbstractTermTuple {


    /**
     * 判断二个三元组内容是否相同
     *
     * @param obj ：要比较的另外一个三元组
     * @return 如果内容相等（三个属性内容都相等）返回true，否则返回false
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        else if (obj instanceof TermTuple) {
            TermTuple termTuple = (TermTuple) obj;
            if (termTuple.term != null && this.term != null)
                return termTuple.term.equals(this.term) && termTuple.curPos == this.curPos;
            else if (termTuple.term == null && this.term == null)
                return termTuple.curPos == this.curPos;
        }
        return false;
    }

    /**
     * 获得三元组的字符串表示
     *
     * @return ： 三元组的字符串表示
     */
    @Override
    public String toString() {
        return this.term.toString() + ", position: " + this.curPos + ", freq: " + this.freq;
    }
}
