package com.example.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity(name = "player")
@Getter
@Setter
@NoArgsConstructor
public class Player {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id", columnDefinition = "INT")
    private Integer id;

    private String playerCode;

    @Pattern(regexp = "^[A-Za-z ]{5,100}$", message = "Tên phải đúng định dạng")
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Ngày sinh không được để trống")
    @PastOrPresent(message = "Ngày sinh không thể lớn hơn ngày hiện tại")
    private LocalDate birthDate;

    private String experience;

    @ManyToOne
    @JoinColumn(name = "id_Position", referencedColumnName = "id")
    @NotNull(message = "Không được thiếu vị trí")
    private Position position;

    private String avatarUrl;

    @ManyToOne
    @JoinColumn(name = "id_Team", referencedColumnName = "id")
    @NotNull(message = "phải chỉ đinh đội bóng")
    private Team team;
}