package com.christianweaves.entities;

import java.io.IOException;
import java.time.Instant;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@ManagedBean
@ViewScoped
public class Auth {

	@Inject
	private GenericDao dao;
	
	private User user;
    private String username;
    private String password;
    private String originalURL;

    @PostConstruct
    public void init() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        originalURL = (String) externalContext.getRequestMap().get(RequestDispatcher.FORWARD_REQUEST_URI);

        if (originalURL == null) {
            originalURL = externalContext.getRequestContextPath() + "/index.faces";
        } else {
            String originalQuery = (String) externalContext.getRequestMap().get(RequestDispatcher.FORWARD_QUERY_STRING);

            if (originalQuery != null) {
                originalURL += "?" + originalQuery;
            }
        }
    }

    @EJB
    private UserService userService;

    public void login() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

        try {
        	request.login(getUsername(), getPassword());
            setUser(userService.find(getUsername(), getPassword()));
            persistLogin(request, "**password**");
            externalContext.getSessionMap().put("user", getUser());
            externalContext.redirect(originalURL);
        } catch (ServletException e) {
            // Handle unknown username/password in request.login().
            context.addMessage(null, new FacesMessage("Unknown login"));
            persistLogin(request, getPassword());
        }
    }

    /**
     * write login attempt to audit table
     * 
     * @param request
     * @param thePassword
     */
	private void persistLogin(HttpServletRequest request, String thePassword) {
		Instant instant = Instant.now();
		long timeStampMillis = instant.toEpochMilli();
		String ipAddress = request.getHeader("X-FORWARDED-FOR");  
		if (ipAddress == null) {  
		    ipAddress = request.getRemoteAddr();  
		}
		Login login = new Login(getUsername(), thePassword, ipAddress, timeStampMillis);
		dao.persist(login);
	}

    public void logout() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.invalidateSession();
        externalContext.redirect(externalContext.getRequestContextPath() + "/login.xhtml");
    }

    public boolean isLoggedIn() {
    	ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        return (User)externalContext.getSessionMap().get("user") != null;
	
    }
    
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}