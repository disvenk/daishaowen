package com.daishaowen.test.NIOReadAndWrite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by disvenk.dai on 2018-10-29 15:14
 */
public class NIOSample {
    /**
     * NIO方式复制文件<br>
     * 目标文件所在的文件夹如果不存在自动创建文件夹
     * @param src 源文件
     * @param dst 目标文件
     * @throws IOException
     */
    public static final void nioCopyFile(File src, File dst) throws IOException {
        if(null==src||null==dst)
            throw new NullPointerException("src or dst is null");
        if(!src.exists()||!src.isFile())
            throw new IllegalArgumentException(String.format("INVALID FIILE NAME(无效文件名) src=%s",src.getCanonicalPath()));
        if (dst.exists() &&!dst.isFile()) {
            throw new IllegalArgumentException(String.format("INVALID FIILE NAME(无效文件名) dst=%s",dst.getCanonicalPath()));
        }
        File folder = dst.getParentFile();
        if (!folder.exists())
            folder.mkdirs();
        if(((src.length()+(1<<10)-1)>>10)>(folder.getFreeSpace()>>10))
            throw new IOException(String.format("DISK ALMOST FULL(磁盘空间不足) %s",folder.getCanonicalPath()));
        FileInputStream fin=null;
        FileOutputStream fout = null;
        FileChannel fic = null;
        FileChannel foc = null;
        try {
            fin=new FileInputStream(src);
            fout = new FileOutputStream(dst);
            // 从FileInputStream创建用于输入的FileChannel
            fic = fin.getChannel();
            // 从FileOutputStream 创建用于输出的FileChannel
            foc = fout.getChannel();
            // 16KB缓冲区
            ByteBuffer bb = ByteBuffer.allocate(1024<<4);
            // 根据 read返回实际读出的字节数 中止循环
            while(fic.read(bb)>0){
                // 缓冲区翻转用于输出到foc
                bb.flip();
                foc.write(bb);
                // 清空缓冲区用于下次读取
                bb.clear();
            }
        } finally {
            // 安全释放资源
            if(null!=fic)
                fic.close();
            if(null!=foc)
                foc.close();
            if(null!=fin)
                fin.close();
            if(null!=fout)
                fout.close();
        }
    }
}
