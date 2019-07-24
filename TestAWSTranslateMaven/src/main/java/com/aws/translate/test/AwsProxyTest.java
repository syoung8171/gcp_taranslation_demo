package com.aws.translate.test;

import com.amazonaws.services.translate.AmazonTranslate;
import com.amazonaws.services.translate.AmazonTranslateClient;
import com.amazonaws.services.translate.model.TranslateTextRequest;
import com.amazonaws.services.translate.model.TranslateTextResult;

/**
 * Hello world!
 *
 */
public class AwsProxyTest {
	public static void main(String[] args) {
		String fr = "Depuis plusieurs années, une des priorités de la prévention de l'échec scolaire porte sur le repérage et la prise en charge précoce des difficultés dans la maîtrise de la langue et de la lecture. Des actions sont menées dans trois directions: - détection, dès l'école maternelle, des signes précurseurs de difficultés ; - adaptation des pratiques pédagogiques pour mieux prendre en compte la diversité des élèves ; - amélioration de l'encadrement des élèves dans les zones défavorisées.";
		String de = "In ihrem Aufgabenbereich lag die komplette Organisation des Sekretariat des Geschäftsführers, sowohl im eigenverantwortlichen Bereich der Organisation von Besprechungen, Erstellung von Unterlagen, Koordination von Mitarbeitern, als auch im administrativen Bereich, was Strukturierung und Ablage angeht. Frau ZZZZZ hat sich dadurch ausgezeichnet, dass die über eine sehr schnelle Auffassungsgabe verfügt und sich in verschiedenste, ihr auch fremde Sachverhalte in kurzer Zeit einarbeiten kann. Sie war immer in der Lage, die entsprechenden Aufgabenstellungen selbstständig, erfolgreich und zu unserer vollsten Zufriedenheit zu erledigen. Dies hat die Geschäftsleitung sehr entlastet, da man Frau ZZZZZ zu regelnde Probleme übertragen konnte und sie sowohl die entsprechenden Telefonate sprachlich gewandt geführt, als auch den Schriftverkehr selbstständig entworfen und betreut hat.";
		String ru = "Лучший, с нашей точки зрения, способ существенно сократить затраты на обучение - это пройти обучение по комплексной программе подготовки специалистов. Такие программы включают в себя несколько взаимосвязанных курсов и ориентированы на получение определённых сертификатов. Курсы, заказанные в составе комплексной программы, обходятся значительно дешевле, чем их прослушивание по отдельности. В стоимость программ обычно входит стоимость экзаменов и индивидуальные подготовки к каждому экзамену.";

		run(false, "es", "ko", "test");

		long start = System.currentTimeMillis();

		//프랑스
		run(false, "fr", "ko", fr);
		//독어
		run(false, "de", "ko", de);
		//러시아
		run(false, "ru", "ko", ru);

		System.out.printf("translation time(milisec) - %d\n", System.currentTimeMillis()-start);
		start = System.currentTimeMillis();

		//프랑스
		run(true, "fr", "ko", fr);
		//독어
		run(true, "de", "ko", de);
		//러시아
		run(true, "ru", "ko", ru);

		System.out.printf("Proxy translation time(milisec) - %d", System.currentTimeMillis()-start);
	}

	public static void run(boolean proxy, String srcLang, String targetLang, String srcText){
		if(proxy) {
			System.setProperty("https.proxyHost", "35.243.110.100") ;
			System.setProperty("https.proxyPort", "3128");
		}

		//AmazonTranslate translate = AmazonTranslateClient.builder().withRegion("us-west-2").build();
		AmazonTranslate translate = AmazonTranslateClient.builder().withRegion("ap-northeast-2").build();

		// Regions.US_WEST_2
		//translate.us-west-2.amazonaws.com

		//translate.ap-northeast-2.amazonaws.com
		TranslateTextRequest request = new TranslateTextRequest().withText(srcText)
				.withSourceLanguageCode(srcLang).withTargetLanguageCode(targetLang);
		TranslateTextResult result = translate.translateText(request);
		System.out.println(result.getTranslatedText());
	}
}
