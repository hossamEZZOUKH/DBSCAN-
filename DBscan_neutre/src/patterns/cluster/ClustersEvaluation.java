package patterns.cluster;

import java.util.List;

import algorithms.clustering.dbscan.AlgoDBSCAN;
import algorithms.clustering.distanceFunctions.DistanceFunction;


public class ClustersEvaluation {
	
	/**
	 * Calculate the sum of squared error metrics for evaluating a set of clusters.
	 * This method is optimized for clusters where the mean has been already calculated.
	 * @param clusters the list of clusters
	 * @param distanceFunction a distance function
	 * @return the sum of square errors (in general, lower is better)
	 */
	public static  double calculateSSE(List<ClusterWithMean> clusters, DistanceFunction distanceFunction) {
		double sse = 0;
		// for each cluster
		for(ClusterWithMean cluster : clusters) {
			// for each instance in that cluster
			for(DoubleArray vector : cluster.getVectors()) {
				sse += Math.pow(distanceFunction.calculateDistance(vector, cluster.getmean()), 2);
			}
		}
		return sse;
	}

	/**
	 * Calculate the sum of squared error metrics for evaluating a set of clusters.
	 * This method is for clusters where the mean has not been calculated previously.
	 * @param clusters the list of clusters
	 * @param distanceFunction a distance function
	 * @return the sum of square errors (in general, lower is better)
	 */ 
	public static double getSSE(List<Cluster> clusters, DistanceFunction distanceFunction) {
		double sse = 0;
		// for each cluster
		for(Cluster cluster : clusters) {
			// if the cluster is not empty
			if(cluster.getVectors().size() > 0) {
				// calculate the mean of the cluster
				DoubleArray mean = calculateClusterMeans(cluster);
				// for each instance in that cluster
				for(DoubleArray vector : cluster.getVectors()) {
					sse += Math.pow(distanceFunction.calculateDistance(vector, mean), 2);
				}
			}
		}
		return sse;
	}
	
	/**
	 * This method calculated the mean of a cluster
	 * @param cluster a non-empty cluster 
	 * @return a vector representing the mean
	 */
	public static DoubleArray calculateClusterMeans(Cluster cluster) {
		int dimensionCount = cluster.getVectors().get(0).data.length;
		double mean [] = new double[dimensionCount];
		// for each vector
		for(DoubleArray vector : cluster.getVectors()) {
			// for each dimension, we add the value
			for(int i=0; i < dimensionCount; i++){
				mean[i] += vector.data[i];
			}
		}
		// finally, fo each dimension, we divide by the number of vectors
		for(int i=0; i < dimensionCount; i++){
			mean[i] = mean[i]  / cluster.getVectors().size();
		}
		return new  DoubleArray(mean);
	}


}