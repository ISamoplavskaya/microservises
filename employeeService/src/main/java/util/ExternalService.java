package util;

import lombok.Getter;

@Getter
public enum ExternalService {
    AUTH("http://localhost:2220/validate"),
    SALARY("http://localhost:2224/salary?employee=");

    private final String url;
    ExternalService(String url) {
        this.url = url;
    }
}
