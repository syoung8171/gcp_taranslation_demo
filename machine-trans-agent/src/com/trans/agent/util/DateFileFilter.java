package com.trans.agent.util;

import java.io.File;
import java.io.FileFilter;
import java.util.Date;

/**
 * The Class DateFileFilter.
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
public class DateFileFilter implements FileFilter {
	private Date startDate;
	private Date endDate;

	/**
	 * Instantiates a new date file filter.
	 *
	 * @param startDate
	 *            the start date
	 * @param endDate
	 *            the end date
	 */
	public DateFileFilter(Date startDate, Date endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
	}

	/* (non-Javadoc)
	 * @see java.io.FileFilter#accept(java.io.File)
	 */
	@Override
	public boolean accept(File file) {
		if (startDate.getTime() <= file.lastModified() && endDate.getTime() >= file.lastModified()) {
			return true;
		}
		return false;
	}
}