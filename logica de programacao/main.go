package main

import (
	"fmt"
)

const (
	notaAprovacao  = 7.0
	notaReprovacao = 4.0
)

type Turma struct {
	Disciplinas []Disciplina
	Nome        string
}

type Disciplina struct {
	Nome              string
	Alunos            int
	Notas             []float64
}

func main() {
	fmt.Print("Qual o nome da turma? ")
	var nomeTurma string
	fmt.Scanln(&nomeTurma)

	turma1 := Turma{
		Nome: nomeTurma,
		Disciplinas: []Disciplina{
			{
				Nome:   "Matematica",
				Alunos: 3,
				Notas:  []float64{4.0, 3.5, 2.0},
			},
			{
				Nome:   "História",
				Alunos: 3,
				Notas:  []float64{6.0, 8.0, 7.0},
			},
		},
	}

	turma2 := Turma{
		Nome: nomeTurma,
		Disciplinas: []Disciplina{
			{
				Nome:  "Matematica",
				Alunos: 3,
				Notas: []float64{65.0, 89.5, 75.0},
			},
			{
				Nome:  "História",
				Alunos: 3,
				Notas: []float64{65.0, 89.5, 75.0},
			},
		},
	}

	relatorioTurma(turma1)
	relatorioTurma(turma2)
}

func relatorioTurma(turma Turma) {
	fmt.Printf("Relatório da Turma: %s\n", turma.Nome)
	for _, disciplina := range turma.Disciplinas {
		media := calcularMedia(disciplina.Notas)
		quantAprovados := calcularAprovados(disciplina.Notas, notaAprovacao)
		quantRecuperacao := calcularRecuperacao(disciplina.Notas, notaReprovacao, notaAprovacao)
		quantReprovados := calcularReprovados(disciplina.Notas, notaReprovacao)
		
		fmt.Println("Quantidade de alunos: ", disciplina.Alunos)
		fmt.Printf("Disciplina: %s, Média: %.2f\n", disciplina.Nome, media)
		fmt.Println("Quantidade de aprovados: ", quantAprovados)
		fmt.Println("Quantidade de alunos em recuperação: ", quantRecuperacao)
		fmt.Println("Quantidade de reprovados: ", quantReprovados)
	}
	fmt.Println()
}

func calcularReprovados(notas []float64, notaReprovacao float64) int {
	quantidadeReprovados := 0
	for _, nota := range notas {
		if nota < notaReprovacao {
			quantidadeReprovados++
		}
	}
	return quantidadeReprovados
}

func calcularRecuperacao(notas []float64, notaReprovacao float64, notaAprovacao float64) int {
	quantidadeRecuperacao := 0
	for _, nota := range notas {
		if nota >= notaReprovacao && nota < notaAprovacao {
			quantidadeRecuperacao++
		}
	}
	return quantidadeRecuperacao
}

func calcularAprovados(notas []float64, notaAprovacao float64) int {
	quantidadeAprovados := 0
	for _, nota := range notas {
		if nota >= notaAprovacao {
			quantidadeAprovados++
		}
	}
	return quantidadeAprovados
}

func calcularMedia(notas []float64) float64 {
	total := 0.0
	for _, nota := range notas {
		total += nota
	}
	return total / float64(len(notas))
}

func statusAluno(calcularMedia float64) string {
	switch {
	case calcularMedia < 4:
		return "situação: reprovado"
	case calcularMedia < 7:
		return "situação: recuperação"
	default:
		return "situação: aprovado"
	}
}
