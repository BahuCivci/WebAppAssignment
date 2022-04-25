/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webapps2022.utils;

import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FacesContextUtils {
	public static <T> T getManagedBean(String managedBeanName) {
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		return ((T)
				FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext, null, managedBeanName));
	}
	public static <T> T getParameter(String key) {
		return (T)
				FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().
				get(key);
	}
	public static void showMessage(String msg) {

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
	}
}