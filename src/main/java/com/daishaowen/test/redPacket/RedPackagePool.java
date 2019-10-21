package com.daishaowen.test.redPacket;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.DecimalFormat;

@Component
public class RedPackagePool {
//    //总金额(元)
//    static double total=100D;
//    //分发的个数
//    static int num=10;
//    //将总金额进行平分
//    static double averageMoney=(total/num);

    public BigDecimal total;
    public Integer num;
    //随机的添加/减去数组
    public BigDecimal[] arr={BigDecimal.valueOf(1),BigDecimal.valueOf(-1)};

    public RedPackagePool(){}

    public RedPackagePool(BigDecimal total, Integer num){
        this.total = total;
        this.num = num;
    }

    //是添加还是减去,该可能性要随机产生
    public BigDecimal getIntend(){
        int aa=(int) (Math.random()*arr.length);
        return arr[aa];
    }

    //生成num个红包的数组
    public BigDecimal[] getAveragePackage(BigDecimal total,Integer num){
        BigDecimal[] arr2=new BigDecimal[num];
        //将其装入arr2数组中,共装入num-1个.
        for(int i=0;i<num;i++){
            //arr2[i]=averageMoney;
            arr2[i]=total.divide(BigDecimal.valueOf(num),BigDecimal.ROUND_HALF_UP);
        }
        return arr2;
    }

    //数组间交换黑箱
    public BigDecimal[] exchangeXBox(BigDecimal total,BigDecimal[] arr2,Integer num){
        for(int i=0;i<num-1;i++){
            BigDecimal random=getXBox(total,num);
            arr2[i]=arr2[i].add(random).setScale(2,BigDecimal.ROUND_HALF_UP);
            arr2[i+1]=arr2[i+1].subtract(random).setScale(2,BigDecimal.ROUND_HALF_UP);
            //金额不能小于1分,否则无法进行分割操作
            while((arr2[i].compareTo(BigDecimal.valueOf(0.01))<0)||(arr2[i].compareTo(BigDecimal.valueOf(0.01))<0)){
                random=getXBox(total,num);
                arr2[i]=arr2[i].add(random).setScale(2,BigDecimal.ROUND_HALF_UP);
                arr2[i+1]=arr2[i+1].subtract(random).setScale(2,BigDecimal.ROUND_HALF_UP);
            }
        }
        return arr2;
    }

    //生成黑箱
    public BigDecimal getXBox(BigDecimal total,Integer num){
        //return getIntend()*averageMoney*Math.random();

        return getIntend().multiply(total.divide(BigDecimal.valueOf(num),BigDecimal.ROUND_HALF_UP))
                .multiply(new BigDecimal(Math.random()));
    }

    //生成红包
    public BigDecimal[] getStarted(BigDecimal total,Integer num) {
        //生成一个平均数组
        BigDecimal[] arr2=getAveragePackage(total,num);
        //数组间交换黑箱
        return exchangeXBox(total,arr2,num);
    }

    //打印出红包的金额
    public void printList(BigDecimal[] arr){
        for(int i=0;i<arr.length;i++){
            System.out.println("第"+(i+1)+"个红包,红包金额为:"+arr[i]);
        }
    }

    //打印红包总金额
    public void getSum(BigDecimal[] arr3) {
        BigDecimal temp= BigDecimal.ZERO;
        for(int i=0;i<arr3.length;i++){
            temp= temp.add(arr3[i]);
        }
        System.out.println("总和:"+temp.setScale(2,BigDecimal.ROUND_HALF_UP));
    }
}
