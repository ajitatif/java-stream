package org.turkisi.training.stream;

import java.time.LocalDateTime;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
public class ProjectRepresentationModel {

    private String projectName;
    private String countryName;
    private String agency;
    private LocalDateTime approvalDate;
    private Long projectCost;

    public static ProjectRepresentationModel fromProject(Project project) {
        return new ProjectRepresentationModel(
                project.getProject_name(), project.getCountryname(), project.getImpagency(),
                project.getBoardapprovaldate(), project.getLendprojectcost());
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public LocalDateTime getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(LocalDateTime approvalDate) {
        this.approvalDate = approvalDate;
    }

    public Long getProjectCost() {
        return projectCost;
    }

    public void setProjectCost(Long projectCost) {
        this.projectCost = projectCost;
    }

    @Override
    public String toString() {
        return "name: '" + projectName + '\'' +
                ", country: '" + countryName + '\'' +
                ", agency: " + agency + '\'' +
                ", approved: " + approvalDate +
                ", cost: " + projectCost;
    }

    private ProjectRepresentationModel(String projectName, String countryName, String agency, LocalDateTime approvalDate, Long projectCost) {
        this.projectName = projectName;

        this.countryName = countryName;
        this.agency = agency;
        this.approvalDate = approvalDate;
        this.projectCost = projectCost;
    }
}
