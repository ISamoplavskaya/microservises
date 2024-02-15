package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Employee;
import entity.Salary;
import exception.EmptyOrInvalidResponseException;
import exception.IOStreamException;
import exception.JsonSerializationException;
import exception.OperationInterruptedException;
import util.ExternalService;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class SalaryService {
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final HttpClient httpClient = HttpClient.newBuilder().build();

    public double getFullSalary(Employee employee) {
        try {
            String jsonEmployee = mapper.writeValueAsString(employee);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(ExternalService.SALARY.getUrl() + employee.getId()))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonEmployee))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            String jsonString = response.body();
            if (jsonString != null && !jsonString.isEmpty()) {
                Salary salary = mapper.readValue(jsonString, Salary.class);
                return salary.getFullSalary();
            } else {
                throw new EmptyOrInvalidResponseException("Received empty or invalid response from salary service");
            }
        } catch (JsonProcessingException e) {
            throw new JsonSerializationException("Error processing JSON", e);
        } catch (IOException e) {
            throw new IOStreamException("Error performing IO operation",e);
        } catch (InterruptedException e) {
            throw new OperationInterruptedException("Operation interrupted",e);
        }

    }
}
