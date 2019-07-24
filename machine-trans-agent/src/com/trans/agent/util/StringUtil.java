package com.trans.agent.util;

import java.io.BufferedReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Clob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Class StringUtil.inst().
 *
 * @author
 * @version 1.0
 * @see <pre>
 * (Modification Information)
 *
 *
 * -----------  --------  ---------------------------
 *
 * </pre>
 * @since 2017. 3. 9
 */

public class StringUtil {

	/** The instance. */
    private volatile static StringUtil instance;

    /**
	 * Gets the single instance of CryptUtil.
	 *
	 * @return single instance of CryptUtil
	 */

    public static StringUtil inst() {
		if(instance == null) {
			synchronized (StringUtil.class) {
				if(instance == null) {
					instance = new StringUtil();
				}
			}
		}
		return instance;
	}


	/**
	 * Capitalize first letter.
	 *
	 * @param original
	 *            the original
	 * @return the string
	 */
	public String capitalizeFirstLetter(String original) {
		if (original == null || original.length() == 0)
			return original;

		return original.substring(0, 1).toUpperCase() + original.substring(1);
	}

	/**
	 * Alpha num only.
	 *
	 * @param s
	 *            the s
	 * @return the string
	 */
	public String alphaNumOnly(String s) {
		int i = s.length();
		StringBuffer stringbuffer = new StringBuffer(i);
		for (int j = 0; j < i; j++) {
			char c = s.charAt(j);
			if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9') || c == '_' || c == '-')
				stringbuffer.append(c);
		}

		return stringbuffer.toString();

	}

