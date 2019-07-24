package com.trans.agent.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * 숫자관련 유틸
 *
 * @author ssy
 *
 */
public class NumberUtil {

	/** The instance. */
	private volatile static NumberUtil instance;

	public static NumberUtil inst() {
		if (instance == null) {
			synchronized (NumberUtil.class) {
				if (instance == null) {
					instance = new NumberUtil();
				}
			}
		}
		return instance;
	}

	/**
	 * 금액에 콤마를 삽입한다.
	 *
	 * @param amt
	 *            변환할 금액
	 * @param dec
	 *            소수자리
	 * @return String
	 *
	 *         <p>
	 *
	 *         <pre>
	 * - 사용 예
	 * String date = NumberUtil.getCommaNumber(123456.123, 3)
	 * 결과 : 123,456.123
	 * </pre>
	 */
	public String getCommaNumber(double amt, int dec) {
		NumberFormat nf = NumberFormat.getNumberInstance();
		nf.setMaximumFractionDigits(dec);
		return nf.format(amt);
	}

	/**
	 * 금액에 콤마를 삽입하고 앞에 ￦를 붙인다.
	 *
	 * @param amt
	 *            변환할 금액
	 * @param dec
	 *            소수자리수
	 * @return
	 *
	 *         <p>
	 *
	 *         <pre>
	 * - 사용 예
	 * String date = NumberUtil.getCurrencyNationNumber(123456.123, 3)
	 * 결과 : ￦123,456.123
	 * </pre>
	 */
	public String getCurrencyNationNumber(double amt, int dec) {
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		nf.setMaximumFractionDigits(dec);
		return nf.format(amt);
	}

	/**
	 * 금액에 콤마를 삽입하고 앞에 해당국가의 통화를 표시한다.
	 *
	 * @param amt
	 * @param dec
	 * @param locale
	 * @return
	 *
	 *         <p>
	 *
	 *         <pre>
	 * - 사용 예
	 * String date = NumberUtil.getCurrencyNationNumber(123456.123, 3, Locale.US)
	 * 결과 : $123,456.123
	 */
	public String getCurrencyNationNumber(double amt, int dec, Locale locale) {
		NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
		nf.setMaximumFractionDigits(dec);
		return nf.format(amt);
	}

	/**
	 * 금액의 뒤에 %를 붙인다.
	 *
	 * @param amt
	 * @param dec
	 * @return
	 *
	 *         <p>
	 *
	 *         <pre>
	 * - 사용 예
	 * String date = NumberUtil.getPercentNumber(123456.123, 3)
	 * 결과 : 12,345,612.300%
	 */
	public String getPercentNumber(double amt, int dec) {
		NumberFormat nf = NumberFormat.getPercentInstance();
		nf.setMinimumFractionDigits(dec);
		return nf.format(amt);
	}

	public BigDecimal bigDecimal(double num) {
		return new BigDecimal(Double.toString(num));
	}

	public BigDecimal bigDecimal(float num) {
		return new BigDecimal(Float.toString(num));
	}
	/**
	 * 입력받은 실수를 올림하여 원하는 자리까지 표현한다.
	 *
	 * @param num
	 *            변환할 실수
	 * @param dec
	 *            표현할 소숫수자리
	 * @return double
	 *
	 *         - 사용 예 String num = NumberUtil.ceil(1163.512, -2)) 결과 : 1200.0
	 *         String num = NumberUtil.ceil(1163.512, 2)) 결과 : 1163.52
	 */
	public double ceil(double num, int dec) {
		BigDecimal bd = bigDecimal(num);
		return bd.setScale(dec, BigDecimal.ROUND_UP).doubleValue();
	}

	public double ceil(float num, int dec) {
		BigDecimal bd = bigDecimal(num);
		return bd.setScale(dec, BigDecimal.ROUND_UP).doubleValue();
	}

