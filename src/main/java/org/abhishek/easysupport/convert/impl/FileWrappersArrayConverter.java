package org.abhishek.easysupport.convert.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.abhishek.easysupport.dto.rest.FileWrapper;
import org.abhishek.fileanalytics.convert.Converter;

public class FileWrappersArrayConverter implements Converter<List<FileWrapper>, File[]> {

	@Override
	public List<FileWrapper> convert(File[] files) {
	    FileWrapperConverter singularConverter = new FileWrapperConverter();
		List<FileWrapper> convFiles = new ArrayList<FileWrapper>(files.length);

		for (File file : files) {
			if (file.isHidden()) {
				continue;
			}
			convFiles.add(singularConverter.convert(file));
		}
		return convFiles;
	}

}
