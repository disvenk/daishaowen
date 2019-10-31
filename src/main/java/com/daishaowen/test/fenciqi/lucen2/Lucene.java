package com.daishaowen.test.fenciqi.lucen2;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.apache.lucene.search.NumericRangeQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

/**
 * @author guzi
 * @version 1.1
 * @date 2016-9-20 上午8:49:23
 */
public class Lucene {
	String src="E:\\课程\\lucene&solr(3天)\\lucene&solr\\day01\\代码\\src";
	String dir= "E:\\课程\\lucene&solr(3天)\\lucene&solr\\day01\\代码\\dir";
	public static void main(String[] args) throws Exception {
		Lucene lu = new Lucene();
		lu.removeIndex();
		//lu.createIndex(lu.src);
		
		/*BooleanQuery bq = new BooleanQuery();
		bq.add(new TermQuery(new Term("con","信息")), Occur.SHOULD);
		bq.add(new TermQuery(new Term("name","信息")), Occur.SHOULD);
		lu.queryIndex(bq);*/
		
		MatchAllDocsQuery madq = new MatchAllDocsQuery();
		lu.queryIndex(madq);
		
		NumericRangeQuery<Long> nrq = NumericRangeQuery.newLongRange("size", 0L, 1000L, true, true);
		lu.queryIndex(nrq);
		
		/*QueryParser qp = new QueryParser("con",new IKAnalyzer());
		Query query = qp.parse("方便的在目标系统中实现");
		lu.queryIndex(query);*/
		
		String[] fields = {"con","name"};
		MultiFieldQueryParser mfqp = new MultiFieldQueryParser(fields, new IKAnalyzer());
		Query query = mfqp.parse("方便的在目标系统中实现");
		lu.queryIndex(query);
		
		/*TermQuery tq = new TermQuery(new Term("con",""));
		lu.removeIndex(tq);*/
	}
	public void queryIndex(Query query) throws Exception {
		System.out.println(query);
		FSDirectory open = FSDirectory.open(new File(dir));
		DirectoryReader dr = DirectoryReader.open(open);
		IndexSearcher is = new IndexSearcher(dr);
		TopDocs tds = is.search(query, 10);
		ScoreDoc[] sds = tds.scoreDocs;
		System.out.println("查询到结果："+tds.totalHits);
		for(ScoreDoc sd : sds){
			int i = sd.doc;
			Document doc = is.doc(i);
			System.out.println(doc.getField("name"));
			System.out.println(doc.getField("size"));
			System.out.println(doc.getField("path"));
		}
		dr.close();
	}
	public void updateIndex(String key,String src) throws IOException {
		IndexWriter iw = getIndexWriter();
		Document doc = new Document();
		File f = new File(src);
		doc.add(new TextField("con",FileUtils.readFileToString(f),Store.YES));
		iw.updateDocument(new Term("con",key), doc);
		iw.close();
	}
	/**
	 * 删除全部索引
	 * @throws Exception
	 */
	@Test
	public void removeIndex() throws Exception {
		IndexWriter iw = getIndexWriter();
		iw.deleteAll();
		iw.close();
	}
	/**
	 * 删除指定的索引
	 * @param key
	 * @throws Exception
	 */
	public void removeIndex(Query query) throws Exception{
		IndexWriter iw = getIndexWriter();
		iw.deleteDocuments(query);
		iw.close();
	}
	/**
	 * 生成或添加索引
	 * 1.需要索引库位置
	 * 2.需要原始文档
	 * @param src 原始文档
	 * @throws IOException 
	 */
	public void createIndex(String src) throws IOException {
		IndexWriter iw = getIndexWriter();
		//原始文件
		fileToIndex(src, iw);
		//关流
		iw.close();
		System.out.println("================分词完成================");
	}
	/**
	 * 得到索引库的流
	 * @return
	 * @throws IOException
	 */
	public IndexWriter getIndexWriter() throws IOException {
		//索引库位置
		FSDirectory open = FSDirectory.open(new File(dir));
		//接入库位置的索引流
		IndexWriter iw = new IndexWriter(open, new IndexWriterConfig(Version.LATEST, new IKAnalyzer()));
		return iw;
	}
	/**
	 * 原始文件写入iw流
	 * @param src
	 * @param iw
	 * @throws IOException
	 */
	public void fileToIndex(String src, IndexWriter iw) throws IOException {
		File file = new File(src);
		File[] files = file.listFiles();
		if(file.isFile()){
			files = new File[]{file};
		}
		for(File f : files){
			if(f.isDirectory()){
				fileToIndex(src, iw);
				break;
			}
			Document doc = new Document();
			doc.add(new TextField("con",FileUtils.readFileToString(f),Store.YES));
			doc.add(new TextField("name",f.getName(),Store.YES));
			doc.add(new StoredField("path",f.getPath()));
			doc.add(new LongField("size",FileUtils.sizeOf(f),Store.YES));
			iw.addDocument(doc);
		}
	}
}