	/**
	 * Checks if is alpha num only with sp.
	 *
	 * @param s
	 *            the s
	 * @return true, if is alpha num only with sp
	 */
	public boolean isAlphaNumOnlyWithSp(String s) {
		int i = s.length();
		for (int j = 0; j < i; j++) {
			char c = s.charAt(j);
			if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c < '0' || c > '9') && c != '_' && c != '-')
				return false;
		}

		return true;
	}

	/**
	 * Checks if is alpha num only.
	 *
	 * @param s
	 *            the s
	 * @return true, if is alpha num only
	 */
	public boolean isAlphaNumOnly(String s) {
		int i = s.length();
		for (int j = 0; j < i; j++) {
			char c = s.charAt(j);
			if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c < '0' || c > '9'))
				return false;
		}

		return true;
	}

	/**
	 * Checks if is alpha only.
	 *
	 * @param s
	 *            the s
	 * @return true, if is alpha only
	 */
	public boolean isAlphaOnly(String s) {
		int i = s.length();
		for (int j = 0; j < i; j++) {
			char c = s.charAt(j);
			if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z'))
				return false;
		}

		return true;

	}

	/**
	 * Checks if is alpha.
	 *
	 * @param ch
	 *            the ch
	 * @return true, if is alpha
	 */
	public boolean isAlpha(char ch) {
		if (ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z')
			return true;
		else
			return false;
	}

	/**
	 * Checks if is num only.
	 *
	 * @param s
	 *            the s
	 * @return true, if is num only
	 */
	public boolean isNumOnly(String s) {
		int i = s.length();
		for (int j = 0; j < i; j++) {
			char ch = s.charAt(j);
			if (ch < '0' || ch > '9')
				return false;
		}

		return true;
	}

	/**
	 * Checks if is numeric.
	 *
	 * @param ch
	 *            the ch
	 * @return true, if is numeric
	 */
	public boolean isNumeric(char ch) {
		if (ch >= '0' && ch <= '9')
			return true;
		else
			return false;
	}

	/**
	 * Normalize whitespace.
	 *
	 * @param s
	 *            the s
	 * @return the string
	 */
	public String normalizeWhitespace(String s) {
		StringBuffer stringbuffer = new StringBuffer();
		int i = s.length();
		boolean flag = false;
		for (int j = 0; j < i; j++) {
			char c = s.charAt(j);
			switch (c) {
				case 9 : // '\t'
				case 10 : // '\n'
				case 13 : // '\r'
				case 32 : // ' '
					if (!flag) {
						stringbuffer.append(' ');
						flag = true;
					}
					break;

				default :
					stringbuffer.append(c);
					flag = false;
					break;
			}
		}

		return stringbuffer.toString();
	}

	/**
	 * Num occurrences.
	 *
	 * @param s
	 *            the s
	 * @param c
	 *            the c
	 * @return the int
	 */
	public int numOccurrences(String s, char c) {
		int i = 0;
		int j = 0;
		int l;
		for (int k = s.length(); j < k; j = l + 1) {
			l = s.indexOf(c, j);
			if (l < 0)
				break;
			i++;
		}

		return i;
	}

	/**
	 * Removes the characters.
	 *
	 * @param s
	 *            the s
	 * @param s1
	 *            the s1
	 * @return the string
	 */
	public String removeCharacters(String s, String s1) {
		int i = s.length();
		StringBuffer stringbuffer = new StringBuffer(i);
		for (int j = 0; j < i; j++) {
			char c = s.charAt(j);
			if (s1.indexOf(c) == -1)
				stringbuffer.append(c);
		}

		return stringbuffer.toString();
	}

	/**
	 * Removes the white space.
	 *
	 * @param s
	 *            the s
	 * @return the string
	 */
	public String removeWhiteSpace(String s) {
		int i = s.length();
		StringBuffer stringbuffer = new StringBuffer(i);
		for (int j = 0; j < i; j++) {
			char c = s.charAt(j);
			if (!Character.isWhitespace(c))
				stringbuffer.append(c);
		}

		return stringbuffer.toString();
	}

	/**
	 * Replace.
	 *
	 * @param target
	 *            the target
	 * @param arguments
	 *            the arguments
	 * @param replacements
	 *            the replacements
	 * @return the string
	 */
	public String replace(String target, String[] arguments, String[] replacements) {
		if (target == null || arguments == null || replacements == null)
			return target;

		for (int index = 0; index < arguments.length; index++) {
			target = replace(target, arguments[index], replacements[index]);
		}

		return target;
	}

	/**
	 * Replace.
	 *
	 * @param target
	 *            the target
	 * @param argument
	 *            the argument
	 * @param replacement
	 *            the replacement
	 * @return the string
	 */
	public String replace(String target, String argument, String replacement) {
		if (target == null || argument == null || replacement == null)
			return target;

		int i = target.indexOf(argument);

		if (i == -1)
			return target;

		StringBuffer targetSB = new StringBuffer(target);
		while (i != -1) {
			targetSB.delete(i, i + argument.length());
			targetSB.insert(i, replacement);
			// check for any more
			i = targetSB.toString().indexOf(argument, i + replacement.length());
		}

		return targetSB.toString();
	}

	/**
	 * Replace.
	 *
	 * @param oTarget
	 *            the o target
	 * @param argument
	 *            the argument
	 * @param replacement
	 *            the replacement
	 * @return the string
	 */
	public String replace(Object oTarget, String argument, String replacement) {
		String target = "";
		if (oTarget instanceof String) {
			target = nchk(oTarget);
		} else {
			return "";
		}
		if (target == null || argument == null || replacement == null)
			return target;

		int i = target.indexOf(argument);

		if (i == -1)
			return target;

		StringBuffer targetSB = new StringBuffer(target);
		while (i != -1) {
			targetSB.delete(i, i + argument.length());
			targetSB.insert(i, replacement);
			// check for any more
			i = targetSB.toString().indexOf(argument, i + replacement.length());
		}

		return targetSB.toString();
	}

	/**
	 * Split string at character.
	 *
	 * @param s
	 *            the s
	 * @param c
	 *            the c
	 * @return the string[]
	 */
	public String[] splitStringAtCharacter(String s, char c) {
		String as[] = new String[numOccurrences(s, c) + 1];
		splitStringAtCharacter(s, c, as, 0);
		return as;
	}

	/**
	 * Split string at character.
	 *
	 * @param s
	 *            the s
	 * @param c
	 *            the c
	 * @param as
	 *            the as
	 * @param i
	 *            the i
	 * @return the int
	 */
	protected static int splitStringAtCharacter(String s, char c, String as[], int i) {
		int j = 0;
		int k = i;
		int l = 0;
		int j1;
		for (int i1 = s.length(); l <= i1 && k < as.length; l = j1 + 1) {
			j1 = s.indexOf(c, l);
			if (j1 < 0)
				j1 = i1;
			as[k] = s.substring(l, j1);
			j++;
			k++;
		}

		return j;
	}

	/**
	 * String2 boolean.
	 *
	 * @param data
	 *            the data
	 * @return true, if successful
	 */
	public boolean string2Boolean(String data) {
		if (data == null)
			return false;
		if (data.equalsIgnoreCase("true"))
			return true;
		if (data.equalsIgnoreCase("yes"))
			return true;
		if (data.equalsIgnoreCase("ok"))
			return true;
		if (data.equalsIgnoreCase("okay"))
			return true;
		if (data.equalsIgnoreCase("on"))
			return true;
		if (data.equalsIgnoreCase("1"))
			return true;
		if (data.equalsIgnoreCase("y"))
			return true;

		return false;
	}

	/**
	 * String2 int.
	 *
	 * @param data
	 *            the data
	 * @return the int
	 */
	public int string2Int(String data) {
		try {
			return Integer.parseInt(data);
		} catch (NumberFormatException ex) {
			return 0;
		}
	}

	/**
	 * String2 array list.
	 *
	 * @param strValue
	 *            the str value
	 * @return the list
	 */
	public List<String> string2ArrayList(String strValue) {

		List<String> arrResult = new ArrayList<String>();
		strValue = StringUtil.inst().nchk(strValue);

		if (strValue == null || strValue.equals("")) {
			return arrResult;
		}

		String[] arrBuff = strValue.split(",");

		if (arrBuff == null) {
			return arrResult;
		}

		for (int i = 0; i < arrBuff.length; i++) {
			arrResult.add(arrBuff[i].trim());
		}

		return arrResult;
	}

	/**
	 * String2 hashtable.
	 *
	 * @param data
	 *            the data
	 * @return the map
	 */
	public Map<String, String> string2Hashtable(String data) {
		Map<String, String> commands = new HashMap<String, String>();

		data = normalizeWhitespace(data);
		String[] data_arr = splitStringAtCharacter(data, ' ');

		for (int i = 0; i < data_arr.length; i++) {
			int equ_pos = data_arr[i].indexOf('=');
			String key = data_arr[i].substring(0, equ_pos);
			String value = data_arr[i].substring(equ_pos + 1);

			commands.put(key, value);
		}

		return commands;
	}

	/**
	 * Hashtable2 string.
	 *
	 * @param commands
	 *            the commands
	 * @return the string
	 */
	public String hashtable2String(Map<String, Object> commands) {
		Iterator<String> it = commands.keySet().iterator();
		StringBuffer retcode = new StringBuffer();

		while (it.hasNext()) {
			String key = "";
			String value = "";

			try {
				key = it.next();
				value = (String) commands.get(key);

				retcode.append(key);
				retcode.append("=");
				retcode.append(value);
				retcode.append(" ");
			} catch (ClassCastException ex) {
			}
		}

		return retcode.toString().trim();
	}

	/**
	 * To lower case.
	 *
	 * @param s
	 *            the s
	 * @return the string
	 */
	public String toLowerCase(String s) {
		int i;
		int j;
		char c;
		label0 : {
			i = s.length();
			for (j = 0; j < i; j++) {
				char c1 = s.charAt(j);
				c = Character.toLowerCase(c1);
				if (c1 != c)
					break label0;
			}

			return s;
		}
		char ac[] = new char[i];
		int k;
		for (k = 0; k < j; k++)
			ac[k] = s.charAt(k);

		ac[k++] = c;
		for (; k < i; k++)
			ac[k] = Character.toLowerCase(s.charAt(k));

		String s1 = new String(ac, 0, i);
		return s1;
	}

	/**
	 * To upper case.
	 *
	 * @param s
	 *            the s
	 * @return the string
	 */
	public String toUpperCase(String s) {
		int i;
		int j;
		char c;
		label0 : {
			i = s.length();
			for (j = 0; j < i; j++) {
				char c1 = s.charAt(j);
				c = Character.toUpperCase(c1);
				if (c1 != c)
					break label0;
			}

			return s;
		}
		char ac[] = new char[i];
		int k;
		for (k = 0; k < j; k++)
			ac[k] = s.charAt(k);

		ac[k++] = c;
		for (; k < i; k++)
			ac[k] = Character.toUpperCase(s.charAt(k));

		return new String(ac, 0, i);
	}

	/**
	 * Tokenizer.
	 *
	 * @param s
	 *            the s
	 * @param s1
	 *            the s1
	 * @return the vector
	 */
	public Vector<String> tokenizer(String s, String s1) {
		if (s == null)
			return null;
		Vector<String> vector = null;
		for (StringTokenizer stringtokenizer = new StringTokenizer(s, s1);
				stringtokenizer.hasMoreTokens();
				vector.addElement(stringtokenizer.nextToken().trim())){
			if (vector == null)
				vector = new Vector<String>();
		}

		return vector;
	}

	/**
	 * Escape html string.
	 *
	 * @param s
	 *            the s
	 * @return the string
	 */
	public String escapeHtmlString(String s) {
		String s1 = s;
		if (s1 == null)
			return null;
		if (s1.indexOf(38, 0) != -1)
			s1 = replace(s1, "&", "&amp;");
		if (s1.indexOf(60, 0) != -1)
			s1 = replace(s1, "<", "&lt;");
		if (s1.indexOf(62, 0) != -1)
			s1 = replace(s1, ">", "&gt;");
		if (s1.indexOf(34, 0) != -1)
			s1 = replace(s1, "\"", "&quot;");
		if (s1.indexOf(13, 0) != -1)
			s1 = replace(s1, "\\n", "<br>");
		return s1;
	}

	/**
	 * Re escape html string.
	 *
	 * @param s
	 *            the s
	 * @return the string
	 */
	public String reEscapeHtmlString(String s) {
		String s1 = s;
		if (s1 == null)
			return null;
		String[] arguments = {"&amp;", "&lt;", "&gt;", "&quot;"};
		String[] replacements = {"&", "<", ">", "\""};
		return replace(s1, arguments, replacements);
	}

	/**
	 * Fill.
	 *
	 * @param c
	 *            the c
	 * @param length
	 *            the length
	 * @return the string
	 */
	public String fill(char c, int length) {
		if (length <= 0)
			return "";

		char[] ca = new char[length];
		for (int index = 0; index < length; index++) {
			ca[index] = c;
		}

		return new String(ca);
	}

	/**
	 * Fill.
	 *
	 * @param c
	 *            the c
	 * @param length
	 *            the length
	 * @return the string
	 */
	public String fill(long c, int length) {
		if (length <= 0)
			return "";
		String str = c + "";

		int len = str.length();
		int loop = length - len;

		StringBuilder buff = new StringBuilder();
		for (int i = 0; i < loop; i++) {
			buff.append("0");
		}
		buff.append(str);

		return buff.toString();
	}

	/**
	 * Pad right.
	 *
	 * @param s
	 *            the s
	 * @param c
	 *            the c
	 * @param length
	 *            the length
	 * @return the string
	 */
	public String padRight(String s, char c, int length) {
		return s + fill(c, length - s.length());
	}

	/**
	 * Pad left.
	 *
	 * @param s
	 *            the s
	 * @param c
	 *            the c
	 * @param length
	 *            the length
	 * @return the string
	 */
	public String padLeft(String s, char c, int length) {
		return fill(c, length - s.length()) + s;
	}

	/**
	 * Pad left zero.
	 *
	 * @param s
	 *            the s
	 * @return the string
	 */
	public String padLeftZero(Object s) {
		String str = ((BigDecimal) s).toString();
		if (str.length() < 2) {
			str = "0" + str;
		}
		return str;
	}

	/**
	 * Cut left zero.
	 *
	 * @param s
	 *            the s
	 * @return the string
	 */
	public String cutLeftZero(String s) {
		String str = left(s, 1);
		if (str.equals("0")) {
			s = s.substring(1, s.length());
		}
		return s;
	}

	/**
	 * To string.
	 *
	 * @param args
	 *            the args
	 * @return the string
	 */
	public String toString(Object[] args) {
		return toString(args, ",");
	}

	/**
	 * To string.
	 *
	 * @param args
	 *            the args
	 * @param separator
	 *            the separator
	 * @return the string
	 */
	public String toString(Object[] args, String separator) {
		if (args == null)
			return null;

		StringBuffer buf = new StringBuffer();

		for (int index = 0; index < args.length; index++) {
			if (index > 0)
				buf.append(separator);

			if (args[index] == null)
				buf.append("");
			else
				buf.append(args[index].toString());
		}

		return buf.toString();
	}

	/**
	 * To string.
	 *
	 * @param list
	 *            the list
	 * @param separator
	 *            the separator
	 * @return the string
	 */
	public String toString(List<?> list, String separator) {
		if (list == null)
			return "";

		StringBuffer buf = new StringBuffer();
		for (int index = 0; index < list.size(); index++) {
			if (index > 0)
				buf.append(separator);
			buf.append(list.get(index).toString());
		}
		return buf.toString();
	}

	/**
	 * To string.
	 *
	 * @param list
	 *            the list
	 * @param mapname
	 *            the mapname
	 * @param separator
	 *            the separator
	 * @return the string
	 */
	public String toString(List<?> list, String mapname, String separator) {
		StringBuffer buf = new StringBuffer();
		for (int index = 0; index < list.size(); index++) {
			HashMap<?, ?> info = (HashMap<?, ?>) list.get(index);

			if (index > 0)
				buf.append(separator);
			buf.append(info.get(mapname).toString());
		}
		return buf.toString();
	}

	/**
	 * To convert.
	 *
	 * @param str
	 *            the str
	 * @param src_enc
	 *            the src_enc
	 * @param dest_enc
	 *            the dest_enc
	 * @return the string
	 * @throws UnsupportedEncodingException
	 *             the unsupported encoding exception
	 */
	public String toConvert(String str, String src_enc, String dest_enc) throws java.io.UnsupportedEncodingException {
		if (str == null)
			return "";
		else
			return new String(str.getBytes(src_enc), dest_enc);
	}

	/**
	 * Nvl.
	 *
	 * @param str
	 *            the str
	 * @return the string
	 */
	public String NVL(String str) {
		if (str == null)
			return "";
		else
			return str.trim();
	}

	// ڿ nullΰ replace_str ReturnѴ.
	// ) ̺ <td>str</td> str null
	// replate_str &nbsp; Ѵ.
	/**
	 * Nvl.
	 *
	 * @param str
	 *            the str
	 * @param replace_str
	 *            the replace_str
	 * @return the string
	 */
	public String NVL(String str, String replace_str) {
		if (str == null || str.length() <= 0)
			return replace_str;
		else
			return str;
	}

	/**
	 * Gets the to comma int.
	 *
	 * @param stText
	 *            the st text
	 * @return the to comma int
	 */
	public String getToCommaInt(String stText) {
		if (stText == null || stText.trim().equals(""))
			return "";
		String ch = "#,###,##0";
		java.text.DecimalFormat df = new java.text.DecimalFormat(ch);
		String stResult = df.format(Integer.parseInt(stText));
		return stResult;
	}

	public String getToCommaDouble(String stText) {
		if (stText == null || stText.trim().equals(""))
			return "";
		String ch = "#,###,###.##";
		java.text.DecimalFormat df = new java.text.DecimalFormat(ch);
		String stResult = df.format(Double.parseDouble(stText));
		return stResult;
	}

	/**
	 * Gets the to comma int.
	 *
	 * @param nValue
	 *            the n value
	 * @return the to comma int
	 */
	public String getToCommaInt(int nValue) {
		String stText = null;
		stText = String.valueOf(nValue);

		return getToCommaInt(stText);
	}

	public String getToCommaDouble(double nValue) {
		String stText = null;
		stText = String.valueOf(nValue);

		return getToCommaDouble(stText);
	}

	/**
	 * Gets the to comma int.
	 *
	 * @param nValue
	 *            the n value
	 * @return the to comma int
	 */
	public String getToCommaInt(long nValue) {
		String stText = null;
		stText = String.valueOf(nValue);

		return getToCommaInt(stText);
	}

	/**
	 * Clob to string.
	 *
	 * @param clob
	 *            the clob
	 * @return the string
	 */
	public String clobToString(Clob clob) {
		String clobString = "";
		try {

			if (clob != null) {
				Reader reader = clob.getCharacterStream();

				BufferedReader clobReader = new BufferedReader(reader);

				StringWriter clobWriter = new StringWriter();
				char[] buffer = new char[1024];
				int size = 0;

				while ((size = clobReader.read(buffer, 0, 1024)) != -1)
					clobWriter.write(buffer, 0, size);

				clobString = clobWriter.toString();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return clobString;
	}

	/**
	 * Cut string.
	 *
	 * @param src
	 *            the src
	 * @param str_length
	 *            the str_length
	 * @param att_str
	 *            the att_str
	 * @return the string
	 */
	public String cutString(String src, int str_length, String att_str) {
		int ret_str_length = 0;

		String ret_str = new String("");

		if (src == null) {
			return ret_str;
		}

		// ȯ Character length Ѵ.
		String tempMulLanChar = new String("");
		int lanCharLength = tempMulLanChar.length();

		// Character ߰ ߸ ʰ ϱ
		int multiLanCharIndex = 0;

		for (int i = 0; i < src.length(); i++) {
			ret_str += src.charAt(i);

			if (src.charAt(i) > '~') {
				ret_str_length = ret_str_length + 2 / lanCharLength;
				multiLanCharIndex++;
			} else {
				ret_str_length = ret_str_length + 1;
			}
			if (ret_str_length >= str_length && (multiLanCharIndex % lanCharLength) == 0) {
				ret_str += nchk(att_str);
				break;
			}
		}

		return ret_str;
	}

	/**
	 * Nchk.
	 *
	 * @param str
	 *            the str
	 * @return the string
	 */

	public String nchk(String str) {
		if (str == null || str.equals(""))
			return "";
		else
			return str;
	}

	/**
	 * Nchk.
	 *
	 * @param obj
	 *            the obj
	 * @return the string
	 */

	public String nchk(Object obj) {
		if (obj == null)
			return "";
		else
			return obj.toString();
	}

	/**
	 * Nchk.
	 *
	 * @param str
	 *            the str
	 * @param dstr
	 *            the dstr
	 * @return the string
	 */

	public String nchk(String str, String dstr) {
		if (str == null || str.equals("")) {
			if (dstr == null || dstr.equals("")) {
				return "";
			} else {
				return dstr;
			}
		} else {
			return str;
		}
	}

	/**
	 * Nchk.
	 *
	 * @param obj
	 *            the obj
	 * @param dstr
	 *            the dstr
	 * @return the string
	 */

	public String nchk(Object obj, String dstr) {
		if (obj == null)
			if (dstr == null)
				return "";
			else
				return dstr;
		else
			return obj.toString();
	}

	/**
	 * Right.
	 *
	 * @param s
	 *            the s
	 * @param len
	 *            the len
	 * @return the string
	 */
	public String right(String s, int len) {
		if (s == null)
			return "";
		int L = s.length();
		if (L <= len)
			return s;
		return s.substring(L - len, L);
	}

	/**
	 * Left.
	 *
	 * @param s
	 *            the s
	 * @param len
	 *            the len
	 * @return the string
	 */
	public String left(String s, int len) {
		if (s == null)
			return "";
		if (s.length() <= len)
			return s;
		return s.substring(0, len);
	}

	/**
	 * Cut left.
	 *
	 * @param s
	 *            the s
	 * @param len
	 *            the len
	 * @param replace_str
	 *            the replace_str
	 * @return the string
	 */
	public String cutLeft(String s, int len, String replace_str) {
		if (s == null) {
			return "";
		} else if (s.length() <= len) {
			return s;
		} else {
			return s.substring(0, len) + replace_str;
		}
	}

	/**
	 * Checks if is alpha hangul num only.
	 *
	 * @param s
	 *            the s
	 * @return true, if is alpha hangul num only
	 */
	public boolean isAlphaHangulNumOnly(String s) {

		Pattern pattern = Pattern.compile("^[a-zA-Z-R0-9]+");
		Matcher match = pattern.matcher(s);

		if (match.find()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks if is hangul only.
	 *
	 * @param s
	 *            the s
	 * @return true, if is hangul only
	 */
	public boolean isHangulOnly(String s) {

		Pattern pattern = Pattern.compile("^[-R]+");
		Matcher match = pattern.matcher(s);

		if (match.find()) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Checks if is email.
	 *
	 * @param s
	 *            the s
	 * @return true, if is email
	 */
	public boolean isEmail(String s) {

		Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher match = pattern.matcher(s);
		return match.matches();
	}

	/**
	 * Checks if is accepted alpha.
	 *
	 * @param s
	 *            the s
	 * @return true, if is accepted alpha
	 */
	public boolean isAcceptedAlpha(String s) {

		Pattern pattern = Pattern.compile("[^-R\\s]");
		Matcher match = pattern.matcher(s);

		if (match.find()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks if is geo tag.
	 *
	 * @param s
	 *            the s
	 * @return true, if is geo tag
	 */
	public boolean isGeoTag(String s) {

		Pattern pattern = Pattern.compile("^(-?[0-9]+(.[0-9]+)?)$");
		Matcher match = pattern.matcher(s);

		if (match.find()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks if is ip address.
	 *
	 * @param s
	 *            the s
	 * @return true, if is ip address
	 */
	public boolean isIpAddress(String s) {

		Pattern pattern = Pattern.compile("^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");
		Matcher match = pattern.matcher(s);

		if (match.find()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks if is cell phone.
	 *
	 * @param s
	 *            the s
	 * @return true, if is cell phone
	 */
	public boolean isCellPhone(String s) {

		Pattern pattern = Pattern.compile("^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}$");
		Matcher match = pattern.matcher(s);

		if (match.find()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks if is 010 cell phone.
	 *
	 * @param s
	 *            the s
	 * @return true, if is 010 cell phone
	 */
	public boolean is010CellPhone(String s) {

		Pattern pattern = Pattern.compile("^010(?:\\d{3}|\\d{4})\\d{4}$");
		Matcher match = pattern.matcher(s);

		if (match.find()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks if is contain.
	 *
	 * @param s1
	 *            the s1
	 * @param s2
	 *            the s2
	 * @return true, if is contain
	 */
	public boolean isContain(String s1, String s2) {
		if (s1.indexOf(s2) > -1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Gets the string l padding zero.
	 *
	 * @param length
	 *            the length
	 * @param value
	 *            the value
	 * @return the string l padding zero
	 */
	public String getStringLPaddingZero(int length, String value) {
		StringBuffer buffer = new StringBuffer();
		if (value.length() < length) {
			for (int tempValueLength = value.length(); tempValueLength < length; tempValueLength++) {
				buffer.append("0");
			}
		}
		buffer.append(value);
		// Log.log("info","[SMSKEY:"+buffer+"]");
		return buffer.toString();
	}

	/**
	 * Gets the random key.
	 *
	 * @return the random key
	 */
	public String getRandomKey() {
		return getRandomKey(6);
	}
	public String getRandomKey_4() {
		return getRandomKey(4);
	}
	/**
	 * Gets the random key.
	 *
	 * @param square
	 *            the square
	 * @return the random key
	 */
	public String getRandomKey(int square) {
		return getStringLPaddingZero(square, String.valueOf(getRandom(square)));
	}

	/**
	 * Gets the random.
	 *
	 * @param square
	 *            the square
	 * @return the random
	 */
	public int getRandom(int square) {
		int ran = (int) (Math.random() * (Math.pow(10, square)));
		return ran;
	}

	public String PropertiesEncode(String str){
		try {
			return new String(str.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	/**
	 * Url encode.
	 *
	 * @param str
	 *            the str
	 * @return the string
	 */
	public String UrlEncode(String str) {
		try {
			return URLEncoder.encode(str, "UTF-8");
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * Url decode.
	 *
	 * @param str
	 *            the str
	 * @return the string
	 */
	public String UrlDecode(String str) {
		try {
			return URLDecoder.decode(str, "UTF-8");
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * Show line text.
	 *
	 * @param str
	 *            the str
	 * @return the string
	 */
	public String showLineText(String str) {
		if (str == null)
			return "";
		return str.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\r", "").replaceAll("\n", " ");
	}

	/**
	 * Show html.
	 *
	 * @param str
	 *            the str
	 * @return the string
	 */
	public String showHtml(String str) {
		if (str == null)
			return "";
		// return rplc(rplc(rplc(str, "<", "&lt;"), ">", "&gt;"),
		// System.getProperty("line.separator"), "<br />");
		return str.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\r", "").replaceAll("\n", "<br />").replaceAll(" ", "&nbsp;");
	}

	/**
	 * Show html not replace blank.
	 *
	 * @param str
	 *            the str
	 * @return the string
	 */
	public String showHtmlNotReplaceBlank(String str) {
		if (str == null)
			return "";
		// return rplc(rplc(rplc(str, "<", "&lt;"), ">", "&gt;"),
		// System.getProperty("line.separator"), "<br />");
		return str.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\r", "").replaceAll("\n", "<br />");
	}

	/**
	 * Show html with tag.
	 *
	 * @param str
	 *            the str
	 * @return the string
	 */
	public String showHtmlWithTag(String str) {
		if (str == null)
			return "";
		// return rplc(str, System.getProperty("line.separator"), "<br />");
		return str.replaceAll("\r", "").replaceAll("\n", "<br />");
	}

	/**
	 * Make phone number.
	 *
	 * @param phoneNumber
	 *            the phone number
	 * @return the string
	 */
	public String makePhoneNumber(String phoneNumber) {
		String regEx = "(\\d{3})(\\d{3,4})(\\d{4})";

		if (!Pattern.matches(regEx, phoneNumber))
			return null;

		return phoneNumber.replaceAll(regEx, "$1-$2-$3");

	}

	/**
	 * To camel case.
	 *
	 * @param value
	 *            the value
	 * @return the string
	 */
	public String toCamelCase(String value) {
		if (value == null)
			return null;

		if (value.indexOf('_') < 0)
			return value;

		value = value.toLowerCase();
		StringTokenizer st = new StringTokenizer(value, "_");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; st.hasMoreTokens(); i++) {
			String token = st.nextToken();
			if (i > 0) {
				sb.append(token.substring(0, 1).toUpperCase());
				sb.append(token.substring(1));
			} else {
				sb.append(token);
			}
		}

		return sb.toString();
	}

	/** * 분할 집합
	 * @param <T>
	 * @param resList 꼭 분할 집합
	 * @param count 모든 집합 요소 개수
	 *  @return 복귀 분할 후 각 집합
	 */
	public <T> List<List<T>> split(List<T> resList, int count) {
		if (resList == null || count <1)
			return null;
		List<List<T>> ret = new ArrayList<List<T>>();
		int size = resList.size();
		if (size <= count) {
			// 데이터 부족 count 지정 크기
			ret.add(resList);
		}
		else {
			int pre = size / count;
			int last = size % count;
			// 앞 pre 개 집합, 모든 크기 다 count 가지 요소
			for (int i = 0; i <pre; i++){
				List<T> itemList = new ArrayList<T>();
				for (int j = 0; j <count; j++) {
					itemList.add(resList.get(i * count + j));
				}
				ret.add(itemList);
			}

			// last 진행이 처리
			if (last > 0) {
				List<T> itemList = new ArrayList<T>();
				for (int i = 0; i <last; i++) {
					itemList.add(resList.get(pre * count + i));
				}
				ret.add(itemList);
			}
		}

		return ret;
	}

    public StringBuffer numRange(int x, int y){
        StringBuffer buffer = new StringBuffer();
        for(int i= x;i<=y;i++){
            if(i==y) {
                buffer.append(i);
                break;
            }
            else
                buffer.append(i+", ");
        }
        return buffer;
    }

	public String replaceLast(String string, String toReplace, String replacement) {
		if(string==null)
			return "";
        int pos = string.lastIndexOf(toReplace);
        if (pos > -1)
            return string.substring(0, pos)+ replacement + string.substring(pos +   toReplace.length(), string.length());
        else
            return string;
    }

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String args[]) {
		/*
		List<Integer> lst = new ArrayList<Integer>();
		for(int i=0; i<100; i++){
			lst.add(i);
		}
		List<List<Integer>> tt= StringUtil.inst().split(lst, 10);
		System.out.println(tt.size());
		*/
		//boolean b=Pattern.matches("\\^[a-zA-Z0-9]*", "/testa");
		//boolean b=Pattern.matches("/\\d+/", "/1212/");

		boolean b=Pattern.matches("wer/.*/tet", "wer/testa/tet");
		System.out.println(b);

		String p="/v1/users/.*/smscode/.*";
		String s="/v1/users/{id}/smscode/{phone}";
		System.out.println(s.matches(p));
		Pattern pattern = Pattern.compile(p);
		Matcher match = pattern.matcher(s);
		System.out.println(match.find());
		System.out.println(match.matches());


		System.out.println(StringUtil.inst().getToCommaDouble("3333.333"));
		System.out.println(StringUtil.inst().getToCommaDouble("0.3156"));
		System.out.println(StringUtil.inst().getToCommaDouble("3333.300"));


		/*
		System.out.println(StringUtil.inst().toCamelCase("sdfsdfsdfsdfdsf"));
		System.out.println(StringUtil.inst().toCamelCase("testUserName"));
		System.out.println(StringUtil.inst().toCamelCase("test_user_name"));

		System.out.println("double: " + Double.MAX_VALUE);
		System.out.println("float: " + Float.MAX_VALUE);
		System.out.println("long: " + Long.MAX_VALUE);
		System.out.println("integer: " + Integer.MAX_VALUE);
		System.out.println("short: " + Short.MAX_VALUE);

		System.out.println(cutString("12345678", 8, "0"));
		*/
	}
}
