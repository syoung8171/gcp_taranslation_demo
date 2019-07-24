package com.trans.agent.util;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


/**
 * base object
 *
 * @author ifif
 * @version 1.0
 * @see <pre>
 * (Modification Information)
 *
 *
 * -----------  --------  ---------------------------
 * 2014. 12. 9   Ʈ
 *
 * </pre>
 * @since 2014. 12. 9
 */
public class BaseObject implements Serializable {
	private static final long serialVersionUID = -8452295749298320236L;

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}
}
