import static org.junit.Assert.fail;

import org.junit.Test;
public class TestProperties {
	//generator of the original array
	public int[] Generate(){
		int length=(int)Math.floor(Math.random()*100)+1;
		int[] result=new int[length];
		for(int i=0;i<length;i++){
			result[i]=(int)Math.floor(Math.random()*100)+1;
		}
		return result;
	}
	//billify method
	public int[] billify(int[] a){
		int length=a.length;
		int[] b=new int[length+1];
		int result=0;
		for(int i=0;i<length;i++){
			b[i]=a[i]*a[i];
			result+=b[i];
		}
		b[length]=result;
		return b;
	}
	//Test if the returned array have one more elements than the original one
	@Test
	public void testLength() {
		for(int i=0;i<500;i++){
			int[] a=Generate();
			int[] b=billify(a);
			if(a.length!=b.length-1){
				fail();
			}
		}
	}
	//Assuming the origin array is consist of integers between 1 and 100,
	//The corresponded value in the returned array should be no smaller than the origin ones
	@Test
	public void testBigger() {
		for(int i=0;i<500;i++){
			int[] a=Generate();
			int[] b=billify(a);
			for(int j=0;j<a.length;j++){
				if(a[j]>b[j]){
					fail();
				}
			}
		}
	}
	//Assuming the origin array is consist of 1 to 100 integers between 1 and 100,
	//The corresponded sum of the value in the returned array should be no bigger than the square of the sum of values in original arrays
	@Test
	public void testSum() {
		for(int i=0;i<500;i++){
			int[] a=Generate();
			int[] b=billify(a);
			int sum=0;
			for(int j=0;j<a.length;j++){
				sum+=a[j];
			}
			sum=sum*sum;
			if(sum<b[a.length]){
				fail();
			}
		}
	}
	
}
