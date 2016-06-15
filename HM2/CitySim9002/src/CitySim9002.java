

public class CitySim9002 {
	
	public static void main(String[] args) {
		if(args.length>1||args.length<=0){
			System.out.println("Please enter one integer argument, seed");
			System.exit(0);
		}
		else if(!ArgumentCheck(args[0])){
			System.out.println("Please enter one integer argument, seed");
			System.exit(0);
		}
		System.out.println("Welcome to CitySim!  Your seed is "+ args[0]+".");
		//Generate visitors
		Visitor[] visitors=new Visitor[5];
		//each visitor visits in order
		for(int i=0;i<=4;i++){
			visitors[i]=new Visitor();
			visitors[i].Visit(i);
			if(i<4)System.out.println("***");
		}
		
	}
	//learned from internet:http://stackoverflow.com/questions/5439529/determine-if-a-string-is-an-integer-in-java
	public static boolean ArgumentCheck(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    return true;
	}
	
}
