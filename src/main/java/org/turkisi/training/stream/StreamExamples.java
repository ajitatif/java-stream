package org.turkisi.training.stream;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
public class StreamExamples {


    public static void main(String[] args) {

        new StreamExamples().go();
    }

    private void go() {
        List<Project> projects = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                        .configure(DeserializationFeature.FAIL_ON_UNRESOLVED_OBJECT_IDS, false)
                .findAndRegisterModules();
            URL resource = getClass().getClassLoader().getResource("world_bank.json");
            projects = objectMapper.readValues(new JsonFactory().createParser(resource), Project.class).readAll();
            System.out.println("Obtained a total of " + projects.size() + " records");
        } catch (IOException e) {
            System.out.println("no luck :/");
            e.printStackTrace();
        }

        if (projects != null) {
            calculateAverageProjectCosts(projects);
            groupProjectsByApprovalYear(projects);
            mapProjectToModelAndSortByCountry(projects);
            findProjectsByCountry(projects, "TR");
            findTotalsForCountries(projects);
        }
    }

    private void findTotalsForCountries(List<Project> projects) {

        Map<String, Long> map = projects.parallelStream().collect(Collectors.groupingBy(Project::getCountryshortname, Collectors.summingLong(Project::getLendprojectcost)));
        map.entrySet().forEach(entry -> System.out.println(entry.getKey() + " -> " + entry.getValue()));
    }

    private void findProjectsByCountry(List<Project> projects, String countryCode) {

        Stream<Project> projectStream =
                projects.stream().filter(project -> project.getCountrycode().equalsIgnoreCase(countryCode));

        projectStream.collect(Collectors.toList()).forEach(project -> {
            System.out.println(project.getBorrower());
        });
    }

    private void mapProjectToModelAndSortByCountry(List<Project> projects) {

        List<ProjectRepresentationModel> modelList = projects.stream().map(
                ProjectRepresentationModel::fromProject).collect(Collectors.toList());

        modelList.sort(Comparator.comparing(ProjectRepresentationModel::getApprovalDate).reversed());
        for (ProjectRepresentationModel projectRepresentationModel : modelList) {
            System.out.println(projectRepresentationModel);
        }
    }

    private void groupProjectsByApprovalYear(List<Project> projects) {

        Map<String, Long> map = projects.stream().collect(
                Collectors.groupingBy(project -> project.getBoardapprovaldate().getMonth().name(), Collectors.counting()));

        for (Map.Entry<String, Long> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }

    private void calculateAverageProjectCosts(List<Project> projects) {

        LocalDateTime started = LocalDateTime.now();
        Double stdStreamResult = projects.stream().collect(Collectors.averagingDouble(Project::getLendprojectcost));
        System.out.println("Calculated with streams in " + Duration.between(started, LocalDateTime.now()));
        System.out.println("Average project costs: " + stdStreamResult);

        started = LocalDateTime.now();
        Double parallelStreamResult = projects.parallelStream().collect(Collectors.averagingDouble(Project::getLendprojectcost));
        System.out.println("Calculated with parallel streams in " + Duration.between(started, LocalDateTime.now()));
    }
}
