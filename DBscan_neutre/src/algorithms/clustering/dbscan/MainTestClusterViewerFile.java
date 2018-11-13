package algorithms.clustering.dbscan;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import algorithms.clustering.clusterreader.AlgoClusterReader;
import outil.pot.clusterView.ClusterViewer;
import patterns.cluster.Cluster;


public class MainTestClusterViewerFile {
    
    public static void ClusterViewer(String input) throws UnsupportedEncodingException, IOException
    {
               // the input file
		
		// Applying the  algorithm
		AlgoClusterReader algorithm = new AlgoClusterReader();
		List<Cluster> clusters = algorithm.runAlgorithm(input);
		List<String> attributeNames = algorithm.getAttributeNames();
		algorithm.printStats();

		ClusterViewer viewer = new ClusterViewer(clusters, attributeNames);
		viewer.setVisible(true);
    
    
    }

	
	
	}
