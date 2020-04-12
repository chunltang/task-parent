package day200411;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * 处理信息类
 */
public class DealData {

    //所有数据行，使用集合保存,比Stringbuiler更容易操作每一行
    private List<String> lines;

    public DealData() {
    }

    public DealData(List<String> lines) {
        this.lines = lines;
    }

    /**
     * 获取处理信息结果
     */
    public void getDealDataResult() {
        int row = 1;
        CheckData checkData;
        //因为不知道有几个校验成功，校验失败是不能转HydrologicalInfo的，数组大小不能确定，用集合保存比较好
        List<HydrologicalInfo> hydrologicalInfos = new ArrayList<>();
        //遍历数据行
        for (String line : lines) {
            checkData = new CheckData(line, row);
            //开始校验
            boolean validate = checkData.validateData();
            if (validate) {
                //校验成功，获取HydrologicalInfo对象
                HydrologicalInfo hydrologicalInfo = checkData.toHydrologicalInfo();
                hydrologicalInfos.add(hydrologicalInfo);
            }
            //行数加1
            row++;
        }
        //只有所有数据校验都正确才计算
        if(hydrologicalInfos.size()==lines.size()){
            computeData(hydrologicalInfos.toArray(new HydrologicalInfo[0]));
        }
    }

    /**
     * 计算
     */
    public void computeData(HydrologicalInfo hydrologicalInfos[]) {
        if (hydrologicalInfos.length > 0) {
            //最大实际水位
            double maxActualWater = 0;
            double countWaterFlow = 0;
            for (int i = 0; i < hydrologicalInfos.length; i++) {
                HydrologicalInfo info = hydrologicalInfos[i];
                double actualGateOpening = info.getActualGateOpening();
                double objectGateOpening = info.getObjectGateOpening();
                if (actualGateOpening > objectGateOpening) {
                    System.out.println("Row："+(i+1)+" GateOpening Warning");
                }
                double waterLevel = info.getActualWaterLevel();
                if (waterLevel > maxActualWater) {
                    maxActualWater = waterLevel;
                }
                double waterFlow = info.getWaterFlow();
                countWaterFlow += 2 * 60 * 60 * waterFlow;
            }
            //保留两位小数输出
            System.out.println("Max Actual Water Level：" + (new BigDecimal(maxActualWater).setScale(2, RoundingMode.UP)));
            System.out.println("Total Water Flow：" + (new BigDecimal(countWaterFlow).setScale(2, RoundingMode.UP)));

        }
    }

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }
}
