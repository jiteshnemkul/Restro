package com.jit.resto.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ordertable extends  AbstractModel<Long> {
    @Column(nullable = false, length = 40)
    private String tablename;

    @Column(nullable = false, length = 40)
    private int total;

    @Column(nullable = false, length = 40)
    private String  paymentstatus;


}
