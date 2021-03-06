package com.daishaowen.test.femciqi;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class IKAnalyzerTest implements WordSegmenter {
    @Override
    public Map<String, String> segMore(String text) {
        Map<String, String> map = new HashMap<>();

        map.put("智能切分", segText(text, true));
        map.put("细粒度切分", segText(text, false));

        return map;
    }
    private String segText(String text, boolean useSmart) {
        StringBuilder result = new StringBuilder();
        IKSegmenter ik = new IKSegmenter(new StringReader(text), useSmart);
        try {
            Lexeme word = null;
            while((word=ik.next())!=null) {
                result.append(word.getLexemeText()).append(" ");
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return result.toString();
    }
}
