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
public class TableEntity extends AbstractModel<Long> {

    @Column(nullable = false, length = 40)
    private String Tablename;

    @Column(nullable = false, length = 40)
    private String Tableplace;

    @Column(nullable = false, length = 40)
    private String Seat;

    @Column(nullable = true, length = 40)
    private boolean status;
}
