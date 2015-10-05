package org.abhishek.easysupport.convert.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.abhishek.easysupport.dto.rest.Restful;
import org.abhishek.fileanalytics.convert.Converter;

public class FileWrappersArrayConverter implements Converter<List<Restful>, File[]> {

	@Override
	public List<Restful> convert(File[] files) {
	    FileWrapperConverter singularConverter = new FileWrapperConverter();
		List<Restful> convFiles = new ArrayList<Restful>(files.length);

		for (File file : files) {
			if (file.isHidden()) {
				continue;
			}
			convFiles.add(singularConverter.convert(file));
		}
		return convFiles;
	}

}
