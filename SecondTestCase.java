package First;

import java.util.ArrayList;
import java.util.List;

import First.Polynomial.Point;

public class SecondTestCase {
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
             "n": 10,
             "k": 7
         },
         "1": {
             "base": "6",
             "value": "13444211440455345511"
         },
         "2": {
             "base": "15",
             "value": "aed7015a346d63"
         },
         "3": {
             "base": "15",
             "value": "6aeeb69631c227c"
         },
         "4": {
             "base": "16",
             "value": "e1b5e05623d881f"
         },
         "5": {
             "base": "8",
             "value": "316034514573652620673"
         },
         "6": {
             "base": "3",
             "value": "2122212201122002221120200210011020220200"
         },
         "7": {
             "base": "3",
             "value": "20120221122211000100210021102001201112121"
         },
         "8": {
             "base": "6",
             "value": "20220554335330240002224253"
         },
         "9": {
             "base": "12",
             "value": "45153788322a1255483"
         },
         "10": {
             "base": "7",
             "value": "1101613130313526312514143"
         }
     }
     """;
		        ObjectMapper mapper = new ObjectMapper();
		        List<Point> points = new ArrayList<>();
		        int k=0;
//	 String keys[]= {"1","2","3","4"};
	 
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

private static Long lagrangeInterpolation(List<int[]> points, int x) {
	// TODO Auto-generated method stub
	Long res=0;
	for(int i=0;i<points.size();i++) {
		int xi=points.get(i)[0];
		int yi=points.get(i)[1];
		Long term=yi;
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
//output expected constant:79836264046592
