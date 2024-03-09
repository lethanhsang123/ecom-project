package com.personal.project.common.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AuditableDomain implements Serializable {

    @JsonProperty("create_date")
    private LocalDateTime createDate;

    @JsonProperty("update_date")
    private LocalDateTime updateDate;

    @JsonProperty("create_by")
    private String createBy;

    @JsonProperty("update_by")
    private String updateBy;

}
