package algorithm;


import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;

import java.util.ArrayList;
import java.util.List;


public class PearsonCorrelation {
    private final static PearsonsCorrelation PC = new PearsonsCorrelation();

    private double getCorrelation(double[] userA, double[] userB){
        return PC.correlation(userA, userB);
    }

    public int getIndexMaxUser(double[] userRating, List<double[]> rating){
        double maxValue = 0;
        int userIndex = 0;
        for(int i=0; i<rating.size(); i++) {
            double correlation = getCorrelation(rating.get(i), userRating);
            if (correlation > maxValue) {
                maxValue = correlation;
                userIndex = i;
            }
        }
        return userIndex;
    }

    public Void getMaxUser(List<User> users, User user){
        List<double[]> ratings = new ArrayList<double[]>();
        /*
               for (User u : users) {

        }
        Map<String, String> m0 = new HashMap<String, String>();
m0.put("a", "a");
m0.put("b", "b");
Map<String, String> m1 = new HashMap<String, String>();
m1.put("c", "c");
m1.put("b", "b");
Set<String> s = new HashSet<String>(m0.keySet());
s.retainAll(m1.keySet());
System.out.println(s);
         */
        return null;
    }
}