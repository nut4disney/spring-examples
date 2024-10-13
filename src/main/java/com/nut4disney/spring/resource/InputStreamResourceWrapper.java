package com.nut4disney.spring.resource;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.lang.Nullable;

/**
 * Custom input stream resource to properly allow Spring to return byte-ranges
 * of an input stream source.
 * 
 * @author nut4disney
 * @since 05/15/2021
 * @see InputStreamResource
 * @see FileSystemResource
 */
public class InputStreamResourceWrapper extends InputStreamResource {
	private long size;
	private long lastModifiedDate;

	/**
	 * default constructor
	 * 
	 * @param inputStream    InputStream the input stream
	 * @param size           long length of the stream
	 * @param epochTimestamp long the date/time in epoch seconds
	 * @param description    String description of the stream (usually the
	 *                       'filename')
	 */
	public InputStreamResourceWrapper(InputStream inputStream, long size, long epochTimestamp, String description) {
		super(inputStream, description);
		this.size = size;
		this.lastModifiedDate = epochTimestamp;
	}

	/**
	 * alternate constructor
	 * 
	 * @param fileSystemResource FileSystemResource the resource on the file system
	 * @throws IOException
	 */
	public InputStreamResourceWrapper(FileSystemResource fileSystemResource)
			throws IOException {
		this(fileSystemResource, fileSystemResource.getFile().lastModified());
	}
	
	/**
	 * alternate constructor
	 * 
	 * @param fileSystemResource FileSystemResource the resource on the file system
	 * @param epochTimestamp long the date/time in epoch seconds
	 * @throws IOException
	 */
	public InputStreamResourceWrapper(FileSystemResource fileSystemResource, long epochTimestamp)
			throws IOException {
		this(fileSystemResource.getInputStream(), fileSystemResource.getFile().length(), epochTimestamp,
				fileSystemResource.getDescription());
	}

	/*
	 * (non-javadoc)
	 * 
	 * @see org.springframework.core.io.InputStreamResource#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(@Nullable Object other) {
		return super.equals(other);
	}

	/*
	 * (non-javadoc)
	 * 
	 * @see org.springframework.core.io.InputStreamResource#hashCode()
	 */
	@Override
	public int hashCode() {
		return super.hashCode();
	}

	/*
	 * (non-javadoc)
	 * 
	 * @see org.springframework.core.io.AbstractResource#contentLength()
	 */
	@Override
	public long contentLength() throws IOException {
		return this.size;
	}

	/*
	 * (non-javadoc)
	 * 
	 * @see org.springframework.core.io.AbstractResource#lastModified()
	 */
	@Override
	public long lastModified() throws IOException {
		return this.lastModifiedDate;
	}
}
