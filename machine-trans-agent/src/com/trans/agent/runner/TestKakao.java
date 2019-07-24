package com.trans.agent.runner;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLEncoder;

public class TestKakao {
/*
 * KAKAO
네이티브 앱 키 	-	80070b75b1e639f82e845d1512880f4b
REST API 키 	-	d8455f04cba148d9918c1c3459ac4daa
JavaScript 키-	13d8d0b2527bbc6bffccf4a60732af75
Admin 키		-	01d2df8faf1a9575a7de7c603aafedc0

//url 상태
https://developers.kakao.com/apps/329045/stats/realtime

리눅스 encoding 방식
curl -v --get 'https://kapi.kakao.com/v1/translation/translate' -d 'src_lang=kr' -d 'target_lang=en' --data-urlencode 'query=지난해 3월 오픈한 카카오톡 주문하기는 현재까지 약 250만명의 회원을 확보했으며, 주문 가능한 프랜차이즈 브랜드는 38개, 가맹점수는 약 1만 5천여곳에 달한다' -H 'Authorization: KakaoAK 13d8d0b2527bbc6bffccf4a60732af75'

윈도우 encoding 방식
curl -v --get "https://kapi.kakao.com/v1/translation/translate" -d "src_lang=en" -d "target_lang=kr" --data-urlencode "query=hi" -H "Authorization: KakaoAK 13d8d0b2527bbc6bffccf4a60732af75"
 */
	public static void main(String[] args) {
		String fr = "Depuis plusieurs années, une des priorités de la prévention de l'échec scolaire porte sur le repérage et la prise en charge précoce des difficultés dans la maîtrise de la langue et de la lecture. Des actions sont menées dans trois directions: - détection, dès l'école maternelle, des signes précurseurs de difficultés ; - adaptation des pratiques pédagogiques pour mieux prendre en compte la diversité des élèves ; - amélioration de l'encadrement des élèves dans les zones défavorisées.";
		String de = "In ihrem Aufgabenbereich lag die komplette Organisation des Sekretariat des Geschäftsführers, sowohl im eigenverantwortlichen Bereich der Organisation von Besprechungen, Erstellung von Unterlagen, Koordination von Mitarbeitern, als auch im administrativen Bereich, was Strukturierung und Ablage angeht. Frau ZZZZZ hat sich dadurch ausgezeichnet, dass die über eine sehr schnelle Auffassungsgabe verfügt und sich in verschiedenste, ihr auch fremde Sachverhalte in kurzer Zeit einarbeiten kann. Sie war immer in der Lage, die entsprechenden Aufgabenstellungen selbstständig, erfolgreich und zu unserer vollsten Zufriedenheit zu erledigen. Dies hat die Geschäftsleitung sehr entlastet, da man Frau ZZZZZ zu regelnde Probleme übertragen konnte und sie sowohl die entsprechenden Telefonate sprachlich gewandt geführt, als auch den Schriftverkehr selbstständig entworfen und betreut hat.";
		String ru = "Лучший, с нашей точки зрения, способ существенно сократить затраты на обучение - это пройти обучение по комплексной программе подготовки специалистов. Такие программы включают в себя несколько взаимосвязанных курсов и ориентированы на получение определённых сертификатов. Курсы, заказанные в составе комплексной программы, обходятся значительно дешевле, чем их прослушивание по отдельности. В стоимость программ обычно входит стоимость экзаменов и индивидуальные подготовки к каждому экзамену.";

		System.out.printf("fr - %d, de - %d, ru - %d\n", fr.length(), de.length(),ru.length());

		run(false, "es", "kr", "test");

		long start = System.currentTimeMillis();

		//프랑스
		run(false, "fr", "kr", fr);
		//독어
		run(false, "de", "kr", de);
		//러시아
		run(false, "ru", "kr", ru);

		System.out.printf("translation time(milisec) - %d\n", System.currentTimeMillis()-start);
		start = System.currentTimeMillis();

		//프랑스
		run(true, "fr", "kr", fr);
		//독어
		run(true, "de", "kr", de);
		//러시아
		run(true, "ru", "kr", ru);

		System.out.printf("Proxy translation time(milisec) - %d", System.currentTimeMillis()-start);
	}

	public static void run(boolean proxy, String srcLang, String targetLang, String srcText){
		String apikey = "d8455f04cba148d9918c1c3459ac4daa";
		try {
			String text = URLEncoder.encode(srcText, "UTF-8");
			//String postParams = "src_lang=kr&target_lang=en&query="+text;
			String postParams = String.format("src_lang=%s&target_lang=%s&query=%s", srcLang, targetLang, text);
			String apiURL = "https://kapi.kakao.com/v1/translation/translate?"+postParams;
			URL url = new URL(apiURL);

			//Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("35.189.188.107", 3128));
			//HttpURLConnection con = (HttpURLConnection)url.openConnection(proxy);

			if(proxy) {
				System.setProperty("https.proxyHost", "35.243.110.100") ;
				System.setProperty("https.proxyPort", "3128");
			}
			HttpURLConnection con = (HttpURLConnection)url.openConnection();

			String userCredentials = apikey;
			String basicAuth = "KakaoAK " + userCredentials;
			con.setRequestProperty("Authorization", basicAuth);
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			con.setRequestProperty("charset", "utf-8");
			con.setUseCaches(false);
			con.setDoInput(true);
			con.setDoOutput(true);
			int responCode = con.getResponseCode();
			System.out.println("responseCode >> " + responCode);
			BufferedReader br;
			if(responCode == 200) {
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			StringBuffer res  = new StringBuffer();
			while((inputLine = br.readLine()) != null) {
				res.append(inputLine);
			}
			br.close();
			System.out.println("응답 결과 >> " + res.toString());
		} catch(Exception e) {
			System.out.println("오류 >> " + e);
		}

	}

}
