package com.trans.agent.runner;

public class TestGcp {
/*
 * cloud translation key
{
  "type": "service_account",
  "project_id": "iron-area-242700",
  "private_key_id": "5447cd07a6cda0f8b4223e2735350ff18a752f31",
  "private_key": "-----BEGIN PRIVATE KEY-----\nMIIEuwIBADANBgkqhkiG9w0BAQEFAASCBKUwggShAgEAAoIBAQC23NMjF3vhDmTX\nZxSQ/gYip6vR/+9AKZj37jKW3i/ALmUAUbAJq8HOfLaJrq5RQ7BlpHKg2Ptj8mj5\nbkJDB3hB1Tr++X7xmjyN20CiKL5XKaQ6K0tL9Hi0cKyqadckTz2JHzO3+JB/kkhe\nYe8+2Vf0EGnE3ZadLDxC4H4e87yLhNZ8ohPWiLFYy8vP+rHzS+AIZs4QNgpi8PUl\nC9POmlgUqn+Cui3PXhhcboRZMblJiuHXjP9iedeH36K9ESlrHn7KOIdvZ8N7EGTi\naF7vKfijWfdTaw91dlNfnGz6KeenVebMaRNlhZr0UvWdOGnezkHr4W6uPQdgtr+q\ncItsRXYRAgMBAAECgf9H+sDwu/JOcce11a7TCqDBJ/4GgPLnOGQ9M6yJgZLMSiGM\nEOS6Z8cdAJCo+vcMoF13xYamwc6sq3/4BHTX2Vvsro61IE/FgVRxJPM97bNoh6RQ\nTZHN8JwlFXIOvuwLTJN0fKUBkuyTOoW35ka6Q8Mofd++3VoMgCpMqujJgzP3fcay\nvMMErANwL7nMJzyhsP+HawPhuhou8NOsFop2eR8rJ7mMvWzoYs7H+vYjgpQ9Gw/H\nRtAXyUhAk3DcA5EhgW5lGHJaNpofUgSXGRujE7jkeAKt4+rZ8MTs221LHdtuzEjk\n40N+DcH10D/PxycS2GalWxri5EqTFGMk5T16E4kCgYEA2F8T788LG1K0iBAZxFo/\nfXo36WVvu1z0jxlQeacCvJcSTjRLZ/YzH73uTaUHVsQ+/HTocyEnp++LNc67muuA\nSQtDsluIKwoLnnTXg/y8skvQipJHCrDJ65Px3aoRTAHs+4DpOf0gb2haGYnEvw7c\npEkhdCmZ35HS3ztznBepQIUCgYEA2Fqh9gj4MVsr/4qN6ePfDZ3XIsV6uH+D9rm0\nzW8nG+1Qzo4ngHpXEBthO5+XeVE2NGSgWBBgnd2GI5BARV113mn3EcwsXjosPTqv\nHo8AJr6a1FrjUH0YeKEgHk3ZKG85C7EKtYqM2/3PE1hwOsjuvJY99b/cq7bsKNo3\n5qmYux0CgYEArow85BdNaXRVyPHcJ4d6cnoXAsTe1vJNI+eTASCW15azoL0VG8AE\nTBYHWUHJYtiby0Nwhff4KsTWs8iblYVoDwT0Tr0CY14OM30kowhPHTKGAFOy1c/6\nhH3r/xsEKyHekKHVA//Mm7M+BB4cxthxbGLGhxRMWowj5gvq3sBDvJUCgYB11bcM\nd4ol55ApF99/GbrsgaEgsQnL3reS80YD9ZwJW9F9DnN5SCiO/zqP7LXlP1QUaRHj\npNfaY0Khr/kxTlvaDUChkuA/WdOqbYHfVOcyMvHE4tOXjOp9GNSTbLbvjNQ3B+/b\nPGDHmI7l7G8DtVhBolqQtDSN2Yz6GxADMy+aCQKBgE/6n3EPnnvfC9L0lj+uDmoe\n3+DpxYqAgtI/16tywJ9N25n2IwqvKXQrb10jGe6f3Hc5+PbvsDaIypQKO/P/V4jh\neVkX7LoxGirNB0e46zZ6wQSYyhzMLoqkPIcasUp/Z8O+OA9qSkPSQX/1QdS9YKd5\nII1cUiSr5V2A0iuLkTIS\n-----END PRIVATE KEY-----\n",
  "client_email": "syoung8171@iron-area-242700.iam.gserviceaccount.com",
  "client_id": "116207491145074762273",
  "auth_uri": "https://accounts.google.com/o/oauth2/auth",
  "token_uri": "https://oauth2.googleapis.com/token",
  "auth_provider_x509_cert_url": "https://www.googleapis.com/oauth2/v1/certs",
  "client_x509_cert_url": "https://www.googleapis.com/robot/v1/metadata/x509/syoung8171%40iron-area-242700.iam.gserviceaccount.com"
}

설정 tip
https://medium.com/@yeksancansu/how-to-use-google-translate-api-in-android-studio-projects-7f09cae320c7
https://dialogflow.com/docs/reference/v2-auth-setup
https://stackoverflow.com/questions/51904999/how-to-get-an-authentication-token-for-google-cloud-translation-api-within-brows
https://codelabs.developers.google.com/codelabs/cloud-translation-intro/index.html#0
https://jeongchul.tistory.com/578

post 사용 방법
https://blog.geun.kr/79
https://stackoverflow.com/questions/4205980/java-sending-http-parameters-via-post-method-easily

Rest API를 사용 방법
https://cloud.google.com/translate/docs/translating-text?hl=ko&refresh=1%29%2C

미인증 방식
curl -s -X POST -H "Content-Type: application/json" \
    -H "Authorization: Bearer "$(gcloud auth application-default print-access-token) \
    --data "{
  'q': 'The Great Pyramid of Giza (also known as the Pyramid of Khufu or the
        Pyramid of Cheops) is the oldest and largest of the three pyramids in
        the Giza pyramid complex.',
  'source': 'en',
  'target': 'es',
  'format': 'text'
}" "https://translation.googleapis.com/language/translate/v2"

API key 적용 방식
curl -s -X POST -H "Content-Type: application/json" \
    --data "{
  'q': 'The Great Pyramid of Giza (also known as the Pyramid of Khufu or the
        Pyramid of Cheops) is the oldest and largest of the three pyramids in
        the Giza pyramid complex.',
  'source': 'en',
  'target': 'es',
  'format': 'text'
}" "https://translation.googleapis.com/language/translate/v2?key=AIzaSyCzXi9wJcUjkkppnHokPACGb5G3Kw53YI0"


*/
	public static void main(String[] args) {
		 // Instantiates a client
		/*
	    Translate translate = TranslateOptions.getDefaultInstance().getService();

	    // The text to translate
	    String text = "Hello, world!";

	    // Translates some text into Russian
	    Translation translation =
	        translate.translate(
	            text,
	            TranslateOption.sourceLanguage("en"),
	            TranslateOption.targetLanguage("ru"));


	    System.out.printf("Text: %s%n", text);
	    System.out.printf("Translation: %s%n", translation.getTranslatedText());*/
	  }

}
