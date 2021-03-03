package cn.jinronga.listener;

import cn.jinronga.entity.ReadData;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ExcelListener
 * @Author 郭金荣
 * @Date 2021/3/2 8:38
 * @Description ExcelListener
 * @Version 1.0
 * 读取监听器
 */
//创建读取excel监听器
public class ExcelListener extends AnalysisEventListener<ReadData> {

    //创建list集合封装最终的数据
    List<ReadData> list= new ArrayList<>();

    //一行一行去读取excle内容
    @Override
    public void invoke(ReadData readData, AnalysisContext analysisContext) {
        System.out.println("==="+readData);
        //数据放进list集合中
        list.add(readData);
    }

    //读取excel表头信息
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        super.invokeHeadMap(headMap, context);
        System.out.println("表头信息："+headMap);
    }

    //读取完成后最后执行
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
