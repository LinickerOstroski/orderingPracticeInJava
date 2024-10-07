package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import entities.Candidate;
import entities.InsertionSort;

public class Program {

	public static void main(String[] args) {

		String path = "C:\\Users\\Iksortso K2\\Desktop\\dadosConcurso.csv";

		List<Candidate> listCandidates = new ArrayList<Candidate>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			String line = br.readLine();
			line = br.readLine();
			while (line != null) {

				String[] vect = line.split(",");
				Integer identifier = Integer.parseInt(vect[0]);
				String name = vect[1];
				String birthData = vect[2];
				Integer score = Integer.parseInt(vect[3]);

				LocalDate birthDataConvert = LocalDate.parse(birthData, formatter);

				Candidate candidate = new Candidate(identifier, name, birthDataConvert, score);
				listCandidates.add(candidate);

				line = br.readLine();
			}

			System.out.println("\t\t --- Lista de candidatos --- \n");
			for (Candidate c : listCandidates) {
				System.out.println(c);
			}

		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}

		Comparator<Candidate> scoreCompare = (c1, c2) -> c2.getScore().compareTo(c1.getScore());

		Candidate[] array = listCandidates.toArray(new Candidate[0]);

		InsertionSort<Candidate> isort = new InsertionSort<>();
		isort.sort(array, scoreCompare);

		System.out.println("\n\n\t --- Pessoas ordenadas pelo score e data de nascimento --- \n\n");

		for (Candidate c : array) {
			System.out.println(c);
		}

	}
}
