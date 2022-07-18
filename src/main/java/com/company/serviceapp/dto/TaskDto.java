package com.company.serviceapp.dto;

import com.company.serviceapp.model.Answer;
import com.company.serviceapp.model.Status;
import com.company.serviceapp.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskDto {

    String title;

    String body;

    String status;
}
