package com.christianweaves.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.NamedQuery;

@Entity
@Table(name = "tb_articles")
@NamedQueries({
	@NamedQuery(name = "allArticles", query = "SELECT a FROM Article a"),
	@NamedQuery(name = "Article.findById", query = "SELECT a FROM Article a where a.id = :id")
})

public class Article implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3352009585501912050L;

	private transient String defaultIcon = "iVBORw0KGgoAAAANSUhEUgAAAQAAAAEACAYAAABccqhmAAARnUlEQVR42u2dCZLjOA5Ff59sOCcrzskGc7KaRrUZqXTKtriABKj/IhzO9CKRJvEB7n+BkHp+/f2Qvx//W50Q0sdfqxNAwvIbrD/hYQGSVv6Lf6KA/6xOCGmHAkBa0WZA/vuRwKZAWCgApJV/4Z8IQB//Xp0Y0gYFgPTw+/GcwCggJBQA0kMRAAGjgJBQAEgP2hGYHn/rM6OAYFAASA+lIxCPZ44IBIMCQHo4CoDC+hQMFhjp4VkAEtgMCAUFgPRw7ANQBOwMDAUFgPTw++Q11qlAsLBID2cCkMBmQBgoAKSVMhPwmQyOBoSBAkBaee4ALAjYDxAGCgBp5feb91ivgsCCIi28Cv8LrFdBYEGRFp6H/55hvQoCC4q08PvD+xnsCAwBBYDU8qrz74i+TwEIAAWA1PCp7V/IoACEgAJAavjU9i9kUABCQAEgV7nq/ZUMCkAIKADkKp86/o5kUABCQAEgV7ga+hf0s1wPEAAKAPnElV7/Z1ivgsCCIu+oafcfYb0KAguKvKOm3X+E9SoILCjyitp2fyGDHYBhoACQM1qNX8mgAISBAkCe6TF+hXUqECwscqTX+BXWqUCwsEhhhPFnMPwPBQWAKCOMH+AEoHBQAMgo4xdwL8BwUADuzSjjVzIY/oeDAnBfRhq/wroUEBbaPRlt/Bn0/iGhANyP0cavsB4FhQV3H1oX9nwig94/LBSAe2Bl/ArrUGBYePtjafwJHPcPDQVgb1o287iKgOP+4aEA7ItFZ98RvTa9f3AoAHtyJ+P/9ZSukm8BI5SPUAD2wrK9X9DrrzAszVt6/J0/fFY/50WgXEMB2IcZxq/MqjPFsydcj2Y0//T6FVAA9sCys+9Igp1nLR6+NR+WadsWCkB8rNv7hYzxE35+oc7DnyGg12+GAhCbWcYvGGdkvZ7+iF6HXr8DCkBMZrX3CyPqychmijyuRePvhAIQj9nGn9BuaJrWDK48dAsFIBZRjN/C8AX0+sOhAMRhVnu/oPeqNTYLw1cE7OgzgQIQg9nGL6gzOCvDB9jRZwoFwD/ejd8qfZqODBq/KRQA33g2fsv+iJp0kA4oAD6Z3dkH1BmdpTDpden1J0EB8Idn47dOWwKNfyoUAF/c1fivpoEMhgLghxXGr1ypAzT+TaEA+GCV8Sd8DrnZ3t8YCsB6aPxkGRSAtXg2fss9Bq7cn0yAArCW2eP8wDXjsxImvWa+cH8yCQrAOu5o/OzscwYFYA2ztvA6kkDjJ09QAOazot2fcC3stohKNK80fqdQAObi2fgtohLNK43fMRSAuXhe3PN74b3JIigA85jt/fVeqxb31NybLIQCMI+Z3l+wdlkv61UQWFBzmNnrL6jzvqPTlsBx/jBQAOYwun39CkF96D0ybRncsTcUFAB7Znr/hDrvO3qvfrb7g0EBsGdW21/vURt6j0xby/3JYigA9swI/xPajG9U2gT0/iGhANgyI/wXtBnfyLSxHgWFBWeLdfgvaPe8FADCgjPGOvxPaG93j0pbBnv+w0IBsMVSADL6DI8CQCgAxlgJgKC/040CQCgAxlgJQELfkNvI9r9ehwIQFAqALRYCIOj3/hQA8gcKgC0WApDQP+GGAkD+QAGwxesa+9HzE1iPgsKCs2W0ACSMmW7LFYDkDxQAW0YLwKjyGi0Aei02AwJCAbDFa1vbYooy61JAWGi2eBUAi12ARqaPTIICYM+oZkDC2Ha2xQgF61MwWGD2jDK00WXldYiSTIQCYM+oZsDosuI5AIQCMIFR7e3RZWW1TXkCo4AwUADmMGJfgIzxnWxWaxVYr4LAgprDCG+bMV4ArHYsskgrMYACMI9ebyuwaV8zCrgxLKR5jPC2FuXFKODGUADm0uttM2yMilHATWEBzWVEXwCjADIMCsB8ekcE9LsWw2yMAm4IC2c+vVGAfteiM9BqXkAGowC3UADW4DUKsDrHgPXMKSyYdfSE3Bl2XtWiKWCZXtIBBWAdvSG3VdlZNQVY1xzCQllLT++7fs/Kq1o0BfR6XCPgDArAelqNTWC78m50UyCDzQB3UADW0xNyJ9h5VYumAOubM1ggPmg1Nv2OZRQwuinA+uYMFogfWvsDEuJEAZo/NgMcQQHwRYvHFdhGAV43NiUDoAD4o0UErMtxVFMggwLgCgqAT2oNLsPWsEY1BazTSSqhAPikxeAiRAEZFABXUAD8UisCGbbGNaIvwDqNpBIKgG9qRcC6PL1uaEIaoQD4p0YEMnxHAdbpI5VQAGJQIwKWZep1ARNphAUSh6vGl2HrZXuaAaxvzmCBxOKqCFiWa6sAZDD8dwcFIB5XRCDD34YhCVwO7A4KQExUBDLej8vre142DxXw0FCXUABi825yjsDPSUKaRnp/h1AA4vNOBPT10YZXKwACen+3UAD24JUICMYaX8swoKaL3t8pFIB9eGWcCeMMsHYikH6WPf+OoQDsxSsRGFXONeG/poOhv3MoAPb8uvAZwTgvfSYCGf2euCb8189FMH7NU3rx3i0iFwpAHWfGnDuuV75rUdme+wX07x6RqVkO3HsvK0r55YrvyOOxpSBQAH5y9Ap58LUFcyvTsc2u9231yle9vzzu58n4S3nmvsv8IWNstLacuwtA8QgJNmfiKYK1HuRovJrHlsp7pe2v9/AU9l+ZLNWKXneLiOBuAtASArYg8OcJSwhfW+ZXev41v16M39Lwvea5mTsIwKwKAfj3DEUAr6bxSuif4EfoRu5gfAVBcBHYWQBmVQaBP28/gk/Gr+95qvwlwpEX6c5G9/X2O1SxowDQ8Mfwqt0fOd+lQ7A8RiEIKgK7CYDFqbZnCIIW+EXOfkfNc0ZMwz9jdNNQrxPut9lFAKzOtD8jIWBBV/Bs/Bm+hr6Ow7Qj0jWq7ug1wjmFXQRg9FHWr8jw3cnXSzF+zadgvdEfJ17lp/f0/1FlMSoaGJmmKewgALPCfiUjWAE753kqbsK1stTPWIhTb10SBIsCdhCAWd6/INh4auhAnqdNJ4wRar2G59OQQ9lUqMSeMHvc9wzB90qzmzC8WzCj5EnpEMzphOwVAU1jmDpAAZiHYF5H5VUS5jWfehDMDa17mgIZFIBpRBIA0kbC/M7I2xyAEiahL5g5/EfmIlg776AnCghjV2ES+oaZowBkDoL1vek90WUYuwqT0DcwCtiLhPXzDxQKQCAoAvER+Jpq3CMA+r0QHYG7CIAyc9kvGUuGP4OhAASF0UAsEvx4/SM9AuA1Tz/YUQAKWoAJjAg8IfgSZ+8ekn0AG8H5AnMRfBm6Pofwhk9wGHBTZu0LeAfy4W9BTEN/Rc8akzB2FSahhrxbcnp38tP/gr2M/B0UgBvTukw1CvnkNcF9jPsTPU1GwfpJTJehALSzcpXclWsLaNCtcDEQITeldxg5IZDwUgD6OTtKTJ/DeAHyjd4Ro1A2FSqxjng361AQqA1IftDT+ScIVvYUgDo+eQdBsApAvtHr/fW7oSI/CsA1rlaMhEDtP/KD3v0lw9lTuARPpnaBEX/PuPTuK5ERzPsrrLCvaQkH+XvG5Ha7AYdO9ARavYGAfQCFMjoi8N8s6g39MwJ6f4UC8JPbHQ4xkFcrMAV+f5NblzcF4Dsj9xLIiOH9Wjmb//AJ/byn32NEeXvLUxUUgO9YnDIkh0fYioKvRVMJ+3jM24b+BQrAF7P2DDjew1vleT7OK7dc5CIJawXx1qF/gQLwxepNQwQ/w1H9f8Tx1+nFeyvzW/KXB+SxFhr/AwrAF7MPGSVfZMyLhmj8BygAX6yOAO6OwD4a6O300+9uY/wKBeAL7ibsgwybaIDGfwIF4DuMAvyQMC4aoPG/gALwE541uB7BuObArTb4qIUCcA6bA/ORx2Nk+N9Tjvq9jI2NX6EAvIfRgD0ZNpOkeoxf0+RtjoYJFIDPlHH0vDohG5FhOzOy1fg1Xbcw/AIFoA52EraRH88zjKuljAQ3CPfPoAC0UaKC8iDfEaxZ/1DbZMsL0ugKCsAY7n7cWMn3yvC5xvjz4rS6gQJgw6iVc94Q+Dv08+q2bYKbhvnvoADM43lRTl6doDcc0+bZU37q7MvwI1QuoQD4weqosXffE8Q1jrOQX7DH3gvToADM5V+PZ1bOPorx58f/Av6mTVAAbHg3SpDhO6wmN4ICMBaeHERCQQEYw9XJJ/qZHb1/2Q1YNs3ftlAA+qg9OUg/u4uBvJoiLeBwWxgoAO20zDfXz0duAtRMeNLP7CJ220IBaOMOy0xb9v2PmtfbQgFoY8QGogI/xjHC2N+h12Q04BAKQD0WewQIviIKq/3wntOcDe7zKY96Tw+CRx5QAOpYsVOQXLhnQpw1BxmMBtxAAaiD+wGMQcBowAUUgDp4eMhYMhgNLIUCUAcFYDwCRgPLoADUQQGwI4EiMJ0dBeDTstpCS+jJXYJtEDAKWEJkARi5647gWgXkeQFjEdDwlxJJAGZszy34PFWXIwFjyPDVAVjWdejjNoLkXQBW7cmv93tXOdkUaEPg28BKH4+mM/Kajct4FYDaVXYWCN5XVjYHrpPhy9u/4lnY9W+vYjUEjwLgLcQWvPcGjAbOyYi3VddZ3dM8bBsNeBKAlR5V3tw7P54T3kcDCb6EazaC+BtyvnI+mqctRcCLAMw0foHtzjVld5w0KT+rENguYFrBu+hT87qdCHgQgBkhtGDNdlU7HSGWH8+CuB7+E5+an2m3vK8WgBnt/QQ/hXacpJTgUxTy4e9dPPtVPs30FGwWBawUAOuwX68dqbB+Pf2fME4gBOe/tb7mRRxXc9UZrXaaQ1mZGcvQXxDL+Ml6rq7zSNhINFcJgLX330qliTm3PVl4laFYtv31utsUEDGnti7qZ7epXxQAcmdamqH6eTYBOrHu/WcTgHyitQ9qq7q1qwAI2AlIzuk902GrerWrACiCzQqLdNM78qTf3Sb8V1aGMzO21xJQBO7OqJWlgg3r0q7zAJ7JYMfg3Ri9pFyvs5X3V3aeCXhGBoVgdyyalwkbGr+yukdz1Vp6Ac+y3wnLFZgZG9eT1QKgrN5QQ0AxiMas/Rf0Hlt6/oIHAVBWi8CR/HgWbF74gVixrFrvs335exEAxZMIHBHst/GFZ46rIvOC+wvsNy4tHZTLRxU8CYDiVQSeEXzvwKQw1ONxb4QM+7Isnd8z7vURbwKgRN9tV0BxKFjucTASwZztyo91W++3vG54FIBClGigBcG5yC2vEBf49eL1vDphDQjmnVPw7Nj0vsvL27MAKB7OB/BKHny9hPv8zoK5B5ScObM08f4v8S4AhbvstEtsEcw1/HcOzIXtuUhEBYwISC2CNfM83s1IzAvSc0o0ASjwIA7yiYx1czk+9V+lRen6QVQBOEIxIIWMtRO4roxg6fvLx/8LOwjAEYrBvRD4mMZd0zTVz7jw/spuAvAMOw/3QuDv/MHQOwrvLgBHjjPP8urEkEvkx7Mro3lQu+xY4Cj0L9xJAM4ok1oSGCWsRPDVdtZnL979jNb9BpLHfN1dAM7wOEd9FwRxp0n3bDSS4ND4FQpAHcdpsAkUhzPy0/+RjPyZEZ3K+n2Xxq9QAMbyap58QmyxyCevCRxX7E5GTTjT77v+jSgAPvjVf4kuInvpkYzaT1Awd8pxMxQAcndGDxULHPb2v4ICQO6I1ZoSvZ57r3+EAkDuguUsUUGQkP+ZuwpAaesJ/M0sI2OYtZGoXjts3bmrABTOQsH8eBYELtibUjpT84R76T3Cd57eXQCOvOoBFsSZpXY3Zhp8QRA03D+DAvCTq23F4/vhPUEAVk/bFmxk+AUKwHtaeovz4W8KQz3epmJnbFyOFIDrjAg3n7+7bcW6gOdp1QIf+wyYQwFow2pISXC+o4y+FiX0PHrwQoIvAz9DcBOjP0IB6GfFuXUFwZpDVFbk1YqMWAI7FArAeLgtmX8ybmz0RygA9qyMEMg/5MfzrcL7K1AA1rB6SGt38uOZBv8BCoAPuF9hOwIe394MBcA3nofKZiPgjMzhUADisqM4CH6OatCrG0IB2BsvW5TlF6/TuBfzf0CNCT1pSuI0AAAAAElFTkSuQmCC";
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column
	private String title;

	@Column
	private String subtitle;

	@Column
	private String body;

	@Column(name="date_added")
	private Date dateAdded;
	
	@Column
	private Boolean featured;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean archived;
	
	@Column
	private String icon;
	
	public Boolean getArchived() {
		return archived;
	}

	public void setArchived(Boolean archived) {
		this.archived = archived;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Article)) {
			return false;
		}
		Article other = (Article) obj;
		if (id != null) {
			if (!id.equals(other.id)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (title != null && !title.trim().isEmpty())
			result += "title: " + title;
		if (subtitle != null && !subtitle.trim().isEmpty())
			result += ", subtitle: " + subtitle;
		return result;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public Boolean getFeatured() {
		return featured;
	}

	public void setFeatured(Boolean featured) {
		this.featured = featured;
	}
	
	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getIcon() {
		if (icon == null) {
			return defaultIcon;
		} else {
			return icon;
		}	
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
}