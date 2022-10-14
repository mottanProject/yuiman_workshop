package com.workshop.yuiman.common;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;


public class LoadProperties {

    public Map<String, String> queryList = new HashMap<>(); //クエリ定義リスト

    public LoadProperties() {
        Properties properties = new Properties();
        String path = "/Users/naoki/IdeaProjects/yuiman_workshop/src/main/resources/queries.properties";

        try{
            //クエリ定義ファイルを読み込む
            InputStream inputFile = new FileInputStream(path);
            properties.load(inputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //クエリリストをマップに格納する
        for (Entry<Object, Object> e : properties.entrySet()) {
            queryList.put(e.getKey().toString(),e.getValue().toString());
        }

    }

    /**
     * <p><b>LoadPropertiesのgetQueryメソッド</b></p>
     * <p>クエリ定義名を基にSQLを返す</p>
     * @param key クエリ定義名
     * @return sql SQL
     * @version 1.0
     * */
    public String getQuery(String key){
        //keyを基にクエリを取得
        String sql = queryList.get(key);
        return sql;
    }
}
