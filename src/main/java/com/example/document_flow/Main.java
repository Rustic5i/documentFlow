package com.example.document_flow;

import com.example.document_flow.generator.DocumentGenerator;
import com.example.document_flow.myException.InvalidParametersException;
import com.example.document_flow.repository.RepositoryDocument;
import com.example.document_flow.validation.ParametersValidation;

import java.util.Scanner;

public class Main {

    private static final RepositoryDocument repository = RepositoryDocument.getInstance();

    public static void main(String[] args) {
        try {
            int count = ParametersValidation.isNumber(args[0]);
            repository.saveDocument(DocumentGenerator.run(count));
            System.out.println(repository.groupByAuthorToString());
        } catch (InvalidParametersException e) {
            main();
        }
    }

    public static void main() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Введите количество требуемых документов");
                int count = ParametersValidation.isNumber(scanner.nextLine());
                repository.saveDocument(DocumentGenerator.run(count));
                System.out.println(repository.groupByAuthorToString());
                break;
            } catch (InvalidParametersException e) {
                e.printStackTrace();
            }
        }
    }
}
