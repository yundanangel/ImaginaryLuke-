

public class Generator {
	public int Generator(int n){
		double flag0=Math.random()*n;
		for (int i=0;i<=n-1;i++){
			if(flag0<=i+1){
				return i;
			}
		}
		return 0;
	}
}
