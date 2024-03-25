package com.hrms;

import java.sql.Blob;

public class Project {
    private String projectName;
    private String projectDescription;
    private String employee;
    private java.sql.Blob document;
    private String documentBase64; // Added field for base64-encoded document

    // Constructor
    public Project() {
    }

    // Getter and Setter methods
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public java.sql.Blob getDocument() {
        return document;
    }

    public void setDocument(java.sql.Blob document) {
        this.document = document;
    }

    public String getDocumentBase64() {
        return documentBase64;
    }

    public void setDocumentBase64(String documentBase64) {
        this.documentBase64 = documentBase64;
    }
}
