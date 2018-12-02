package model;

import java.util.Map;
import java.util.TreeMap;

import org.json.JSONArray;
import org.json.JSONObject;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class ExchangeService {
	
	public JSONArray getExchangeData(String currency) throws Exception {
		
		final WebClient mWebClient = new WebClient();
		
		mWebClient.getOptions().setCssEnabled(false);
		
		final HtmlPage mHtmlPage = mWebClient.getPage(
				"https://www.esunbank.com.tw/bank/personal/deposit/rate/forex/exchange-rate-chart?Currency=" + currency + "/TWD");
		
		DomNodeList domNodeList;
		DomElement domElement;
		
		int page = 1;
		boolean isNextPage = true;
		
		Map<String, Exchange> map = new TreeMap<>();
		
		String year = null;
		String month = null;
		String day = null;
		String deal = null;
		double price = 0.0;
		
		while (isNextPage) {
			
			domNodeList = mHtmlPage.getElementsByTagName("td");
	        
			for (int i = 4; i < domNodeList.size(); i++) {
	        	
	        	domElement = (DomElement) domNodeList.get(i);
	        	
	        	if(i % 3 == 1) {
	        		year = domElement.asText().substring(0, 4);
	        		month = domElement.asText().substring(5, 7);
	        		day = domElement.asText().substring(8, 10);
	        	} else if (i % 3 == 2) {
	        		deal = "buy";
	        		price = Double.parseDouble(domElement.asText());
	        		if (!map.containsKey(year + month + day + deal)) {
	        			map.put(year + month + day + deal, new Exchange(year, month, day, deal, price));
	        		} else {
	        			isNextPage = false;
	        		}
	        	} else if (i % 3 == 0) {
	        		deal = "sell";
	        		price = Double.parseDouble(domElement.asText());
	        		if (!map.containsKey(year + month + day + deal)) {
	        			map.put(year + month + day + deal, new Exchange(year, month, day, deal, price));
	        		} else {
	        			isNextPage = false;
	        		}
	        	}
	        }
			page++;
			mHtmlPage.executeJavaScript("GotoPage(" + page +  ")");
		}
		
		JSONArray exchanges = new JSONArray();
	
		for(Exchange exchange : map.values()) {
			JSONObject jsonObject = new JSONObject(exchange);
			exchanges.put(jsonObject);
		}
		
		mWebClient.close();
		
		return exchanges;
	}

}
