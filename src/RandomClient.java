import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;

public class RandomClient {
	public ArrayList<Integer> tmpArrayListInt;
	public ArrayList<String> tmpArrayListString;
	public String [] tmpArrayString;
	public String url;
	/**
	 * httpGet() returns the contents of a get line by line in an ArrayList	
	 * @param url String of url to get from
	 * @return String ArrayList
	 * @throws Exception
	 */
	public ArrayList<String> httpGet(String url) throws Exception{
		tmpArrayListString = new ArrayList<String>();
		
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(30 * 1000).build();
		HttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
		HttpGet httpget = new HttpGet(url);
		ResponseHandler<String> responseHandler = new BasicResponseHandler();		
		String responseBody = httpClient.execute(httpget, responseHandler);
		httpClient.getConnectionManager().shutdown();
		
		tmpArrayString = responseBody.split("\n");
		int arrayCount = tmpArrayString.length;

		for (int i = 0;i < arrayCount;i++){
			tmpArrayListString.add(tmpArrayString[i]);
		}
		return tmpArrayListString;
	}
	
	 /* randomNumberBaseTenInt() return an integer ArrayList
	 * @param amountOfNumbersToReturn Integer of how many numbers you want generated
	 * @param minNumber Integer of the minimum number to start with
	 * @param maxNumber Integer of the maximum number to start with
	 * @return Return an Integer ArrayList
	 * @throws Exception
	 */
	public ArrayList<Integer> randomNumberBaseTenInt(int amountOfNumbersToReturn,int minNumber, int maxNumber) throws Exception{
		
		tmpArrayListInt = new ArrayList<Integer>();

		String url = "http://www.random.org/integers/?num="+Integer.toString(amountOfNumbersToReturn)+"&min="+Integer.toString(minNumber)+"&max="+Integer.toString(maxNumber)+"&col=1&base=10&format=plain&rnd=new";

		tmpArrayListString = this.httpGet(url);
		
		int arrayCount = tmpArrayListString.size();
		
		for (int i = 0;i < arrayCount;i++){
			tmpArrayListInt.add(Integer.parseInt(tmpArrayListString.get(i)));
		}
		return tmpArrayListInt;
		
	}

	public void displayImage() throws IOException{
		BufferedImage img=ImageIO.read(new File("RGB.bmp"));
        ImageIcon icon=new ImageIcon(img);
        JFrame frame=new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setSize(200,300);
        JLabel lbl=new JLabel();
        lbl.setIcon(icon);
        frame.add(lbl);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RandomClient obj = new RandomClient();
		try {
			//System.out.println(obj.randomNumberBaseTenInt(1630, 0, 1000));
			ImageGenerator img = new ImageGenerator();
			obj.randomNumberBaseTenInt(10000, 1, 1000);
			obj.randomNumberBaseTenInt(6385, 1, 1000);
			img.imageGenerator(obj.tmpArrayListInt);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error while retriving random numbers"+e.toString());
		}		
				
	}

}
