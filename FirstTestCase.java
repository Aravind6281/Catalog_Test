8package First;

import java.util.ArrayList;
import java.util.List;

public class Polynomial {
	 static class Point {
	        int x;
	        int y;
	        Point(int x, int y) {
	            this.x = x;
	            this.y = y;
	        }
	    }

     public static void main(String[] args) {
    	 String jsonInput = """
    		        {
    		            "keys": {
    		                "n": 4,
    		                "k": 3
    		            },
    		            "1": {
    		                "base": "10",
    		                "value": "4"
    		            },
    		            "2": {
    		                "base": "2",
    		                "value": "111"
    		            },
    		            "3": {
    		                "base": "10",
    		                "value": "12"
    		            },
    		            "6": {
    		                "base": "4",
    		                "value": "213"
    		            }
    		        }
    		        """;
    		        ObjectMapper mapper = new ObjectMapper();
    		        List<Point> points = new ArrayList<>();
    		        int k=0;
//    	 String keys[]= {"1","2","3","4"};
    	 
    	 List<Point> points=new ArrayList<>();
    	 try {
             JsonNode root = mapper.readTree(jsonInput);
             k = root.path("keys").path("k").asInt();

             for (String key : List.of("1", "2", "3", "6")) {
                 if (root.has(key)) {
                     int base = root.path(key).path("base").asInt();
                     String value = root.path(key).path("value").asText();
                     int decimalValue = convertToDecimal(value, base);
                     points.add(new Point(Integer.parseInt(key), decimalValue));
                 }
             }
         } catch (IOException e) {
             e.printStackTrace();
             return;
         }
    	 if(points.size()<k) {
    		 System.out.println("Impossible");
    		 return;
    	 }
    	 double con=lagrangeInterpolation(points,0);
    	 System.out.println(con);
    	 
     }

	private static int convertToDecimal(String value, int base) {
		// TODO Auto-generated method stub
		return Integer.parseInt(value,base);
	}

	private static double lagrangeInterpolation(List<int[]> points, int x) {
		// TODO Auto-generated method stub
		double res=0.0;
		for(int i=0;i<points.size();i++) {
			int xi=points.get(i)[0];
			int yi=points.get(i)[1];
			double term=yi;
			 for (int j = 0; j < points.size(); j++) {
	                if (i != j) {
	                    int xj = points.get(j)[0];
	                    term *= (double)(x - xj) / (xi - xj);
	                }
	            }
			 res+=term;
			
		}
		return (int)Math.round(res);
	}
}


//output constant value:39

//Calculated output:-21.42
