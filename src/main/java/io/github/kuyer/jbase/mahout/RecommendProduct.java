package io.github.kuyer.jbase.mahout;

import java.io.File;
import java.util.List;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

public class RecommendProduct {

	public static void main(String[] args) throws Exception {
		String filePath = "D:\\Work\\project\\kuyer-jbase\\src\\main\\resources\\mahout\\recommend-product.csv";
		DataModel model = new FileDataModel(new File(filePath));
		UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
		UserNeighborhood neighborhood = new NearestNUserNeighborhood(2, similarity, model);
		Recommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
		List<RecommendedItem> ritems = recommender.recommend(1, 2);
		System.out.println("recommend items size: "+ritems.size());
		for(RecommendedItem ritem : ritems) {
			System.out.println(ritem);
		}
	}

}
