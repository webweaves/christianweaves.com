package com.christianweaves.view;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.ResourceBundle;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;


@Named
@RequestScoped
public class FileListView {
 
	ResourceBundle bundle = ResourceBundle.getBundle("Messages");
	private String fileLocation = bundle.getString("uploadResourceFolder");
	private Collection<File> files = new ArrayList<>();

	public FileListView() {
		File folder = new File(fileLocation);
		File[] listOfFiles = folder.listFiles();
		if (listOfFiles == null) {
			return;
		}
		Arrays.sort(listOfFiles, new Comparator<File>() {
		    public int compare(File f1, File f2) {
		        return Long.compare(f1.lastModified(), f2.lastModified());
		    }
		});
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				files.add(listOfFiles[i]);
			} 
		}
	}

	public Collection<File> getFiles() {
		return files;
	}

	public void setFiles(Collection<File> files) {
		this.files = files;
	}
	
}