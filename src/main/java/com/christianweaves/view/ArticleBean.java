package com.christianweaves.view;


import java.util.Map;

import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.christianweaves.service.AppService;


/**
 * Backing bean for Article entities.
 */

@Named
@RequestScoped
public class ArticleBean extends BaseBean {

	@Inject
	private AppService service;
	
	public String editArticle() {
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String articleId = params.get("articleId");
		return service.editArticle(new Long(articleId));
	}
}
