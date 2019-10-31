package com.daishaowen.test.fenciqi.lucen1;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MultiPhraseQuery;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

/**
 * @author guzi
 * @version 1.1
 * @date 2016-9-19 下午5:17:25
 */
public class Lucene {
	String src="E:\\课程\\lucene&solr(3天)\\lucene&solr\\day01\\代码\\src";
	String dir="E:\\课程\\lucene&solr(3天)\\lucene&solr\\day01\\代码\\dir";
	public static void main(String[] args) throws Exception {
		Lucene lu = new Lucene();
		//lu.removeDocument("");
		//lu.createIndex(lu.src);
		lu.queryKey("spring");
	}
	public void updateDocument() throws IOException {
		FSDirectory open = FSDirectory.open(new File(dir));
		IKAnalyzer ik = new IKAnalyzer();
		IndexWriter iw = new IndexWriter(open, new IndexWriterConfig(Version.LATEST, ik));
		Document doc = new Document();
		doc.add(new TextField("","",Store.YES));
		iw.updateDocument(new Term("",""), doc);
		iw.close();

	}
	public void removeDocument(String key) throws IOException {
		FSDirectory open = FSDirectory.open(new File(dir));
		IKAnalyzer ik = new IKAnalyzer();
		IndexWriter iw = new IndexWriter(open, new IndexWriterConfig(Version.LATEST, ik));
		
		iw.deleteAll();
		//iw.deleteDocuments(new TermQuery(new Term("con",key)));
		
		iw.close();

	}
	public void queryKey(String key) throws Exception {
		FSDirectory open = FSDirectory.open(new File(dir));
		DirectoryReader dr = DirectoryReader.open(open);
		IndexSearcher is = new IndexSearcher(dr);
		IKAnalyzer ik = new IKAnalyzer();
		TokenStream ts = ik.tokenStream("", key);
		CharTermAttribute cta = ts.addAttribute(CharTermAttribute.class);
		ts.reset();
		MultiPhraseQuery mpq = new MultiPhraseQuery();
		while(ts.incrementToken()){
			mpq.add(new Term("con",cta.toString()));
		}
		TopDocs tds = is.search(mpq, 10);
		ScoreDoc[] sds = tds.scoreDocs;
		for(ScoreDoc sd : sds){
			int i = sd.doc;
			Document doc = is.doc(i);
			System.out.println(doc.getField("con"));
		}
		ts.close();
		ik.close();
		dr.close();

	}
	public void createIndex(String src) throws Exception {
		FSDirectory open = FSDirectory.open(new File(dir));
		IKAnalyzer ik = new IKAnalyzer();
		IndexWriter iw = new IndexWriter(open, new IndexWriterConfig(Version.LATEST, ik));
		File file = new File(src);
		File[] files = file.listFiles();
		if(file.isFile()){
			files = new File[]{file};
		}
		for(File f : files){
			Document doc = new Document();
			doc.add(new TextField("con",FileUtils.readFileToString(f),Store.NO));
			doc.add(new TextField("name",f.getName(),Store.YES));
			doc.add(new LongField("size",FileUtils.sizeOf(f),Store.YES));
			iw.addDocument(doc);
		}
		iw.close();
		System.out.println("===========分词加载完成=======================");
	}
}
