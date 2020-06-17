package com.christianweaves.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ResourceBundle;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@Named
@RequestScoped
public class FileUploadController {
    
    private UploadedFile file;
    private UploadedFile icon;
 
    ResourceBundle bundle = ResourceBundle.getBundle("Messages");
	
	//private String uploadFileLocation = "/home/blog/uploads";
	private String fileLocation = bundle.getString("uploadResourceFolder");
	
	@Inject
	private ArticleController articleController;
	
    public UploadedFile getFile() {
        return file;
    }
 
    public void setFile(UploadedFile file) {
        this.file = file;
    }
     
    public void upload() {
        if(file != null) {
    		File folder = new File(fileLocation);
    		if (!folder.exists()) {
    			folder.mkdirs();
    		}
    		
            InputStream initialStream;
            OutputStream outStream = null;
			try {
				initialStream = file.getInputstream();
				byte[] buffer = new byte[initialStream.available()];
            	initialStream.read(buffer);
            	
				File targetFile = new File(fileLocation +"/"+ file.getFileName());
            	outStream = new FileOutputStream(targetFile);
            	outStream.write(buffer);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					outStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
     
    public void handleFileUpload(FileUploadEvent event) {
        UploadedFile uploadedFile = event.getFile();
        String fileName = uploadedFile.getFileName();
        String contentType = uploadedFile.getContentType();
        byte[] contents = uploadedFile.getContents();
        
        articleController.getNewArticle().setIcon(contents.toString());
        /*
        FacesMessage msg = new FacesMessage("Succesful", icon.getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);*/
    }

	public UploadedFile getIcon() {
		return icon;
	}

	public void setIcon(UploadedFile icon) {
		this.icon = icon;
	}

	public ArticleController getArticleController() {
		return articleController;
	}

	public void setArticleController(ArticleController articleController) {
		this.articleController = articleController;
	}
}