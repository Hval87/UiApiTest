package util;

import model.TestModel;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CollectionsUtil {
    public static boolean listTestModelComparator(List<TestModel> listA, List<TestModel> listB) {
        return listB.stream().anyMatch(item1 -> listA.stream().anyMatch(item2 -> item1.equals(item2)));
    }

    public static List<TestModel> getSortedListByDate(List<TestModel> list) {
        return list.stream().sorted(Comparator.comparing(TestModel::getStartTime).reversed()).collect(Collectors.toList());
    }

     public static Map<String,String> getParamsForAttachLog(String testId,String log){
         Map<String,String>params=new HashMap<>();
         params.put(Constants.TEST_ID,testId);
         params.put(Constants.CONTENT,log);
         return params;
     }

    public static Map<String,String> getParamsForTestCreating(String sid,String projectName,String methodName,String testName,String env){
        Map<String,String> params=new HashMap<>();
        params.put(Constants.SID,sid);
        params.put(Constants.PROJECT_NAME,projectName);
        params.put(Constants.METHOD_NAME,methodName);
        params.put(Constants.TEST_NAME,testName);
        params.put(Constants.ENVIRONMENT,TextUtil.getIP());
        return params;
    }
    public static Map<String,String> getParamsForAttachScreen(String testId,String content,String contentType){
        Map<String,String>params=new HashMap<>();
        params.put(Constants.TEST_ID,testId);
        params.put(Constants.CONTENT,content);
        params.put(Constants.CONTENT_TYPE,DataManager.getTestValue("contentImgType"));
        return params;
    }



}
