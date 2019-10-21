package com.daishaowen.test.qingqiuhebing;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class CommodityService {

    class Request{
        String commodityCode;
        CompletableFuture<Map<String,Object>> future;
    }

    LinkedBlockingQueue<Request> queue = new LinkedBlockingQueue<>();

    @PostConstruct
    public void init(){
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(()->{
            //取出queue的请求生成批量查询
            int size=queue.size();
            if(size==0){
                return;
            }

            List<Request> requests= new ArrayList<>();
            for(int i=0;i<size;i++){
                Request request = queue.poll();
                requests.add(request);
            }
            System.out.println("批量处理数据:"+size);

            //组装批量查询
            List<String> commodityCodes=new ArrayList<>();
            for(Request request:requests){
                commodityCodes.add(request.commodityCode);
            }
//            List<Map<String,Object>> responses=queryCommodityMapper.query(commodityCodes);
            List<Map<String,Object>> responses=null;

            //将结果进行转换
            Map<String,Map<String,Object>> responseMap=new HashMap<>();
            for(Map<String,Object> response:responses){
                String code=response.get("code").toString();
                responseMap.put(code,response);
            }

            //返回响应结果
            for(Request request:requests){
                Map<String,Object> result=responseMap.get(request.commodityCode);
                request.future.complete(result);
            }
        },0,10,TimeUnit.MILLISECONDS);
    }

    public Map<String,Object> queryCommodity(String commodityCode) throws ExecutionException, InterruptedException {
        Request request = new Request();
        CompletableFuture<Map<String,Object>> future=new CompletableFuture<>();
        request.future=future;
        request.commodityCode=commodityCode;
        queue.add(request);
        return future.get();
    }
}
