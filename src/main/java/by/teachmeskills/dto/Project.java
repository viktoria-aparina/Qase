package by.teachmeskills.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Project {

    String name;
    String code;
    String description;
    ProjectAccess accessLevel;
}
