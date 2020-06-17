package day200411;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckData {

    /**
     * 当前一条水文数据
     */
    private String data;

    /**
     * 当前行数
     */
    private int row;

    //当前校验第几列
    private int column = 0;

    //字段校验错误信息
    StringBuilder sb=new StringBuilder();

    public CheckData() {

    }

    public CheckData(String data, int row) {
        this.data = data;
        this.row = row;
    }

    /**
     * 数据合法校验
     */
    public boolean validateData() {
        //参数校验
        if (null == data || data.length() == 0 || row <= 0) {
            System.out.println("Wrong params");
            return false;
        }
        //每行数据按\t拆分
        String[] split = data.split("\\t");
        //如果数组长度不为5，打印错误信息
        if (split.length != 5) {
            System.out.println("Wrong Format");
            System.out.println("Data: " + data);
            return false;
        }
        //对每列数据校验,如果调整校验顺序，则column也要调整
        boolean validateMeasureDateTime = validateMeasureDateTime(split[0]);//时间校验
        boolean validateWaterLevel = validateWaterLevel(split[1]);//目标水位校验
        boolean validateWaterLevel1 = validateWaterLevel(split[2]);//实际水位校验
        boolean validateGateOpening = validateGateOpening(split[3]);//闸门开度校验
        boolean validateWaterLevel2 = validateWaterLevel(split[4]);//流量校验
        if (validateMeasureDateTime && validateWaterLevel && validateWaterLevel1 && validateGateOpening && validateWaterLevel2) {
            //所有数据都校验成功，返回true
            return true;
        }
        sb.append("\r\n").append("Data：").append(data);
        System.out.println(sb.toString());
        return false;

    }

    /**
     * 采集时间合法性校验
     */
    public boolean validateMeasureDateTime(String measureDateTime) {
        column++;
        Pattern pattern = Pattern.compile("([0-9]{4}|[1-9][0-9]{0,2})/([1-9]|1[0-2])/([1-9]|[1-2][0-9]|3[0-1])\\s(1[0-9]|2[0-3]|[0-9])\\:00");
        Matcher matcher = pattern.matcher(measureDateTime);
        if (!matcher.matches()) {
            print();
            return false;
        }
        //重置正则匹配，不然在matches后调用find返回false
        matcher.reset();
        if (matcher.find()) {
            //获取第4个分组，代表小时
            String group = matcher.group(4);
            Integer hour = Integer.valueOf(group);
            //小时不是偶数
            if (hour % 2 != 0) {
                print();
                return false;
            }
        }
        return true;
    }

    private void print() {
        if(sb.length()>0){
            sb.append("\r\n");
        }
        sb.append("Row：").append(row).append("，column：").append(column).append("Wrong Format");
    }

    /**
     * 水位、流量合法性校验
     */
    public boolean validateWaterLevel(String waterLevel) {
        column++;
        Pattern pattern = Pattern.compile("[1-9][0-9]{0,3}(\\.[0-9]{1,3})?");
        Matcher matcher = pattern.matcher(waterLevel);
        if (!matcher.matches()) {
            print();
            return false;
        }
        return true;
    }

    /**
     * 闸门开度合法性校验
     */
    public boolean validateGateOpening(String gateOpening) {
        String[] split = gateOpening.split("/");
        if (split.length != 2) {
            column++;
            print();
            column++;
            return false;
        }
        int errorNum=0;
        for (String s : split) {
            Pattern pattern = Pattern.compile("[1-9]\\.[0-9]{2}");
            Matcher matcher = pattern.matcher(s);
            column++;
            if (!matcher.matches()) {
                print();
                errorNum++;
            }
        }
        if(errorNum>0){
            return false;
        }
        return true;
    }

    /**
     * 将该条信息转换为对象
     */
    public HydrologicalInfo toHydrologicalInfo() {
        String[] split = data.split("\\t");
        HydrologicalInfo info = new HydrologicalInfo();
        info.setMeasureDateTime(parseTime(split[0]));
        info.setObjectWaterLevel(Double.parseDouble(split[1]));
        info.setActualWaterLevel(Double.parseDouble(split[2]));
        String str = split[3];
        String[] gateOpening = str.split("/");
        info.setObjectGateOpening(Double.parseDouble(gateOpening[0]));
        info.setActualGateOpening(Double.parseDouble(gateOpening[1]));
        info.setWaterFlow(Double.parseDouble(split[4]));
        return info;
    }

    /**
     * 时间转换
     */
    private LocalDateTime parseTime(String str) {
        Pattern pattern = Pattern.compile("([0-9]{4}|[1-9][0-9]{0,2})/([1-9]|1[0-2])/([1-9]|[1-2][0-9]|3[0-1])\\s(1[0-9]|2[0-3]|[0-9])\\:00");
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            Integer year = Integer.valueOf(matcher.group(1));
            Integer month = Integer.valueOf(matcher.group(2));
            Integer date = Integer.valueOf(matcher.group(3));
            Integer hour = Integer.valueOf(matcher.group(4));
            return LocalDateTime.of(year, month, date, hour, 0);
        }
        return null;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }
}
