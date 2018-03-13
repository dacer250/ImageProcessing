package com.b505.tools;

import java.io.File;
import java.io.IOException;

import javax.validation.constraints.Null.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.springframework.stereotype.Component;

@Component
public class UserCF {
	final static int NEIGHBORHOOD_NUM = 2;
    final static int RECOMMENDER_NUM = 3;

    
    public static void CAT() throws TasteException, IOException {
    	System.out.println("推荐系统");
    	 File file=new File("D:/workspace333/.metadata/.plugins/org.eclipse.wst.server.core/tmp2/wtpwebapps/SpringMvc/images/ds.csv");
    	 
    	 if(file.exists()){
    		 System.out.println("文件存在");
    	 }
    	 
    	 System.out.println("step1");
         DataModel model = new FileDataModel(file);
         System.out.println("step2");
         UserSimilarity user = new EuclideanDistanceSimilarity(model);
         System.out.println("step3");
         NearestNUserNeighborhood neighbor = new NearestNUserNeighborhood(NEIGHBORHOOD_NUM, user, model);
         System.out.println("step4");
         Recommender r = new GenericUserBasedRecommender(model, neighbor, user);
         System.out.println("step5");
         LongPrimitiveIterator iter = model.getUserIDs();

         while (iter.hasNext()) {
             long uid = iter.nextLong();
             java.util.List<RecommendedItem> list = r.recommend(uid, RECOMMENDER_NUM);
             System.out.printf("uid:%s", uid);
             for (RecommendedItem ritem : list) {
                 System.out.printf("(%s,%f)", ritem.getItemID(), ritem.getValue());
             }
             System.out.println("dsdsds");
         }
    	
    	
    	
	}
    
    
}
