package com.test.aws;

import com.amazonaws.services.translate.AmazonTranslate;
import com.amazonaws.services.translate.AmazonTranslateClient;
import com.amazonaws.services.translate.model.TranslateTextRequest;
import com.amazonaws.services.translate.model.TranslateTextResult;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {

		//AmazonTranslate translate = AmazonTranslateClient.builder().withRegion("us-west-2").build();
		AmazonTranslate translate = AmazonTranslateClient.builder().withRegion("ap-northeast-2").build();

		// Regions.US_WEST_2
		//translate.us-west-2.amazonaws.com

		//translate.ap-northeast-2.amazonaws.com
		TranslateTextRequest request = new TranslateTextRequest().withText("Greetings from webischia :)")
				.withSourceLanguageCode("en").withTargetLanguageCode("it");
		TranslateTextResult result = translate.translateText(request);
		System.out.println(result.getTranslatedText());
	}
}
