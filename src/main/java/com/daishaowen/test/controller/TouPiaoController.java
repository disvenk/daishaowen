//package com.daishaowen.test.controller;
//
//import com.daishaowen.test.canjiacore.entity.Result;
//import com.daishaowen.test.dianzan.RedisArticleService;
//import org.apache.xmlbeans.soap.SOAPArrayType;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Map;
//
//@RestController
//@RequestMapping("toupiao")
//public class TouPiaoController {
//
//    @Autowired
//    RedisArticleService redisArticleService;
//
//    @RequestMapping("toupiao1")
//    public String toupiao1(){
//        String userId = "001";
//        String title = "The road to west";
//        String content = "About body and mental health";
//        String link = "www.baidu.com";
//        //发布文章，返回文章id
//        String articleId = redisArticleService.postArticle(title,content,link,userId);
//        System.out.println("刚发布了一篇文章，文章ID为："+articleId);
//        System.out.println("文章所有属性值内容如下：");
//        Map<String,String> articleData = redisArticleService.hgetAll("article:"+articleId);
//        for(Map.Entry<String,String> entry : articleData.entrySet()){
//            System.out.println(entry.getKey()+":"+entry.getValue());
//        }
//        return "fabusuccess";
//    }
//
//    @RequestMapping("toupiao2")
//    public Result toupiao2(String userId,String articleId){
//       return redisArticleService.articleVote(userId,"article:"+articleId);
//    }
//}