	/**
	 * 입력받은 실수를 반올림하여 원하는 자리까지 표현한다.
	 *
	 * @param num
	 *            변환할 실수
	 * @param dec
	 *            표현할 소숫자리
	 * @return double
	 *
	 *         - 사용 예 String num = NumberUtil.inst().round(1163.512, -2)) 결과 : 1200.0
	 *         String num = NumberUtil.ceil(1163.512, 2)) 결과 : 1163.51
	 */
	public double round(double num, int dec) {
		BigDecimal bd = bigDecimal(num);
		return bd.setScale(dec, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public double round(float num, int dec) {
		BigDecimal bd = bigDecimal(num);
		return bd.setScale(dec, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	/**
	 * 입력받은 실수를 내림하여 원하는 자리까지 표현한다.
	 *
	 * @param num
	 *            변환할 실수
	 * @param dec
	 *            표현할 소숫자리
	 * @return double
	 *
	 *         - 사용 예 String num = NumberUtil.inst().round(1163.512, -2)) 결과 : 1100.0
	 *         String num = NumberUtil.ceil(1163.512, 2)) 결과 : 1163.51
	 */
	public double floor(double num, int dec) {
		BigDecimal bd = bigDecimal(num);
		return bd.setScale(dec, BigDecimal.ROUND_DOWN).doubleValue();
	}

	public double floor(float num, int dec) {
		BigDecimal bd = bigDecimal(num);
		return bd.setScale(dec, BigDecimal.ROUND_DOWN).doubleValue();
	}

	/**
	 * 숫자와 '.'가 아닌문자의 경우 문자를제외후 double형으로 리턴한다.
	 *
	 * @param str
	 * @return double
	 *
	 *         - 사용 예 String num =
	 *         NumberUtil.stringToNumber("####1163.51244***123#####") 결과 :
	 *         1163.51244123
	 */
	public double stringToNumber(String str) {
		StringBuffer sb = new StringBuffer();
		String number = "1234567890.";

		for (int i = 0; i < str.length(); i++) {
			if (number.indexOf(str.charAt(i)) > -1) {
				sb.append(str.charAt(i));
			}
		}

		return Double.parseDouble(sb.toString());
	}

	/**
	 * 실수형을 정수형으로...
	 *
	 * @param str
	 * @return double
	 *
	 *         - 사용 예 String num = NumberUtil.doubleToInt(23.124) 결과 : 23
	 */
	public int doubleToInt(double num) {
		return (int) num;
	}

	public float doubleToFloat(double num) {
		BigDecimal bd = bigDecimal(num);
		return bd.floatValue();
	}

	public double floatToDouble(float num) {
		BigDecimal bd = bigDecimal(num);
		return bd.doubleValue();
	}

	/**
	 * 정수형을 실수형으로...
	 *
	 * @param str
	 * @return double
	 *
	 *         - 사용 예 String num = NumberUtil.intToDouble(23) 결과 : 23.0
	 */
	public double intToDouble(int num) {
		return (double) num;
	}

	public double doubleSum(double num1, double num2){
		BigDecimal bd1 = bigDecimal(num1);
		BigDecimal bd2 = bigDecimal(num2);

		return bd1.add(bd2).doubleValue();
	}

	public double doubleSub(double num1, double num2){
		BigDecimal bd1 = bigDecimal(num1);
		BigDecimal bd2 = bigDecimal(num2);

		return bd1.subtract(bd2).doubleValue();
	}

	public double doubleDiv(double num1, double num2, int round){
		BigDecimal bd1 = bigDecimal(num1);
		BigDecimal bd2 = bigDecimal(num2);

		return bd1.divide(bd2, round, BigDecimal.ROUND_CEILING).doubleValue();
	}

	public double doubleDiv_Down(double num1, double num2, int round){
		BigDecimal bd1 = bigDecimal(num1);
		BigDecimal bd2 = bigDecimal(num2);

		return bd1.divide(bd2, round, BigDecimal.ROUND_DOWN).doubleValue();
	}


}