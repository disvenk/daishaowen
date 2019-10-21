package com.daishaowen.test.femciqi.lucen1;

import java.io.File;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.NumericRangeQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class Test {
	String dir = "E:\\课程\\lucene&solr(3天)\\lucene&solr\\day01\\代码\\dir";
	public static void main(String[] args) throws Exception {
		new Test().queryIndex();
	}
	public void queryIndex() throws Exception {
		FSDirectory open = FSDirectory.open(new File(dir));
		DirectoryReader dr = DirectoryReader.open(open);
		IndexSearcher is = new IndexSearcher(dr);
		// TermQuery tq = new TermQuery(new Term("title",key));
		//MatchAllDocsQuery query = new MatchAllDocsQuery();
		 Query query = NumericRangeQuery.newLongRange("field", 0L, 100L, true,true);
		System.out.println(query.toString());
		TopDocs tds = is.search(query, 10);
		//System.out.println(tds.totalHits);
		ScoreDoc[] sds = tds.scoreDocs;
		for (ScoreDoc sd : sds) {
			int i = sd.doc;
			System.out.println(i);
			/*Document doc = is.doc(i);
			System.out.println(doc.get("title"));*/
		}
		dr.close();

	}

	public void createIndex() throws Exception {
		FSDirectory open = FSDirectory.open(new File(dir));
		IndexWriter iw = new IndexWriter(open, new IndexWriterConfig(Version.LATEST, new IKAnalyzer()));
		Document doc = new Document();
		doc.add(new TextField("title", "xxxxxxx", Store.YES));
		iw.addDocument(doc);
		iw.close();

	}
}
