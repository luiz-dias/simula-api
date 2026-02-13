package br.com.simula.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "questao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Questao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(columnDefinition = "TEXT", nullable = false)
    private String enunciado;

    @Column(name = "alternativa_a", columnDefinition = "TEXT")
    private String alternativaA;

    @Column(name = "alternativa_b", columnDefinition = "TEXT")
    private String alternativaB;

    @Column(name = "alternativa_c", columnDefinition = "TEXT")
    private String alternativaC;

    @Column(name = "alternativa_d", columnDefinition = "TEXT")
    private String alternativaD;

    @Column(name = "alternativa_e", columnDefinition = "TEXT")
    private String alternativaE;

    @Column(name = "resposta_correta", length = 1)
    private String respostaCorreta; // A, B, C, D ou E

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_id")
    private Tipo tipo;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "materia_id", nullable = false)
    private Materia materia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assunto_id")
    private Assunto assunto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico_id")
    private Topico topico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orgao_id")
    private Orgao orgao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "banca_id")
    private Banca banca;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cargo_id")
    private Cargo cargo;

    private Integer ano;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = Instant.now();
        updatedAt = Instant.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = Instant.now();
    }
}
