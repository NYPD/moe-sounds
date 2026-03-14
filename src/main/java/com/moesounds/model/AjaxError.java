package com.moesounds.model;


public class AjaxError {

    private String modalTitle;
    private String modalMessage;

    public AjaxError(String modalTitle, String modalMessage) {
        this.modalTitle = modalTitle;
        this.modalMessage = modalMessage;
    }

    public String getModalTitle() {
        return modalTitle;
    }

    public String getModalMessage() {
        return modalMessage;
    }

    @Override
    public String toString() {
        return "AjaxError [modalTitle=" + modalTitle + ", modalMessage=" + modalMessage + "]";
    }

}
