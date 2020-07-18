package com.fyz.spring.cloud.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment implements Serializable {
    /**
    * id
    */
    private Long id;

    private String serial;

    private static final long serialVersionUID = 1L;
}