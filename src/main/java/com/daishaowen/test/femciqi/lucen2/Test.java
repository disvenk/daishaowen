package com.daishaowen.test.femciqi.lucen2;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.apache.lucene.search.NumericRangeQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class Test {
//	能够复述lucene的实现流程	
//	搭建lucene的开发环境	
//	实现索引库的创建和查询，实现中文分词	
	public static void main(String[] args) throws Exception {
		new Test().queryIndex();
	}
	public void queryIndex() throws Exception {
		FSDirectory open = FSDirectory.open(new File("E:\\课程\\lucene&solr(3天)\\lucene&solr\\day01\\代码\\dir"));
		DirectoryReader dr = DirectoryReader.open(open);
		IndexSearcher is = new IndexSearcher(dr);
		
		Query query = getQuery();
		TopDocs tds = is.search(query, 10);
		System.out.println("共记录数："+tds.totalHits);
		ScoreDoc[] sds = tds.scoreDocs;
		for(ScoreDoc sd : sds){
			int id = sd.doc;
			System.out.println(id);
			Document doc = is.doc(id);
			System.out.println(doc.get("域名"));
		}
		dr.close();

	}
	
	private Query getQuery() throws Exception {
		TermQuery query = new TermQuery(new Term("域名", "key"));
		System.out.println(query);//域名:key
		MatchAllDocsQuery query2 = new MatchAllDocsQuery();
		System.out.println(query2);//*:*
		NumericRangeQuery<Integer> query3 = NumericRangeQuery.newIntRange("域名", 1, 12, true, true);
		System.out.println(query3);//域名:[1 TO 12]
		BooleanQuery query4 = new BooleanQuery();
		query4.add(query, Occur.MUST);
		query4.add(query2, Occur.MUST);
		System.out.println(query4);//+域名:key +域名:[1 TO 12]
		
		QueryParser qp = new QueryParser("域名",new IKAnalyzer());
		Query parse = qp.parse("我们要查询的语句");
		System.out.println(parse);
		/*
		域名:key
		*:*
		域名:[1 TO 12]
		+域名:key +*:*
		我们 我 们 要 查询 语句
		*/
		return query;
	}
	public void createIndex() throws Exception {
		FSDirectory open = FSDirectory.open(new File(""));
		IndexWriter iw = new IndexWriter(open, new IndexWriterConfig(Version.LATEST, new IKAnalyzer()));
		Document doc = new Document();
		doc.add(new TextField("域名", "域的值",Store.YES));
		doc.add(new TextField("域名", "域的值",Store.YES));
		doc.add(new TextField("域名", "域的值",Store.YES));
		iw.addDocument(doc);
		iw.close();

	}
//	完成索引库文档的添加、删除以及更新	
//	掌握Query子类查询使用以及QueryParser查询使用
}
