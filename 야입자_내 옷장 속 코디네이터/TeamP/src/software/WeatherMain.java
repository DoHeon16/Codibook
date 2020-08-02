package software;

//Reference :: http://blog.naver.com/PostView.nhn?blogId=tkddlf4209&logNo=220632424141&categoryNo=0&parentCategoryNo=48&viewDate=&currentPage=1&postListTopCurrentPage=1&from=postView
//[Java] ���û ���׿��� XML �Ľ�


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class WeatherMain {
	public static String user_loca;
	public static void main(String[] args) {
		List<Map<String,Object>> list = getXml("���� ������ ȭ�絿");
		String[] savetxt = new String[4];
	for(int i = 0 ; i < 4 ; i++) {
		String wfimage;
		//if(!(weatherfc.get(i).get("wfEn")=="Snow/Rain")) {
		Map<String,Object> map = new HashMap<String,Object>();
		map = list.get(i);
		String temp1 = (String) map.get("wfEn");
		wfimage = "wf/"+temp1+".jpg";
		savetxt[i] ="wf/"+temp1+".jpg"+"#"+map.get("hour")+"#"+map.get("temp");
	}
	try {
        File forwrite = new File("Weather.txt"); //�ؽ�Ʈ���ϸ��� ������ ���Ƿ� ������ �� �ְ� ���߿� ����
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(forwrite));
        if(forwrite.isFile() && forwrite.canWrite()) {
           for(int i = 0; i< 4;i++) {
              bufferedWriter.write(savetxt[i]);
              bufferedWriter.newLine();
           }
           bufferedWriter.close();
        }
     } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
     }
	}
	
	
	public static List<Map<String, Object>> getXml(String locate) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		String location=locate;
		String json="";
		StringBuilder sb = new StringBuilder();
		double v1 = 0.0;
		double v2 = 0.0;
		try {
			
			String addr = "http://maps.google.com/maps/api/geocode/json?address=";
			URL url = new URL(addr + URLEncoder.encode(location, "UTF-8"));
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setConnectTimeout(10000);
			con.setUseCaches(false);
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

			while (true) {
				String line = br.readLine();
				if (line == null)
					break;
				sb.append(line);
			}
			br.close();
			con.disconnect();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		json = sb.toString();
		JSONObject object = (JSONObject) JSONValue.parse(json);
		JSONArray array = (JSONArray) object.get("results");
		for (Object o : array) {
			JSONObject object2 = (JSONObject) o;
			String lat = ((JSONObject) (((JSONObject) object2.get("geometry")).get("location"))).get("lat").toString();
			String lng = ((JSONObject) (((JSONObject) object2.get("geometry")).get("location"))).get("lng").toString();

			v1 = Double.parseDouble(lat);
			v2 = Double.parseDouble(lng);
		}
		
		//������ ���� ���� �浵�� ���� GridX, GridY��ǥ�� ���Ѵ�. 
		Map<String, Object> map = getGridxy(v1, v2);
		
		String xml = "";
		//GridX, GridY ��ǥ�� ������ XML�����͸� �����´�.
		try {
			// ���� ���� ����
			String addr = "http://www.kma.go.kr/wid/queryDFS.jsp?gridx=" + map.get("x") + "&gridy=" + map.get("y");
			URL url = new URL(addr);
			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			http.setConnectTimeout(10000);
			http.setUseCaches(false);

			BufferedReader br = new BufferedReader(new InputStreamReader(http.getInputStream()));
			sb = new StringBuilder();
			while (true) {
				String line = br.readLine();
				if (line == null)
					break;
				sb.append(line);
			}
			xml = sb.toString();
			br.close();
			http.disconnect();

		} catch (Exception e) {
			System.out.println("�ٿ�ε� ����" + e.getMessage());

		}

		Map<String, Object> data = new HashMap<String, Object>();
		//�����ؿ� XML �����͸� �Ľ��Ѵ�.
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentbuilder = factory.newDocumentBuilder();
			InputStream is = new ByteArrayInputStream(xml.getBytes());
			Document doc = documentbuilder.parse(is);
			Element element = doc.getDocumentElement();

			NodeList list1 = element.getElementsByTagName("data");

			for (int i = 0; i < list1.getLength(); i++) {
				for (Node node = list1.item(i).getFirstChild(); node != null; node = node.getNextSibling()) {

					if (node.getNodeName().equals("hour")) { //�ð�
						data = new HashMap<String, Object>();
						data.put("hour", node.getTextContent().toString());
					}

					if (node.getNodeName().equals("temp")) { //�µ�
						data.put("temp", node.getTextContent().toString());
					}

					//if (node.getNodeName().equals("reh")) { //����
					//	data.put("reh", node.getTextContent().toString());
					//}

					if (node.getNodeName().equals("wfEn")) { //���� ����
						data.put("wfEn", node.getTextContent().toString());
						list.add(data);
					}
				}
			}

		} catch (Exception e) {
			System.out.println("�Ľ̿���" + e.getMessage());
		}

		return list;
	}



	public static Map<String, Object> getGridxy(double v1, double v2) {
		double RE = 6371.00877; // ������ �ݰ�(km)
		double GRID = 5.0; // ���� ����(km)
		double SLAT1 = 30.0; // ���� ����1(degree)
		double SLAT2 = 60.0; // ���� ����2(degree)
		double OLON = 126.0; // ������ �浵(degree)
		double OLAT = 38.0; // ������ ����(degree)
		double XO = 43; // ������ X��ǥ(GRID)
		double YO = 136; // ������ Y��ǥ(GRID)
		double DEGRAD = Math.PI / 180.0;
		// double RADDEG = 180.0 / Math.PI;

		double re = RE / GRID;
		double slat1 = SLAT1 * DEGRAD;
		double slat2 = SLAT2 * DEGRAD;
		double olon = OLON * DEGRAD;
		double olat = OLAT * DEGRAD;

		double sn = Math.tan(Math.PI * 0.25 + slat2 * 0.5) / Math.tan(Math.PI * 0.25 + slat1 * 0.5);
		sn = Math.log(Math.cos(slat1) / Math.cos(slat2)) / Math.log(sn);
		double sf = Math.tan(Math.PI * 0.25 + slat1 * 0.5);
		sf = Math.pow(sf, sn) * Math.cos(slat1) / sn;
		double ro = Math.tan(Math.PI * 0.25 + olat * 0.5);
		ro = re * sf / Math.pow(ro, sn);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("lat", v1);
		map.put("lng", v1);
		double ra = Math.tan(Math.PI * 0.25 + (v1) * DEGRAD * 0.5);
		ra = re * sf / Math.pow(ra, sn);
		double theta = v2 * DEGRAD - olon;
		if (theta > Math.PI)
			theta -= 2.0 * Math.PI;
		if (theta < -Math.PI)
			theta += 2.0 * Math.PI;
		theta *= sn;

		map.put("x", Math.floor(ra * Math.sin(theta) + XO + 0.5));
		map.put("y", Math.floor(ro - ra * Math.cos(theta) + YO + 0.5));

		return map;
	}

}
