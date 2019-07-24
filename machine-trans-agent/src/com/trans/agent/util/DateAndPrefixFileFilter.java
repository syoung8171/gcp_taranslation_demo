package com.trans.agent.util;

import java.io.File;
import java.io.FileFilter;
import java.util.Date;

/**
 * The Class DateAndPrefixFileFilter.
 *
 * @author
 * @version 1.0
 * @see <pre>
 * (Modification Information)
 *
 *
 * ---------------  ----------------  ---------------------------
 * 2015. 1. 15
 *
 * </pre>
 * @since 2015. 1. 15
 */
public class DateAndPrefixFileFilter implements FileFilter {
	private Date startDate;
	private Date endDate;
	private String prefix;

	/**
	 * Instantiates a new date and prefix file filter.
	 *
	 * @param startDate
	 *            the start date
	 * @param endDate
	 *            the end date
	 * @param prefix
	 *            the prefix
	 */
	public DateAndPrefixFileFilter(Date startDate, Date endDate, String prefix) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.prefix = prefix;
	}

	/* (non-Javadoc)
	 * @see java.io.FileFilter#accept(java.io.File)
	 */
	@Override
	public boolean accept(File file) {
		if (startDate.getTime() <= file.lastModified() && endDate.getTime() >= file.lastModified() && (file.getName().startsWith(prefix+".") || file.getName().startsWith(prefix+"-"))) {
			return true;
		}
		return false;
	}
}