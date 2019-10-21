package com.daishaowen.test.femciqi.lucen2;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class LuceneFirst {

	@Test
	public void createIndex() throws Exception {
		//1、指定索引库存放的位置，可以是内存也可以是磁盘
		//索引库保存到内存中，一般不用
		//Directory directory = new RAMDirectory();
		//保存到磁盘上
		Directory directory = FSDirectory.open(new File("C:\\temp\\index"));
		//2、创建一个IndexWriter对象。需要一个分析器对象。
//		Analyzer analyzer = new StandardAnalyzer();
		Analyzer analyzer = new IKAnalyzer();
		//参数1：lucene的版本号，第二个参数：分析器对象
		IndexWriterConfig conf = new IndexWriterConfig(Version.LATEST, analyzer);
		//参数1：索引库存放的路径 参数2：配置信息，其中包含分析器对象
		IndexWriter indexWriter = new IndexWriter(directory, conf);
		//3、获得原始文档，使用io流读取文本文件
		File docPath = new File("C:\\传智播客\\04.lucene&solr\\searchsource");
		for (File f : docPath.listFiles()) {
			//取文件名
			String fileName = f.getName();
			//取文件路径
			String filePath = f.getPath();
			//文件的内容
			String fileContent = FileUtils.readFileToString(f);
			//文件的大小
			long fileSize = FileUtils.sizeOf(f);
			//4、创建文档对象
			Document document = new Document();
			//创建域
			//参数1：域的名称 参数2：域的内容 参数3：是否存储
			TextField fileNameField = new TextField("name", fileName, Store.YES);
			StoredField filePathField = new StoredField("path", filePath);
			TextField fileContentField = new TextField("content", fileContent, Store.NO);
			LongField fileSizeField = new LongField("size", fileSize, Store.YES);
			//5、向文档中添加域
			document.add(fileNameField);
			document.add(filePathField);
			document.add(fileContentField);
			document.add(fileSizeField);
			//6、把文档对象写入索引库
			indexWriter.addDocument(document);
		}
		//7、关闭IndexWriter对象
		indexWriter.close();
	}
	
	@Test
	public void searchIndex() throws Exception {
		//1指定索引库存放的位置
		Directory directory = FSDirectory.open(new File("C:\\temp\\index"));
		//2使用IndexReader对象打开索引库
		IndexReader indexReader = DirectoryReader.open(directory);
		//3创建一个IndexSearcher对象，构造方法需要一个indexReader对象
		IndexSearcher indexSearcher = new IndexSearcher(indexReader);
		//4创建一个查询对象,需要指定查询域及要查询的关键字。
		//term的参数1：要搜索的域 参数2：搜索的关键字
		Query query = new TermQuery(new Term("name", "spring"));
		System.out.println(query);
		//参数1：查询条件 参数2：查询结果返回的最大值
		//5取查询结果
		TopDocs topDocs = indexSearcher.search(query, 10);
		//取查询结果总记录数
		System.out.println("查询结果总记录数："  + topDocs.totalHits);
		//6遍历查询结果并打印.
		for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
			//取文档id
			int id = scoreDoc.doc;
			//从索引库中取文档对象
			Document document = indexSearcher.doc(id);
			//取属性
			System.out.println(document.get("name"));
			System.out.println(document.get("size"));
			System.out.println(document.get("content"));
			System.out.println(document.get("path"));
		}
		//7关闭IndexReader对象
		indexReader.close();
	}
	
	//查看分析器的分词效果
	@Test
	public void testAnanlyzer() throws Exception {
		//创建一个分析器对象
		//Analyzer analyzer = new StandardAnalyzer();
		//Analyzer analyzer = new CJKAnalyzer();
//		Analyzer analyzer = new SmartChineseAnalyzer();
		Analyzer analyzer = new IKAnalyzer();
		//从分析器对象中获得tokenStream对象
		//参数1：域的名称，可以为null或者""
		//参数2：要分析的文本内容
		TokenStream tokenStream = analyzer.tokenStream("", "数据库中存储的数据是高富帅结构化数据传智播客，即行数据java，可以用二维表结构来逻辑表达实现的数据。");
		//设置一个引用，引用可以有多重类型，可以时候关键词的引用、偏移量的引用
		CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
		//偏移量
		OffsetAttribute offsetAttribute = tokenStream.addAttribute(OffsetAttribute.class);
		//调用tokenStream的reset方法
		tokenStream.reset();
		//使用while循环变量单词列表
		while (tokenStream.incrementToken()) {
			System.out.println("start->" + offsetAttribute.startOffset());
			//打印单词
			System.out.println(charTermAttribute);
			System.out.println("end->" + offsetAttribute.endOffset());
		}
		//关闭tokenStream
		tokenStream.close();
	}
}
