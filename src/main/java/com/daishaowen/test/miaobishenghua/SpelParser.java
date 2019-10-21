package com.daishaowen.test.miaobishenghua;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/*
* @sign 长沙亿软软件架构部
* create by disvenk.dai
* */
public class SpelParser {
    private static ExpressionParser parser = new SpelExpressionParser();

    /*
    * @param key el表达式字符串，占位符以#开头
    * @param paramNames 形参名称，可以理解为占位符名称
    * @param args 参数值，可以理解为占位符的真实值
    * @return 返回el表达式经过参数替换过的字符串值
    * */
    public static String getKey(String key,String[] paramsNames,Object[] args){
        Expression exp = parser.parseExpression(key);//将可以字符串解析为el表达式
        EvaluationContext context = new StandardEvaluationContext();//初始化赋值上下文
        if(args.length<=0){
            return null;
        }

        //将形参和形参值以配对的方式配置到复制上下文中
        for(int i=0;i<args.length;i++){
            context.setVariable(paramsNames[i],args[i]);
        }

        //根据赋值上下文运算el表达式
        return exp.getValue(context,String.class);
    }

    public static void main(String[] args) {
        String key = "#user.Id+'||'+#userCode";//el表达式字符串
        Tuser user = new Tuser();
        user.setId("disvenk");
        String userCode = "大蚊子";
        //paramsName赋值
        String[] paramsName = new String[]{"user","userCode"};
        //args赋值
        Object[] _args = new Object[2];
        _args[0]=user;
        _args[1]=userCode;
        System.out.println(SpelParser.getKey(key,paramsName,_args));

    }
}
