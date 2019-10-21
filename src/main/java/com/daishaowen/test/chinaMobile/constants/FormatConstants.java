/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT China Mobile (SuZhou) Software Technology Co.,Ltd. 2016
 *
 * The copyright to the computer program(s) herein is the property of
 * CMSS Co.,Ltd. The programs may be used and/or copied only with written
 * permission from CMSS Co.,Ltd. or in accordance with the terms and conditions
 * stipulated in the agreement/contract under which the program(s) have been
 * supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package com.daishaowen.test.chinaMobile.constants;


public final class FormatConstants extends BasePatterns {

    /**
     * 验证能力url 大小写字母， _ - . ~
     */
    public final static String ABILITY_URL = "^[a-zA-Z0-9]{1}([a-zA-Z0-9.\\-_~]{0,99})$"; //$NON-NLS-1$

    public final static String CALLBACL_URL = "^((http|ftp|https):\\/\\/)(([a-zA-Z0-9\\._-]+\\.[a-zA-Z]{2,6})|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,5})*(\\/[a-zA-Z0-9\\&%_\\.\\/-~-;?@:=+$,!'\\(\\)<>#%']*)?$";

    /**
     * APP编号格式
     */
    public final static String APP_NO_FORMAT = "^A2[0-9]{18}$"; //$NON-NLS-1$

    /**
     * 防SQL注入
     */
    public final static String ANTI_SQL_INJECTION = "(\\s*'\\s*)|('\\s*--\\s)|('\\s*(and|or|xor|&&|\\|\\|)\\s*\\(?\\s*('|[0-9]|`?[a-z\\._-]+`?\\s*(=|like)|[a-z]+\\s*\\())|('\\s*(not\\s+)?in\\s*\\(\\s*['0-9])|(union(\\s+all)?(\\s*\\(\\s*|\\s+)select(`|\\s))|(select(\\s*`|\\s+)(\\*|[a-z0-9_\\, ]*)(`\\s*|\\s+)from(\\s*`|\\s+)[a-z0-9_\\.]*)|(insert\\s+into(\\s*`|\\s+).*(`\\s*|\\s+)(values\\s*)?\\(.*\\))|(update(\\s*`|\\s+)[a-z0-9_\\.]*(`\\s*|\\s+)set(\\s*`|\\s+).*=)|(delete\\s+from(\\s*`|\\s+)[a-z0-9_\\.]*`?)";
    
    /**
     * 长度为216
     */
    public final static String PUBLIC_KEY_LENGTH = "^.{216}$"; //$NON-NLS-1$

}
